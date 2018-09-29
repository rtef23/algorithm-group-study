package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by juhyung0818@naver.com on 2018. 9. 18.
 * https://algospot.com/judge/problem/read/LIS
 */
public class Problem1 {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int c, n;
        c = in.nextInt();
        List<Integer> answers = new ArrayList<>();
        List<Sequence> sequences;

        for(int i = 0; i < c; i++) {
            sequences = new ArrayList<>();
            n = in.nextInt();
            for(int j = 0; j < n; j++) {
                sequences.add(new Sequence(in.nextInt()));
            }
            answers.add(solution(sequences));
        }
        print(answers);
    }

    private static void print(List<Integer> answers) {
        answers.forEach(System.out::println);
    }

    private static int solution(List<Sequence> sequences) {
        int size = sequences.size();

        int max, now;
        Sequence pivot, target;
        for(int i = size - 1; i >= 0; i--) {
            pivot = sequences.get(i);
            max = 0;
            for(int j = i + 1; j < size; j++) {
                now = pivot.getCount();
                target = sequences.get(j);
                if(pivot.getValue() < target.getValue()) {
                    if(target.getCount() != 0) {
                        now += target.getCount();
                    }
                }
                if(max < now) {
                    max = now;
                }
            }
            if(pivot.getCount() < max) {
                pivot.setCount(max);
            }
        }

        return sequences.stream()
                .map(Sequence::getCount)
                .max(Integer::compare)
                .orElse(0);
    }

    static class Sequence {
        private int value;
        private int count;

        Sequence(int value) {
            setValue(value);
            setCount(1);
        }

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }

        int getCount() {
            return count;
        }

        void setCount(int count) {
            this.count = count;
        }
    }
}
