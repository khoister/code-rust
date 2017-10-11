import org.apache.commons.lang3.ArrayUtils;

import static com.google.common.truth.Truth.assertThat;

/**
 * Smallest Common Number
 */
public class SmallestCommonNumber {

    public static int smallestCommon(final int[] arr1, final int[] arr2, final int[] arr3) {
        if (ArrayUtils.isEmpty(arr1) || ArrayUtils.isEmpty(arr2) || ArrayUtils.isEmpty(arr3)) {
            throw new IllegalArgumentException("Invalid input data");
        }

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i];
            }

            if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                ++i;
            } else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                ++j;
            } else if (arr3[k] <= arr1[i] && arr3[k] <= arr2[j]) {
                ++k;
            }
        }
        throw new RuntimeException("Smallest common number not found");
    }

    public static void main(final String[] args) {
        final int arr1[] = { 6, 7, 10, 25, 30, 63, 64 };
        final int arr2[] = { -1, 4, 5, 6, 7, 8, 10, 50 };
        final int arr3[] = { 1, 6, 10, 14 };
        assertThat(smallestCommon(arr1, arr2, arr3)).isEqualTo(6);
    }
}
