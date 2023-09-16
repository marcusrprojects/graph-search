# Graph Data Structures and Algorithms with Java

This repository contains a Java implementation of various graph algorithms and data structures. Below is an overview of the key classes and their functionalities.

## Node

- **Class Name:** Node
- **Purpose:** Represents a single node in a graph.
- **Description:** This class defines a simple node with a value. It's used as the basic building block for constructing graphs.

## Graph

- **Class Name:** Graph
- **Purpose:** Represents a general undirected graph.
- **Description:** The Graph class provides methods to create, manipulate, and visualize an undirected graph. It uses a map-based adjacency list representation for efficient graph operations.

## DirectedGraph

- **Class Name:** DirectedGraph
- **Purpose:** Represents a directed graph.
- **Description:** This class extends the Graph class and adds support for directed edges. It can be used to create, modify, and analyze directed graphs.

## BiDiGraph

- **Class Name:** BiDiGraph
- **Purpose:** Represents a bidirectional graph.
- **Description:** The BiDiGraph class extends DirectedGraph and provides functionality for working with bidirectional graphs. It supports both forward and reverse edges between nodes.

## BidirectionalSearch

- **Class Name:** BidirectionalSearch
- **Purpose:** Implements a bidirectional search algorithm for finding paths in graphs.
- **Description:** This class effectively uses runs a pair of BFS's (Breadth-First Search) concurrently to efficiently find paths between two nodes in a graph. It checks for the existence of a path connecting a source and destination node.

## How to Use

- Clone the repository.
- Import the Java classes into your project.
- Create instances of the Node, Graph, DirectedGraph, or BiDiGraph classes to work with graphs.
- Utilize the provided algorithms and methods for graph operations.

## Contributor

- Marcus Ribeiro
