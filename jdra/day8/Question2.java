package day8;

import java.util.Scanner;

public class Question2 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine().trim();

    int testCase = Integer.parseInt(line);

    for (int i = 0; i < testCase; i++) {
      line = scanner.nextLine().trim();
      int n = Integer.parseInt(line);

      int[][] map = new int[n + 1][n + 1];

      for (int j = 1; j <= n; j++) {
        String[] values = scanner.nextLine().trim().split(" ");

        for (int k = 0; k < values.length; k++) {
          map[j][k + 1] = Integer.parseInt(values[k]);
        }
      }

      System.out.println(F(map, n));
    }
  }

  public static int F(int[][] map, int n) {
    int[][] countPath = new int[n + 1][n + 1];
    int[][] maxValue = new int[n + 1][n + 1];

    countPath[1][1] = 1;
    maxValue[1][1] = map[1][1];

    for (int y = 2; y <= n; y++) {
      for (int x = 1; x <= n; x++) {
        compute(map, countPath, maxValue, x, y);
      }
    }

    int maxLineValue = 0;
    int maxCountPath = 0;

    for (int i = 1; i <= n; i++) {
      if (maxLineValue < maxValue[n][i]) {
        maxLineValue = maxValue[n][i];
        maxCountPath = countPath[n][i];
      } else if (maxLineValue == maxValue[n][i]) {
        maxCountPath += countPath[n][i];
      }
    }

    return maxCountPath;
  }

  private static void compute(int[][] map, int[][] countPath, int[][] maxValue, int x, int y) {
    if (maxValue[y - 1][x - 1] == maxValue[y - 1][x]) {
      maxValue[y][x] = maxValue[y - 1][x] + map[y][x];
      countPath[y][x] = countPath[y - 1][x - 1] + countPath[y - 1][x];
    } else if (maxValue[y - 1][x - 1] > maxValue[y - 1][x]) {
      maxValue[y][x] = maxValue[y - 1][x - 1] + map[y][x];
      countPath[y][x] = countPath[y - 1][x - 1];
    } else {
      maxValue[y][x] = maxValue[y - 1][x] + map[y][x];
      countPath[y][x] = countPath[y - 1][x];
    }
  }
}
