package zcy.graph;

import java.util.ArrayList;

/**
 * @ClassName Node
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 16:05
 * @Version 1.0
 */
public class Node {

    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
