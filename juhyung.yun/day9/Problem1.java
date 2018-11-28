
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
	private static final BigDecimal ONE = BigDecimal.ONE;
	private static final BigDecimal THREE = new BigDecimal(3);
	private static final BigDecimal FOUR = new BigDecimal(4);

	private static final BigDecimal SUNNY = ONE.divide(FOUR);
	private static final BigDecimal RAIN = THREE.divide(FOUR);

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int c = in.nextInt();
		List<BigDecimal> answers = new ArrayList<>();
		for(int i = 0; i<c; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			answers.add(solution(n, m));
		}

		answers.forEach(System.out::println);
	}

	private static BigDecimal solution(int n, int m) {
		if (n <= m) {
			System.out.println(100.00000000);
		}

		int min = n - m;
		BigDecimal result = BigDecimal.ZERO;
		for(int i = min; i <= m; i++) {
			result = result.add(cal(i, m - i));
		}

		return result;
	}

	private static BigDecimal cal(int rain, int sunny) {
		return powRain(rain).multiply(powSunny(sunny))
							.multiply(combination(rain, sunny));
	}

	private static BigDecimal powRain(int rain) {
		return RAIN.pow(rain);
	}

	private static BigDecimal powSunny(int sunny) {
		return SUNNY.pow(sunny);
	}

	private static BigDecimal combination(int n, int m) {
		if(n == 0 || m == 0) {
			return BigDecimal.ONE;
		}

		int result = 1;
		for (int i = n + m; i > m; i--) {
			result *= i;
		}

		for (int i = n; i > 0; i--) {
			result /= i;
		}

		return new BigDecimal(result);
	}
}
