import java.util.*;
import java.util.stream.*;

class MostCommonWord {

  static String mostCommon(String paragraph, String[] banned) {
    if (paragraph == null || paragraph.isEmpty())
      return null;

    int max = 0;
    String result = "";
    HashMap<String, Integer> map = new HashMap<>();
    Set<String> bannedSet = Arrays.stream(banned).collect(Collectors.toSet());
    String[] words = paragraph.toLowerCase().split("\\W");
    for (String word : words) {
      if (!bannedSet.contains(word)) {
        int count = map.containsKey(word) ? map.get(word)+1 : 1;
        map.put(word, count);

        if (count > max) {
          max = count;
          result = word;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String[] banned = new String[]{"hit"};
    System.out.println(MostCommonWord.mostCommon(paragraph, banned));
  }
}

