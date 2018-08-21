package tutorial;

import java.io.FileWriter;
import java.io.IOException;

public class Tutorial3A {
    public static int K = 25;
    public static final int MAX = 100;
    public static final int MIN = 0;
    public static int range = MAX - MIN + 1;

    public static void main(String[] args) {

        int[] comp_list = new int[K-10+1];
        int[] swap_list = new int[K-10+1];
        int[] partition_list = new int[K-10+1];

        int index = 0;
        for (int k = 10; k <= K; k++) {
            Integer[] arr = createArray(k);
            QuickSort qs = new QuickSort();
            qs.quicksort(arr);
            comp_list[index] = qs.num_comps;
            swap_list[index] = qs.num_swaps;
            index++;
        }

        writeToFile(comp_list, swap_list);

    } // end main

    /**
     * Creates an integer array of length 2^K, where K is passed as parameter.
     * Fills the array with random integers, in the range specified by the
     * global variables
     *
     * @param K used to determine array size of length 2^K
     * @return Integer Array
     */
    public static Integer[] createArray(int K) {
        int array_len = (int) Math.pow(2, K);
        Integer[] arr = new Integer[array_len];
        for (int i = 0; i < array_len; i++) {
            arr[i] = (int) (Math.random() * range) + (int) MIN;
        }
        return arr;
    } // end createArray

    public static void writeToFile(int[] comp_list, int[] swap_list) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("tut3.txt");

            for (int comp : comp_list) {
                fileWriter.append(String.valueOf(comp));
                fileWriter.append(" ");
            }
            for (int swap : swap_list) {
                fileWriter.append(String.valueOf(swap));
                fileWriter.append(" ");
            }

        } catch (IOException e) {
            System.out.println("ERROR!");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("ERROR FLUSHING/ CLOSING");
            }
        }
    }

} // end Tutorial3A
