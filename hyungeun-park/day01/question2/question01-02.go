/*
Question2)
안녕하세요, 매일프로그래밍 이번주 문제입니다.
0과 1로 만들어진 2D 정수 배열이 있습니다.
0은 장애물이고 1은 도로일때, 두 좌표가 주어지면,
첫번째 좌표에서 두번째 좌표까지 가장 가까운 거리를 구하시오.
두 좌표는 모두 도로에서 시작되고 좌, 우, 아래, 위로 움직이며 대각선으로는 움직일 수 없습니다.
만약 갈 수 없다면 -1을 리턴하시오.

예제)
Input:
{{1, 0, 0, 1, 1, 0},
{1, 0, 0, 1, 0, 0},
{1, 1, 1, 1, 0, 0},
{1, 0, 0, 0, 0, 1},
{1, 1, 1, 1, 1, 1}}

Start: (0, 0)
Finish: (0, 4)
Output: 8
*/

package main

import (
	"fmt"
)

type Node struct {
	y        int
	x        int
	distance int
}

func main() {
	// y = 5, x = 6
	matrix := [][]int{
		{1, 1, 1, 1, 1, 0},
		{1, 0, 0, 1, 0, 0},
		{1, 1, 1, 1, 0, 0},
		{1, 0, 0, 0, 0, 1},
		{1, 1, 1, 1, 1, 1}}

	start := Node{0, 0, 0}
	end := Node{0, 4, 0}

	fmt.Println("Start: ", start)
	fmt.Println("Finish: ", end)
	fmt.Println("Output : ", calculate(matrix, start, end))
}

func calculate(matrix [][]int, start Node, end Node) int {
	queue := make(chan Node, 1024)
	queue <- start

	current := <-queue
	if matrix[current.y][current.x] == 0 {
		return -1
	}

	find(queue, matrix, end, current)
	close(queue)

	queueSize := len(queue)
	if queueSize == 0 {
		return -1
	}

	result := <-queue
	for node := range queue {
		if node.distance < result.distance {
			result = node
		}
	}

	return result.distance
}

func find(queue chan Node, matrix [][]int, destination Node, current Node) {
	if current.y < 0 || current.x < 0 || current.y >= len(matrix) || current.x >= len(matrix[0]) {
		return
	}

	if matrix[current.y][current.x] == 0 {
		return
	}

	matrix[current.y][current.x] = 0
	for _, mat := range matrix {
		fmt.Println(mat)
	}

	if destination.y == current.y && destination.x == current.x {
		queue <- current
		return
	}

	current.distance++
	find(queue, copyArray2(matrix), destination, Node{current.y + 1, current.x, current.distance})
	find(queue, copyArray2(matrix), destination, Node{current.y - 1, current.x, current.distance})
	find(queue, copyArray2(matrix), destination, Node{current.y, current.x + 1, current.distance})
	find(queue, copyArray2(matrix), destination, Node{current.y, current.x - 1, current.distance})
}

func copyArray2(src [][]int) [][]int {
	copiedMatrix := make([][]int, len(src))
	for i := range src {
		copiedMatrix[i] = make([]int, len(src[0]))
		for j := range src[i] {
			copiedMatrix[i][j] = src[i][j]
		}
	}

	return copiedMatrix
}
