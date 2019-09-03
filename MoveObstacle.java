import java.util.*;

class MoveObstacle {

  private static final int NOT_FOUND = -1;
  private static final int TRENCH = 0;
  private static final int FLAT = 1;
  private static final int OBSTACLE = 9;

  static class Point {
    int row, col;

    Point(int r, int c) {
      row = r;
      col = c;
    }
  }

  enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    final Point coord;

    Direction(int row, int col) {
      coord = new Point(row, col);
    }

    int row() { return coord.row; }
    int col() { return coord.col; }
  }

  static int distanceToObstacle(int[][] lot) {
    int steps = 0;

    if (lot == null)
      return steps;

    final Queue<Point> q = new LinkedList<>();
    q.add(new Point(0,0));
    visit(lot, 0, 0);

    while (!q.isEmpty()) {
      ++steps;

      for (int size = q.size(); size > 0; size--) {
        final Point p = q.remove();

        for (final Direction dir : Direction.values()) {
          int r = p.row + dir.row();
          int c = p.col + dir.col();

          if (canMove(lot, r, c)) {
            if (foundObstacle(lot, r, c)) {
              return steps;
            }
            visit(lot, r, c);
            q.add(new Point(r,c));
          }
        }
      }
    }
    return NOT_FOUND;
  }

  static void visit(int[][] lot, int row, int col) {
    lot[row][col] = TRENCH;
  }

  static boolean canMove(int[][] lot, int row, int col) {
    return (row >= 0 && row < lot.length)
        && (col >= 0 && col < lot[0].length)
        && (lot[row][col] != TRENCH);
  }

  static boolean foundObstacle(int[][] lot, int row, int col) {
    return lot[row][col] == OBSTACLE;
  }

  public static void main(String[] args) {
    int[][] lot = {
      {1, 0, 0},
      {1, 0, 0},
      {1, 9, 1},
    };
    System.out.println(MoveObstacle.distanceToObstacle(lot));
  }
}

