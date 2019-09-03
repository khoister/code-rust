import java.util.*;

class NoThreeConsecChars {

  static final int MAX_CONSEC_CHARS = 2;

  static String generate(int A, int B, int C) {
    Queue<int[]> pq = new PriorityQueue<>((a,b) -> b[0]-a[0]);
    if (A > 0) pq.add(new int[]{A, 'a'});
    if (B > 0) pq.add(new int[]{B, 'b'});
    if (C > 0) pq.add(new int[]{C, 'c'});

    StringBuilder sb = new StringBuilder();
    int[] onHold = new int[]{0,0};

    while (!pq.isEmpty()) {
      // Greedily use the largest quantity char from the queue
      int[] tuple = pq.remove();
      int writeLen = Math.min(tuple[0], MAX_CONSEC_CHARS);
      for (int i = 0; i < writeLen; i++)
        sb.append((char) tuple[1]);
      tuple[0] -= writeLen;

      // Put the previous character back in for use
      if (onHold[0] > 0)
        pq.add(onHold);

      // Put the character we just wrote on hold
      // because we won't be able to use it for
      // the next write.
      onHold = tuple;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(NoThreeConsecChars.generate(1, 1, 6));
    System.out.println(NoThreeConsecChars.generate(0, 3, 6));
    System.out.println(NoThreeConsecChars.generate(1, 2, 3));
  }
}

