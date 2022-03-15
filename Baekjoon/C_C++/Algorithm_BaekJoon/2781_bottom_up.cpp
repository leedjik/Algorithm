#include <cstdio>

long long dp[100];

long long fibbo(int n) {
	dp[0] = 0;
	dp[1] = 1;

	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}

	return dp[n];
}


int main(void) {
	int n;
	scanf("%d", &n);

	printf("%lld\n", fibbo(n));
}