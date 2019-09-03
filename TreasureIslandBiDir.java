import java.util.*;

class TreasureIslandBiDir {

  private static final char DANGER = 'D';
  private static final char OCEAN = 'O';
  private static final char TREASURE = 'X';
  private static final int NOT_FOUND = 0;

  static class Point {
    final int row, col;

    Point(int r, int c) {
      row = r;
      col = c;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + row;
      result = prime * result + col;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Point other = (Point) obj;
      if (row != other.row)
        return false;
      if (col != other.col)
        return false;
      return true;
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

    int row() { return coord.row; }
    int col() { return coord.col; }
  }

  static int stepsToFindTreasure(char[][] grid, Point start, Point treasure) {
    int steps = 0;
    int examined = 0;

    if (grid == null)
      return steps;

    Set<Point> s1 = new HashSet<>();
    Set<Point> s2 = new HashSet<>();

    // Add starting coord
    s1.add(start);
    grid[start.row][start.col] = DANGER;

    // Add treasure coord
    s2.add(treasure);

    while (!s1.isEmpty() && !s2.isEmpty()) {
      ++steps;

      // Always work with the smaller set
      if (s1.size() > s2.size()) {
        Set<Point> tmp = s1;
        s1 = s2;
        s2 = tmp;
      }

      Set<Point> set = new HashSet<>();

      for (final Point p : s1) {
        for (final Direction dir : Direction.values()) {
          int r = p.row + dir.row();
          int c = p.col + dir.col();

          ++examined;
          if (s2.contains(new Point(r, c))) {
            System.out.println("Examined: " + examined);
            return steps;
          }
          if (canSailTo(grid, r, c)) {
            grid[r][c] = DANGER;
            set.add(new Point(r, c));
          }
        }
      }
      s1 = set;
    }
    return NOT_FOUND;
  }

  static boolean canSailTo(char[][] grid, int row, int col) {
    return (row >= 0 && row < grid.length)
        && (col >= 0 && col < grid[0].length)
        && (grid[row][col] != DANGER);
  }

  public static void main(String[] args) {
    char[][] grid = {
      {'O', 'O', 'O', 'O'},
      {'D', 'O', 'D', 'O'},
      {'O', 'O', 'O', 'O'},
      {'X', 'D', 'D', 'O'}
    };
    System.out.println(TreasureIslandBiDir.stepsToFindTreasure(grid, new Point(0,0), new Point(3, 0)));
  }
}

