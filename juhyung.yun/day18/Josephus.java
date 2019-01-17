//package io.juhyung.algorithm.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Josephus {

    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        int c, n, k;

        c = IN.nextInt();
        for (int i = 0; i < c; i++) {
            n = IN.nextInt();
            k = IN.nextInt();
            solution(n, k);

        }
    }

    private static void solution(int n, int k) {
        List<Integer> people = getSequence(n);
        k--;

        int suicideNumber = 0;
        while (n > 2) {
            people.remove(suicideNumber); //처음에 자살

            suicideNumber += k;
            n--;
            suicideNumber = (suicideNumber % n);
        }

        people.forEach(number -> System.out.print(number + " "));
    }

    private static List<Integer> getSequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            sequence.add(i);
        }
        return sequence;
    }
}
