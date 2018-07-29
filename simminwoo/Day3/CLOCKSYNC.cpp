#include <cstdio>
#include <cstring>

int C;
int button[10][5] = {
	{ 0, 1, 2, -1, -1 }
	,{ 3, 7, 9, 11, -1 }
	,{ 4, 10, 14, 15, -1 }
	,{ 0, 4, 5, 6, 7 }
	,{ 6, 7, 8, 10, 12 }
	,{ 0, 2, 14, 15, -1 }
	,{ 3, 14, 15, -1, -1 }
	,{ 4, 5, 7, 14, 15 }
	,{ 1, 2, 3, 4, 5 }
	,{ 3, 4, 5, 9, 13 }
};

int clock[16];
int ans = -1;

bool isAnswer() {
	for (int i = 0; i < 16; i++) {
		if (clock[i] == 0 || clock[i] == 12) {
			continue;
		}
		else {
			return false;
		}
	}

	return true;
}

void push(int current) {
	for (int i = 0; i < 5; i++) {
		int targetIndex = button[current][i];

		if (targetIndex != -1) {
			clock[targetIndex] = (clock[targetIndex] + 3) % 12;
		}
	}
}

void solve(int current, int count) {
	if (current == 10) {
		if (isAnswer() && (ans == -1 || ans > count)) {
			ans = count;
		}

		return;
	}

	for (int i = 0; i < 4; i++) {
		push(current);
		solve(current + 1, count + (i + 1) % 4);
	}

	return;
}

int main(void)
{
	scanf("%d", &C);

	while (C > 0) {
		C--;

		memset(clock, 0, sizeof(clock));
		ans = -1;

		for (int i = 0; i < 16; i++) {
			scanf("%d", &clock[i]);
		}

		solve(0, 0);
		printf("%d\n", ans);
	}
}