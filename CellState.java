import java.lang.*;
import java.util.*;
import java.util.stream.*;

class CellState {

  public static List<Integer> setCellState(int[] states, int days) {

    // Invalid cell states
    if (states == null || states.length == 0)
      return new LinkedList<>();

    // Invalid days
    if (days < 0)
      return new LinkedList<>();

    // Make copy of original cell states
    int[] current = new int[states.length];
    System.arraycopy(states, 0, current, 0, current.length);

    // New cell states
    int[] next = new int[current.length];

    for (int i = 0; i < days; i++) {
      for (int j = 0; j < current.length; j++) {
        next[j] = switcher(current, j);
      }
      int[] tmp = current;
      current = next;
      next = tmp;
    }

    return Arrays.stream(current)
      .boxed()
      .collect(Collectors.toList());
  }

  private static int switcher(int[] a, int i) {
    int l = i <= 0 ? 0 : a[i-1];
    int r = i >= a.length-1 ? 0 : a[i+1];
    return l^r;
  }

  public static void main(String[] args) {
    int[] states1 = new int[]{1, 0, 0, 0, 0, 1, 0, 0};
    System.out.println(CellState.setCellState(states1, 0));

    int[] states2 = new int[]{1, 1, 1, 0, 1, 1, 1, 1};
    System.out.println(CellState.setCellState(states2, 2));
  }
}

