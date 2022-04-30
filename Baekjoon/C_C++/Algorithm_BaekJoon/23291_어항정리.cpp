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
	//2개 이상의 어항 시작 fromIdx 찾기
	int fromIdx = 0;
	int toIdx = 0;
	for (int i = N - 1; i >= 0; i--) {
		if (fishbowl[i].size() >= 2) {
			fromIdx = i;
			toIdx = i + 1;
			break;
		}
	}

	// 바닥에 놓인 어항 제일 오른쪽(N-1)을 벗어나는 경우

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
			// 좌우로 이동 체크
			int diff = calDiffFish(fishbowl[fromIdx][fromNum], fishbowl[toIdx][toNum]);

			if (diff > 0) {
				// 오른쪽이 더 많음
				q.push({ fromIdx, fromNum, toIdx, toNum, diff });
			}
			else if (diff < 0) {
				// 왼쪽이 더 많음
				q.push({ toIdx, toNum, fromIdx, fromNum, diff * -1 });
			}
			fromNum++;
			toNum++;
		}
	}

	// 위아래 이동 체크
	for (int fromIdx = N - 1; fromIdx >= 0 && fishbowl[fromIdx].size(); fromIdx--) {
		int	fromNum = 0;
		int fromMaxNum = fishbowl[fromIdx].size();

		while (fromNum + 1 < fromMaxNum) {

			int diff = calDiffFish(fishbowl[fromIdx][fromNum], fishbowl[fromIdx][fromNum + 1]);

			if (diff > 0) {
				// 아래가 더 많음
				q.push({ fromIdx, fromNum, fromIdx, fromNum + 1, diff });
			}
			else if (diff < 0) {
				// 위가 더 많음
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
	// 1. 제일 처음에 맨 왼쪽은 그냥 이동
	fishbowl[1].push_back(fishbowl[0][0]);
	fishbowl[0].clear();

	// 2. flag가 false될때까지 반복수행 -> 길이 벗어나는 경우
	bool flag = true;

	while (flag) {
		flag = moveFromTo();
	}

	// 3. 어항 물고기 퍼트리기
	moveFish();

	// 4. 아래로 내리기
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

		// 가장 숫자가 적은 어항에 1마리 넣기
		addFishFirst();
		// 첫 방식으로 움직이고 내리기
		move();

		// 두번째 방식으로 움직이고 내리기
		move2();
		time++;
	}
	
	printf("%d\n", time);
}