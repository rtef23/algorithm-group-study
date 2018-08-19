#include <cstdio>

int C;
int n;

int map[102][102];
int sum[102][102];

void clear() {
	for (int i = 0; i < 102; i++) {
		for (int j = 0; j < 102; j++) {
			map[i][j] = sum[i][j] = 0;
		}
	}
}

int main(void)
{
	scanf("%d", &C);

	while (C > 0) {
		C--;
		scanf("%d", &n);

		clear();

		int temp = 1;
		int maxnum = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < temp; j++) {
				scanf("%d", &map[i][j]);
			}
			temp++;
		}

		sum[0][0] = map[0][0];

		temp = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < temp; j++) {
				if (sum[i + 1][j] < sum[i][j] + map[i][j]) {
					sum[i + 1][j] = sum[i][j] + map[i][j];
				}

				if (j < temp && sum[i + 1][j + 1] < sum[i][j] + map[i][j + 1]) {
					sum[i + 1][j + 1] = sum[i][j] + map[i][j + 1];
				}
			}

			temp++;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <n; j++) {
				if (sum[i][j] > maxnum)
					maxnum = sum[i][j];
			}
		}

		printf("%d\n", maxnum);
	}
}