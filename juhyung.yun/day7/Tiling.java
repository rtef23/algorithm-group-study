
package algorithm.fibonaci;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static int MOD = 1000000007;
	private Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Main tiling = new Main();
		int n = tiling.in.nextInt();

		int[] answer = new int[n];

		for (int i = 0; i < n; i++) {
			answer[i] = tiling.fibonacci(tiling.in.nextInt());
		}

		Arrays.stream(answer)
			  .forEach(System.out::println);
	}

	private int fibonacci(int index) {
		int n1 = 1, n2 = 2;

		if (index <= 2) {
			return index;
		}

		int answer = 0;
		for (int i = 2; i < index; i++) {
			answer = (n1 + n2) % MOD;
			n1 = n2 % MOD;
			n2 = answer % MOD;
		}

		return answer;
	}
}
