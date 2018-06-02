package homework1;

/**
 *
 * @author Ken Jun 1, 2018
 */
public class bubbleSortDemo {

    public static void main(String[] args) {
        // Create unsorted array and print contents
        int[] array1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Original Array");
        System.out.println(arrayContents(array1));

        // Copy unsorted array, bubbleSort, and print contents
        int[] array2 = new int[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);
        bubbleSort(array2);
        System.out.println("Bubble sorted array");
        System.out.println(arrayContents(array2));

        // Copy unsorted array, bubbleSortReverse, and print contents
        int[] array3 = new int[array1.length];
        System.arraycopy(array1, 0, array3, 0, array1.length);
        bubbleSortReverse(array3);
        System.out.println("Reverse bubble sorted array");
        System.out.println(arrayContents(array3));

    } // end main

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // Compare items
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    // Swap items
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    } // bubbleSort

    public static void bubbleSortReverse(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Compare items
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap items
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    } // bubbleSort

    public static StringBuilder arrayContents(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e).append(" ");
        }
        return sb;
    } // printArray

} // end bubbleSortDemo
