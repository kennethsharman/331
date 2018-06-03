

/**
 * Implements BubbleSort of a singly linked list, with type parameter T.
 *
 * Date: June 2, 2018
 *
 * @author Kenneth Sharman
 */
public class SLLBubbleSort {

    /**
     * Method performs bubble sort on list parameter. Largest elements are
     * bubbled to the tail of the list
     *
     * @param <T> generic base type of list. Must be Comparable
     * @param list singly linked list
     */
    public static <T extends Comparable<? super T>> void BubbleSort(SLL<T> list) {

        // Consider sublists where the tail moves closer to head after each iteration
        for (int i = list.getLength(); i > 1; i--) {

            // Initialize current node to start of the list
            SLLNode<T> currentNode = list.head;

            // Consider nodes in the sublist, from the head to the second last
            for (int j = 0; j < i - 1; j++) {

                // If current node.data > next node.data then swap data
                if (currentNode.info.compareTo(currentNode.next.info) > 0) {
                    T temp = currentNode.next.info;
                    currentNode.next.info = currentNode.info;
                    currentNode.info = temp;
                }
                // Increment current node to next node in list
                currentNode = currentNode.next;
            }
        }

    } // end BubbleSort

} // end SLLBubbleSort
