import java.util.*;
import java.util.stream.*;

class PrioritizeOrders {

  static class Order {
    String id;
    String meta;

    Order(String id, String meta) {
      this.id = id;
      this.meta = meta;
    }

    String getId() { return id; }
    String getMeta() { return meta; }

    @Override
    public String toString() { return getId() + " " + getMeta(); }
  }

  static List<String> prioritizeOrders(String[] orders) {
    List<Order> primeOrders = new ArrayList<>();
    List<String> nonPrimeOrders = new ArrayList<>();

    for (String s : orders) {
      int spaceIndex = s.indexOf(' ');
      String id = s.substring(0, spaceIndex);
      String meta = s.substring(spaceIndex+1);

      if (isNumeric(meta))
        nonPrimeOrders.add(s);
      else if (isAlphabetic(meta.toLowerCase()))
        primeOrders.add(new Order(id, meta));
      else
        // Log error
        System.out.println("Invalid order meta data. Id: " + id);
    }

    // Sort the Prime orders
    Collections.sort(primeOrders, getPrimeComparator());
    List<String> results = primeOrders.stream()
      .map(order -> order.getId() + " " + order.getMeta())
      .collect(Collectors.toList());
    results.addAll(nonPrimeOrders);
    return results;
  }

  private static boolean isAlphabetic(final String s) {
    if (s == null || s.length() == 0)
      return false;

    for (char c : s.toCharArray()) {
      if (!(isAlpha(c) || isSpace(c)))
        return false;
    }
    return true;
  }

  private static boolean isNumeric(final String s) {
    if (s == null || s.length() == 0)
      return false;

    for (char c : s.toCharArray()) {
      if (!(isDigit(c) || isSpace(c)))
        return false;
    }
    return true;
  }

  private static boolean isAlpha(char c) {
    return c >= 'a' && c <= 'z';
  }

  private static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private static boolean isSpace(char c) {
    return c == ' ';
  }

  private static Comparator<Order> getPrimeComparator() {
    Comparator<Order> comparator = Comparator.comparing(Order::getMeta);
    return comparator.thenComparing(Comparator.comparing(Order::getId));
  }

  public static void main(String[] args) {
    String[] orders = {
      "Zid 93 12",
      "fp kindle book",
      "IOa echo show",
      "17g 12 256" ,
      "abl kindle book",
      "125 echo dot second generation"
    };
    System.out.println(PrioritizeOrders.prioritizeOrders(orders));
  }

}

