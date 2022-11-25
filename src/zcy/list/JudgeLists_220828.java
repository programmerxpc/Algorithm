package zcy.list;

import java.util.Stack;

/**
 * @ClassName JudgeLists_220828
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-28 15:02
 * @Version 1.0
 */
public class JudgeLists_220828 {

    public static void main(String[] args) {
        Node head = new Node(2);
        Node node1 = new Node(3);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        System.out.println(judgeList1(head));
    }

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static boolean judgeList(Node head) {
        Node cur = head;
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.data);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty() && cur != null) {
            if (!stack.peek().equals(cur.data)) {
                return false;
            }
            stack.pop();
            cur = cur.next;
        }
        return true;
    }

    public static boolean judgeList1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // 找中间节点
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; // n2指向右部分第一个节点
        n1.next = null; // n1指向null
        Node n3 = null;
        while (n2 != null) { // 右部分反转
            n3 = n2.next; // n3 保存下一个节点
            n2.next = n1; // 右边下一个节点反转
            n1 = n2; // n1右移
            n2 = n3; // n2右移
        }
        n3 = n1; // n3保存最后一个节点
        n2 = head; // n2左边第一个节点
        boolean res = true;
        while (n1 != null && n2 != null) { // 判断回文
            if (n1.data != n2.data) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // 反转回去
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

}
