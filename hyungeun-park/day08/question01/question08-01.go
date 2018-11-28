package main

import (
	"fmt"
	"math/big"
)

/*

문제)
2xn 크기의 사각형을 2x1 크기의 사각형으로 빈틈없이 채우는 경우의 수를 구하는 프로그램을 작성하세요.

예를 들어 n=5라고 하면 다음 그림과 같이 여덟 가지의 방법이 있습니다.

경우의 수는 n이 커지면 아주 커질 수 있으므로, 1000000007으로 나눈 값을 대신 출력하세요.

해답) http://dororongju.tistory.com/100

*/

func main() {
	var count int
	fmt.Scanf("%d", &count)

	numbers := make([]int, count)
	for i := 0; i < count; i++ {
		fmt.Scanf("%d", &numbers[i])
	}

	for _, number := range numbers {
		fmt.Println(tiling(number))
	}
}

func tiling(n int) int64 {
	var total = big.NewInt(0)
	var length = n / 2

	for r := 0; r <= length; {
		total.Add(total, getCombination(big.NewInt(int64(n)), big.NewInt(int64(r))))
		n--
		r++
	}

	var result = big.NewInt(0)
	result.Mod(total, big.NewInt(int64(1000000007)))

	return result.Int64()
}

/* Factorial : n! = n * n-1 * n-2 * ... */
func getFactorial(n *big.Int) *big.Int {
	if n.Int64() < 2 {
		return big.NewInt(int64(1))
	}

	// n - 1
	var nSubOne = big.NewInt(0)
	nSubOne.Sub(n, big.NewInt(int64(1)))

	// n * (n-1)!
	var result = big.NewInt(0)
	result.Mul(n, getFactorial(nSubOne))

	return result
}

/* Permutation : nPr = n! / (n-r)! */
func getPermutation(n *big.Int, r *big.Int) *big.Int {
	// n - r
	var nSubR = big.NewInt(0)
	nSubR.Sub(n, r)

	// n! / (n-r)!
	var result = big.NewInt(0)
	result.Div(getFactorial(n), getFactorial(nSubR))

	return result
}

/* Combination : nCr = nPr / r! */
func getCombination(n *big.Int, r *big.Int) *big.Int {
	var result = big.NewInt(0)
	result.Div(getPermutation(n, r), getFactorial(r))

	return result
}
