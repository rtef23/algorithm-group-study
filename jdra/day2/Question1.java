package day2;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int t = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < t; i++) {
      String[] splitStrings = scanner.nextLine().trim().split(" ");

      int n = Integer.parseInt(splitStrings[0]);
      int m = Integer.parseInt(splitStrings[1]);

      boolean[][] map = new boolean[n][n];

      for (int j = 0; j < n; j++) {
        Arrays.fill(map[j], false);
      }

      String line = scanner.nextLine().trim();

      String[] strings = line.split(" ");

      for (int j = 0; j + 2 <= strings.length; j += 2) {
        int integer1 = Integer.parseInt(strings[j]);
        int integer2 = Integer.parseInt(strings[j + 1]);

        int min = Math.min(integer1, integer2);
        int max = Math.max(integer1, integer2);

        map[min][max] = true;
      }

      boolean[] memo = new boolean[n];

      Arrays.fill(memo, false);

      System.out.println(solve(map, memo, n, m));
    }
  }

  private static int solve(boolean[][] map, boolean[] memo, int n, int m) {
    int result = 0;
    int leftX = 0;
    boolean isAllPaired = true;

    for (int i = 0; i < n; i++) {
      if (!memo[i]) {
        leftX = i;

        isAllPaired = false;
        break;
      }
    }

    if (isAllPaired) {
      return 1;
    }

    for (int j = 0; j < n; j++) {
      if (map[leftX][j] && !memo[j]) {
        memo[leftX] = true;
        memo[j] = true;

        result += solve(map, memo, n, m);

        memo[leftX] = false;
        memo[j] = false;
      }
    }

    return result;
  }
}
