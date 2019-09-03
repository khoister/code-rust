import java.util.*;

class MinSpanningTree2 {

  static class Pair extends AbstractMap.SimpleEntry<Integer,Integer> {
    Pair(int i, int j) {
      super(i,j);
    }

    public String toString() {
      return String.format("(%d,%d)", getKey(), getValue());
    }
  }

  static class DisjointSets {
    int sets;
    final int[] parent;
    final int[] weight;

    DisjointSets(int n) {
      sets = n;
      parent = new int[n+1];
      weight = new int[n+1];

      for (int i = 0; i <= n; i++) {
        parent[i] = i;
        weight[i] = 1;
      }
    }

    boolean isDisjoint() {
      return sets != 1;
    }

    int find(int i) {
      if (i != parent[i])
        parent[i] = find(parent[i]);
      return parent[i];
    }

    void union(int i, int j) {
      if (i <= 0 || i >= parent.length || j <= 0 || j >= parent.length)
        return;

      int p = find(i);
      int q = find(j);

      // Already a union
      if (p == q)
        return;

      if (weight[p] > weight[q]) {
        parent[q] = p;
        weight[p] += weight[q];
      } else {
        parent[p] = q;
        weight[q] += weight[p];
      }

      // Reduce the number of disjoint sets after the union
      --sets;
    }
  }

  static int minCost(int n, int[][] edges, int[][] edgesToRepair) {
    Set<Pair> set = new HashSet<>();

    // Add edges to active set
    for (int[] edge : edges) {
      normalize(edge);
      set.add(new Pair(edge[0], edge[1]));
    }

    // Remove edges that need to be repaired
    for (int[] edge : edgesToRepair) {
      normalize(edge);
      set.remove(new Pair(edge[0], edge[1]));
    }

    // Create union sets based on edges minus the edges needing repair
    final DisjointSets graph = new DisjointSets(n);
    for (Pair pair : set)
      graph.union(pair.getKey(), pair.getValue());

    int cost = 0;
    Arrays.sort(edgesToRepair, (u,v) -> u[2]-v[2]);
    for (int[] edge : edgesToRepair) {
      graph.union(edge[0], edge[1]);
      cost += edge[2];

      // All subsets are connected, we can just return the cost
      if (!graph.isDisjoint())
        return cost;
    }
    return -1;
  }

  private static void normalize(int[] a) {
    int i = a[0];
    int j = a[1];
    a[0] = Math.min(i,j);
    a[1] = Math.max(i,j);
  }

  public static void main(String[] args) {
    int n = 5;
    int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
    int[][] edgesToRepair = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
    System.out.println(MinSpanningTree2.minCost(n, edges, edgesToRepair));

    n = 6;
    int[][] edges2 = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
    int[][] edgesToRepair2 = {{1, 6, 410}, {2, 4, 800}};
    System.out.println(MinSpanningTree2.minCost(n, edges2, edgesToRepair2));

    n = 6;
    int[][] edges3 = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
    int[][] edgesToRepair3 = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
    System.out.println(MinSpanningTree2.minCost(n, edges3, edgesToRepair3));
  }
}

