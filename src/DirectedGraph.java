import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph extends Graph{

    public DirectedGraph(Map<?, ?> adjList) {

        if (isNodeListMap(adjList)) {
            this.adjList = (Map<Node, List<Node>>) adjList;
        } else if (isStringListMap(adjList)) {
            this.adjList = new HashMap<>();
            convertStringMapToAdjacencyList((Map<String, List<String>>) adjList);
        } else {
            // Handle other cases (not Node-List or String-List maps)
            this.adjList = new HashMap<>();
        }
    }

    private boolean isNodeListMap(Map<?, ?> map) {
        if (map.isEmpty()) {
            return true; // Empty map is treated as a Node-List map
        }

        Map.Entry<?, ?> entry = map.entrySet().iterator().next();
        return entry.getKey() instanceof Node && entry.getValue() instanceof List<?>;
    }

    private boolean isStringListMap(Map<?, ?> map) {
        if (map.isEmpty()) {
            return false; // Empty map is not treated as a String-List map
        }

        Map.Entry<?, ?> entry = map.entrySet().iterator().next();
        return entry.getKey() instanceof String && entry.getValue() instanceof List<?>;
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

    // Convert a string map into an adjacency list to fit into the graph
    public void convertStringMapToAdjacencyList(Map<String, List<String>> stringAdjList) {
        for (Map.Entry<String, List<String>> entry : stringAdjList.entrySet()) {
            Node source = new Node(entry.getKey());
            addNode(source);

            List<Node> neighbors = new ArrayList<>();
            for (String neighborValue : entry.getValue()) {
                Node neighbor = new Node(neighborValue);
                neighbors.add(neighbor);
            }

            this.adjList.get(source).addAll(neighbors);
        }
    }

    @Override
    public String toString() {
        return adjList.entrySet().stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue().stream()
                        .map(Node::toString)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {

        Map<String, List<String>> smallAdjacencyList = new HashMap<>();
        List<String> aList = Arrays.asList("B", "C");
        List<String> bList = Arrays.asList("D", "E");
        List<String> cList = List.of("F");
        smallAdjacencyList.put("A", new ArrayList<>(aList));
        smallAdjacencyList.put("B", new ArrayList<>(bList));
        smallAdjacencyList.put("C", new ArrayList<>(cList));
        System.out.println(smallAdjacencyList);
        DirectedGraph smallGraph = new DirectedGraph(smallAdjacencyList);
        System.out.println(smallGraph);
    }
}
