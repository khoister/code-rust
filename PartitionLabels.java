import java.util.*;

class PartitionLabels {

  static List<Integer> partition(String s) {
    // Keep track of the last occurrence of each character in the string
    int[] lastIndex = new int[26];
    for (int i = 0; i < s.length(); i++)
      lastIndex[ord(s.charAt(i))] = i;

    int startOfPartition = 0;
    int endOfPartition = 0;

    List<Integer> results = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      endOfPartition = Math.max(endOfPartition, lastIndex[ord(s.charAt(i))]);
      if (i == endOfPartition) {
        results.add(endOfPartition-startOfPartition+1);

        // Start of a new partition
        startOfPartition = endOfPartition+1;
      }
    }
    return results;
  }

  static int ord(char c) {
    return c - 'a';
  }

  public static void main(String[] args) {
    System.out.println(PartitionLabels.partition("ababcbacadefegdehijhklij"));
  }
}

