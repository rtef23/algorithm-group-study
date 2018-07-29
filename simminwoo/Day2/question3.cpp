#include <cstdio>
#include <cstring>
int C;
int n, m;

int friendPair[11][11];
int visit[11];

int find() {
	int index = -1;
	int sum = 0;

	for (int i = 0; i < n; i++) {
		if (visit[i] == 0) {
			index = i;
			break;
		}
	}

	if (index == -1) return 1;

	for (int i = index + 1; i < n; i++) {
		if (visit[i] == 0 && friendPair[index][i] == 1) {
			visit[i] = visit[index] = 1;
			sum += find();
			visit[i] = visit[index] = 0;
		}
	}

	return sum;
}

int main(void) {
	scanf("%d", &C);

	int x, y;
	while (C > 0) {
		scanf("%d %d", &n, &m);

		for (int i = 0; i < m; i++) {
			scanf("%d %d", &x, &y);
			friendPair[x][y] = friendPair[y][x] = 1;
		}

		printf("%d\n", find());

		memset(friendPair, 0, sizeof(friendPair));
		memset(visit, 0, sizeof(visit));
		C--;
	}
}