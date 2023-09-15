import java.util.List;
import java.util.Map;

public abstract class Graph {

    protected Map<Node, List<Node>> adjList;

    public Graph(Map<Node, List<Node>> adjList) {
        this.adjList = adjList;
    }

    public abstract Node addNode(Node node);

    public abstract Node addNode(String value);

    public abstract void addEdge(String src, String dest);

    public abstract List<Node> getNeighbors(Node node);

    public abstract Node getNode(String value);

}