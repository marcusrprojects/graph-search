import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DirectedGraph extends Graph{


    public DirectedGraph(Map<Node, List<Node>> adjList) {
        super(adjList);
    }

    @Override
    public Node addNode(Node node) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<>());
        }
        return node;
    }

    @Override
    public Node addNode(String value) {
//        node is already added
        if (getNode(value) != null) {
            return null;
        }
        Node node = new Node(value);
        adjList.put(node, new ArrayList<>());
        return node;
    }

    @Override
    public void addEdge(String src, String dest) {

        Node srcNode = getNode(src);
        if (getNode(src) == null) {
            this.addNode(src);
            srcNode = getNode(src);
        }
        Node destNode = getNode(dest);
        if (getNode(dest) == null) {
            this.addNode(dest);
            destNode = getNode(src);
        }

        List<Node> neighbors = getNeighbors(srcNode);
        for (Node neighbor : neighbors) {
            if (neighbor == destNode) {
                return;
            }
        }

        adjList.get(srcNode).add(destNode);
    }

    @Override
    public List<Node> getNeighbors(Node node) {
        if (!adjList.containsKey(node)) {
            return adjList.get(node);
        }
        return null;
    }

    @Override
    public Node getNode(String value) {
        for (Node node : adjList.keySet()) {

            if (Objects.equals(node.getValue(), value)) {
                return node;
            }
        }

        return null;
    }
}
