import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Find the maximum values in a sliding window
 */
public class SlidingWindow {

    public static void slide(final int[] data, int window) {
        if (data == null || data.length == 0 || data.length < window) {
            return;
        }

        final Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < window; ++i) {
            while (!deque.isEmpty() && data[deque.peekLast()] <= data[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
        }
        System.out.print(data[deque.peekFirst()] + " ");

        for (int i = window; i < data.length; ++i) {
            while (!deque.isEmpty() && data[deque.peekLast()] <= data[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);

            // The first element is outside of the window now. Remove it.
            if (deque.peekFirst() < windowStartIndex(i, window)) {
                deque.removeFirst();
            }
            System.out.print(data[deque.peekFirst()] + " ");
        }
        System.out.println();
    }

    private static int windowStartIndex(int currentPos, int windowSize) {
        // Add 1 because we're zero-based e.g. at position 3 with window size 3,
        // the starting position of the window is position 1
        return currentPos - windowSize + 1;
    }

    public static void main(final String[] args) {
        int data[] = { -4, 2, -5, 3, 6, 7, 3, 5, 4, 8 };
        slide(data, 3);
    }
}
