package zcy.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LowerCommonAncestor
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-6 21:49
 * @Version 1.0
 */
public class LowerCommonAncestor_220906 {

    public static void main(String[] args) {

    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // o1,o2一定属于head为头的树
    public static Node lca(Node head, Node o1, Node o2) {
        Map<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        process(head, fatherMap);
        Set<Node> set1 = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);
        cur = o2;
        while (cur != fatherMap.get(cur)) {
            for (Node temp : set1) {
                if (temp == cur) {
                    return cur;
                }
            }
            cur = fatherMap.get(cur);
        }
        return head;
    }

    public static void process(Node head, Map<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }

    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        // 左右两颗树并不都有返回值
        return left != null ? left : right;
    }

}
