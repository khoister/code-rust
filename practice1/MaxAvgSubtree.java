import java.util.*;

class MaxAvgSubtree {
  int maxAvg;
  Node maxNode;

  static class Node {
    int val;
    Node[] children = new Node[0];

    Node(int val) {
      this.val = val;
    }

    Node(int val, Node[] children) {
      this.val = val;
      this.children = children;
    }
  }

  int findMaxAvgSubtree(Node root) {
    findMaxSumAndCount(root);
    return Optional.ofNullable(maxNode)
      .map(node -> node.val)
      .orElse(0);
  }

  int[] findMaxSumAndCount(Node root) {
    if ( root == null
      || root.children == null
      || root.children.length == 0)
      return new int[]{0,0};

    int sum = root.val;
    int count = 1;

    for (Node child : root.children) {
      int[] data = findMaxSumAndCount(child);
      sum += data[0];
      count += data[1];
    }

    int avg = sum/count;
    if (avg > maxAvg) {
      maxAvg = avg;
      maxNode = root;
    }
    return new int[]{sum, count};
  }

  public static void main(String[] args) {
    Node tree = 
      new Node(20, new Node[]{
        new Node(12, new Node[]{
          new Node(11),
          new Node(2),
          new Node(3)
        }),
        new Node(18, new Node[]{
          new Node(15),
          new Node(8)
        })
    });
    System.out.println((new MaxAvgSubtree()).findMaxAvgSubtree(tree));
  }
}

