import java.util.*;

public class Main {
    private static Map<Integer, Integer> map = new HashMap<>();
    private static int DEVIDE_NUM = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();

        List<Integer> caseInputs = new ArrayList<>();

        for (int i = 0; i < caseCount; i++) {
            caseInputs.add(scanner.nextInt());
        }

        map.put(1, 1);
        map.put(2, 2);

        for (int caseInput : caseInputs) {
            if (map.containsKey(caseInput)) {
                System.out.println(map.get(caseInput));

            } else {
                System.out.println(getResult(3, caseInput));
            }

        }
    }

    public static int getResult(int caseInput, int breakPoint) {
        if (caseInput == breakPoint) {
            return (map.get(caseInput - 2) + map.get(caseInput - 1)) % DEVIDE_NUM;

        } else {
            int result = (map.get(caseInput - 2) + map.get(caseInput - 1)) % DEVIDE_NUM;

            map.put(caseInput, result);

            return getResult(caseInput + 1, breakPoint);
        }
    }
}
