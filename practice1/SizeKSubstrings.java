import java.util.*;

class SizeKSubstrings {

  public static List<String> uniqueSubstrings(String s, int k) {
    if (s == null || s.isEmpty() || k < 1)
      return new ArrayList<>();

    Set<String> results = new LinkedHashSet<>();
    int[] index = new int[26];

    for (int start = 0, end = 0; end < s.length(); end++) {
      char c = s.charAt(end);
      start = Math.max(start, index[c-'a']);

      // Found unique substring of length k
      int len = end-start+1;
      if (len == k) {
        results.add(s.substring(start, end+1));

        // Slide the window forward
        ++start;
      }

      // Set the destination index for the current character
      index[c-'a'] = end+1;
    }
    return new ArrayList<>(results);
  }

  public static void main(String[] args) {
    System.out.println(SizeKSubstrings.uniqueSubstrings("abcabc", 3));
    System.out.println(SizeKSubstrings.uniqueSubstrings("awaglknagawunagwkwagl", 4));
  }
}

