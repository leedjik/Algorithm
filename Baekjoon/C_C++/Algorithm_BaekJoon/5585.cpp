#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int N, remain, ans;
int money[6] = { 500, 100, 50, 10, 5, 1 };

int main(void) {
	scanf("%d", &N);

	remain = 1000 - N;

	for (int i = 0; i < 6; i++) {
		// i번째 배열에 있는 금액권으로 거슬러 줄 수 있는지
		if (remain >= money[i]) {
			// 몇 개의 money[i]로 거슬러 줄 수 있는지
			int num = remain / money[i];
			// (money[i] * num)빼고 남은 금액으로 변경
			remain %= (money[i] * num);

			ans += num;
		}
	}

	printf("%d\n", ans);
}