package zcy.graph;

import java.util.*;

/**
 * @ClassName Sort_220908
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 18:26
 * @Version 1.0
 */
public class Sort_220908 {

    public static List<Node> sort(Graph graph) {
        // key: 某一个node    value： 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 入度为0的点，才能进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

}
