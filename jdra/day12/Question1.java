package day12;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Question1 {

  private static final String LONG_POINT = "-";
  private static final String SHORT_POINT = "o";

  private static final Boolean LONG_VALUE = true;
  private static final Boolean SHORT_VALUE = false;

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      String line = scanner.nextLine().trim();
      int testCase = Integer.parseInt(line);

      for (int i = 0; i < testCase; i++) {
        line = scanner.nextLine().trim();
        String[] values = line.split(" ");

        int n = Integer.parseInt(values[0]);
        int m = Integer.parseInt(values[1]);
        int k = Integer.parseInt(values[2]);

        Deque<Boolean> result = F(n, m, k);

        String resultString = result.stream()
            .map((value) -> value ? LONG_POINT : SHORT_POINT)
            .collect(Collectors.joining());

        System.out.println(resultString);
      }
    }
  }

  /*
  F(n, m, k) : n개의 장점과 m개의 단점으로 만들 수 있는 k번째 배열
  F(n, m, k) =
    iff) n == a, m == 0
      -------...a
    iff) n == 0, m == a
      ooooooo...a
    iff) n == 1, m == 1, k == 0
      --
    iff) n == 1, m == 1, k == 1
      oo
    iff) k <= (n + m - 1)C(n - 1)
      = - + F(n - 1, m, k)
    iff) k > (n + m - 1)C(n - 1)
      = o + F(n, m - 1, k - (n + m - 1)C(n - 1))
   */

  public static Deque<Boolean> F(int n, int m, double k) {
    Deque<Boolean> result = new LinkedList<>();

    while (true) {
      if (k == 1) {
        for (int i = 0; i < n; i++) {
          result.addLast(LONG_VALUE);
        }

        for (int i = 0; i < m; i++) {
          result.addLast(SHORT_VALUE);
        }

        return result;
      }

      if (n == 0) {
        for (int i = 0; i < m; i++) {
          result.addLast(SHORT_VALUE);
        }

        return result;
      }

      if (m == 0) {
        for (int i = 0; i < n; i++) {
          result.addLast(LONG_VALUE);
        }

        return result;
      }

      double nCk = nCk(n + m - 1, n - 1);

      if (nCk >= k) {
        n -= 1;

        result.addLast(LONG_VALUE);
      } else {
        m -= 1;
        k -= nCk;

        result.addLast(SHORT_VALUE);
      }
    }
  }

  public static double nCk(int n, int k) {//overflow?
    if (n == k || n == 0 || k == 0) {
      return 1d;
    }

    if (k == 1) {
      return n;
    }

    double result = 1d;

    for (int i = k + 1; i <= n; i++) {
      result *= i;
    }

    for (int i = n - k; i > 1; i--) {
      result /= i;
    }

    return result;
  }
}
