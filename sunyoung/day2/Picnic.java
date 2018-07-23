package day2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 안드로메다 유치원 익스프레스반에서는 다음 주에 율동공원으로 소풍을 갑니다.
 * 원석 선생님은 소풍 때 학생들을 두 명씩 짝을 지어 행동하게 하려고 합니다.
 * 그런데 서로 친구가 아닌 학생들끼리 짝을 지어 주면 서로 싸우거나 같이 돌아다니지 않기 때문에,
 * 항상 서로 친구인 학생들끼리만 짝을 지어 줘야 합니다.
 *
 * 각 학생들의 쌍에 대해 이들이 서로 친구인지 여부가 주어질 때,
 * 학생들을 짝지어줄 수 있는 방법의 수를 계산하는 프로그램을 작성하세요.
 * 짝이 되는 학생들이 일부만 다르더라도 다른 방법이라고 봅니다.
 * 예를 들어 다음 두 가지 방법은 서로 다른 방법입니다.
 *
 * (태연,제시카) (써니,티파니) (효연,유리)
 * (태연,제시카) (써니,유리) (효연,티파니)
 */

public class Picnic {
    private static int possibility = 0;
    private static int[][] pairs;
    private static int numOfStudent;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        while (testCase-- > 0) {
            possibility = 0;

            numOfStudent = scan.nextInt();
            int NumOfFriendPair = scan.nextInt();

            pairs = new int[NumOfFriendPair][2];
            for (int i = 0; i < NumOfFriendPair; i++) {
                int[] pair = new int[2];
                pair[0] = scan.nextInt();
                pair[1] = scan.nextInt();

                pairs[i] = pair;
            }

            boolean[] matched = new boolean[numOfStudent];
            calc(0, matched, 0);

            System.out.println(possibility);
        }
    }

    public static void calc(int pairIndex, boolean[] matched, int matchedCount) {
        if (matchedCount == numOfStudent) {
            possibility++;
            return;
        }

        if (pairIndex >= pairs.length) {
            return;
        }

        if (!matched[pairs[pairIndex][0]] && !matched[pairs[pairIndex][1]]) {
            boolean[] newMatched = Arrays.copyOf(matched, numOfStudent);
            newMatched[pairs[pairIndex][0]] = true;
            newMatched[pairs[pairIndex][1]] = true;

            calc(pairIndex + 1, newMatched, matchedCount + 2);
        }

        calc(pairIndex + 1, matched, matchedCount);
    }
}