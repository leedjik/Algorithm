#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int N, A[10001];
int cost[4] = { 0, 3, 5, 7 };
int ans;

bool IsInnerSize(int idx, int num) {
	if (idx + num - 1 >= N) return false;
	return true;
}
int HowManyBuy(int idx, int num) {

	for (int cnt = 0; cnt <= 10000; cnt++) {
		
		bool buyFlag = false; //cnt ������ŭ ��� �ϴ°�? 

		for (int i = idx; i < idx + num; i++) {
			if (A[i] <= cnt) {
				buyFlag = true;
				break;
			}
		}

		// cnt�� ������ ��� �Ѵ�.
		if (buyFlag) return cnt;
	}
}
void Sol() {
	for (int i = 0; i < N; i++) {
		bool canBuy = false;
		int num = 0;
		
		// (3)��� -> (2)��� -> (1)��� ������ ����
		for (int num = 3; num > 0; num--) {

			if (IsInnerSize(i, num)) {
				// ������ ��� ����(num)�� ���忡�� ������ Ƚ�� ī��Ʈ
				int buyCount = HowManyBuy(i, num);

				// num��° ������δ� ������ �� ����.
				if (buyCount == 0) continue;

				for (int j = i; j < i + num; j++) {
					A[j] -= buyCount;
				}

				canBuy = true;
				ans += buyCount * cost[num];
			}
			// num��° ������� �����ߴٸ�, ���� ���� �湮
			if (canBuy) break;
		}
	}
}

int main(void) {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) scanf("%d", &A[i]);

	Sol();
	printf("%d\n", ans);
}