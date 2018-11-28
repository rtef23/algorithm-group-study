package day13;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int testCase = scanner.nextInt();

    for (int i = 0; i < testCase; i++) {
      int n = scanner.nextInt();

      int[] russiaTeam = new int[n];
      int[] koreaTeam = new int[n];

      for (int j = 0; j < n; j++) {
        russiaTeam[j] = scanner.nextInt();
      }

      for (int j = 0; j < n; j++) {
        koreaTeam[j] = scanner.nextInt();
      }

      System.out.println(F(russiaTeam, koreaTeam, n));
    }
  }

  public static int F(int[] russiaTeam, int[] koreaTeam, int n) {
    Arrays.sort(russiaTeam);
    Arrays.sort(koreaTeam);

    int koreaMaxIndex = n - 1;

    int count = 0;

    for (int i = n - 1; i >= 0; i--) {
      if (russiaTeam[i] <= koreaTeam[koreaMaxIndex]) {
        count++;
        koreaMaxIndex--;
      }
    }

    return count;
  }
}
