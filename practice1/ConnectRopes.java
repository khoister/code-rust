import java.util.*;

class ConnectRopes {

  static int minCost(int[] ropes) {
    // Not enough ropes to connect
    if (ropes == null || ropes.length < 2)
      return 0;

    final Queue<Integer> pq = new PriorityQueue<>();
    for (int rope : ropes)
      pq.add(rope);

    int cost = 0;
    while (pq.size() > 1) {
      int sum = pq.poll() + pq.poll();
      cost += sum;
      pq.offer(sum);
    }
    return cost;
  }

  public static void main(String[] args) {
    System.out.println(ConnectRopes.minCost(new int[]{8, 4, 6, 12}));
    System.out.println(ConnectRopes.minCost(new int[]{20, 4, 8, 2}));
    System.out.println(ConnectRopes.minCost(new int[]{1, 2, 5, 10, 35, 89}));
    System.out.println(ConnectRopes.minCost(new int[]{2, 2, 3, 3}));
  }
}

