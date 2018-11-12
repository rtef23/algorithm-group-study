package io.juhyung.algorithm.day12;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/MORSE
 */

public class Problem1 {
    private static final int MAX_VALUE = 1_000_000_000;
    private static final int[][] memo = new int[101][101];

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        combination();

        int c, n, m, k;

        c = in.nextInt();

        for (int i = 0; i < c; i++) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();

            solution(n, m, k , "");
        }
    }

    static void solution(int n, int m, int k, String answer) {
        if (n == 0) {
            for(int i = 0 ; i < m; i++) {
                answer += "o";
            }
            System.out.println(answer);
            return;
        }

        if (memo[n - 1][m] < k) {
            k -= memo[n - 1][m];
            answer += "o";
            solution(n, m - 1, k, answer);
            return;
        }
        answer += "-";
        solution(n - 1, m, k, answer);
    }


    static void combination() {
        for (int i = 0; i <= 100; i++) {
            memo[i][0] = memo[0][i] = 1;
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1] > MAX_VALUE ? MAX_VALUE : memo[i - 1][j] + memo[i][j - 1];
            }
        }
    }
}
