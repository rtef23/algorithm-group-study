package day9;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

  public static final double DEFAULT = -1;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String line = scanner.nextLine().trim();
    int testCase = Integer.parseInt(line);

    for (int i = 0; i < testCase; i++) {
      line = scanner.nextLine().trim();
      String[] part = line.split(" ");

      int n = Integer.parseInt(part[0]);
      int m = Integer.parseInt(part[1]);

      System.err.printf("%.10f\n", G(n, m));
    }
  }

  /*
  G(n, m) : 달팽이가 n 깊이의 우물을 m일 안에 탈출할 수 있는 확률
  G(n, m) = sum[k = 0;k <= m - 1;k++](F(n - 1, k));달팽이가 k일에 n-1 깊이에 위치할 확률 + sum[k = 0;k <= m - 1;k++](F(n - 2, k) * 0.75);달팽이가 k일에 n-2의 깊이에서 n의 깊이로 올라올 확률

  F(n, m) : 달팽이가 m일에 n 위치에 있을 확률
  F(n, m) = F(n - 1, m - 1) * 0.25 + F(n - 2, m - 1) * 0.75
  F(0, 0) = 1
  F(1, 0) = 0
  F(2, 0) = 0
  F(0, 1) = 0
  F(0, 2) = 0
   */

  public static double G(int n, int m) {
    double[][] memo = new double[n + 1][m + 1];

    for (int k = 0; k <= n; k++) {
      Arrays.fill(memo[k], DEFAULT);
      memo[k][0] = 0d;
    }

    Arrays.fill(memo[0], 0d);

    memo[0][0] = 1d;

    double result = 0d;

    for (int k = 0; k <= m - 1; k++) {
      result += F(memo, n - 1, k) + (F(memo, n - 2, k) * 0.75d);
    }

    return result;
  }

  public static double F(double[][] memo, int n, int m) {
    if (n < 0 || m < 0) {
      return 0d;
    }

    if (memo[n][m] > DEFAULT) {
      return memo[n][m];
    }

    memo[n][m] = F(memo, n - 1, m - 1) * 0.25d + F(memo, n - 2, m - 1) * 0.75d;

    return memo[n][m];
  }
}
