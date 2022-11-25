package zcy.list;

/**
 * @ClassName PrintListCommon_220828
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-28 10:18
 * @Version 1.0
 */
public class PrintListCommon_220828 {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(5);
        node1.next = node2;
        node2.next = node3;

        Node node4 = new Node(0);
        Node node5 = new Node(2);
        Node node6 = new Node(3);
        Node node7 = new Node(5);
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        printListCommon(node1, node4);
    }

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void printListCommon(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != null && cur2 != null) {
            if (cur1.data < cur2.data) {
                cur1 = cur1.next;
            }
            if (cur1.data > cur2.data) {
                cur2 = cur2.next;
            }
            if (cur1.data == cur2.data) {
                System.out.println(cur1.data + " ");
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
    }

}
