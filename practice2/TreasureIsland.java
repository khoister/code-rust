import java.util.*;

class TreasureIsland {

  static final char DANGER = 'D';
  static final char OCEAN = 'O';
  static final char TREASURE = 'X';
  static final char VISITED = 'V';
  static final int  NOT_FOUND = -1;

  static class Coord {
    final int row, col;
    Coord(int r, int c) {
      row = r;
      col = c;
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
      row = r;
      col = c;
    }
    int getRow() { return row; }
    int getCol() { return col; }
  }

  static int findTreasure(int[][] map) {
    if (map == null || map.length == 0 || map[0].length == 0)
      return NOT_FOUND;

    Queue<Coord> q = new LinkedList<>();
    q.add(new Coord(0,0));
    map[0][0] = VISITED;

    int steps = 0;
    while (!q.isEmpty()) {
      ++steps;

      for (int size = q.size(); size > 0; size--) {
        Coord coord = q.remove();

        // Iterate through every direction from this point
        for (Direction dir : Direction.values()) {
          int r = coord.getRow() + dir.getRow();
          int c = coord.getCol() + dir.getCol();

          // Check each direction for possible next destination
          if (canSail(map, r, c)) {
            if (isTreasure(map, r, c))
              return steps;

            q.add(new Coord(r, c));
            map[r][c] = VISITED;
          }
        }
      }
    }
    return NOT_FOUND;
  }

  static boolean canSail(int[][] map, int row, int col) {
    return row >= 0 && row < map.length
        && col >= 0 && col < map[0].length
        && map[row][col] != DANGER
        && map[row][col] != VISITED;
  }

  static boolean isTreasure(int[][] map, int row, int col) {
    return map[row][col] == TREASURE;
  }

  public static void main(String[] args) {
    int[][] map = {
      {'O', 'O', 'O', 'O'},
      {'D', 'O', 'D', 'O'},
      {'O', 'O', 'O', 'O'},
      {'X', 'D', 'D', 'O'},
    };
    System.out.println(TreasureIsland.findTreasure(map));
  }
}

