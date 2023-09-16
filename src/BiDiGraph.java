import java.util.*;
import java.util.stream.Collectors;

public class BiDiGraph extends DirectedGraph{

    Map<Node, List<Node>> reverseAdjList;

    public BiDiGraph(Map<?, ?> adjList) {
        super(adjList);
        reverse(this.adjList);
    }

    public void reverse(Map<Node, List<Node>> adjList){
        // Create a new adjacency list to store the reversed edges
        Map<Node, List<Node>> reversedAdjList = new HashMap<>();

        // Iterate through the existing adjacency list
        for (Node srcNode : adjList.keySet()) {
            List<Node> neighbors = adjList.get(srcNode);

            // Iterate through the neighbors of the source node
            for (Node destNode : neighbors) {
                // Add the reverse edge to the reversed adjacency list
                reversedAdjList.computeIfAbsent(destNode, k -> new ArrayList<>()).add(srcNode);
            }
        }

        // Replace the current adjacency list with the reversed one
        this.reverseAdjList = reversedAdjList;
    }

    public Node getReverseNode(String value) {
        for (Node node : reverseAdjList.keySet()) {

            if (Objects.equals(node.getValue(), value)) {
                return node;
            }
        }
        return null;
    }

    public List<Node> getReversedNeighbors(Node node) {
        if (reverseAdjList.containsKey(node)) {
            return reverseAdjList.get(node);
        }
        return null;
    }

    public static Map<String, List<String>> generateLargeAdjacencyList(int nodes, int maxEdgesPerNode) {
        Map<String, List<String>> adjacencyList = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            String node = "Node" + i;
            int edges = (int) (Math.random() * maxEdgesPerNode) + 1;

            List<String> neighbors = new ArrayList<>();
            for (int j = 0; j < edges; j++) {
                int neighborIndex = (int) (Math.random() * nodes);
                neighbors.add("Node" + neighborIndex);
            }

            adjacencyList.put(node, neighbors);
        }

        return adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Call parent class's toString method to print the forward graph
        String forwardGraph = super.toString();
        sb.append("Forward graph:\n").append(forwardGraph);

        sb.append("\nReverse graph:\n");
        for (Node node : reverseAdjList.keySet()) {
            sb.append(node).append(" -> ");
            List<Node> neighbors = reverseAdjList.get(node);
            if (neighbors != null && !neighbors.isEmpty()) {
                sb.append(neighbors.stream().map(Object::toString).collect(Collectors.joining(", ")));
            }
            sb.append("\n");
        }

        return sb.toString();
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
        BiDiGraph smallGraph = new BiDiGraph(smallAdjacencyList);
        System.out.println(smallGraph);
    }
}