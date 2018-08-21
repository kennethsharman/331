package tutorial;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Purpose: Empirical investigation of binary search complexity
 * 
 * May 28, 2018
 * @author Ken Sharman
 */
public class Tutorial1A {

    public static void main(String[] args) {

        int n = (int)Math.pow(2,30); // number of elements in array
        int[] array = sequenceGenerator(n); // array[0 ... n-1]
        int key = 7; // Element to be found

        int[] results = binarySearch(array, key); // returns (key_pos, num_comp)
        int key_pos = results[0];
        int num_comp = results[1];

        System.out.println(arrayContents(array));
        System.out.println("The key is " + key + ".\n");
        System.out.println(formatResults(key_pos, num_comp));
        
        /*FileWriter fileWriter = null;
        
        try {
        fileWriter = new FileWriter("data.txt");
        
        for(int i=0; i<10; i++) {
        fileWriter.append(String.valueOf(i));
        fileWriter.append(" ");
        }
        for(int i=0; i<10; i++) {
        fileWriter.append(String.valueOf(binarySearch(array, key)[1]));
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
        }*/
        

    } // end main

    /**
     * Generates an int array with elements 0 ... n-1
     *
     * @param n is number of elements in array- type int
     * @return int array
     */
    private static int[] sequenceGenerator(int n) {

        int[] sequence = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            sequence[i] = i;
        }

        return sequence;

    } // end sequenceGenerator

    /**
     * If the key is in the array the position of the first occurrence will be
     * returned, along with the number of comparisons made in the search. If the
     * key is not in the array, -1 will be returned (along with comparisons).
     *
     * @param arr is the int array to be searched
     * @param key of type int
     * @return int array containing key position and number of comparisons
     */
    private static int[] binarySearch(int[] arr, int key) {

        int lo = 0, mid, hi = arr.length - 1;
        int comp = 0;

        while (lo <= hi) {

            mid = (lo + hi) / 2;

            if (key < arr[mid]) {
                comp++;
                hi = mid - 1;
            } else if (arr[mid] < key) {
                comp++;
                lo = mid + 1;
            } else {
                return zipResults(mid, comp); // success
            }
        }

        return zipResults(-1, comp); // key not found

    } // end binarySearch

    /**
     * Creates a two-element int array from the parameters. Used to return two
     * values from binarySearch method
     *
     * @param pos of type int
     * @param comp of type int
     * @return 2 element int array
     */
    private static int[] zipResults(int pos, int comp) {

        int[] arr = {pos, comp};
        return arr;

    } // end zipResults

    private static StringBuilder formatResults(int pos, int comp) {

        StringBuilder sb = new StringBuilder();

        if (pos >= 0) {
            sb.append("The key was found at position ").append(pos).append(".\n");
        } else {
            sb.append("They key was not found in the array.\n");
        }

        sb.append("The numbers of comparisons was ").append(comp).append(".\n");

        return sb;

    } // end formatResults

    /**
     * Returns a StringBuilder with contents of array parameter.
     *
     * @param arr of type int array
     * @return StringBuilder
     */
    private static StringBuilder arrayContents(int[] arr) {

        StringBuilder sb = new StringBuilder();

        sb.append("The array has the following elements: ").append("\n{");
        for (int i : arr) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("}");

        return sb;

    } // end arrayContents

} // end Tutorial1A
