import java.util.List;
import java.util.Map;

/**
 * Represents an abstract graph data structure.
 *
 * <p>
 * This class defines the basic structure and operations that are common to all
 * types of graphs. Concrete graph classes, such as {@link DirectedGraph} and
 * {@link BiDiGraph}, should extend this class to provide specific
 * implementations.
 * </p>
 *
 * <p>
 * The graph is represented using an adjacency list, where each node is mapped
 * to its list of neighboring nodes.
 * </p>
 *
 */
public abstract class Graph <T extends Node> {

    protected Map<Node, List<T>> adjList;

    /**
     * Adds a node to the graph.
     *
     * @param node The node to be added.
     */
    public abstract void addNode(T node);

    /**
     * Adds a node to the graph.
     *
     * @param value The value of the node to be added.
     */
    public abstract void addNode(String value);

    /**
     * Adds an edge between two nodes in the graph.
     *
     * @param src The source node.
     * @param dest   The target node.
     */
    public abstract void addEdge(String src, String dest);

    /**
     * Returns the neighbors of a given node.
     *
     * @param node The node whose neighbors are requested.
     * @return A list of neighboring nodes.
     */
    public abstract List<T> getNeighbors(T node);

    /**
     * Returns the given node or null.
     *
     * @param value The value of the node of interest.
     * @return given node or null if this node does not exist yet.
     */
    public abstract T getNode(String value);


    /**
     * Converts a string map into an adjacency list utilizing the generic Node type.
     *
     * @param stringAdjList adjacency list using strings
     */
    public abstract void convertStringMapToAdjacencyList(Map<String, List<String>> stringAdjList);

}