import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();

		Scanner scanner = new Scanner(System.in);

		int tc = scanner.nextInt();
		int[] cache = new int[500];

		while(tc-- > 0) {
			int len = scanner.nextInt();
			int[] array = new int[len];

			main.init(cache);

			for(int i=0; i<len; i++) {
				array[i] = scanner.nextInt();
			}

			System.out.println(main.findMaxLen(array, cache));
		}
	}

	private void init(int[] cache) {
		int size = cache.length;

		for(int i=0; i<size; i++) {
			cache[i] = 0;
		}
	}

	private int findMaxLen(int[] array, int[] cache) {
		int maxIndex = 0;

		for(int ele : array) {
			for(int i=0; i<maxIndex+1; i++) {
				if(ele > cache[i]) {
					maxIndex = put(cache, i+1, ele) ? i+1 : maxIndex;
				}
			}
		}

		return maxIndex;
	}

	private boolean put(int[] cache, int index, int value) {
		boolean isMax = cache[index] == 0;

		if(isMax || cache[index] > value) {
			cache[index] = value;
		}

		return isMax;
	}
}
