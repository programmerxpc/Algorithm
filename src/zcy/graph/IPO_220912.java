package zcy.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName IPO_220912
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-12 19:21
 * @Version 1.0
 */
public class IPO_220912 {

    public static class Node {
        int c;
        int p;

        public Node (int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int MaxMoney(int k, int m, int[] costs, int[] profits) {
        PriorityQueue<Node> minCostsQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> MaxProfitsQ = new PriorityQueue<>(new MaxProfitComparator());
        // 所有项目放入花费的小根堆
        for (int i = 0; i < costs.length; i++) {
            minCostsQ.add(new Node(costs[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostsQ.isEmpty() && minCostsQ.peek().c <= m) {
                MaxProfitsQ.add(minCostsQ.poll());
            }
            if (MaxProfitsQ.isEmpty()) {
                return m;
            }
            m += MaxProfitsQ.poll().p;
        }
        return m;
    }

}
