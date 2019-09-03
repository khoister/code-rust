import java.util.*;

class TreasureIsland1 {

  private static final char DANGER = 'D';
  private static final char OCEAN = 'O';
  private static final char TREASURE = 'X';

  static class Point {
    int row;
    int col;

    Point(int r, int c) {
      row = r;
      col = c;
    }

    @Override
    public String toString() {
      return String.format("(%d,%d)", row, col);
    }
  }

  enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1);

    final Point coord;

    Direction(int row, int col) {
      coord = new Point(row, col);
    }

    int row() {
      return coord.row;
    }

    int col() {
      return coord.col;
    }
  }

  static int findMinStepsToTreasure(char[][] grid) {
    if (grid == null)
      return 0;

    int steps = 0;
    int examined = 0;

    final Queue<Point> q = new LinkedList<>();
    q.add(new Point(0, 0));
    grid[0][0] = DANGER;

    while (!q.isEmpty()) {
      ++steps;

      for (int size = q.size(); size > 0; size--) {
        final Point p = q.remove();
        for (final Direction dir : Direction.values()) {
          int r = p.row + dir.row();
          int c = p.col + dir.col();

          ++examined;
          if (canSailTo(grid, r, c)) {
            if (foundTreasure(grid, r, c)) {
              System.out.println("Examined: " + examined);
              return steps;
            }
            grid[r][c] = DANGER;
            q.add(new Point(r, c));
          }
        }
      }
    }
    return steps;
  }

  private static boolean canSailTo(char[][] grid, int row, int col) {
    return (row >= 0 && row < grid.length)
        && (col >= 0 && col < grid[0].length)
        && (grid[row][col] != DANGER);
  }

  private static boolean foundTreasure(char[][] grid, int row, int col) {
    return grid[row][col] == TREASURE;
  }

  public static void main(String[] args) {
    final char[][] grid = {
      {'O', 'O', 'O', 'O'},
      {'D', 'O', 'D', 'O'},
      {'O', 'O', 'O', 'O'},
      {'X', 'D', 'D', 'O'}
    };
    System.out.println(TreasureIsland1.findMinStepsToTreasure(grid));
  }
}

