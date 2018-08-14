#include <cstdio>
#include <queue>
#include <cstring>
#define MAXVALUE 99999
using namespace std;

int map[101][101];
int visitCount[101][101];
int C;
int n;

void init() {
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			map[i][j] = 0;
			visitCount[i][j] = MAXVALUE;
		}
	}
}

int main(void) {
	scanf("%d", &C);


	while (C > 0) {
		init();
		C--;
		scanf("%d", &n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				scanf("%d", &map[i][j]);
			}
		}

		queue<pair<int, int> > find;
		find.push(make_pair(0, 0));
		visitCount[0][0] = 1;

		while (!find.empty()) {
			int x = find.front().first;
			int y = find.front().second;
			find.pop();

			int tx = x + map[x][y];
			if (tx >= 0 && tx < n && visitCount[tx][y] > visitCount[x][y] + 1) {
				visitCount[tx][y] = visitCount[x][y] + 1;
				find.push(make_pair(tx, y));
			}

			int ty = y + map[x][y];
			if (ty >= 0 && ty < n && visitCount[x][ty] > visitCount[x][y] + 1) {
				visitCount[x][ty] = visitCount[x][y] + 1;
				find.push(make_pair(x, ty));
			}
		}

		if (visitCount[n - 1][n - 1] == MAXVALUE) {
			printf("NO\n");
		}
		else {
			printf("YES\n");
		}
	}
}