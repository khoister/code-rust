import java.util.*;

class AmazonSortCenter2 {

  static final int SAFETY_SPACE = 30;
  static final int[] NOT_FOUND = new int[]{-1,-1};

  static int[] pickPackages(int[] packages, int space) {
    if (packages == null || packages.length < 2 || space <= SAFETY_SPACE)
      return NOT_FOUND;

    final int target = space - SAFETY_SPACE;
    final int[] results = new int[]{-1,-1};
    final Map<Integer, Integer> map = new HashMap<>();
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < packages.length; i++) {
      final int diff = target - packages[i];
      if (map.containsKey(diff)) {
        final int partner = map.get(diff);
        if (packages[i] > max || packages[partner] > max) {
          results[0] = partner;
          results[1] = i;
          max = Math.max(packages[i], packages[partner]);
        }
      }
      map.put(packages[i], i);
    }
    return results;
  }

  public static void main(String[] args) {
    int[] a = {20, 50, 40, 25, 30, 10};
    int[] pair = AmazonSortCenter2.pickPackages(a, 90);
    System.out.println(String.format("[%d, %d]", pair[0], pair[1]));
  }
}

