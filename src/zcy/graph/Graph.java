package zcy.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Graph
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-8 16:02
 * @Version 1.0
 */
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
