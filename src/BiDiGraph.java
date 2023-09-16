import java.util.*;
import java.util.stream.Collectors;

/**
 * A bidirectional graph representation.
 * <p>
 * This class models a bidirectional graph where each node can have edges
 * to other nodes in both forward and reverse directions.
 */

public class BiDiGraph extends DirectedGraph{

    /**
     * The reversed adjacency list representation of the bidirectional graph,
     * reversing relationships between nodes in the bidirectional graph.
     */
    Map<Node, List<Node>> reverseAdjList;

    /**
     * Constructs a bidirectional graph from a given adjacency list, updating {@link BiDiGraph#adjList}.
     * This function utilizes two helper functions to check whether it has
     * been initialized with strings or Node's.
     * If neither is true, adjacency list becomes a blank map.
     * Additionally, the adjacency list is reversed for {@link BiDiGraph#reverseAdjList}
     *
     * @param adjList The adjacency list to initialize the bidirectional graph.
     */
    public BiDiGraph(Map<?, ?> adjList) {
        super(adjList);
        reverse(this.adjList);
    }

    /**
     * Reverses the edges in the graph, creating a reverse graph: {@link BiDiGraph#reverseAdjList}.
     * <p>
     * This method inverts the direction of edges in the current graph to create a reverse graph,
     * which allows for bidirectional search.
     *
     * @param adjList The adjacency list to reverse.
     */
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

    /**
     * Retrieves a reversed node corresponding to a given node.
     *
     * @param value The value of the node to find the reversed counterpart for.
     * @return The reversed node, or {@code null} if not found.
     */
    public Node getReverseNode(String value) {
        for (Node node : reverseAdjList.keySet()) {

            if (Objects.equals(node.value(), value)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Retrieves the neighbors of a node in the reversed graph.
     *
     * @param node The node for which to retrieve reversed neighbors.
     * @return A list of reversed neighboring nodes.
     */
    public List<Node> getReversedNeighbors(Node node) {
        if (reverseAdjList.containsKey(node)) {
            return reverseAdjList.get(node);
        }
        return null;
    }

    /**
     * Generates a random adjacency list for testing purposes.
     * <p>
     * This method creates a random bidirectional graph with the specified number of nodes
     * and maximum edges per node for testing and experimentation.
     *
     * @param nodes          The number of nodes in the graph.
     * @param maxEdgesPerNode The maximum number of edges each node can have.
     * @return A randomly generated adjacency list.
     */
    @Override
    public Map<String, List<String>> generateRandomAdjacencyList(int nodes, int maxEdgesPerNode) {
        Map<String, List<String>> adjacencyList = new HashMap<>();

        for (int i = 0; i < nodes; i++) {
            String node = "Node" + i;
            int edges = (int) (Math.random() * maxEdgesPerNode) + 1;

            List<String> neighbors = new ArrayList<>();
            for (int j = 0; j < edges; j++) {
                int neighborIndex = (int) (Math.random() * nodes);
                String neighbor = "Node" + neighborIndex;
                neighbors.add(neighbor);

                // Add a backward edge from the neighbor to the current node
                adjacencyList.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(node);
            }

            adjacencyList.put(node, neighbors);
        }

        return adjacencyList;
    }

    /**
     * Converts the bidirectional graph to a string representation.
     * <p>
     * This method returns a string representation of both the forward and reverse graphs
     * in the bidirectional graph.
     *
     * @return A string representation of the bidirectional graph.
     */
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

    /**
     * The main method for running bidirectional search.
     * <p>
     * This method demonstrates bidirectional search on a bidirectional graph.
     *
     * @param args The command-line arguments (not used in this implementation).
     */
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