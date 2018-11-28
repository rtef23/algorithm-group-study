/*
문제) https://algospot.com/judge/problem/read/PI

가끔 TV 에 보면 원주율을 몇만 자리까지 줄줄 외우는 신동들이 등장하곤 합니다. 이들이 이 수를 외우기 위해 사용하는 방법 중 하나로, 숫자를 몇 자리 이상 끊어 외우는 것이 있습니다. 이들은 숫자를 세 자리에서 다섯 자리까지로 끊어서 외우는데, 가능하면 55555 나 123 같이 외우기 쉬운 조각들이 많이 등장하는 방법을 택하곤 합니다.

이 때, 각 조각들의 난이도는 다음과 같이 정해집니다:

1. 모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
2. 숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
3. 두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
4. 숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
5. 그 외의 경우 난이도: 10
원주율의 일부가 입력으로 주어질 때, 난이도의 합을 최소화하도록 숫자들을 3자리에서 5자리까지 끊어 읽고 싶습니다. 최소의 난이도를 계산하는 프로그램을 작성하세요.

5
12341234
11111222
12122222
22222222
12673939

*/

package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func input() []string {
	reader := bufio.NewReader(os.Stdin)

	testCaseCount := readInt(reader)
	testCaseList := make([]string, testCaseCount)
	for i := 0; i < testCaseCount; i++ {
		testCaseList[i] = readLine(reader)
	}

	return testCaseList
}

func main() {
	testCaseList := input()

	for i := 0; i < len(testCaseList); i++ {
		fmt.Println(calc(testCaseList[i]))
	}
}

func calc(str string) int {
	if len(str) == 0 {
		return 0
	}

	if len(str) < 2 {
		return 10
	}

	//1. 모든 숫자가 같을 때 (예: 333, 5555) 난이도: 1
	//================================================================
	if index, ok := isSameNumber(str); ok {
		// fmt.Println("No.1")
		return 1 + calc(str[index+1:])
	}

	//2. 숫자가 1씩 단조 증가하거나 단조 감소할 때 (예: 23456, 3210) 난이도: 2
	//================================================================
	if index, ok := isConsecutiveNumber(str); ok {
		// fmt.Println("No.2")
		return 2 + calc(str[index+1:])
	}

	//3. 두 개의 숫자가 번갈아 가며 출현할 때 (예: 323, 54545) 난이도: 4
	//================================================================
	if index, ok := isSamePatternNumber(str); ok {
		// fmt.Println("No.3")
		return 4 + calc(str[index+1:])
	}

	//4. 숫자가 등차 수열을 이룰 때 (예: 147, 8642) 난이도: 5
	//================================================================
	if index, ok := isIsothermalSeriesNumber(str); ok {
		// fmt.Println("No.4")
		return 5 + calc(str[index+1:])
	}

	//5. 그 외의 경우 난이도: 10
	//================================================================
	// 12673939
	// fmt.Println("No.5")
	if len(str) <= 5 {
		return 10
	}

	size3 := calc(str[3:])
	size4 := calc(str[4:])
	size5 := calc(str[5:])

	result := size3

	if result > size4 {
		result = size4
	}

	if result > size5 {
		result = size5
	}

	return 10 + result
}

func isSameNumber(str string) (int, bool) {
	if len(str) < 2 {
		return 0, false
	}

	index := 0
	for index < len(str)-1 {
		if str[index] != str[index+1] {
			break
		}
		index++
		if index > 3 {
			break
		}
	}

	return index, index >= 2
}

func isConsecutiveNumber(str string) (int, bool) {
	if len(str) < 2 {
		return 0, false
	}

	index := 0
	pre := str[0]
	post := str[1]
	if post > pre {
		// increment
		for index < len(str)-1 {
			pre = str[index]
			post = str[index+1]

			if post-pre != 1 {
				break
			}

			index++
			if index > 3 {
				break
			}
		}
	} else {
		// decrement
		for index < len(str)-1 {
			pre = str[index]
			post = str[index+1]

			if pre-post != 1 {
				break
			}

			index++
			if index > 3 {
				break
			}
		}
	}
	return index, index >= 2
}

func isSamePatternNumber(str string) (int, bool) {
	if len(str) < 2 {
		return 0, false
	}

	index := 0
	pre := str[0]
	post := str[1]
	for index < len(str)-1 {
		if index%2 == 0 && pre != str[index] {
			index--
			break
		}

		if index%2 == 1 && post != str[index] {
			index--
			break
		}

		if index > 3 {
			break
		}

		index++
	}
	return index, index >= 2
}

func isIsothermalSeriesNumber(str string) (int, bool) {
	if len(str) < 2 {
		return 0, false
	}

	index := 0
	differValue := int(str[index]) - int(str[index+1])
	index++
	for index < len(str)-1 {
		if differValue != int(str[index])-int(str[index+1]) {
			break
		}

		index++
		if index > 3 {
			break
		}
	}
	return index, index >= 2
}

func readLine(reader *bufio.Reader) string {
	line, _ := reader.ReadString('\n')
	return trim(line)
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
