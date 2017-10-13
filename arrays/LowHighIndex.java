import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Find Low/High Index
 */
public class LowHighIndex {

    private static final int NOT_FOUND = -1;

    @Data
    public static class Tuple {
        private int first;
        private int second;
        public Tuple(int a, int b) {
            this.first = a;
            this.second = b;
        }
    }

    /**
     * Finds the low and high index of an element in the array.
     * The array is sorted and could contains millions of items.
     * Duplicate items may be expected.
     */
    public static Tuple findHighLow(final int[] arr, final int key) {
        if (ArrayUtils.isEmpty(arr)) {
            throw new IllegalArgumentException("Invalid input");
        }
        return new Tuple(findLow(arr, key), findHigh(arr, key));
    }

    private static int findLow(final int[] arr, final int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                high = mid - 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (inRange(low, 0, arr.length) && arr[low] == key) ? low : NOT_FOUND;
    }

    private static int findHigh(final int[] arr, final int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                low = mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (inRange(high, 0, arr.length) && arr[high] == key) ? high : NOT_FOUND;
    }

    private static boolean inRange(int x, int min, int max) {
        return (x >= min && x < max);
    }

    public static void main(final String[] args) {
        // index       0  1  2  3  4  5  6  7  8  9  10
        int data[] = { 1, 2, 5, 5, 5, 5, 5, 5, 5, 5, 20 };

        Tuple tuple = findHighLow(data, 5);
        System.out.println(String.format("(%d, %d)", tuple.getFirst(), tuple.getSecond()));

        tuple = findHighLow(data, 0);
        System.out.println(String.format("(%d, %d)", tuple.getFirst(), tuple.getSecond()));

        tuple = findHighLow(data, 7);
        System.out.println(String.format("(%d, %d)", tuple.getFirst(), tuple.getSecond()));
    }
}
