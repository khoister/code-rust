import java.util.*;
import java.util.stream.*;

class Code {

  static int toInteger(int[] cells) {
    int state = 0;
    for (int i = cells.length-1; i >= 0; i--) {
      if (cells[i] > 0)
        state ^= 1 << cells.length-1-i;
    }
    return state;
  }


  public static void main(String[] args) {
    System.out.println(toInteger(new int[]{0,1,0,1,1,0,0,1}));
    System.out.println(toInteger(new int[]{0,0,0,0,1,0,0,1}));
  }
}

