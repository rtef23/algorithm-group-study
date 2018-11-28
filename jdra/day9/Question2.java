package day9;

import java.util.Arrays;
import java.util.Scanner;

public class Question2 {

  public static final double DEFAULT = -1d;
  public static final double LIMIT = 1_000_000_007d;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String line = scanner.nextLine().trim();
    int testCase = Integer.parseInt(line);

    for (int i = 0; i < testCase; i++) {
      line = scanner.nextLine().trim();
      int n = Integer.parseInt(line);

      System.out.printf("%.0f\n", F(n));
    }
  }

  /*
  F(n) : 2 * n 크기의 직사각형을 비대칭으로 타일을 놓는 방법의 수
  G(n) : 2 * n 크기의 직사각형에 타일을 놓는 방법의 수
  S(n) : 2 * n 크기의 직사각형을 대칭으로 타일을 놓는 방법의 수

  F(n) = G(n) - S(n)
  G(n) = G(n - 1) + G(n - 2)
  S(n) = iff n mod 2 == 0
             G(n / 2) + G((n - 2) / 2) + G((n - 4) / 2)
             n mod 2 != 0
             G((n - 1) / 2)
   */
  public static double F(int n) {
    double[] gMemo = new double[n + 1];

    Arrays.fill(gMemo, DEFAULT);

    gMemo[0] = 1d;
    gMemo[1] = 1d;
    gMemo[2] = 2d;

    return (G(gMemo, n) - S(gMemo, n) + LIMIT) % LIMIT;
  }

  private static double G(double[] gMemo, int n) {
    if (n < 0) {
      return 0d;
    }

    if (gMemo[n] > DEFAULT) {
      return gMemo[n];
    }

    gMemo[n] = (G(gMemo, n - 1) + G(gMemo, n - 2));

    return gMemo[n];
  }

  private static double S(double[] gMemo, int n) {
    if (n == 1) {
      return 1d;
    }

    if (n == 2) {
      return 2d;
    }

    if (n % 2 == 0) {
      return G(gMemo, (n - 2) / 2) * 2 + G(gMemo, (n - 4) / 2);
    } else {
      return (G(gMemo, (n - 1) / 2));
    }
  }
}
