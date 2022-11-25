package zcy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName SerializeAndReconstructTree
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-7 16:05
 * @Version 1.0
 */
public class SerializeAndReconstructTree_220907 {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            value = val;
        }
    }

    // 以head为头的树，请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

}
