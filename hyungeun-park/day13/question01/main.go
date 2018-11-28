package main

import (
	"fmt"
	"sort"
)

/*
3
6
3000 2700 2800 2200 2500 1900
2800 2750 2995 1800 2600 2000
3
1 2 3
3 2 1
4
2 3 4 5
1 2 3 4
*/
type TestCase struct {
	russianTeamRatings []int
	koreanTeamRatings  []int
}

func input() []TestCase {
	var testCount int
	fmt.Scanf("%d", &testCount)
	testCaseList := make([]TestCase, testCount)
	for i := 0; i < testCount; i++ {
		var teamCount int

		fmt.Scanf("%d", &teamCount)

		russianTeamRatings := make([]int, teamCount)
		for j := 0; j < teamCount; j++ {
			var russiaRating int
			fmt.Scanf("%d", &russiaRating)
			russianTeamRatings[j] = russiaRating
		}

		koreanTeamRatings := make([]int, teamCount)
		for j := 0; j < teamCount; j++ {
			var koreanRating int
			fmt.Scanf("%d", &koreanRating)
			koreanTeamRatings[j] = koreanRating
		}

		testCaseList[i] = TestCase{russianTeamRatings: russianTeamRatings, koreanTeamRatings: koreanTeamRatings}
	}
	return testCaseList
}

func main() {
	testCaseList := input()

	for _, testCase := range testCaseList {
		sort.Ints(testCase.russianTeamRatings)
		sort.Ints(testCase.koreanTeamRatings)

		var count = 0
		for _, russianRating := range testCase.russianTeamRatings {
			var koreanRatingIndex int
			for koreanRatingIndex = 0; koreanRatingIndex < len(testCase.koreanTeamRatings); koreanRatingIndex++ {
				if testCase.koreanTeamRatings[koreanRatingIndex] >= russianRating {
					count++
					break
				}
			}

			if koreanRatingIndex+1 >= len(testCase.koreanTeamRatings) {
				break
			}
			testCase.koreanTeamRatings = testCase.koreanTeamRatings[koreanRatingIndex+1:]
		}
		fmt.Println(count)
	}
}
