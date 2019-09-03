import java.util.*;
import java.util.stream.*;

class MemoryTasks {

  /**
   * Using SimpleEntry as a tuple pair to inherit equals/hashcode
   * Key field holds the task's memory usage
   * Value field holds the index of the task in the original order
   */
  static class Tuple extends AbstractMap.SimpleEntry<Integer,Integer> {
    Tuple(Integer k, Integer v) { super(k, v); }

    @Override
    public String toString() {
      return String.format("(%d,%d)", getKey(), getValue());
    }
  }

  static List<Tuple> pairTasks(int[] fgTasks, int[] bgTasks, int K) {
    final List<Tuple> results = new ArrayList<>();
    final ArrayList<Tuple> fg = toSortedList(fgTasks);
    final ArrayList<Tuple> bg = toSortedList(bgTasks);

    int fgLow = 0;
    int bgHigh = bg.size()-1;
    int maxSum = 0;

    while (fgLow < fg.size() && bgHigh >= 0) {
      final Tuple low = fg.get(fgLow);
      final Tuple high = bg.get(bgHigh);

      final int sum = low.getKey() + high.getKey();
      if (sum >= maxSum && sum <= K) {
        maxSum = sum;
        results.add(new Tuple(low.getValue(), high.getValue()));
      }

      if (sum > K)
        --bgHigh;
      else
        ++fgLow;
    }
    final int sum = maxSum;
    return results.stream()
      .filter(t -> fgTasks[t.getKey()] + bgTasks[t.getValue()] == sum)
      .collect(Collectors.toList());
  }

  static ArrayList<Tuple> toSortedList(int[] a) {
    ArrayList<Tuple> list = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      list.add(new Tuple(a[i], i));
    }
    Collections.sort(list, Comparator.comparing(Tuple::getKey));
    return list;
  }

  public static void main(String[] args) {
    System.out.println(MemoryTasks.pairTasks(new int[]{1, 7, 2, 4, 5, 6}, new int[]{3, 1, 2}, 10));
    System.out.println(MemoryTasks.pairTasks(new int[]{1, 7, 2, 4, 5, 6}, new int[]{3, 1, 2}, 6));
  }

}

