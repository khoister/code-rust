import java.util.*;
import java.util.stream.*;

class NearestPostOffices {

  static class Pair {
    final int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return String.format("(%d,%d)", x, y);
    }
  }

  static List<Pair> findNearest(int[] you, int[][] postOffices, int k) {
    List<Pair> closestOffices = new ArrayList<>();
    int[][] distances = calcDistances(you, postOffices);
    while (--k >= 0) {
      System.out.println("Calculating select for k = " + k);
      int[] office = select(distances, k);
      closestOffices.add(new Pair(office[1], office[2]));
      System.out.println("Closest offices: " + closestOffices);
    }
    return closestOffices;
  }

  private static int[] select(int[][] a, int k) {
    int left = 0;
    int right = a.length-1;
    while (true) {
      if (left == right)
        return a[left];

      int p = partition(a, left, right);
      System.out.println(String.format("Calling partition... left = %d, right = %d, p = %d, k = %d", left, right, p, k));
      if (p == k)
        return a[k];
      else if (p < k)
        left = p+1;
      else
        right = p-1;
    }
  }

  private static int partition(int[][] a, int left, int right) {
    int pivot = a[left][0];
    int l = left-1;
    int r = right+1;

    while (true) {
      do ++l; while (a[l][0] < pivot);
      do --r; while (a[r][0] > pivot);

      if (l < r)
        swap(a, l, r);
      else
        return r;
    }
  }

  private static void swap(int[][]a, int i, int j) {
    int len = a[i].length;
    int[] tmp = new int[len];
    System.arraycopy(a[i], 0, tmp, 0, len);  // tmp = a[i]
    System.arraycopy(a[j], 0, a[i], 0, len); // a[i] = a[j]
    System.arraycopy(tmp, 0, a[j], 0, len);  // a[j] = tmp
  }

  private static int[][] calcDistances(int[] you, int[][] postOffices) {
    int[][] distances = new int[postOffices.length][3];
    for (int i = 0; i < postOffices.length; i++) {
      distances[i][0] = distance(you[0], you[1], postOffices[i][0], postOffices[i][1]);
      distances[i][1] = postOffices[i][0]; // x coordinate
      distances[i][2] = postOffices[i][1]; // y coordinate
      System.out.println(String.format("(%d, %d, %d)", distances[i][0], distances[i][1], distances[i][2]));
    }
    return distances;
  }

  // Calculate the relative distances (skipping sqrt calculation)
  static int distance(int x1, int y1, int x2, int y2) {
    int deltaX = x1 - x2;
    int deltaY = y1 - y2;
    return deltaX*deltaX + deltaY*deltaY;
  }

  public static void main(String[] args) {
    int[] you = new int[]{0,0};
    int[][] postOffices = new int[][] { {-16, 5}, {-1, 2}, {4, 3}, {10, -2}, {0, 3}, {-5, -9} };
    int k = 3;
    System.out.println(NearestPostOffices.findNearest(you, postOffices, k));
  }
}

