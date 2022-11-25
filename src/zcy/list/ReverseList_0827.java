package zcy.list;

/**
 * @ClassName reverseList_0827
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-27 16:05
 * @Version 1.0
 */
public class ReverseList_0827 {

    public static void main(String[] args) {
//        Node node = new Node(1);
//        Node node1 = new Node(2);
//        Node node2 = new Node(3);
//        node.next = node1;
//        node1.next = node2;
//        node2.next = null;

        DNode node = new DNode(1);
        DNode node1 = new DNode(2);
        DNode node2 = new DNode(3);
        node.next = node1;
        node.pre = null;
        node1.next = node2;
        node1.pre = node;
        node2.next = null;
        node2.pre = node1;

        DNode head = reverseDNode(node);
        DNode temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }

    static class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    static class DNode {
        private Object data;
        private DNode pre;
        private DNode next;

        public DNode(Object data) {
            this.data = data;
        }
    }

    public static Node reverseNode(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            // 保存当前节点下一个节点
            Node temp = cur.next;
            // 指向前一个节点（头结点指向null）
            cur.next = pre;
            // pre和cur后移
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static DNode reverseDNode(DNode head) {
        DNode cur = head;
        DNode pre = null;
        while (cur != null) {
            // 交换后向指针和前向指针
            DNode temp = cur.next;
            cur.next = pre;
            cur.pre = temp;
            // pre和cur后移
            pre = cur;
            cur = temp;
        }
        return pre;
    }

}
