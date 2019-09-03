import java.util.*;
import java.util.stream.*;

class PrisonCells {

  static int[] afterDays(int[] cells, int N) {
    if (N <= 0 || cells == null || cells.length == 0)
      return cells;

    Map<String, Integer> map = new HashMap<>();
    int[] copy = new int[cells.length];

    for (int i = N; i > 0; i--) {
      String state = asString(cells);
      if (map.containsKey(state)) {
        int interval = map.get(state) - i;
        System.out.println(String.format("Skipping %d days [interval %d]", i - (i % interval), interval));
        i = i % interval;
      }
      map.put(state, i);

      if (i == 0)
        break;

      // Calculate the next state of the cells
      for (int j = 0; j < copy.length; j++)
        copy[j] = calc(cells, j);

      // Swap array references so 'cell' has the current state
      int[] tmp = copy;
      copy = cells;
      cells = tmp;
    }
    return cells;
  }

  static String asString(int[] cells) {
    return Arrays.stream(cells)
      .mapToObj(String::valueOf)
      .collect(Collectors.joining(""));
  }

  static int calc(int[] a, int i) {
    if (i-1 < 0 || i+1 >= a.length)
      return 0;
    return ~(a[i-1]^a[i+1]) & 0x1;
  }

  static void print(int[] a) {
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }

  public static void main(String[] args) {
    //int N = 1000000000;
    //int cells[] = {1,0,0,1,0,0,1,0};
    int N = 85;
    int cells[] = {0,1,0,1,1,0,0,1};
    int answer[] = PrisonCells.afterDays(cells, N);
    print(answer);
  }
}

