import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Merge overlapping intervals
 */
public class MergeIntervals {

    @Data
    private static class Tuple {
        private int first;
        private int second;

        Tuple(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", getFirst(), getSecond());
        }
    }

    public static List<Tuple> merge(final List<Tuple> intervals) {
        if (CollectionUtils.isEmpty(intervals)) {
            throw new IllegalArgumentException("Invalid input");
        }

        sort(intervals);

        // Use a deque as a stack
        final Deque<Tuple> deque = new ArrayDeque<>();
        deque.offerLast(intervals.get(0));

        for (int i = 1; i < intervals.size(); ++i) {
            final Tuple top = deque.peekLast();
            final Tuple current = intervals.get(i);
            if (current.getFirst() <= top.getSecond()) {
                // Overlapping interval
                top.setSecond(Math.max(top.getSecond(), current.getSecond()));
                deque.pollLast();
                deque.offerLast(top);
            } else {
                deque.offerLast(current);
            }
        }
        return deque.stream().collect(Collectors.toList());
    }

    private static void sort(final List<Tuple> intervals) {
        intervals.sort(Comparator.comparing(Tuple::getFirst));
    }

    private static void print(final List<Tuple> intervals) {
        print(intervals, 0);
    }

    private static void print(final List<Tuple> intervals, int index) {
        if (index < intervals.size()-1) {
            System.out.print(intervals.get(index) + ", ");
            print(intervals, index+1);
        } else {
            System.out.println(intervals.get(index));
        }
    }

    public static void main(final String[] args) {
        final List<Tuple> intervals = new ArrayList<>();
        intervals.add(new Tuple(6, 8));
        intervals.add(new Tuple(10, 12));
        intervals.add(new Tuple(4, 6));
        intervals.add(new Tuple(1, 5));
        intervals.add(new Tuple(11, 15));
        intervals.add(new Tuple(3, 7));

        // Original intervals
        print(intervals);
        final List<Tuple> merged = merge(intervals);
        // Sorted intervals
        print(intervals);
        // Merged intervals
        print(merged);
    }
}
