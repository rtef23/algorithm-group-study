package day8;

import java.util.Scanner;

public class Question1 {

  private static final int LIMIT = 1000000007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String line = scanner.nextLine().trim();

    int testCase = Integer.parseInt(line);

    for (int i = 0; i < testCase; i++) {
      line = scanner.nextLine().trim();

      int n = Integer.parseInt(line);

      System.out.println(F(n));
    }
  }

  /*
  F(0) = 0
  F(1) = 1
  F(2) = 2
  F(n) = F(n - 1) + F(n - 2)
   */

  public static int F(int n) {
    int[] memo = new int[n + 1];

    return F(memo, n);
  }

  private static int F(int[] memo, int n) {
    if (n == 0) {
      return 0;
    }

    if (n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    if (memo[n] > 0) {
      return memo[n];
    }

    int result = F(memo, n - 1) + F(memo, n - 2);

    memo[n] = result % LIMIT;

    return memo[n];
  }
}
