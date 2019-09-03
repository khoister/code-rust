import java.util.*;
import java.util.stream.*;

class LogFile {

  static class LogEntry {
    final String id;
    final String meta;

    LogEntry(String id, String meta) {
      this.id = id;
      this.meta = meta;
    }

    String getId() { return id; }
    String getMeta() { return meta; }
  }

  static List<String> process(String[] lines) {
    if (lines == null || lines.length == 0)
      return new ArrayList<>();

    List<String> numberLines = new ArrayList<>();
    List<LogEntry> alphaLines = new ArrayList<>();

    for (String line : lines) {
      int i = line.indexOf(' ');
      String id = line.substring(0, i);
      String meta = line.substring(i+1, line.length());

      if (isNumeric(meta))
        numberLines.add(line);
      else if (isAlphabetic(meta))
        alphaLines.add(new LogEntry(id, meta));
    }

    Collections.sort(alphaLines, getSortComparator());
    List<String> results = alphaLines.stream()
      .map(e -> e.getId() + " " + e.getMeta())
      .collect(Collectors.toList());
    results.addAll(numberLines);
    return results;
  }

  static boolean isNumeric(String s) {
    for (char c : s.toCharArray()) {
      if (!(isDigit(c) || isSpace(c)))
        return false;
    }
    return true;
  }

  static boolean isAlphabetic(String s) {
    for (char c : s.toCharArray()) {
      if (!(isAlpha(c) || isSpace(c)))
        return false;
    }
    return true;
  }

  static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  static boolean isAlpha(char c) {
    return c >= 'a' && c <= 'z';
  }

  static boolean isSpace(char c) {
    return c == ' ';
  }

  static Comparator<LogEntry> getSortComparator() {
    Comparator<LogEntry> cmp = Comparator.comparing(LogEntry::getMeta);
    return cmp.thenComparing(Comparator.comparing(LogEntry::getId));
  }

  public static void main(String[] args) {
    String[] lines = {
      "a1 9 2 3 1",
      "rd2 off key dog",
      "g1 act car",
      "zo4 4 7",
      "ab1 off key dog",
      "a8 act zoo"
    };
    System.out.println(LogFile.process(lines));
  }
}

