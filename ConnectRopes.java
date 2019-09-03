import java.util.*;

class ConnectRopes {

  static int calculateCost(int[] a) {
    int cost = 0;

    if (a == null || a.length == 0)
      return cost;

    final PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int i : a)
      q.add(i);

    while (q.size() > 1) {
      int first = q.poll();
      int second = q.poll();
      int sum = first + second;
      q.offer(sum);
      cost += sum;
    }
    return cost;
  }

  public static void main(String[] args) {
    System.out.println(ConnectRopes.calculateCost(new int[]{20, 4, 8, 2}));
    System.out.println(ConnectRopes.calculateCost(new int[]{1, 2, 5, 10, 35, 89}));
    System.out.println(ConnectRopes.calculateCost(new int[]{2, 2, 3, 3}));
  }
}

