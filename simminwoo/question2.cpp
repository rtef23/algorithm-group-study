#include <cstdio>
#include <queue>
#include <string>
using namespace std;

int width, height;
int map[10001][10001];
int visit[10001][10001];
int dx[4] = { -1, 0, 0, 1 };
int dy[4] = { 0, 1, -1, 0 };
int startX, startY;
int endX, endY;

int main(void)
{
	scanf("%d %d", &width, &height);

	int i, j;
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	scanf("%d %d", &startX, &startY);
	scanf("%d %d", &endX, &endY);

	memset(visit, -1, sizeof(visit));
	queue<pair<int, int> > currentPosition;
	currentPosition.push(make_pair(startX, startY));
	visit[startX][startY] = 0;

	while (!currentPosition.empty()) {
		int currentX = currentPosition.front().first;
		int currentY = currentPosition.front().second;
		int nextX, nextY;

		currentPosition.pop();

		if (currentX == endX && currentY == endY) {
			continue;
		}

		for (int d = 0; d < 4; d++) {
			nextX = currentX + dx[d];
			nextY = currentY + dy[d];

			if (nextX >= 0 && nextX < height && nextY >= 0 && nextY < width) {
				if (map[nextX][nextY] == 1 && (visit[nextX][nextY] == -1 || visit[nextX][nextY] > visit[currentX][currentY])) {
					visit[nextX][nextY] = visit[currentX][currentY] + 1;
					currentPosition.push(make_pair(nextX, nextY));
				}
			}
		}
	}

	printf("%d\n", visit[endX][endY]);
}