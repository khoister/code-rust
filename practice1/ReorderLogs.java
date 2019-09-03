import java.util.*;
import java.util.stream.*;


class ReorderLogs {

  static String[] reorder(String[] logs) {
    if (logs == null || logs.length == 0)
      return logs;

    Arrays.sort(logs, cmp);
    return logs;
  }

  static Comparator<String> cmp = (log1, log2) -> {
    String[] split1 = log1.split(" ", 2);
    String id1 = split1[0];
    String meta1 = split1[1];

    String[] split2 = log2.split(" ", 2);
    String id2 = split2[0];
    String meta2 = split2[1];

    boolean isNumeric1 = isNumeric(meta1);
    boolean isNumeric2 = isNumeric(meta2);

    // Comparing both alphabetic logs
    if (!isNumeric1 && !isNumeric2) {
      int result = meta1.compareTo(meta2);
      if (result != 0)
        return result;
      return id1.compareTo(id2);
    }

    // Both are numeric logs
    if (isNumeric1 && isNumeric2)
      return 0;

    if (isNumeric1 && !isNumeric2)
      return 1;

    if (!isNumeric1 && isNumeric2)
      return -1;

    return 0;
  };

  static boolean isNumeric(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (!(isDigit(s.charAt(i)) || isSpace(s.charAt(i))))
        return false;
    }
    return true;
  }

  static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  static boolean isSpace(char c) {
    return c == ' ';
  }

  static void print(String[] logs) {
    for (String line : logs)
      System.out.println(line);
  }

  public static void main(String[] args) {
    String[] logs = new String[]{"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
    ReorderLogs.reorder(logs);
    print(logs);
  }
}

