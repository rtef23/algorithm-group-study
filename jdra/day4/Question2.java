package day4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Question2 {

  private static final String YES = "YES";
  private static final String NO = "NO";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int testCase = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < testCase; i++) {
      int n = Integer.parseInt(scanner.nextLine().trim());

      int[][] map = new int[n][n];

      for (int j = 0; j < n; j++) {
        String[] values = scanner.nextLine().split(" ");

        for (int k = 0; k < n; k++) {
          map[j][k] = Integer.parseInt(values[k].trim());
        }
      }

      System.out.println(F(map, n));
    }
  }

  private static String F(int[][] map, int n) {
    Queue<Point> points = new LinkedList<>();

    points.add(new Point(0, 0));

    return go(map, n, points);
  }

  private static String go(int[][] map, int n, Queue<Point> queue) {
    if (queue.isEmpty()) {
      return NO;
    }

    Point point = queue.poll();

    if (map[point.getX()][point.getY()] == 0) {
      return YES;
    }

    int newX = point.getX() + map[point.getX()][point.getY()];
    int newY = point.getY() + map[point.getX()][point.getY()];

    if (newX < n) {
      queue.add(new Point(newX, point.getY()));
    }

    if (newY < n) {
      queue.add(new Point(point.getX(), newY));
    }

    return go(map, n, queue);
  }

  private static class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }
  }
}
