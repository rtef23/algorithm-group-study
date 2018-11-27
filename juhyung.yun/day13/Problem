package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by juhyung0818@naver.com on 2018. 11. 27.
 */
public class Problem {
    static int solution(List<Integer> russia, List<Integer> korea) {
        int win = 0;

        int r = 0, k = 0;
        int end = korea.size();
        while(true) {
            if(russia.get(r) <= korea.get(k)) {
                win++;
                r++;
            }
            k++;

            if(k == end) {
                break;
            }
        }

        return win;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int c, n;
        List<Integer> korea, russia;
        c = in.nextInt();

        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            n = in.nextInt();
            russia = new ArrayList<>();
            korea = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                russia.add(in.nextInt());
            }

            for (int j = 0; j < n; j++) {
                korea.add(in.nextInt());
            }

            answers.add(
                    solution(
                            russia.stream()
                                    .sorted()
                                    .collect(Collectors.toList()),
                            korea.stream()
                                    .sorted()
                                    .collect(Collectors.toList())
                    )
            );
        }
        answers.forEach(System.out::println);
    }
}
