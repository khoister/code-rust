import java.util.*;

class KDistinctCharacters {

  static List<String> kSubstrings(String s, int k) {
    if (s == null || s.isEmpty())
      return new ArrayList<>();

    int[] index = new int[26];
    Set<String> results = new LinkedHashSet<>();

    for (int start = 0, end = 0; end < s.length(); end++) {
      start = Math.max(start, index[s.charAt(end)-'a']);
      int len = end-start+1;
      if (len == k) {
        results.add(s.substring(start, end+1));

        // Slide window to find next k length substring
        ++start;
      }
      // Update to index of most recently occurrence of this character
      index[s.charAt(end)-'a'] = end+1;
    }
    return new ArrayList<>(results);
  }

  public static void main(String[] args) {
    System.out.println(KDistinctCharacters.kSubstrings("awaglknagawunagwkwagl", 4));
  }
}

