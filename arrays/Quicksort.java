/**
 * Quicksort
 */
public class Quicksort {

    public static void sort(final int[] arr) {
        sortR(arr, 0, arr.length-1);
    }

    private static void sortR(final int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            sortR(arr, low, p-1);
            sortR(arr, p+1, high);
        }
    }

    private static int partition(final int[] arr, int low, int high) {
        int l = low;
        int r = high;
        int p = arr[low];

        while (l < r) {
            while (arr[l] <= p) {
                ++l;
            }
            while (arr[r] > p) {
                --r;
            }

            if (l < r) {
                swap(arr, l, r);
            }
        }
        // Pivot value goes to position 'r'
        swap(arr, low, r);
        return r;
    }

    private static void swap(final int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void print(final int[] arr) {
        print(arr, 0);
    }

    private static void print(final int[] arr, int index) {
        if (index == arr.length-1) {
            System.out.println(arr[index]);
            return;
        }
        System.out.print(arr[index] + ", ");
        print(arr, index + 1);
    }

    public static void main(final String[] args) {
        final int[] arr = { 55, 23, 26, 2, 18, 78, 23, 8, 2, 3 };
        print(arr);
        sort(arr);
        print(arr);
    }
}
