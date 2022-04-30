#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;
int N, K, time;
bool visited[410][410];
int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

typedef struct {
	int fromIdx, fromNum;
	int toIdx, toNum;
	int moveFishNum;
}moveStruct;

vector <int> fishbowl[410];
vector <int> tmpbowl[410];
queue <moveStruct> q;

bool isFinished() {
	int min = 2100000000;
	int max = -1;

	for (int i = 0; i < N; i++) {
		if (min > fishbowl[i][0]) min = fishbowl[i][0];
		if (max < fishbowl[i][0]) max = fishbowl[i][0];
	}

	if (max - min <= K) return true;
	return false;
}

bool moveFromTo() {
	//2�� �̻��� ���� ���� fromIdx ã��
	int fromIdx = 0;
	int toIdx = 0;
	for (int i = N - 1; i >= 0; i--) {
		if (fishbowl[i].size() >= 2) {
			fromIdx = i;
			toIdx = i + 1;
			break;
		}
	}

	// �ٴڿ� ���� ���� ���� ������(N-1)�� ����� ���

	if (toIdx + fishbowl[fromIdx].size() > N) return false;

	for (; fromIdx >= 0 && fishbowl[fromIdx].size() > 0; fromIdx--) {
		for (int num = 0; num < fishbowl[fromIdx].size(); num++) {
			fishbowl[toIdx + num].push_back(fishbowl[fromIdx][num]);
		}
		fishbowl[fromIdx].clear();
	}
	return true;
}
int calDiffFish(int num1, int num2) {
	return (num1 - num2) / 5;
}
void moveFish() {

	for (int fromIdx = N - 1, toIdx = N - 2; toIdx >= 0; fromIdx--, toIdx--) {
		int fromNum = 0, toNum = 0;
		int fromMaxNum = fishbowl[fromIdx].size();
		int toMaxNum = fishbowl[toIdx].size();

		while (fromNum < fromMaxNum && toNum < toMaxNum && fishbowl[fromIdx].size() && fishbowl[toIdx].size()) {
			// �¿�� �̵� üũ
			int diff = calDiffFish(fishbowl[fromIdx][fromNum], fishbowl[toIdx][toNum]);

			if (diff > 0) {
				// �������� �� ����
				q.push({ fromIdx, fromNum, toIdx, toNum, diff });
			}
			else if (diff < 0) {
				// ������ �� ����
				q.push({ toIdx, toNum, fromIdx, fromNum, diff * -1 });
			}
			fromNum++;
			toNum++;
		}
	}

	// ���Ʒ� �̵� üũ
	for (int fromIdx = N - 1; fromIdx >= 0 && fishbowl[fromIdx].size(); fromIdx--) {
		int	fromNum = 0;
		int fromMaxNum = fishbowl[fromIdx].size();

		while (fromNum + 1 < fromMaxNum) {

			int diff = calDiffFish(fishbowl[fromIdx][fromNum], fishbowl[fromIdx][fromNum + 1]);

			if (diff > 0) {
				// �Ʒ��� �� ����
				q.push({ fromIdx, fromNum, fromIdx, fromNum + 1, diff });
			}
			else if (diff < 0) {
				// ���� �� ����
				q.push({ fromIdx, fromNum + 1, fromIdx, fromNum, diff * -1 });
			}
			fromNum++;
		}
	}

	while (!q.empty()) {
		int fromIdx = q.front().fromIdx, fromNum = q.front().fromNum;
		int toIdx = q.front().toIdx, toNum = q.front().toNum;
		int moveFishNum = q.front().moveFishNum;
		q.pop();

		fishbowl[fromIdx][fromNum] -= moveFishNum;
		fishbowl[toIdx][toNum] += moveFishNum;
	}
}
void moveDown() {
	int startIdx = 0;
	int positionIdx = 0;

	for (int i = 0; i < N; i++) {
		if (fishbowl[i].size()) {
			startIdx = i;
			break;
		}
	}

	for (; startIdx < N; startIdx++) {
		for (int num = 0; num < fishbowl[startIdx].size(); num++) {
			tmpbowl[positionIdx++].push_back(fishbowl[startIdx][num]);
		}
		fishbowl[startIdx].clear();
	}

	for (int i = 0; i < N; i++) {
		fishbowl[i].push_back(tmpbowl[i][0]);
		tmpbowl[i].pop_back();
	}
}
void move() {
	// 1. ���� ó���� �� ������ �׳� �̵�
	fishbowl[1].push_back(fishbowl[0][0]);
	fishbowl[0].clear();

	// 2. flag�� false�ɶ����� �ݺ����� -> ���� ����� ���
	bool flag = true;

	while (flag) {
		flag = moveFromTo();
	}

	// 3. ���� ����� ��Ʈ����
	moveFish();

	// 4. �Ʒ��� ������
	moveDown();
}
void addFishFirst() {
	int num = 2100000000;

	for (int i = 0; i < N; i++) {
		if (fishbowl[i][0] < num) {
			num = fishbowl[i][0];
		}
	}
	for (int i = 0; i < N; i++) {
		if(num == fishbowl[i][0]) fishbowl[i][0]++;
	}
}
void move2() {
	int centerIdx = (N / 2) - 1;

	for (int t = 0; t < 2; t++) {
		for (int i = centerIdx, j = centerIdx + 1; i >= 0 && fishbowl[i].size(); i--, j++) {
			for (int num = fishbowl[i].size() - 1; num >= 0; num--) {
				fishbowl[j].push_back(fishbowl[i][num]);
			}
			fishbowl[i].clear();
		}
		centerIdx += (N / 4);
	}

	moveFish();

	moveDown();
}
int main(void) {
	scanf("%d %d", &N, &K);

	for (int i = 0, input; i < N; i++) {
		scanf("%d", &input);
		fishbowl[i].push_back(input);
	}


	while (!isFinished()) {

		// ���� ���ڰ� ���� ���׿� 1���� �ֱ�
		addFishFirst();
		// ù ������� �����̰� ������
		move();

		// �ι�° ������� �����̰� ������
		move2();
		time++;
	}
	
	printf("%d\n", time);
}