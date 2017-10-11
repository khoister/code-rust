import org.apache.commons.lang3.ArrayUtils;

import static com.google.common.truth.Truth.assertThat;

/**
 * Search in a Rotated Array
 */
public class SearchRotatedArray {
    private static final int NOT_FOUND = -1;

    public static int search(final int[] arr, int key) {
        if (ArrayUtils.isEmpty(arr)) {
            return NOT_FOUND;
        }

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[low] < arr[mid] && (arr[low] <= key && key < arr[mid])) {
                high = mid - 1;
            } else if (arr[high] > arr[mid] && (arr[mid] < key && key <= arr[high])) {
                low = mid + 1;
            } else if (arr[low] > arr[mid]) {
                high = mid - 1;
            } else if (arr[high] < arr[mid]) {
                low = mid + 1;
            } else {
                return NOT_FOUND;
            }
        }
        return NOT_FOUND;
    }

    public static void main(final String[] args) {
        // Index:        0    1    2    3    4    5  6   7   8   9  10  11  12  13  14   15   16   17   18   19
        int data[] = { 176, 188, 199, 200, 210, 222, 1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162 };

        for (int i = 0; i < data.length; ++i) {
            assertThat(search(data, data[i])).isEqualTo(i);
        }
        // Negative case
        assertThat(search(data, 223)).isEqualTo(NOT_FOUND);
        assertThat(search(data, 42)).isEqualTo(NOT_FOUND);
        assertThat(search(data, 125)).isEqualTo(NOT_FOUND);
    }
}
