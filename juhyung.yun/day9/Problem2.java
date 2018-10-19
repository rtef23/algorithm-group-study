import java.util.Arrays;
import java.util.Scanner;


public class Problem2 {
	private static final int[] FIBONACCI = new int[101];
	private static final int[] ODD = new int[51];
	private static final int[] EVEN = new int[51];

	private static final int MOD = 1_000_000_007;

	private static void init() {
		FIBONACCI[1] = 1;
		FIBONACCI[2] = 2;

		ODD[1] = 1;
		ODD[2] = 1;

		EVEN[1] = 2;
		EVEN[2] = 3;
	}

	public static void main(String[] args) {
		init();

		Scanner in = new Scanner(System.in);
		int c = in.nextInt();

		int[] answers = new int[c];
		for (int i = 0; i < c; i++) {
			answers[i] = solution(in.nextInt());
		}

		Arrays.stream(answers)
			  .map(i -> (i + MOD) % MOD)
			  .forEach(System.out::println);
	}

	private static int solution(int n) {
		if (n <= 2) {
			return 0;
		}

		fibonacci(n);

		if (n % 2 == 0) {
			return (FIBONACCI[n - 2] + FIBONACCI[n - 1] - EVEN[n / 2]) % MOD;
		}

		return (FIBONACCI[n - 2] + FIBONACCI[n - 1] - ODD[(n + 1) / 2]) % MOD;

	}

	private static void fibonacci(int index) {
		tileFibonacci(index);
		oddFibonacci((index + 1) / 2);
		evenFibonacci((index + 1) / 2);
	}

	private static int tileFibonacci(int index) {
		if (index <= 0) {
			return 0;
		}

		if (FIBONACCI[index] > 0) {
			return FIBONACCI[index];
		}

		return FIBONACCI[index] = (tileFibonacci(index - 2) % MOD + tileFibonacci(index - 1) % MOD) % MOD;
	}

	private static int oddFibonacci(int index) {
		if (index <= 0) {
			return 0;
		}

		if (ODD[index] > 0) {
			return ODD[index];
		}

		return ODD[index] = (oddFibonacci(index - 2) % MOD + oddFibonacci(index - 1) % MOD) % MOD;
	}

	private static int evenFibonacci(int index) {
		if (index <= 0) {
			return 0;
		}

		if (EVEN[index] > 0) {
			return EVEN[index];
		}

		return EVEN[index] = (evenFibonacci(index - 2) % MOD + evenFibonacci(index - 1)% MOD) % MOD;
	}
}
