package day6;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      int c = Integer.parseInt(scanner.nextLine().trim());

      for (int i = 0; i < c; i++) {
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[] arr = new int[n];
        String line = scanner.nextLine();

        String[] characters = line.split(" ");

        for (int j = 0; j < n; j++) {
          arr[j] = Integer.parseInt(characters[j].trim());
        }

        System.out.println(F(arr));
      }
    }
  }

  public static int F(int[] arr) {
    int[] memo = new int[arr.length];

    Arrays.fill(memo, -1);

    int result = 0;

    for (int i = 0; i < arr.length; i++) {
      result = Math.max(result, F(arr, memo, i));
    }

    return result;
  }

  public static int F(int[] arr, int[] memo, int targetIndex) {
    if (targetIndex == 0) {
      memo[targetIndex] = 1;

      return 1;
    }

    int result = 0;

    for (int i = 0; i < targetIndex; i++) {
      if (arr[targetIndex] > arr[i]) {
        result = Math.max(result, memo[i]);
      }
    }

    memo[targetIndex] = result + 1;

    return memo[targetIndex];
  }
}
