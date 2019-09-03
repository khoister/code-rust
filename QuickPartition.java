import java.util.*;
import java.util.stream.*;

class QuickPartition {

  static int partition(int[] a, int p, int r) {
    int x = a[p];
    int i = p-1;
    int j = r+1;

    while (true) {
      do --j; while (a[j] > x);
      do ++i; while (a[i] < x);

      if (i < j) {
        swap(a, i, j);
      }
      else
        return j;
    }
  }

  static void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void main(String[] args) {
    //int[] a = {5,3,2,6,4,1,3,7};
    int[] a = {4, 6, 10, 2, 0, 5, 3, 9, 7, 12, 1};
    System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    System.out.println(QuickPartition.partition(a, 0, a.length-1));
    System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
  }
}

