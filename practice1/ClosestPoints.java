import java.util.*;


class ClosestPoints {

  static int[][] kClosest(int[][] points, int K) {
    // No points available
    if (points == null || points.length == 0 || points[0].length == 0)
      return new int[][]{};

    // Invalid K
    if (K < 1 || K > points.length)
      return new int[][]{};

    // Maintain an array of distances
    int[] distances = new int[points.length];
    for (int i = 0; i < points.length; i++)
      distances[i] = distance(0, 0, points[i][0], points[i][1]);

    return Arrays.copyOfRange(points, 0, select(distances, points, K));
  }

  // Calculate relative distance, i.e. skip sqrt calculation
  static int distance(int x1, int y1, int x2, int y2) {
    int deltaX = x1-x2;
    int deltaY = y1-y2;
    return deltaX*deltaX + deltaY*deltaY;
  }

  static int select(int[] distance, int[][] points, int k) {
    int l = 0;
    int r = distance.length-1;

    while (true) {
      if (l == r)
        return l;

      int pivot = partition(distance, points, l, r);
      if (pivot == k)
        return pivot;

      if (pivot < k)
        l = pivot+1;
      else
        r = pivot-1;
    }
  }

  static int partition(int[] distance, int[][] points, int left, int right) {
    int x = distance[left];
    int l = left-1;
    int r = right+1;

    while (true) {
      do ++l; while (distance[l] < x);
      do --r; while (distance[r] > x);

      if (l < r)
        swap(distance, points, l, r);
      else
        return r;
    }
  }

  static void swap(int[] distance, int[][] points, int i, int j) {
    swapDistance(distance, i, j);
    swapPoints(points, i, j);
  }

  static void swapDistance(int[] d, int i, int j) {
    int tmp = d[i];
    d[i] = d[j];
    d[j] = tmp;
  }

  static void swapPoints(int[][] p, int i, int j) {
    int len = p[i].length;
    int[] tmp = new int[len];
    System.arraycopy(p[i], 0, tmp, 0, len);
    System.arraycopy(p[j], 0, p[i], 0, len);
    System.arraycopy(tmp, 0, p[j], 0, len);
  }

  public static void main(String[] args) {
    int[][] points = {
      {3,3},
      {5,-1},
      {-2,4}
    };
    int[][] results = ClosestPoints.kClosest(points, 2);
    for (int[] point : results) {
      System.out.println(String.format("[%d,%d]", point[0], point[1]));
    }
  }
}

