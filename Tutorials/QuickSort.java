package tutorial;

import java.util.ArrayList;

/**
 * CPSC 331, Spring 2018
 *
 * Quicksort code adapted from: Data Structures and Algorithms in Java, by Adam
 * Drozdek Available online at: http://www.mathcs.duq.edu/drozdek/DSinJava/
 */
public class QuickSort {

    public int num_comps;
    public int num_swaps;
    public ArrayList<Double> partition_size = new ArrayList<>();

    public void swap(Object[] a, int e1, int e2) {
        num_swaps++;
        Object tmp = a[e1];
        a[e1] = a[e2];
        a[e2] = tmp;
    }

    private <T extends Comparable<? super T>> void quicksort(T[] data, int first, int last) {
        int lower = first + 1, upper = last;
        
        // Swap middle element (pivot) with first element
        swap(data, first, (first + last) / 2);
        T bound = data[first];
        while (lower <= upper) {
            num_comps += 4;
            while (bound.compareTo(data[lower]) > 0) {
                lower++;
            }
            while (bound.compareTo(data[upper]) < 0) {
                upper--;
            }
            if (lower < upper) {
                swap(data, lower++, upper--);
            } else {
                lower++;
            }
        }
        Double temp1 = (double)(upper - first) / (last - first + 1);
        Double temp2 = (double)(last - upper) / (last - first + 1);
        swap(data, upper, first); // restore pivot
        num_comps += 2;
        
        if (first < upper - 1) {
            quicksort(data, first, upper - 1);
        }
        if (upper + 1 < last) {
            quicksort(data, upper + 1, last);
        }
        
        if(temp1 > temp2) {
            if(temp1 > 0.5)
                partition_size.add(temp1);
        }
        else {
            if(temp2 > 0.5)
                partition_size.add(temp2);
        }
        
    }

    public <T extends Comparable<? super T>> void quicksort(T[] data) {
        num_comps += 2; // 1 comp for 'if' and 1 for exiting the for loop
        if (data.length < 2) {
            return;
        }
        int max = 0;
        // find the largest element and put it at the end of data;
        for (int i = 1; i < data.length; i++) {
            num_comps += 2;
            if (data[max].compareTo(data[i]) < 0) {
                max = i;
            }
        }
        swap(data, data.length - 1, max);    // largest el is now in its
        quicksort(data, 0, data.length - 2); // final position;
    }
}
