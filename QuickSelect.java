import java.util.*;
import java.util.stream.*;

class QuickSelect {

  static final int ERROR = Integer.MIN_VALUE;

  static int select(int[] a, int k) {
    int left = 0;
    int right = a.length-1;

    if (k < left || k > right)
      return ERROR;

    while (true) {
      if (left == right)
        return a[left];

      int pivot = partition(a, left, right);
      if (k == pivot)
       return a[k];
      else if (k < pivot)
       right = pivot-1;
      else
       left = pivot+1;
    }
  }

  static int partition(int[] a, int p, int r) {
    int x = a[p];
    int i = p-1;
    int j = r+1;

    while (true) {
      do --j; while(a[j] > x);
      do ++i; while(a[i] < x);

      if (i < j)
        swap(a, i, j);
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
    int[] a = {281,5,25,104,9,106};
    System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    System.out.println("Pivot: " + QuickSelect.partition(a, 0, a.length-1));
    System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));

    System.out.println(QuickSelect.select(a, Integer.valueOf(args[0])));
  }
}

