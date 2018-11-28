package day10;

import java.util.Scanner;

public class Question1 {

  private static final int LIMIT = 10_000_000;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String line;

    line = scanner.nextLine().trim();
    int testCase = Integer.parseInt(line);

    for (int i = 0; i < testCase; i++) {
      line = scanner.nextLine().trim();
      int n = Integer.parseInt(line);

      System.out.println(F(n));
    }
  }

  public static int F(int n) {
    return 1;
  }
}
