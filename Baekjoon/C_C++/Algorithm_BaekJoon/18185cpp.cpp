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
		
		bool buyFlag = false; //cnt 개수만큼 사야 하는가? 

		for (int i = idx; i < idx + num; i++) {
			if (A[i] <= cnt) {
				buyFlag = true;
				break;
			}
		}

		// cnt개 까지는 사야 한다.
		if (buyFlag) return cnt;
	}
}
void Sol() {
	for (int i = 0; i < N; i++) {
		bool canBuy = false;
		int num = 0;
		
		// (3)방법 -> (2)방법 -> (1)방법 순으로 구매
		for (int num = 3; num > 0; num--) {

			if (IsInnerSize(i, num)) {
				// 선택한 방법 개수(num)의 공장에서 구매할 횟수 카운트
				int buyCount = HowManyBuy(i, num);

				// num번째 방법으로는 구매할 수 없다.
				if (buyCount == 0) continue;

				for (int j = i; j < i + num; j++) {
					A[j] -= buyCount;
				}

				canBuy = true;
				ans += buyCount * cost[num];
			}
			// num번째 방법에서 구매했다면, 다음 공장 방문
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