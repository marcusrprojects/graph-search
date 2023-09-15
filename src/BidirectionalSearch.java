import java.util.HashSet;
import java.util.LinkedList;

public class BidirectionalSearch{

    BiDiGraph biDiGraph;

    public BidirectionalSearch(BiDiGraph biDiGraph) {
        this.biDiGraph = biDiGraph;
    }

    public boolean search(String src, String dest) {
        HashSet<Node> forwardVisited = new HashSet<>();
        HashSet<Node> reversedVisited = new HashSet<>();

        LinkedList<Node> forwardQueue = new LinkedList<>();
        LinkedList<Node> reversedQueue = new LinkedList<>();

        Node srcNode = biDiGraph.addNode(src);
        Node destNode = biDiGraph.addNode(dest);

        forwardQueue.add(srcNode);
        reversedQueue.add(destNode);

        while (!forwardQueue.isEmpty() && !reversedQueue.isEmpty()) {
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
}
