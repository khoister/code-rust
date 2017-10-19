import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

/**
 * Sum of two values
 */
public class SumOfTwoValues {

    public static boolean hasSum(final int[] arr, int sum) {
        if (ArrayUtils.isEmpty(arr)) {
            throw new IllegalArgumentException("Invalid input");
        }

        final Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            final int diff = sum - i;
            if (set.contains(diff)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }

    public static void main(final String[] args) {
        final int[] arr = { 5, 7, 1, 2, 8, 4, 3 };
        for (int i = 0; i < arr.length-1; ++i) {
            for (int j = i + 1; j < arr.length; ++j)  {
                assertThat(hasSum(arr, arr[i] + arr[j])).isTrue();
            }
        }

        // A few negative cases
        assertThat(hasSum(arr, 0)).isFalse();
        assertThat(hasSum(arr, 100)).isFalse();
        assertThat(hasSum(arr, 20)).isFalse();
    }
}
