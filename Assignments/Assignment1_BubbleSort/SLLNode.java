package homework1;

/**
 *
 * @author Ken
 * @param <T>
 */
public class SLLNode<T> {

    public T info;
    public SLLNode<T> next;

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
