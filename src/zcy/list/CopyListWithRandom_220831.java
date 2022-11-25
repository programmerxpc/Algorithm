package zcy.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CopyListWithRandom_220831
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-31 16:25
 * @Version 1.0
 */
public class CopyListWithRandom_220831 {

    public static void main(String[] args) {

    }

    static class Node {
        int value;
        Node next;
        Node rand;
        Node(int val) {
            value = val;
        }
    }

    public static Node copyListWithRand1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 在当前节点后克隆一个节点
        // 1 -> 2
        // 1 -> 1' -> 2
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // 设置克隆节点rand
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // 分离
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

}
