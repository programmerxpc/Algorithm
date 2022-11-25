package zcy.tree;

/**
 * @ClassName IsBalancedTree_220906
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-6 16:35
 * @Version 1.0
 */
public class IsBalancedTree_220906 {

    public static void main(String[] args) {

    }

    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node (int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei){
            isBalanced = isB;
            height = hei;
        }
    }

    public static ReturnType process(Node x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

}
