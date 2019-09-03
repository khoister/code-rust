import java.util.*;

class TreasureIsland {
  final static int DANGER = 'D';
  final static int OCEAN = 'O';
  final static int TREASURE = 'X';
  final static int NOT_FOUND = -1;

  static class Pair {
    final int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    int getRow() { return row; }
    int getCol() { return col; }
  }

  enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    WEST(0, -1),
    EAST(0, 1);

    final int row, col;

    Direction(int r, int c) {
      this.row = r;
      this.col = c;
    }
    int getRow() { return row; }
    int getCol() { return col; }
  }

  static int findTreasure(int[][] map) {
    if (map == null || map.length == 0 || map[0].length == 0)
      return NOT_FOUND;

    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(0,0));
    visited(map, 0, 0);

    int steps = 0;
    while (!q.isEmpty()) {
      ++steps;

      for (int size = q.size(); size > 0; size--) {
        Pair p = q.remove();
        for (Direction dir : Direction.values()) {
          int r = p.getRow() + dir.getRow();
          int c = p.getCol() + dir.getCol();

          if (canSail(map, r, c)) {
            if (isTreasure(map, r, c)) {
              return steps;
            }
            q.add(new Pair(r,c));
            visited(map, r, c);
          }
        }
      }
    }
    return NOT_FOUND;
  }

  /**
   * Mark the position on the map as already been visited
   */
  static void visited(int[][] map, int r, int c) {
    map[r][c] = DANGER;
  }

  /**
   * Determines is coordinate on the map is safe to sail to
   */
  static boolean canSail(int[][] map, int r, int c) {
    return (r >= 0 && r < map.length)
        && (c >= 0 && c < map[0].length)
        && (map[r][c] != DANGER);
  }

  /**
   * Determines if coordinate on the map is the treasure
   */
  static boolean isTreasure(int[][] map, int r, int c) {
    return map[r][c] == TREASURE;
  }

  public static void main(String[] args) {
    int[][] map = {
      {'O', 'O', 'O', 'O'},
      {'D', 'O', 'D', 'O'},
      {'O', 'O', 'O', 'O'},
      {'X', 'D', 'D', 'O'}
    };
    System.out.println(TreasureIsland.findTreasure(map));
  }
}

