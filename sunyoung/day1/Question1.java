package day1;

import java.util.Arrays;

public class Question1 {

    /**
     * Question1)
     *
     *     정수로된 배열이 주어지면, 각 원소가 자신을 뺀 나머지 원소들의 곱셈이 되게하라.
     *     단, 나누기 사용 금지, O(n) 시간복잡도
     */

    public static void main(String[] args) {
        Answer answer = new Answer();
        int[] result = answer.calculate(new int[] {0,0,2,3});

        System.out.println(Arrays.toString(result));
    }
}

class Answer {
    int[] calculate(int[] array) {
        int size = array.length;

        int[] left = new int[size];
        int product = 1;

        for(int li = 0; li < size; li++) {
            left[li] = product;
            product *= array[li];
        }

        int[] right = new int[size];
        product = 1;

        for(int ri = size-1; ri >= 0; ri--) {
            right[ri] = product;
            product *= array[ri];
        }

        int[] result = new int[size];

        for(int i=0; i < size; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }
}