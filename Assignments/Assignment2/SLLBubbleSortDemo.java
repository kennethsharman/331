
/**
 * Class demonstrates the bubbleSort method implemented in SLLBubbleSort.java.
 * SLLs of base type Integer and String are sorted and contents displayed.
 *
 * Date June 3, 2018
 *
 * @author Kenneth Sharman
 */
public class SLLBubbleSortDemo {

    public static void main(String[] args) {

        SLL<Integer> list1 = new SLL();

        for (int i = 10; i > 0; i--) {
            list1.addToTail(i);
        }
        System.out.println("Original Integer List");
        list1.printAll();
        SLLBubbleSort.BubbleSort(list1);
        System.out.println("\nSorted Integer List");
        list1.printAll();

        SLL<String> list2 = new SLL();

        list2.addToHead("d");
        list2.addToTail("c");
        list2.addToTail("b");
        list2.addToTail("a");

        System.out.println("\nOriginal String List");
        list2.printAll();
        SLLBubbleSort.BubbleSort(list2);
        System.out.println("\nSorted String List");
        list2.printAll();

    } // end main

} // end SLLBubbleSortDemo
