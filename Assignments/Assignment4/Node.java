
package hw4;

public class Node {

    int data;
    Node predecessor;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node pointer) {
        this.data = data;
        this.predecessor = pointer;
    }
    
} // end class Node
