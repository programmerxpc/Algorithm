package zcy.graph;

/**
 * @ClassName Edge
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 16:05
 * @Version 1.0
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
