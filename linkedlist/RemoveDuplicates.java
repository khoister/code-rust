import java.util.HashSet;
import java.util.Set;

/**
 * Remove duplicates from a linked list
 */
public class RemoveDuplicates {

    public static <T> ListNode deDuplication(final ListNode<T> head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }

        final Set<T> set = new HashSet<>();
        set.add(head.data);

        ListNode<T> prev = head;
        ListNode<T> curr = head.next;
        while (curr != null) {
            if (set.contains(curr.data)) {
                // Duplicate. Delete node
                prev.next = curr.next;
            } else {
                prev = curr;
                set.add(curr.data);
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(final String[] args) {
        ListNode<Integer> node = new ListNode<Integer>(21);
        node = new ListNode<Integer>(14, node);
        node = new ListNode<Integer>(28, node);
        node = new ListNode<Integer>(28, node);
        node = new ListNode<Integer>(14, node);
        node = new ListNode<Integer>(7, node);

        ListNode.print(node);
        node = deDuplication(node);
        ListNode.print(node);
    }
}
