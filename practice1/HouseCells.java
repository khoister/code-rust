import java.util.*;
import java.util.stream.*;

class HouseCells {

  public static int[] afterDays(int[] cells, int days) {
    if (cells == null || cells.length == 0 || days <= 0)
      return cells;

    Map<String, Integer> history = new HashMap<>();

    int[] copy = Arrays.copyOf(cells, cells.length);
    for (int i = days; i > 0; i--) {
      String state = asString(cells);
      if (history.containsKey(state)) {
        int interval = i - history.get(state);
        i = i % interval;
      }
      history.put(state, i);

      if (i == 0)
        break;

      // Calculate the next state of the cells
      for (int j = 0; j < cells.length; j++)
        copy[j] = calcState(cells, j);

      // Swap array references so that 'cell' is the current state of the cells
      int[] tmp = copy;
      copy = cells;
      cells = tmp;
    }
    return cells;
  }

  private static String asString(int[] cells) {
    return Arrays.stream(cells)
      .mapToObj(String::valueOf)
      .collect(Collectors.joining(""));
  }

  private static int calcState(int[] cells, int i) {
    int left = i < 1 ? 0 : cells[i-1];
    int right = i >= cells.length-1 ? 0 : cells[i+1];
    return left^right;
  }

  private static void print(int[] cells) {
    for (int cell : cells)
      System.out.print(cell + " ");
    System.out.println();
  }

  public static void main(String[] args) {
    int days = 1000000000;
    int[] cells = new int[]{1, 0, 0, 0, 0, 1, 0, 0};
    HouseCells.print(HouseCells.afterDays(cells, days));
  }
}

