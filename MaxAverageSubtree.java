import java.util.*;

class MaxAverageSubtree {
  int maxAvg;
  Node maxSubtree;

  static class Node {
    int val;
    List<Node> children = new LinkedList<>();

    Node(int val) {
      this.val = val;
    }

    Node(int val, List<Node> children) {
      this.val = val;
      this.children.addAll(children);
    }

    @Override
    public String toString() {
      if (children.isEmpty()) {
        return String.format("%d", val);
      } else {
        return String.format("%d -> ", val) + children;
      }
    }
  }

  Node maxAvgSubtree(Node root) {
    avg(root);
    return maxSubtree;
  }

  int[] avg(Node root) {
    if (root == null)
      return new int[]{0,0};

    int sum = root.val;
    int count = 1;

    for (Node node : root.children) {
      int[] pair = avg(node);
      sum += pair[0];
      count += pair[1];
    }

    int average = sum/count;
    if (average > maxAvg) {
      maxAvg = average;
      maxSubtree = root;
    }
    return new int[]{sum,count};
  }

  public static void main(String[] args) {
    Node tree = new Node(1, Arrays.asList(
      new Node(-5,
        Arrays.asList(new Node(1), new Node(2))),
      new Node(13,
        Arrays.asList(new Node(4), new Node(-2))),
      new Node(4)));
    System.out.println(tree);

    Node subtree = (new MaxAverageSubtree()).maxAvgSubtree(tree);
    System.out.println(subtree);
  }
}

