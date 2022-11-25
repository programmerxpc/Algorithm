package zcy.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName Prim_220909
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-9 16:54
 * @Version 1.0
 */
public class Prim_220909 {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> prim(Graph graph) {
        // 依次解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        HashSet<Node> set = new HashSet<>();

        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) { // 随便挑一个点
            if (!set.contains(node)) { // node是开始点
                set.add(node);
                for (Edge edge : node.edges) { // 由一个点,解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    if (!set.contains(toNode)) { // 不含有的时候就是新的点
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

}
