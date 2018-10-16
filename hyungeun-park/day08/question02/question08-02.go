/*
문제) 삼각형 위의 최대 경로 수 세기 (https://algospot.com/judge/problem/read/TRIPATHCNT)

9
5 7
1 3 2
3 5 5 6
위 형태와 같이 삼각형 모양으로 배치된 자연수들이 있습니다. 맨 위의 숫자에서 시작해, 한 번에 한 칸씩 아래로 내려가 맨 아래 줄로 내려가는 경로를 만들려고 합니다. 경로는 아래 줄로 내려갈 때마다 바로 아래 숫자, 혹은 오른쪽 아래 숫자로 내려갈 수 있습니다.

이 때 숫자의 합이 가장 큰 경로는 하나가 아니라 여러 개일 수 있습니다. 예를 들어 위 삼각형에서는 {9, 7, 2, 6}과 {9, 7, 3, 5}의 합이 모두 최대인 24이고, {9, 7, 3, 5}는 두 번 등장하거든요.

삼각형이 주어질 때 최대 경로의 수를 세는 프로그램을 작성하세요

2
4
1
1 1
1 1 1
1 1 1 1
4
9
5 7
1 3 2
3 5 5 6

*/

package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"log"
)

type TestCase struct {
	elements [][]int64
}

func main() {
	testCaseList := input()
	for _, testCase := range testCaseList {
		fmt.Println(getMaxPath(testCase))
	}
}

func input() []TestCase {
	reader := bufio.NewReader(os.Stdin)

	testCaseCount := readInt(reader)
	testCaseList := make([]TestCase, testCaseCount)

	for i := 0; i < testCaseCount; i++ {
		rowCount := readInt(reader)
		elements := make([][]int64, rowCount)

		for r := 0; r < rowCount; r++ {
			line, _ := reader.ReadString('\n')
			strRowElements := strings.Split(trim(line), " ")

			element := make([]int64, len(strRowElements))

			for index, strRowElement := range strRowElements {
				number := stringToInt(strRowElement)
				element[index] = number
			}

			elements[r] = element
		}

		testCaseList[i] = TestCase{elements}
	}

	return testCaseList
}

func readInt(reader *bufio.Reader) int {
	line, _ := reader.ReadString('\n')
	return int(stringToInt(line))
}

func stringToInt(str string) int64 {
	str = trim(str)

	number, error := strconv.ParseInt(str, 10, 64)
	if error != nil {
		log.Println("str : ", str, "을 int64로 변경하지 못했습니다.")
	}

	return number
}

func trim(str string) string {
	str = strings.TrimSuffix(str, "\r\n")
	str = strings.TrimSuffix(str, "\n")
	str = strings.Trim(str, " ")

	return str
}

func getMaxPath(testCase TestCase) int64 {
	elements := testCase.elements

	max := new(int64)
	count := new(int64)

	calc(max, count, 0, 0, 0, elements)

	return *count
}

func calc(max *int64, count *int64, sum int64, row int, col int, elements [][]int64) {
	if row > len(elements)-1 {
		return
	}

	sum += elements[row][col]

	if sum > *max {
		*max = sum
		*count = 1
	} else if sum == *max {
		*count++
	}

	calc(max, count, sum, row+1, col, elements)
	calc(max, count, sum, row+1, col+1, elements)
}
