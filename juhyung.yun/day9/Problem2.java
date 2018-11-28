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

		run(n);

		return (FIBONACCI[n - 2] + FIBONACCI[n - 1] - getSub(n)) % MOD;
	}

	private static void run(int index) {
		fibonacci(index, FIBONACCI);
		fibonacci((index + 1) / 2, ODD);
		fibonacci((index + 1) / 2, EVEN);
	}

	private static int fibonacci(int index, int[] arr) {
		if (index <= 0) {
			return 0;
		}

		if (arr[index] > 0) {
			return arr[index];
		}

		return arr[index] = (fibonacci(index - 2, arr) + fibonacci(index - 1, arr)) % MOD;
	}

	private static int getSub(int n) {
		if (n % 2 == 0) {
			return EVEN[n / 2];
		}

		return ODD[(n + 1) / 2];
	}

}
