

/**
 * Class provides structure to a single node, which is to be instantiated as
 * part of a singly linked list in SSL.java
 *
 * Date: June 2, 2018
 *
 * @author Ken Sharman
 * @param <T> Comparable type parameter
 */
public class SLLNode<T> {

    public T info; // data
    public SLLNode<T> next; // reference to next node in list

    public SLLNode() {
        this(null, null);
    } // end constructor

    public SLLNode(T el) {
        this(el, null);
    } // end constructor

    public SLLNode(T el, SLLNode<T> ptr) {
        info = el;
        next = ptr;
    } // end constructor

} // end SLLNode<T>
