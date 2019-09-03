import java.util.*;
import java.util.stream.*;

class AmazonSortCenter {

  static int[] loadPackages(int[] a, int truckSpace) {
    if (a == null || a.length < 2)
      return new int[]{-1,-1};

    int target = truckSpace - 30;
    int max = 0;
    int pkg1 = -1;
    int pkg2 = -1;

    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < a.length; i++) {
      int diff = target - a[i];
      Integer partner = map.floorKey(diff);
      if (partner != null) {
        int sum = a[i] + partner;
        if (sum >= max) {
          if (pkg1 == -1 && pkg2 == -1 || isGreaterPair(partner, a[i], a[pkg1], a[pkg2])) {
            max = sum;
            pkg1 = map.get(partner);
            pkg2 = i;
            System.out.println(String.format("Max pair: (%d,%d) = %d", pkg1, pkg2, sum));
          }
        }
      }
      map.put(a[i], i);
    }
    return new int[]{pkg1, pkg2};
  }

  static boolean isGreaterPair(int a, int b, int x, int y) {
    return (a > x && a > y) || (b > x && b > y);
  }

  public static void main(String[] args) {
    //int[] a = {50, 1, 10, 25, 35, 60};
    int[] a = {1, 10, 25, 35, 60, 50};
    int[] packages = AmazonSortCenter.loadPackages(a, 90);
    System.out.println(Arrays.stream(packages).boxed().collect(Collectors.toList()));
  }
}

