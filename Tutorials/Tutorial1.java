package tutorial;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Purpose: Empirical investigation of binary search complexity
 *          May need to allocate more memory using: java -Xmx8g Tutorial1
 *          For an input size N = 2^k, k=5,6,...,25 determine the average number
 *          of comparisons for a binary search of a sorted array [0,1,...,25].
 * 
 *          The results are saved to a text file, which was later used to create
 *          a plot of the results.
 * 
 *          Big-Theta was confirmed to be logarithmic.
 * 
 * May 28, 2018
 * @author Ken Sharman
 */
public class Tutorial1 {

    public static void main(String[] args) {

        // Create an array [2^5, 2^6, ..., 2^30]
        int[] nvals = new int[21];
        for (int k = 5; k <= 25; k += 1) {
            nvals[k - 5] = (int) Math.pow(2.0, k);
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("data.txt");

            // for (int N : array) append(N) to fileWriter
            for (int N : nvals) {
                fileWriter.append(String.valueOf(N));
                fileWriter.append(" ");
            }

            // for each N=2^k
            for (int N : nvals) {

                // Make array [0, 1, ..., N-1]
                int[] array = sequenceGenerator(N);
                int tot_comps = 0;

                // for key = 
                for (int key = 0; key <= N; key++) {
                    tot_comps += binarySearch(array, key)[1];
                }
                int av_comp = tot_comps / N;
                fileWriter.append(String.valueOf(av_comp));
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
            comp += 1;
            mid = (lo + hi) / 2;

            if (key < arr[mid]) {
                comp += 1;
                hi = mid - 1;
            } else if (arr[mid] < key) {
                comp += 2;
                lo = mid + 1;
            } else {
                comp += 2;
                return zipResults(mid, comp); // success
            }
        }
        comp++;
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

} // end Tutorial1
