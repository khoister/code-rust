import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * Rotate Array
 */
public class RotateArray {

    public static void rotate(final int[] arr, int shift) {
        if (ArrayUtils.isEmpty(arr)) {
            return;
        }

        shift = shift % arr.length;
        if (shift < 0) {
            shift += arr.length;
        }
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, shift - 1);
        reverse(arr, shift, arr.length - 1);
    }

    private static void reverse(final int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    private static void swap(final int[] arr, int x, int y) {
        final int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void print(final int[] arr) {
        System.out.println(new StrBuilder().appendWithSeparators(ArrayUtils.toObject(arr), ", ").toString());
    }

    public static void main(final String[] args) {
        int data[] = { 1, 10, 20, 0, 59, 86, 32, 11, 9, 40 };
        print(data);

        rotate(data, 2);
        print(data);

        rotate(data, -2);
        rotate(data, -1);
        print(data);
    }
}
