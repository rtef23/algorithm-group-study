#include <cstdio>
#include <cstring>

int C;
char member[200001];
char fan[200001];
int compare[200001];

int toBinary(const char *target, int index, int num) {
	int sum = 0;
	for (int i = index; i >= num; i--) {
		if (target[i] == 'F') {
			sum += 1 << (index - i);
		}
	}

	return sum;
}

int toBinary(int index) {
	int sum = 0;
	for (int i = 0; i <= index; i++) {
		sum += (1 << i);
	}

	return sum;
}

int main(void) {
	scanf("%d", &C);

	while (C > 0) {
		C--;
		scanf("%s", &member);
		scanf("%s", &fan);
		memset(compare, 0, sizeof(compare));

		int memberLength = strlen(member);
		int fanLength = strlen(fan);
		int range = fanLength - memberLength + 1;
		int i;

		int answer = 0;
		int memberSum = toBinary(member, memberLength - 1, 0);
		int totalSum = toBinary(memberLength - 1);

		for (i = 0; i < range; i++) {
			compare[i] = toBinary(fan, fanLength - range + i, i);
		}

		for (i = 0; i < range; i++) {
			if (totalSum == (memberSum | compare[i])) {
				answer++;
			}
		}

		printf("%d\n", answer);
	}
}