#include <cstdio>

int input[501];
int dp[501];

void init() {
	for (int i = 0; i < 501; i++) {
		input[i] = dp[i] = 0;
	}
}

int main(void)
{
	int C;
	int N;
	scanf("%d", &C);

	while (C > 0) {
		C--;
		scanf("%d", &N);
		init();

		for (int i = 0; i < N; i++) {
			scanf("%d", &input[i]);
		}

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (input[i] > input[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (ans < dp[i])
				ans = dp[i];
		}

		printf("%d\n", ans);

	}
}