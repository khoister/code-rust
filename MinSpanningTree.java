import java.util.*;

class MinSpanningTree {

  static final int NOT_POSSIBLE = -1;

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
      if (p == q)
        return;

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

  static int minCost(int n, int[][] edges, int[][] newEdges) {
    if (n <= 0
      || edges == null || edges.length == 0
      || newEdges == null || newEdges.length == 0)
      return NOT_POSSIBLE;

    DisjointSets graph = new DisjointSets(n);
    for (int[] edge : edges)
      graph.union(edge[0], edge[1]);

    // Graph is already connected
    if (!graph.isDisjoint())
      return 0;

    int cost = 0;
    Arrays.sort(newEdges, (u,v) -> u[2]-v[2]);
    for (int[] edge : newEdges) {
      graph.union(edge[0], edge[1]);
      cost += edge[2];

      if (!graph.isDisjoint())
        return cost;
    }
    return NOT_POSSIBLE;
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 4}, {4, 5}, {2, 3}};
    int[][] newEdges = {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
    System.out.println(MinSpanningTree.minCost(6, edges, newEdges));
  }
}

