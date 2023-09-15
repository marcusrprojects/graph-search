import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiDiGraph extends DirectedGraph{

    Map<Node, List<Node>> reverseAdjList;

    public BiDiGraph(Map<Node, List<Node>> adjList) {
        super(adjList);
        reverse(adjList);
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


    public List<Node> getReversedNeighbors(Node node) {
        if (!reverseAdjList.containsKey(node)) {
            return reverseAdjList.get(node);
        }
        return null;
    }
}