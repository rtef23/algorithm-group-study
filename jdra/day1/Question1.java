package day1;

import java.util.Arrays;

public class Question1 {

  public static int[] solve(int[] array) {
    int arrayLength = array.length;
    int[] answer = new int[arrayLength];

    int[] left = new int[arrayLength];
    int[] right = new int[arrayLength];

    //initialize
    Arrays.fill(left, 1);
    Arrays.fill(right, 1);

    for (int i = 0; i < arrayLength; i++) {
      left[i] = (i == 0) ? 1 : left[i - 1] * array[i - 1];
    }

    for (int i = arrayLength - 1; i >= 0; i--) {
      right[i] = (i == arrayLength - 1) ? 1 : right[i + 1] * array[i + 1];
    }

    for (int i = 0; i < arrayLength; i++) {
      answer[i] = left[i] * right[i];
    }

    return answer;
  }
}
