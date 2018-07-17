/*
Question1)
정수로된 배열이 주어지면, 각 원소가 자신을 뺀 나머지 원소들의 곱셈이 되게하라.
단, 나누기 사용 금지, O(n) 시간복잡도

예제)
input: [1, 2, 3, 4, 5]
output: [120, 60, 40, 30, 24]
*/

package main

import (
	"fmt"
)

func main() {
	args := []int{1, 2, 3, 4, 5}
	argSize := len(args)

	left := make([]int, argSize)
	temp := 1
	for i := 0; i < argSize; i++ {
		left[i] = temp
		temp *= args[i]
	}

	right := make([]int, argSize)
	temp = 1
	for i := argSize - 1; i >= 0; i-- {
		right[i] = temp
		temp *= args[i]
	}

	result := make([]int, argSize)
	for i := 0; i < argSize; i++ {
		result[i] = left[i] * right[i]
	}

	fmt.Println("input : ", args)
	fmt.Println("output : ", result)
}
