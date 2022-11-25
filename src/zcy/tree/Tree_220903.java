package zcy.tree;

import java.util.*;

/**
 * @ClassName preOrderUnRecur
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-3 19:59
 * @Version 1.0
 */
public class Tree_220903 {

    public static void main(String[] args) {

    }

    static class Node {
        private int value;
        private Node left;
        private Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value);
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }


    public static void posOrderUnRecur1(Node head) {
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.empty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.empty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
    }

    public static void inOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.empty() || head != null) {
                if (head != null) { // 把左边界进栈
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + "");
                    head = head.right;
                }
            }
        }
    }

    public static void w(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static int w1(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
                max = Math.max(max, curLevelNodes);
            } else {
                curLevel++;
                curLevelNodes = 1;
                max = Math.max(max, curLevelNodes);
            }
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel+1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel+1);
                queue.add(cur.right);
            }
        }
        return max;
    }

    public static int preValue = Integer.MIN_VALUE;

    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean isLeftBst = checkBST(head.left);
        if (!isLeftBst) {
            return false;
        }
        if (head.value < preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }

    public static boolean checkBST2(Node head) {
        List<Node> inOrderList = new ArrayList<>();
        process2(head, inOrderList);
        for (int i = 1; i < inOrderList.size(); i++) {
            if (inOrderList.get(i-1).value > inOrderList.get(i).value) {
                return false;
            }
        }
        return true;
    }
    public static void process2(Node head, List<Node> inOrderList) {
        if (head == null) {
            return;
        }
        process2(head.left, inOrderList);
        inOrderList.add(head);
        process2(head.right, inOrderList);
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static class ReturnData {
        private boolean isBST;
        private int min;
        private int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static ReturnData process(Node x) {
        if (x == null) {
            return null;
        }
        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);
        int min = x.value;
        int max = x.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || x.value >= rightData.min)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }

    public static boolean isF(Node head) {
        if (head == null) {
            return true;
        }
        ReturnInfo info = processF(head);
        return info.nodes == ((1 << info.height) - 1);
    }
    static class ReturnInfo {
        private int height;
        private int nodes;

        public ReturnInfo(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }
    public static ReturnInfo processF(Node x) {
        if (x == null) {
            return new ReturnInfo(0, 0);
        }
        ReturnInfo leftData = processF(x.left);
        ReturnInfo rightData = processF(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnInfo(height, nodes);
    }

}
