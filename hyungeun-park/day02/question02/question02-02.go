/*
https://algospot.com/judge/problem/read/BOARDCOVER

[문제]
H*W 크기의 게임판이 있습니다.
게임판은 검은 칸과 흰 칸으로 구성된 격자 모양을 하고 있는데 이 중 모든 흰 칸을 3칸짜리 L자 모양의 블록으로 덮고 싶습니다.
이 때 블록들은 자유롭게 회전해서 놓을 수 있지만, 서로 겹치거나, 검은 칸을 덮거나, 게임판 밖으로 나가서는 안 됩니다.
위 그림은 한 게임판과 이를 덮는 방법을 보여줍니다.

게임판이 주어질 때 이를 덮는 방법의 수를 계산하는 프로그램을 작성하세요.

입력
력의 첫 줄에는 테스트 케이스의 수 C (C <= 30) 가 주어집니다.
각 테스트 케이스의 첫 줄에는 2개의 정수 H, W (1 <= H,W <= 20) 가 주어집니다.
다음 H 줄에 각 W 글자로 게임판의 모양이 주어집니다.
# 은 검은 칸, . 는 흰 칸을 나타냅니다.
입력에 주어지는 게임판에 있는 흰 칸의 수는 50 을 넘지 않습니다.

출력
한 줄에 하나씩 흰 칸을 모두 덮는 방법의 수를 출력합니다.

예제 입력
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 10
##########
#........#
#........#
#........#
#........#
#........#
#........#
##########
예제 출력
0
2
1514
*/
package main

import (
	"fmt"
	"time"
)

type TestCase struct {
	matrix [][]int
	row    int
	col    int
	free   int
}

var BLOCK1 = []int{0, 1, 1, 1}
var BLOCK2 = []int{1, 0, 1, 1}
var BLOCK3 = []int{1, 1, 0, 1}
var BLOCK4 = []int{1, 1, 1, 0}
var BLOCK_BOX = [][]int{BLOCK1, BLOCK2, BLOCK3, BLOCK4}

func main() {
	var testCaseSize int

	_, err := fmt.Scanf("%d", &testCaseSize)
	if err != nil {
		fmt.Println("[ERROR] 테스트 케이스의 수 값을 읽지 못했습니다.", err)
		return
	}

	if testCaseSize < 1 && testCaseSize > 30 {
		fmt.Println("[ERROR] 테스트 케이스의 수는 {0 <= C <= 30} 이어야 합니다. input : ", testCaseSize)
		return
	}

	free := 0

	testCaseList := make([]TestCase, testCaseSize)
	for i := 0; i < testCaseSize; i++ {
		var row int
		var col int

		_, err := fmt.Scanf("%d %d", &row, &col)
		if err != nil {
			fmt.Println("[ERROR] row, col 값을 읽어오지 못했습니다.", err)
			return
		}

		if row < 1 || row > 20 || col < 1 || col > 20 {
			fmt.Println("[ERROR] row, col 값은 {0 <= h, w <= 20} 이어야 합니다. row : ", row, ", col : ", col)
			return
		}

		matrix := make([][]int, row)
		for h := 0; h < row; h++ {

			var rowString string
			_, err := fmt.Scanf("%s", &rowString)
			if err != nil {
				fmt.Println("[ERROR] matrixRow을 읽어오지 못했습니다.", err)
				return
			}

			matrixRow := make([]int, col)
			for col, unit := range rowString {
				if unit == '#' {
					matrixRow[col] = 1
				} else {
					free++
				}
			}
			matrix[h] = matrixRow
		}

		testCaseList[i] = TestCase{matrix, row, col, free}
		free = 0
	}

	for _, testCase := range testCaseList {
		fmt.Println("==========================")
		fmt.Println("height : ", testCase.row, ", width : ", testCase.col, ", free : ", testCase.free)
		for _, matrixRow := range testCase.matrix {
			fmt.Println(matrixRow)
		}

		startTime := time.Now()
		fmt.Println("result : ", calculate(testCase))
		fmt.Println("경과 시간 :", time.Since(startTime))
		fmt.Println("==========================")
	}

}

func calculate(testCase TestCase) int {
	matrix := testCase.matrix
	row := testCase.row
	col := testCase.col
	free := testCase.free

	if free == 0 {
		return 1
	}

	if free%3 != 0 {
		return 0
	}

	return find(matrix, row, col, 0, 0, free)
}

func find(matrix [][]int, row int, col int, y int, x int, free int) int {
	for i := 0; i < x; i++ {
		if matrix[y][i] == 0 {
			return 0
		}
	}

	if free == 0 {
		return 1
	}

	if x >= col-1 {
		x = 0
		y++
	}

	if y >= row-1 {
		return 0
	}

	count := 0
	for _, block := range BLOCK_BOX {
		if isPossibleBlock(matrix, y, x, block) {
			copiedMatrix := copyArray(matrix)

			copiedMatrix[y][x] += block[0]
			copiedMatrix[y][x+1] += block[1]
			copiedMatrix[y+1][x] += block[2]
			copiedMatrix[y+1][x+1] += block[3]

			//fmt.Println("y:", y, ", x:", x, ", free:", free-3, ", block:", block)
			//printMatrix(copiedMatrix)

			count += find(copiedMatrix, row, col, y, x+1, free-3)
		}
	}

	return count + find(matrix, row, col, y, x+1, free)
}

func isPossibleBlock(matrix [][]int, y int, x int, block []int) bool {
	if matrix[y][x]+block[0] > 1 {
		return false
	}

	if matrix[y][x+1]+block[1] > 1 {
		return false
	}

	if matrix[y+1][x]+block[2] > 1 {
		return false
	}

	if matrix[y+1][x+1]+block[3] > 1 {
		return false
	}

	return true
}

func printMatrix(matrix [][]int) {
	fmt.Println("==========================")
	for _, row := range matrix {
		fmt.Println(row)
	}
	fmt.Println("==========================")
}

func copyArray(src [][]int) [][]int {
	copiedMatrix := make([][]int, len(src))
	for i := range src {
		copiedMatrix[i] = make([]int, len(src[0]))
		for j := range src[i] {
			copiedMatrix[i][j] = src[i][j]
		}
	}

	return copiedMatrix
}
