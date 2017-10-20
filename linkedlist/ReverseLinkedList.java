/**
 * Reverse a singly link list
 */
public class ReverseLinkedList<T> {

    public static <T> ListNode<T> reverseR(ListNode<T> head) {
        if (head == null) {
            return null;
        }
        // Base case
        if (head.next == null) {
            return head;
        }

        final ListNode newHead = reverseR(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static <T> ListNode<T> reverse(ListNode<T> head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(final String[] args) {
        ListNode<Integer> next = null;
        ListNode<Integer> head = null;
        for (int i = 10; i > 0; --i) {
            head = new ListNode<>(i, next);
            next = head;
        }
        ListNode.print(head);

        head = reverseR(head);
        ListNode.print(head);

        head = reverse(head);
        ListNode.print(head);
    }
}
