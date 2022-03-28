#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int N, remain, ans;
int money[6] = { 500, 100, 50, 10, 5, 1 };

int main(void) {
	scanf("%d", &N);

	remain = 1000 - N;

	for (int i = 0; i < 6; i++) {
		// i��° �迭�� �ִ� �ݾױ����� �Ž��� �� �� �ִ���
		if (remain >= money[i]) {
			// �� ���� money[i]�� �Ž��� �� �� �ִ���
			int num = remain / money[i];
			// (money[i] * num)���� ���� �ݾ����� ����
			remain %= (money[i] * num);

			ans += num;
		}
	}

	printf("%d\n", ans);
}