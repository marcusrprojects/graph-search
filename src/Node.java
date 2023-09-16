import java.util.Objects;

/**
 * The Node class represents a node in a graph with a unique value.
 */
public record Node(String value) {

    /**
     * Overrides toString() to return the string representation of the node's value.
     *
     * @return The string representation of the node's value.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the {@code o} argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

}
