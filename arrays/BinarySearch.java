import static com.google.common.truth.Truth.assertThat;

/**
 * Binary Search
 */
public class BinarySearch {
    public static int ERROR = -1;

    public static int find(int[] arr, int key) {
        if (arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("Invalid input data");
        }

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return ERROR;
    }

    public static void main(final String[] args) {
        //             0   1   2   3   4   5   6   7   8    9   10   11   12   13   14   15   16   17   18   19
        int data[] = { 1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162, 176, 188, 199, 200, 210, 222 };
        for (int i = 0; i < data.length; ++i) {
            assertThat(find(data, data[i])).isEqualTo(i);
        }
        assertThat(find(data, 17)).isEqualTo(ERROR);
        assertThat(find(data, 221)).isEqualTo(ERROR);
    }
}
