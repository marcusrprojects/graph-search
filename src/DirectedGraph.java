import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a directed graph data structure using adjacency lists.
 * This class allows you to create, manipulate, and traverse directed graphs.
 */

public class DirectedGraph extends Graph{

    /**
     * Constructs a new directed graph with the provided adjacency list,
     * updating {@link DirectedGraph#adjList}.
     * This function utilizes two helper functions to check whether it has
     * been initialized with strings or Node's.
     * If neither is true, adjacency list becomes a blank map.
     *
     * @param adjList The adjacency list representing the directed graph.
     *                It must be a map.
     * @see DirectedGraph#isStringListMap(Map)
     * @see DirectedGraph#convertStringMapToAdjacencyList(Map)
     */
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

    /**
     * Checks if the given map represents a Node-List adjacency list.
     *
     * This method determines if the map contains nodes as keys and lists of nodes as values.
     *
     * @param map The map to check.
     * @return {@code true} if the map represents a Node-List adjacency list, {@code false} otherwise.
     */
    private boolean isNodeListMap(Map<?, ?> map) {
        if (map.isEmpty()) {
            return true; // Empty map is treated as a Node-List map
        }

        Map.Entry<?, ?> entry = map.entrySet().iterator().next();
        return entry.getKey() instanceof Node && entry.getValue() instanceof List<?>;
    }

    /**
     * Checks if the given map represents a String-List adjacency list.
     * <p>
     * This method determines if the map contains strings as keys and lists of strings as values.
     *
     * @param map The map to check.
     * @return {@code true} if the map represents a String-List adjacency list, {@code false} otherwise.
     */
    private boolean isStringListMap(Map<?, ?> map) {
        if (map.isEmpty()) {
            return false; // Empty map is not treated as a String-List map
        }

        Map.Entry<?, ?> entry = map.entrySet().iterator().next();
        return entry.getKey() instanceof String && entry.getValue() instanceof List<?>;
    }

    /**
     * Adds a node to the directed graph.
     *
     * @param node The node to add.
     */
    @Override
    public void addNode(Node node) {
        if (!adjList.containsKey(node)) {
            adjList.put(node, new ArrayList<>());
        }
    }

    /**
     * Adds a node with the specified value to the directed graph.
     *
     * @param value The value of the node to add.
     */
    @Override
    public void addNode(String value) {
        if (getNode(value) == null) {
            Node node = new Node(value);
            adjList.put(node, new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge between two nodes in the graph.
     *
     * @param src The source node.
     * @param dest   The destination node.
     */
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

    /**
     * Returns the neighbors of a given node in the directed graph.
     *
     * @param node The node for which to retrieve neighbors.
     * @return A list of neighboring nodes.
     */
    @Override
    public List<Node> getNeighbors(Node node) {
        if (adjList.containsKey(node)) {
            return adjList.get(node);
        }
        return null;
    }

    /**
     * Retrieves a node by its value from the directed graph.
     *
     * @param value The value of the node to retrieve.
     * @return The node with the specified value, or {@code null} if not found.
     */
    @Override
    public Node getNode(String value) {
        for (Node node : adjList.keySet()) {

            if (Objects.equals(node.value(), value)) {
                return node;
            }
        }

        return null;
    }


    /**
     * Converts a String-List adjacency list to the internal Node-List representation.
     * <p>
     * This method takes a map with strings as keys and lists of strings as values and
     * converts it into the Node-List format used by the directed graph.
     *
     * @param stringAdjList The String-List adjacency list to convert.
     */
    @Override
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

    /**
     * Generates a random adjacency list for testing purposes.
     * <p>
     * This method creates a random directed graph with the specified number of nodes
     * and maximum edges per node for testing and experimentation.
     *
     * @param nodes          The number of nodes in the graph.
     * @param maxEdgesPerNode The maximum number of edges each node can have.
     * @return A randomly generated adjacency list.
     */
    public Map<String, List<String>> generateRandomAdjacencyList(int nodes, int maxEdgesPerNode) {
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

    /**
     * Returns a string representation of the directed graph.
     * <p>
     * This method generates a human-readable string that represents the directed graph,
     * including its nodes and edges in the forward direction.
     *
     * @return A string representation of the directed graph.
     */
    @Override
    public String toString() {
        return adjList.entrySet().stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue().stream()
                        .map(Node::toString)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
    }


    /**
     * The main method for testing the DirectedGraph class.
     * <p>
     * This method is the entry point for running the DirectedGraph class as a standalone application.
     * It demonstrates the usage of the DirectedGraph class by creating a sample graph and printing its contents.
     *
     * @param args The command-line arguments (not used in this example).
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
        DirectedGraph smallGraph = new DirectedGraph(smallAdjacencyList);
        System.out.println(smallGraph);
    }
}
