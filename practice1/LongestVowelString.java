import java.util.*;

class LongestVowelString {

  private static int maxVowelLength(String s) {
    if (s == null || s.isEmpty())
      return 0;

    int start = 0;
    int end = s.length()-1;

    while (isVowel(s.charAt(start)))
      ++start;

    while (isVowel(s.charAt(end)))
      --end;

    int maxVowelSubstrLen = 0;
    int vowelSubstrLen = 0;
    for (int i = start; i < end; i++) {
      if (isVowel(s.charAt(i))) {
        ++vowelSubstrLen;
        maxVowelSubstrLen = Math.max(maxVowelSubstrLen, vowelSubstrLen);
      } else {
        // Reset
        vowelSubstrLen = 0;
      }
    }
    return start + maxVowelSubstrLen + (s.length()-1-end);
  }

  private static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  public static void main(String[] args) {
    System.out.println(LongestVowelString.maxVowelLength("earthproblem"));
    System.out.println(LongestVowelString.maxVowelLength("letsgosomewhere"));
    System.out.println(LongestVowelString.maxVowelLength("aaayyyaayyayaaayayaaa"));
  }
}

