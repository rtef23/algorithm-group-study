package day1;

import java.util.Arrays;

public class Day1Main {

  public static void main(String[] args) {
    //question1
    int array[] = {1, 2, 3, 4, 5};

    System.err.printf("answer : %s\n", Arrays.toString(Question1.solve(array)));

    //question2
    boolean map[][] = {
        {true,  true,  true,  true,   true},
        {false, false, true,  false,  true},
        {false, false, true,  false,  true},
        {true,  true,  true,  false,  true},
        {true,  false, false, false,  true},
        {false, true,  false, true,   true}
    };

    int[] start = {0, 0};
    int[] end = {0, 4};

    System.err.printf("answer : %d\n", Question2.solve(map, start, end));
  }
}
