package main

import "fmt"

func main() {
	var count int
	fmt.Scanf("%d", &count)

	testCase := make([]string, count)
	for i := 0; i < count; i++ {
		fmt.Scanln(&testCase[i])
	}

	fmt.Println(testCase)
}

func reverse(string ``)