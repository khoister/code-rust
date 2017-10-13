import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * Move zeroes to left
 */
public class MoveZeroesToLeft {

    public static void moveZeroes(final int[] arr) {
        if (ArrayUtils.isEmpty(arr)) {
            throw new IllegalArgumentException("Invalid input");
        }

        int read = arr.length - 1;
        int write = arr.length - 1;
        while (read >= 0) {
            if (arr[read] != 0) {
                arr[write--] = arr[read];
            }
            --read;
        }
        // Fill in the remaining slots with zeroes
        while (write >= 0) {
            arr[write--] = 0;
        }
    }

    private static void print(final int[] arr) {
        System.out.println(new StrBuilder().appendWithSeparators(ArrayUtils.toObject(arr), ", ").toString());
    }

    public static void main(final String[] args) {
        final int arr[] = { 1, 10, 20, 0, 59, 63, 0, 88, 0 };
        print(arr);
        moveZeroes(arr);
        print(arr);
    }
}
