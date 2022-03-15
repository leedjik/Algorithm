#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

long long dp[100];

long long fibbo(int n) {
	if (n <= 1) return n;
	else if (dp[n] != 0) return dp[n];

	dp[n] = fibbo(n - 1) + fibbo(n - 2);

	return dp[n];
}

int main(void) {
	int n;
	scanf("%d", &n);

	printf("%lld\n", fibbo(n));
}