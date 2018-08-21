package tutorial;

import java.util.ArrayList;

public class Tutorial3 {

    public static final int K = 10;
    public static final int MAX = 100;
    public static final int MIN = 0;
    public static int range = MAX - MIN + 1;

    public static void main(String[] args) {

        // Create and array of length 2^K and fill with random integers
        Integer[] arr = createArray(K);
        //printArray(arr);

        QuickSort qs = new QuickSort();
        qs.quicksort(arr);
        //printArray(arr);
        //System.out.println("Number of comparisons: " + qs.num_comps);
        //System.out.println("Number of swaps: " + qs.num_swaps);

        ArrayList<Double> partition_sizes = qs.partition_size;
        //printRatios(partition_sizes);
        System.out.println("Average Partition Size: " + avgPartitionSize(partition_sizes));
        System.out.println("Minimum Ratio: " + minPart(partition_sizes));
        System.out.println("Maximum Ratio: " + maxPart(partition_sizes));

    } // end main
    
    public static double avgPartitionSize(ArrayList<Double> partition_list){
        double avg = 0;
        double sum = 0;
        for(int i = 0; i < partition_list.size(); i++ ) {
            sum += partition_list.get(i);
        }
        avg = sum / partition_list.size();
        return avg;
    } // end avgPartitionSizer
    
    public static double minPart(ArrayList<Double> partition_list) {
        double min = 1.0;
        for(int i = 0; i < partition_list.size(); i++ ) {
            if(partition_list.get(i) < min) {
                min = partition_list.get(i);
            }
        }
        return min;
    } // end minPart
    
    public static double maxPart(ArrayList<Double> partition_list) {
        double max = 0.0;
        for(int i = 0; i < partition_list.size(); i++ ) {
            if(partition_list.get(i) > max) {
                max = partition_list.get(i);
            }
        }
        return max;
    }

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

    /**
     * Prints the contents of array parameter
     *
     * @param arr type Integer Array
     */
    public static void printArray(Integer[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    } // end pritnArray

    public static void printRatios(ArrayList<Double> partition_sizes) {

        System.out.println("Partition Ratios:");
        
        for (int k = 0; k < partition_sizes.size(); k++) {
            System.out.print((partition_sizes.get(k) ) + " ");
            if (k % 21 == 0) {
                System.out.println();
            }
        }
        System.out.println();

    } // end printRatios

} // end Tutorial3
