/*
문제) https://algospot.com/judge/problem/read/SNAIL
깊이가 n 미터인 우물의 맨 밑바닥에 달팽이가 있습니다.
이 달팽이는 우물의 맨 위까지 기어올라가고 싶어하는데, 달팽이의 움직임은 그 날의 날씨에 좌우됩니다.
만약 비가 내리면 달팽이는 하루에 2미터를 기어올라갈 수 있지만, 날이 맑으면 1미터밖에 올라가지 못합니다.

여름 장마가 찾아와, 앞으로 m 일간 각 날짜에 비가 올 확률이 정확히 75%일 전망입니다.
m 일 안에 달팽이가 우물 끝까지 올라갈 확률을 계산하는 프로그램을 작성하세요.

해답) http://jaimemin.tistory.com/326
*/

package main

import (
	"fmt"
)

type TestCase struct {
	height int // 우물 깊이(n)
	day    int // 날짜(m)
}

func input() []TestCase {
	var testCaseCount int
	fmt.Scanf("%d\n", &testCaseCount)

	testCaseList := make([]TestCase, testCaseCount)

	for i := 0; i < testCaseCount; i++ {
		var height int
		var day int
		fmt.Scanf("%d %d", &height, &day)

		testCaseList[i] = TestCase{height, day}
	}

	return testCaseList
}

var height int
var day int
var cache [1000][2000]float64

func initalize(h int, d int) {
	height = h
	day = d

	for i := 0; i < len(cache); i++ {
		for j := 0; j < len(cache[i]); j++ {
			cache[i][j] = -1
		}
	}
}

func main() {
	testCaseList := input()

	for _, testCase := range testCaseList {
		initalize(testCase.height, testCase.day)
		fmt.Println(climb(0, 0))
	}
}

func climb(passed int, climbed int) float64 {
	if passed == day {
		if climbed >= height {
			return 1
		} else {
			return 0
		}
	}

	result := &cache[passed][climbed]
	if *result != -1.0 {
		return *result
	} else {
		*result = (0.25 * climb(passed+1, climbed+1)) + (0.75 * climb(passed+1, climbed+2))
		return *result
	}
}
