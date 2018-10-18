package main

import "fmt"

var cache [101]int

const DIVIDER = 1000000007

func main() {
	initialize()

	testCaseList := input()

	for _, num := range testCaseList {
		fmt.Println(getAsymmetricTiling(num))
	}
}

func input() []int {
	var testCaseCount int
	fmt.Scanf("%d\n", &testCaseCount)

	testCaseList := make([]int, testCaseCount)
	for i := 0; i < testCaseCount; i++ {
		var num int
		fmt.Scanf("%d\n", &num)

		testCaseList[i] = num
	}

	return testCaseList
}

func initialize() {
	for i := 0; i < len(cache); i++ {
		cache[i] = -1
	}
}

func tiling(n int) int {
	if n <= 1 {
		return 1
	}

	if cache[n] != -1 {
		return cache[n]
	}

	cache[n] = (tiling(n-2) + tiling(n-1)) % DIVIDER
	return cache[n]
}

func getAsymmetricTiling(n int) int {
	if n%2 == 1 {
		return (tiling(n) - tiling(n/2) + DIVIDER) % DIVIDER
	}

	result := tiling(n)
	result = (result - tiling(n/2) + DIVIDER) % DIVIDER
	result = (result - tiling(n/2-1) + DIVIDER) % DIVIDER

	return result
}
