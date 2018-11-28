package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Question2 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int c = scan.nextInt();
    for (int i = 0; i < c; i++) {
      int n = scan.nextInt();
      int d = scan.nextInt();
      int p = scan.nextInt();
      boolean map[][] = new boolean[n][n];
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          map[j][k] = scan.nextInt() == 1;
        }
      }
      int t = scan.nextInt();
      int q[] = new int[t];
      for (int j = 0; j < t; j++) {
        q[j] = scan.nextInt();
      }
      ArrayList<Double> res = F(n, d, p, map, t, q);
      for (int j = 0; j < res.size(); j++) {
        System.out.printf("%.8f\t", res.get(j));
      }
      System.out.println();
    }

    scan.close();
  }

  public static ArrayList<Double> F(int n, int d, int p, boolean map[][], int t, int q[]) {
    ArrayList<Double> res = new ArrayList<>();
    int Pmemo[] = new int[n];
    double[][] memo = new double[d + 1][n];

    Arrays.fill(Pmemo, -1);
    for (int i = 0; i < d + 1; i++) {
      if (i == 0) {
        Arrays.fill(memo[i], 0);
      } else {
        Arrays.fill(memo[i], -1);
      }
    }
    memo[0][p] = 1;
    for (int i = 0; i < t; i++) {
      res.add(G(memo, Pmemo, map, q[i], d));
    }
    return res;
  }

  public static double G(double memo[][], int pmemo[], boolean map[][], int targetTown, int day) {
    if (memo[day][targetTown] >= 0) {
      return memo[day][targetTown];
    }
    double ret = 0;

    for (int i = 0; i < map.length; i++) {
      if (!map[targetTown][i]) {
        continue;
      }
      double tmpG = G(memo, pmemo, map, i, day - 1);
      int tmpP = P(pmemo, map, i);
      if (tmpG == 0 || tmpP == 0) {
        continue;
      }
      ret += (tmpG / tmpP);
    }
    memo[day][targetTown] = ret;
    return memo[day][targetTown];
  }

  public static int P(int pmemo[], boolean map[][], int i) {
    if (pmemo[i] >= 0) {
      return pmemo[i];
    }
    int ret = 0;
    for (int j = 0; j < map.length; j++) {
      if (map[i][j]) {
        ret++;
      }
    }
    pmemo[i] = ret;
    return pmemo[i];
  }
}
