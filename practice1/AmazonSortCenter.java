import java.util.*;


class AmazonSortCenter {
  static final int SAFETY_SPACE = 30;
  static final int[] NOT_FOUND = new int[]{-1,-1};

  static int[] pickPackages(int[] packages, int space) {
    if (packages == null || packages.length == 0 || space <= SAFETY_SPACE)
      return NOT_FOUND;

    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    Queue<int[]> pq = new PriorityQueue<>(packageComparator);

    final int target = space - SAFETY_SPACE;
    int max = 0;

    for (int i = 0; i < packages.length; i++) {
      int diff = target - packages[i];
      Integer partner = treeMap.floorKey(diff);
      if (partner != null) {
        int sum = partner + packages[i];
        if (sum == max) {
          // This pair is as large as some previously encountered pair(s).
          // The one with the largest package will be chosen.
          pq.offer(new int[]{treeMap.get(partner), i, partner, packages[i]});
        } else if (sum > max) {
          // Found largest package pair without any equal.
          // Eliminate all the max package pairs seen previously.
          max = sum;
          pq.clear();
          pq.offer(new int[]{treeMap.get(partner), i, partner, packages[i]});
        }
      }
      treeMap.put(packages[i], i);
    }
    return !pq.isEmpty() ? Arrays.copyOfRange(pq.peek(), 0, 2) : NOT_FOUND;
  }

  /**
   * Sorts in descending order based on the largest item found in two pairs
   */
  static Comparator<int[]> packageComparator = (a,b) -> {
    int result = 0;
    for (int i = 2; i < b.length; i++) {
      for (int j = 2; j < a.length; j++) {
        result = b[i]-a[j];
        if (result != 0)
          return result;
      }
    }
    return result;
  };

  public static void main(String[] args) {
    //int[] packages = {1, 10, 25, 35, 60};
    //int[] packages = {20, 50, 40, 25, 30, 10};
    int[] packages = {50, 20, 10, 40};
    int[] pair = AmazonSortCenter.pickPackages(packages, 90);
    System.out.println(String.format("[%d, %d]", pair[0], pair[1]));
  }
}

