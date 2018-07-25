package main

import (
	"fmt"
)

var result, numOfStudent, numOfPair int

func main() {
	var testCase int
	fmt.Scanf("%d", &testCase)

	for i := 0; i < testCase; i++ {
		fmt.Scanf("%d %d", &numOfStudent, &numOfPair)

		pairs := make([][2]int, numOfPair)

		for eachPair := 0; eachPair < numOfPair; eachPair++ {
			var pair [2]int
			fmt.Scanf("%d %d", &pair[0], &pair[1])

			pairs[eachPair] = pair
		}

		result = 0
		matched := make([]bool, numOfStudent)

		//fmt.Println(numOfPair, numOfStudent, matched, pairs)
		calculate(pairs, 0, 0, matched)
		fmt.Println(result)
	}
}

func calculate(pairs [][2]int, pairIndex int, matchedCount int, matched []bool) {
	if matchedCount == numOfStudent {
		result++
		return
	}

	if pairIndex == numOfPair {
		return
	}

	if !matched[pairs[pairIndex][0]] && !matched[pairs[pairIndex][1]] {
		newMatched := make([]bool, len(matched))
		copy(newMatched, matched)

		newMatched[pairs[pairIndex][0]] = true
		newMatched[pairs[pairIndex][1]] = true

		calculate(pairs, pairIndex+1, matchedCount+2, newMatched)
	}

	calculate(pairs, pairIndex+1, matchedCount, matched)
}
