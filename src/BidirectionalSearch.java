import java.util.*;

/**
 * The BidirectionalSearch class provides functionality to perform bidirectional search
 * on a bidirectional graph represented by a {@link BiDiGraph} object.
 */

public class BidirectionalSearch extends BiDiGraph {

    /**
     * The bidirectional graph on which the search will be performed.
     */
    BiDiGraph biDiGraph;

    /**
     * Constructs a BidirectionalSearch instance with the specified bidirectional graph.
     *
     * @param biDiGraph The bidirectional graph on which the search will be performed.
     */
    public BidirectionalSearch(BiDiGraph biDiGraph) {
        super();
        this.biDiGraph = biDiGraph;
    }

    /**
     * Performs bidirectional search between two specified nodes and returns the path, if found.
     *
     * @param src The starting node of the search.
     * @param dest   The target node to reach.
     * @return {@code true} if it finds a path from the src to dest, {@code false} otherwise.
     */
    public boolean search(String src, String dest) {
        HashSet<Node> forwardVisited = new HashSet<>();
        HashSet<Node> reversedVisited = new HashSet<>();

        LinkedList<Node> forwardQueue = new LinkedList<>();
        LinkedList<Node> reversedQueue = new LinkedList<>();

        Node srcNode = biDiGraph.getNode(src);
        Node destNode = biDiGraph.getReverseNode(dest);

        forwardQueue.add(srcNode);
        reversedQueue.add(destNode);

        while (!forwardQueue.isEmpty() && !reversedQueue.isEmpty()) {
            System.out.println("while ran!");

            // Forward search
            Node currentForward = forwardQueue.poll();
            forwardVisited.add(currentForward);

            if (reversedVisited.contains(currentForward)) {
                return true;  // Intersection found
            }

            for (Node neighbor : biDiGraph.getNeighbors(currentForward)) {
                if (!forwardVisited.contains(neighbor)) {
                    forwardQueue.add(neighbor);
                }
            }

            // Reversed search
            Node currentReversed = reversedQueue.poll();
            reversedVisited.add(currentReversed);

            if (forwardVisited.contains(currentReversed)) {
                return true;  // Intersection found
            }

            for (Node neighbor : biDiGraph.getReversedNeighbors(currentReversed)) {
                if (!reversedVisited.contains(neighbor)) {
                    reversedQueue.add(neighbor);
                }
            }
        }

        return false;  // No intersection found
    }

    /**
     * Returns a string representation of the bidirectional search results.
     *
     * @return A string containing the search results, including the path if found.
     */
    @Override
    public String toString() {
        return biDiGraph.toString(); // Assuming you want to print the forward graph
    }

    /**
     * The main method that demonstrates how to use bidirectional search.
     *
     * @param args Command-line arguments
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
        BidirectionalSearch smallGraphSearch = new BidirectionalSearch(smallGraph);
        boolean canSearchAE = smallGraphSearch.search("A", "E");
        System.out.println(smallGraphSearch);
        System.out.println(canSearchAE);
    }
}
