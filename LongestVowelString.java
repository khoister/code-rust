import java.util.*;

class LongestVowelString {

  static int findLongestVowelStringLength(String s) {
    if (s == null || s.length() == 0)
      return 0;

    int low = 0;
    int high = s.length()-1;

    while (isVowel(s.charAt(low)))
      ++low;

    while (isVowel(s.charAt(high)))
      --high;

    int maxVowelSubstrLen = 0;
    int vowelSubstrLen = 0;

    for (int i = low; i < high; i++) {
      if (isVowel(s.charAt(i))) {
        ++vowelSubstrLen;
        maxVowelSubstrLen = Math.max(maxVowelSubstrLen, vowelSubstrLen);
      } else {
        vowelSubstrLen = 0;
      }
    }

    int numVowelsAtStart = low;
    int numVowelsAtEnd = s.length()-1-high;
    System.out.println(String.format("%s => start: %d, substr: %d, end: %d", s, numVowelsAtStart, maxVowelSubstrLen, numVowelsAtEnd));
    return numVowelsAtStart + maxVowelSubstrLen + numVowelsAtEnd;
  }

  static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

  public static void main(String[] args) {
    System.out.println(LongestVowelString.findLongestVowelStringLength("earthproblem"));
    System.out.println(LongestVowelString.findLongestVowelStringLength("letsgosomewhere"));
    System.out.println(LongestVowelString.findLongestVowelStringLength(args[0]));
  }
}
