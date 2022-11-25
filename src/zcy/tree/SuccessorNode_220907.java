package zcy.tree;

/**
 * @ClassName SuccessorNode_220907
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-7 15:39
 * @Version 1.0
 */
public class SuccessorNode_220907 {

    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            value = val;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { // 无右树
            Node parent = node.parent;
            while (parent != null && parent.left != node) { // 当前节点是其父亲节点右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
