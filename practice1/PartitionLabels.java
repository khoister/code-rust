import java.util.*;

class PartitionLabels {

  static List<Integer> partition(String s) {
    int[] lastIndex = new int[26];
    for (int i = 0; i < s.length(); i++)
      lastIndex[s.charAt(i)-'a'] = i;

    List<Integer> results = new ArrayList<>();

    int startOfPartition = 0;
    int endOfPartition = -1;

    for (int i = 0; i < s.length(); i++) {
      endOfPartition = Math.max(endOfPartition, lastIndex[s.charAt(i)-'a']);
      if (i == endOfPartition) {
        results.add(endOfPartition-startOfPartition+1);
        startOfPartition = endOfPartition+1;
      }
    }
    return results;
  }

  public static void main(String[] args) {
    System.out.println(PartitionLabels.partition("ababcbacadefegdehijhklij"));
  }
}
