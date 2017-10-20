/**
 * Node for a singly linked list
 */
public class ListNode<T> {
    public T data;
    public ListNode<T> next = null;

    public ListNode(final T data) {
        this.data = data;
    }

    public ListNode(final T data, final ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public static <T> void print(ListNode<T> node) {
        if (node != null && node.next == null) {
            System.out.println(node.data);
            return;
        }
        System.out.print(node.data + " -> ");
        print(node.next);
    }
}
