#include <cstdio>
using namespace std;

int testNum;
int input[10001];
int orderedMultiplication[10001];
int reverseMultiplication[10001];
int result[10001];

int main(void) {
	scanf("%d", &testNum);

	for (int i = 0; i < testNum; i++) {
		scanf("%d", &input[i]);
	}

	int temp = 1;
	int i;
	for (i = 0; i < testNum; i++) {
		orderedMultiplication[i] = temp;
		temp *= input[i];
	}

	temp = 1;
	for (int i = testNum - 1; i >= 0; i--) {
		reverseMultiplication[i] = temp;
		temp *= input[i];
	}

	for (i = 0; i < testNum; i++) {
		result[i] = orderedMultiplication[i] * reverseMultiplication[i];
		printf("%d ", result[i]);
	}
}