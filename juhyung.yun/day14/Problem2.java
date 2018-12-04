package io.juhyung.algorithm.day14;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Problem2 {
    private static final int ZERO = 0;

    private static Scanner in = new Scanner(System.in);

    private static void solution(Queue<Integer> queue, int count) {
        if(queue.size() == 1) {
            System.out.println(count);
            return;
        }
        int a, b;
        a = queue.poll();
        b = queue.poll();
        count += a + b;
        queue.add(a + b);
        solution(queue, count);
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();

        int c, n;
        c = in.nextInt();

        for (int i = 0; i < c; i++) {
            queue.clear();
            n = in.nextInt();
            for (int j = 0; j < n; j++) {
                queue.add(in.nextInt());
            }
            solution(queue, ZERO);
        }
    }
}
