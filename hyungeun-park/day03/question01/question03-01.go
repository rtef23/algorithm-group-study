package main

import (
	"fmt"
)

var button = [][]int{
	{0, 1, 2},
	{3, 7, 9, 11},
	{4, 10, 14, 15},
	{0, 4, 5, 6, 7},
	{6, 7, 8, 10, 12},
	{0, 2, 14, 15},
	{3, 14, 15},
	{4, 5, 7, 14, 15},
	{1, 2, 3, 4, 5},
	{3, 4, 5, 9, 1},
}

var answer = -1

func main() {
	var count int
	fmt.Scanf("%d", &count)

	clocksList := make([][]int, count)

	for i := 0; i < count; i++ {
		clocks := make([]int, 16)

		for j := 0; j < len(clocks); j++ {
			fmt.Scanf("%d", &clocks[j])
		}

		clocksList[i] = clocks
	}

	for _, clocks := range clocksList {
		answer = -1
		search(clocks, 0, 0)
		fmt.Println(answer)
	}
}

func search(clocks []int, depth int, count int) {
	if depth == 10 {
		if isAnswer(clocks) && (answer == -1 || answer > count) {
			answer = count
		}
		return
	}

	for i := 0; i < 4; i ++ {
		forward(clocks, depth)
		search(clocks, depth+1, count+((i+1)%4))
	}
}

func isAnswer(clocks []int) bool {
	for _, c := range clocks {
		if c != 12 {
			return false
		}
	}
	return true
}

func forward(clocks [] int, depth int) {
	for _, c := range button[depth] {
		clocks[c] = (clocks[c]+2)%12 + 1
	}
	return
}
