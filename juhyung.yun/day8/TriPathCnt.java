
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TriPathCnt {
	static List<List<Point>> triangle = new ArrayList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int c, n;
		c = in.nextInt();
		for (int i = 0; i < c; i++) {
			n = in.nextInt();
			triangle.clear();
			for (int j = 0; j < n; j++) {
				List<Point> points = new ArrayList<>();
				triangle.add(points);
				for (int k = 0; k < j + 1; k++) {
					Point point = new Point();
					point.setValue(in.nextInt());
					points.add(point);
				}
			}
			solution(n);

			int max = triangle.get(n - 1).stream()
							  .map(Point::getMax)
							  .max(Integer::compareTo)
							  .get();

			System.out.println(
				triangle.get(n - 1).stream()
						.filter(points -> points.getMax() == max)
						.map(Point::getCases)
						.reduce(0, (n1, n2) -> n1 + n2)
			);

		}
	}

	static void solution(int n) {
		triangle.get(0).get(0).setMax(triangle.get(0).get(0).getValue());
		triangle.get(0).get(0).setCases(1);

		for (int i = 1; i < n; i++) {
			maxSum(i);
		}
	}

	static void maxSum(int line) {
		Point target;
		target = triangle.get(line).get(0);
		target.setMax(target.getValue() + triangle.get(line - 1).get(0).getMax());
		target.setCases(triangle.get(line - 1).get(0).getCases());
		for (int i = 1; i < line; i++) {
			target = triangle.get(line).get(i);
			Point p1 = triangle.get(line - 1).get(i - 1);
			Point p2 = triangle.get(line - 1).get(i);
			if (p1.getMax() > p2.getMax()) {
				target.setMax(target.getValue() + p1.getMax());
				target.setCases(p1.getCases());
			} else if (p1.getMax() < p2.getMax()) {
				target.setMax(target.getValue() + p2.getMax());
				target.setCases(p2.getCases());
			} else {
				target.setMax(target.getValue() + p1.getMax());
				target.setCases(p1.getCases() + p2.getCases());
			}
		}
		target = triangle.get(line).get(line);
		target.setMax(target.getValue() + triangle.get(line - 1).get(line - 1).getMax());
		target.setCases(triangle.get(line - 1).get(line - 1).getCases());
	}
  
	static class Point {
		private int value;
		private int max;
		private int cases = 1;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public int getCases() {
			return cases;
		}

		public void setCases(int cases) {
			this.cases = cases;
		}


	}
}
