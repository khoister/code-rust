import java.util.*;

class NoThreeConsecutiveChars {

  private final static Pair NOT_USED = new Pair(0, ' ');

  static class Pair {
    int count;
    char val;

    Pair(int count, char val) {
      this.count = count;
      this.val = val;
    }

    @Override
    public String toString() {
      return String.format("(%d,%c)", count, val);
    }
  }

  static String generate(int A, int B, int C) {
    // Max heap to get the character with the largest allowed occurrence
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
    pq.add(new Pair(A, 'a'));
    pq.add(new Pair(B, 'b'));
    pq.add(new Pair(C, 'c'));

    StringBuilder sb = new StringBuilder();
    Pair charCurrentlyNotUsed = NOT_USED;

    while (!pq.isEmpty()) {
      // Current character that will be used for writing
      Pair top = pq.poll();

      // Construct the string using the current character. Cannot use more than 2 consecutive
      int i;
      for (i = 0; i < Math.min(top.count, 2); i++)
        sb.append(top.val);

      // Portion of the allowed count for this char has been used up
      top.count -= i;

      // Put the previously unused char back on the priority queue for further consideration
      if (charCurrentlyNotUsed.count > 0)
        pq.offer(charCurrentlyNotUsed);

      // Save the character we just used for later
      charCurrentlyNotUsed = top;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(NoThreeConsecutiveChars.generate(1, 1, 6));
    System.out.println(NoThreeConsecutiveChars.generate(1, 2, 3));
  }
}

