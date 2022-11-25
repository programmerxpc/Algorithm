package zcy.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName BFS_220908
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 16:30
 * @Version 1.0
 */
public class BFS_220908 {

    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
