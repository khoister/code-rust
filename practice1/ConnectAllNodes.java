import java.util.*;

class ConnectAllNodes {
  static final int NOT_POSSIBLE = -1;

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
        // Compress path
        parent[i] = find(parent[i]);

      return parent[i];
    }

    void union(int i, int j) {
      int p = find(i);
      int q = find(j);

      if (p == q)
        return;

      // Give preference to the node with greater weight
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
    if (n < 1
      || edges == null || edges.length == 0
      || newEdges == null || newEdges.length == 0)
      return NOT_POSSIBLE;

    // Connect the edges
    DisjointSets graph = new DisjointSets(n);
    for (int i = 0; i < edges.length; i++)
      graph.union(edges[i][0], edges[i][1]);

    // Graph already connected by its existing edges
    if (!graph.isDisjoint())
      return 0;

    // Sort new edges by cost in ascending order
    Arrays.sort(newEdges, (u,v) -> u[2]-v[2]);

    for (int i = 0, cost = 0; i < newEdges.length; i++) {
      graph.union(newEdges[i][0], newEdges[i][1]);
      cost += newEdges[i][2];

      if (!graph.isDisjoint())
        return cost;
    }
    return NOT_POSSIBLE;
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 4}, {4, 5}, {2, 3}};
    int[][] newEdges = {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
    System.out.println(ConnectAllNodes.minCost(6, edges, newEdges));
  }
}

