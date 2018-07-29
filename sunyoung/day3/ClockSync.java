package day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClockSync {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tc = scan.nextInt();

        while(tc-- > 0) {
            int[] clock = new int[16];

            for(int i=0; i<16; i++) {
                clock[i] = (12-scan.nextInt())/3;
            }

            Answer answer = new Answer();
            System.out.println(answer.calc(clock));
        }
    }
}

class Answer {
    private static int[][] clockSwitch = new int[][]{
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };

    public int calc(int[] clock) {
        LinkedList<Result> queue = new LinkedList<>();
        queue.add(new Result(clock, 0));

        while(queue.size() != 0) {
            Result result = queue.poll();
            int[] resultClock = result.clock;

            if(Arrays.equals(resultClock, new int[16]) ) {
                return result.times;
            }

            if(result.times > 100) {
                return -1;
            }

            //3. 경우의 수 대로 queue에 집어넣기
            List<Result> results = new ArrayList<>();
            for(int switchIdx : possibleSwitch(pickClock(resultClock))) {
                int[] copiedClock = Arrays.copyOf(resultClock, 16);
                clickSwitch(copiedClock, switchIdx);

                results.add(new Result(copiedClock, result.times+1));
            }

            queue.addAll(results);
        }


        return -1;
    }

    public List<Integer> possibleSwitch(int clockIdx) {
        List<Integer> possibleSwitch = new ArrayList<>();

        for(int idx=0; idx<10; idx++) {
            int[] clockIdxs = clockSwitch[idx];

            for(int i : clockIdxs) {
                if(i == clockIdx) {
                    possibleSwitch.add(idx);
                    break;
                }
            }
        }

        return possibleSwitch;
    }

    public void clickSwitch(int[] clock, int switchIndex) {
        int[] clockIdxs = clockSwitch[switchIndex];

        for(int idx : clockIdxs) {
            clock[idx] -= 1;
            if(clock[idx] < 0) {
                clock[idx] += 4;
            }
        }
    }

    public int pickClock(int[] clock) {
        for(int i=0; i<16; i++) {
            if(clock[i] != 0) {
                return i;
            }
        }

        return 0;
    }
}

class Result {
    int[] clock;
    int times;

    public Result(int[] clock, int times) {
        this.clock = clock;
        this.times = times;
    }
}