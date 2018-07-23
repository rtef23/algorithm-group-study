package day1;

import java.util.Arrays;

public class Question2 {

  private static final int X = 0;
  private static final int Y = 1;
  private static final int NOT_VISITED = -1;
  private static final int[] dx = {-1, 0, 1, 0};
  private static final int[] dy = {0, -1, 0, 1};

  public static int solve(boolean[][] map, int[] start, int[] end) {
    int xLength = map[0].length;
    int yLength = map.length;

    int[][] memo = new int[yLength][xLength];

    //initialize
    for (int index = 0; index < yLength; index++) {
      Arrays.fill(memo[index], NOT_VISITED);
    }

    memo[start[Y]][start[X]] = 0;

    //solve
    return go(map, 0, memo, end);
  }

  private static int go(boolean[][] map, int step, int[][] memo, int[] end) {
    if (memo[end[Y]][end[X]] != NOT_VISITED) {
      return memo[end[Y]][end[X]];
    }

    boolean isMoved = false;

    int xLength = memo[0].length;
    int yLength = memo.length;

    for (int y = 0; y < yLength; y++) {
      for (int x = 0; x < xLength; x++) {
        if (memo[y][x] == step) {
          for (int i = 0; i < 4; i++) {
            int dX = x + dx[i];
            int dY = y + dy[i];

            if (dY < 0 || dY >= yLength) {
              continue;
            }

            if (dX < 0 || dX >= xLength) {
              continue;
            }

            if (!map[dY][dX] || memo[dY][dX] != NOT_VISITED) {
              continue;
            }

            isMoved = true;
            memo[dY][dX] = step + 1;
          }
        }
      }
    }

    if (!isMoved) {
      return -1;
    }

    return go(map, step + 1, memo, end);
  }
}
