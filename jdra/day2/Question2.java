package day2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Question2 {

  /*
   **  *   **   *
   *   **   *  **

   * */
  private static final List<List<Point>> BLOCKS = Arrays.asList(
      Arrays.asList(
          new Point(0, 0),
          new Point(1, 0),
          new Point(0, 1)
      ),
      Arrays.asList(
          new Point(0, 0),
          new Point(0, 1),
          new Point(1, 1)
      ),
      Arrays.asList(
          new Point(0, 0),
          new Point(1, 0),
          new Point(1, 1)
      ),
      Arrays.asList(
          new Point(0, 0),
          new Point(0, 1),
          new Point(-1, 1)
      ));

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int t = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < t; i++) {
      String line = scanner.nextLine().trim();

      String[] splitString = line.split(" ");

      int h = Integer.parseInt(splitString[0]);
      int w = Integer.parseInt(splitString[1]);

      boolean[][] map = new boolean[h][w];

      for (int j = 0; j < h; j++) {
        line = scanner.nextLine().trim();

        for (int k = 0; k < w; k++) {
          map[j][k] = ('.' == line.charAt(k));
        }
      }

      System.out.println(Question2.solve(map, h, w));
    }
  }

  public static int solve(boolean[][] map, int h, int w) {
    int result = 0;
    int leftTopX = 0;
    int leftTopY = 0;
    boolean isWhiteExist = false;

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        if (map[i][j]) {
          leftTopX = j;
          leftTopY = i;

          isWhiteExist = true;
          i = h;
          j = w;
        }
      }
    }

    if (!isWhiteExist) {
      return 1;
    }

    for (List<Point> targetBlock : BLOCKS) {
      if (isBlockFill(targetBlock, map, h, w, leftTopX, leftTopY)) {
        set(targetBlock, map, leftTopX, leftTopY, false);

        result += solve(map, h, w);

        set(targetBlock, map, leftTopX, leftTopY, true);
      }
    }

    return result;
  }

  private static boolean isBlockFill(List<Point> block, boolean[][] map, int h, int w, int curX,
      int curY) {
    for (Point point : block) {
      int x = curX + point.getX();
      int y = curY + point.getY();

      if (x < 0 || x >= w) {
        return false;
      }

      if (y < 0 || y >= h) {
        return false;
      }

      if (!map[y][x]) {
        return false;
      }
    }

    return true;
  }

  private static void set(List<Point> block, boolean[][] map, int curX, int curY, boolean value) {
    for (Point point : block) {
      map[curY + point.getY()][curX + point.getX()] = value;
    }
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
