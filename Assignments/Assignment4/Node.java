package hw4;

/**
 * Class is used in BFS method in ShortestPath.java. For each vertex visited in
 * the search, a Node is created and added to a list, in order to compile the
 * shortest path once the end vertex is found.
 *
 * June 24, 2018
 *
 * @author Kenneth Sharman
 */
public class Node {

    int data; // vertex number
    Node predecessor; // pointer to previous vertex

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node pointer) {
        this.data = data;
        this.predecessor = pointer;
    }

} // end class Node
