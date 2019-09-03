import java.util.*;

class RepairEdges {

  static final int NOT_POSSIBLE = -1;

  static class Edge extends AbstractMap.SimpleEntry<Integer, Integer> {
    Edge(Integer u, Integer v) {
      super(Math.min(u,v), Math.max(u,v));
    }

    public String toString() {
      return String.format("[%d, %d]", getKey(), getValue());
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
      for (int i = 0; i < parent.length; i++) {
        parent[i] = i;
        weight[i] = 1;
      }
    }

    boolean isDisjoint() {
      return sets > 1;
    }

    int find(int i) {
      if (i != parent[i])
        // Path compression
        parent[i] = find(parent[i]);

      return parent[i];
    }

    void union(int i, int j) {
      int p = find(i);
      int q = find(j);

      // Already unioned
      if (p == q)
        return;

      // Merge smaller set into larger one
      if (weight[p] > weight[q]) {
        parent[q] = p;
        weight[p] += weight[q];
      } else {
        parent[p] = q;
        weight[q] += weight[p];
      }
      --sets;
    }
  }

  static int minCost(int n, int[][] edges, int[][] edgesToRepair) {
    if (edges == null || edges.length == 0
      || edgesToRepair == null || edgesToRepair.length == 0
      || n < 1)
      return NOT_POSSIBLE;

    // Add all edges of the graph
    Set<Edge> set = new HashSet<>();
    for (int[] edge : edges)
      set.add(new Edge(edge[0], edge[1]));

    // Remove the ones needing repair
    for (int[] edge : edgesToRepair)
      set.remove(new Edge(edge[0], edge[1]));

    // Build graph with edges from the set (edges - edgesToRepair)
    DisjointSets graph = new DisjointSets(n);
    for (Edge edge : set)
      graph.union(edge.getKey(), edge.getValue());

    // No repairs needed. Graph already connected
    if (!graph.isDisjoint())
      return 0;

    // Sort edges to repair by cost in ascending order
    Arrays.sort(edgesToRepair, (u,v) -> u[2]-v[2]);

    int cost = 0;
    for (int[] edge : edgesToRepair) {
      graph.union(edge[0], edge[1]);
      cost += edge[2];

      if (!graph.isDisjoint())
        return cost;
    }
    return NOT_POSSIBLE;
  }

  public static void main(String[] args) {
    //int n = 5;
    //int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
    //int[][] edgesToRepair = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};

    //int n = 6;
    //int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
    //int[][] edgesToRepair = {{1, 6, 410}, {2, 4, 800}};

    int n = 6;
    int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
    int[][] edgesToRepair = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};

    System.out.println(RepairEdges.minCost(n, edges, edgesToRepair));
  }
}

