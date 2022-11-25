package zcy.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @ClassName DFS_220908
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 16:51
 * @Version 1.0
 */
public class DFS_220908 {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
