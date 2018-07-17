/*
https://algospot.com/judge/problem/read/PICNIC

[문제]
안드로메다 유치원 익스프레스반에서는 다음 주에 율동공원으로 소풍을 갑니다.
원석 선생님은 소풍 때 학생들을 두 명씩 짝을 지어 행동하게 하려고 합니다.
그런데 서로 친구가 아닌 학생들끼리 짝을 지어 주면 서로 싸우거나 같이 돌아다니지 않기 때문에, 항상 서로 친구인 학생들끼리만 짝을 지어 줘야 합니다.
각 학생들의 쌍에 대해 이들이 서로 친구인지 여부가 주어질 때, 학생들을 짝지어줄 수 있는 방법의 수를 계산하는 프로그램을 작성하세요.
짝이 되는 학생들이 일부만 다르더라도 다른 방법이라고 봅니다. 예를 들어 다음 두 가지 방법은 서로 다른 방법입니다.

(태연,제시카) (써니,티파니) (효연,유리)
(태연,제시카) (써니,유리) (효연,티파니)

입력
입력의 첫 줄에는 테스트 케이스의 수 C (C <= 50) 가 주어집니다.
각 테스트 케이스의 첫 줄에는 학생의 수 n (2 <= n <= 10) 과 친구 쌍의 수 m (0 <= m <= n*(n-1)/2) 이 주어집니다.
그 다음 줄에 m 개의 정수 쌍으로 서로 친구인 두 학생의 번호가 주어집니다.
번호는 모두 0 부터 n-1 사이의 정수이고, 같은 쌍은 입력에 두 번 주어지지 않습니다.
학생들의 수는 짝수입니다.

출력
각 테스트 케이스마다 한 줄에 모든 학생을 친구끼리만 짝지어줄 수 있는 방법의 수를 출력합니다.

예제 입력
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
예제 출력
1
3
4
*/
package main

import (
	"fmt"
)

type TestCase struct {
	studentSize int
	pairSize    int
	pairList    []Pair
}

type Pair struct {
	student1 int
	student2 int
}

func main() {
	var testCaseSize int

	// 테스트 케이스 읽기
	_, err := fmt.Scanf("%d", &testCaseSize)
	if err != nil {
		fmt.Println("[ERROR] 테스트 케이스의 수 값을 읽지 못했습니다.", err)
		return
	}

	if testCaseSize > 50 {
		fmt.Println("[ERROR] 테스트 케이스의 수는 50개까지 가능 합니다. input : ", testCaseSize)
		return
	}

	testCaseList := make([]TestCase, testCaseSize)
	for i := 0; i < testCaseSize; i++ {
		var studentSize int
		var pairSize int

		// 학생수의 수와 친구 쌍의 수 읽기
		_, err := fmt.Scanf("%d %d", &studentSize, &pairSize)
		if err != nil {
			fmt.Println("[ERROR] 학생수의 수와 친구 쌍의 수를 읽어오지 못했습니다.", err)
			return
		}

		if studentSize < 2 || studentSize > 10 {
			fmt.Println("[ERROR] 학생수는 2명 이상 10명 이하여야 합니다. input : ", studentSize)
			return
		}

		maxPairSize := (studentSize * (studentSize - 1)) / 2
		if pairSize < 0 || pairSize > maxPairSize {
			fmt.Println("[ERROR] 친구 쌍의 수는 0이상 ", maxPairSize, "이하여야 합니다. input : ", pairSize)
			return
		}

		// pair 읽기
		pairList := make([]Pair, pairSize)
		for j := 0; j < pairSize; j++ {
			var student1 int
			var student2 int

			_, err := fmt.Scanf("%d %d", &student1, &student2)
			if err != nil {
				fmt.Println("[ERROR] pair를 읽어오지 못했습니다.", err)
				return
			}

			pairList[j] = Pair{student1, student2}
		}

		testCaseList[i] = TestCase{studentSize, pairSize, pairList}
	}

	for _, testCase := range testCaseList {
		fmt.Println("== 입력 테스트 케이스 ==")
		fmt.Println("1. 학생 수 : ", testCase.studentSize)
		fmt.Println("1. pair 수 : ", testCase.pairSize)
		for _, pair := range testCase.pairList {
			fmt.Println("\t * pair : ", pair)
		}
		fmt.Println()
	}

	for _, testCase := range testCaseList {
		result := calculate(testCase)
		if result == -1 {
			fmt.Println("[WARN] 매칭 할 수 없습니다. testCase : ", testCase)
			continue
		}

		fmt.Println(result)
	}
}

func calculate(testCase TestCase) int {
	matrix := make([]int, testCase.studentSize)
	pairList := testCase.pairList
	size := 0
	maxSize := testCase.studentSize / 2

	return find(matrix, pairList, size, maxSize)
}

func find(matrix []int, pairList []Pair, size int, maxSize int) int {
	if size >= maxSize {
		return 0
	}

	sum := 0
	for i, pair := range pairList {
		copiedMatrix := make([]int, len(matrix))
		copy(copiedMatrix, matrix)

		if copiedMatrix[pair.student1] == 0 && copiedMatrix[pair.student2] == 0 {
			copiedMatrix[pair.student1] = 1
			copiedMatrix[pair.student2] = 1
		}

		if isFullMatrix(copiedMatrix) {
			return 1
		}

		sum += find(copiedMatrix, pairList[i:], size+1, maxSize)
	}
	return sum
}

func isFullMatrix(matrix []int) bool {
	for _, temp := range matrix {
		if temp == 0 {
			return false
		}
	}

	return true
}
