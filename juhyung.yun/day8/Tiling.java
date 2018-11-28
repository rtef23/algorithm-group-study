package algorithm.fibonaci;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	private static final int MOD = 1000000007;
	private Scanner in = new Scanner(System.in);
	private static int[] fibonacci = new int[101];

	private void init() {
		fibonacci[1] = 1;
		fibonacci[2] = 2;
	}

	public static void main(String[] args) {
		Main tiling = new Main();
		tiling.init();
		int n = tiling.in.nextInt();

		IntStream.rangeClosed(0, n - 1)
				 .map((i) -> tiling.calFibonacci(tiling.in.nextInt()))
				 .boxed()
				 .collect(Collectors.toList())
				 .forEach(System.out::println);
	}

	private int calFibonacci(int index) {
		if (index <= 0) {
			return 0;
		}

		if (fibonacci[index] > 0) {
			return fibonacci[index];
		}

		return fibonacci[index] = (calFibonacci(index - 2) + calFibonacci(index - 1)) % MOD;
	}
}
