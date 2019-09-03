import java.util.*;
import java.util.stream.*;


class OptimalAircraftUtilization {

  static class Pair {
    final int first, second;
    final int distance;

    Pair(int first, int second, int distance) {
      this.first = first;
      this.second = second;
      this.distance = distance;
    }

    public String toString() {
      return String.format("(%d,%d)", first, second);
    }
  }

  static List<Pair> optimize(int[][] forwardRoutes, int[][] returnRoutes, int limit) {
    TreeMap<Integer,Integer> treeMap = new TreeMap<>();
    for (int[] route : returnRoutes) {
      treeMap.put(route[1], route[0]);
    }

    List<Pair> results = new ArrayList<>();

    int maxDist = -1;
    for (int[] forwardRoute : forwardRoutes) {
      int diff = limit - forwardRoute[1];
      Integer returnDistance = treeMap.floorKey(diff);
      if (returnDistance == null)
        continue;

      int roundTrip = forwardRoute[1] + returnDistance;
      if (roundTrip >= maxDist) {
        maxDist = roundTrip;
        results.add(new Pair(forwardRoute[0], treeMap.get(returnDistance), roundTrip));
      }
    }
    final int optimalDistance = maxDist;
    return results.stream()
      .filter(pair -> pair.distance == optimalDistance)
      .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    int limit = 10000;
    int[][] forwardRoutes = {{1,3000},{2,5000},{3,7000},{4,10000}};
    int[][] returnRoutes = {{1,2000},{2,3000},{3,4000},{4,5000}};
    System.out.println(OptimalAircraftUtilization.optimize(forwardRoutes, returnRoutes, limit));
  }
}

