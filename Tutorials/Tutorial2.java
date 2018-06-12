package tutorial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Compare a super basic hashing method to the built-in hashCode method. Data
 * being hashed is a text file with 11000+ words in alphabetical order. The
 * maximum number of collisions and the average number of collisions are
 * analyzed.
 *
 * June 11, 2018
 *
 * @author Ken Sharman
 */
public class Tutorial2 {

    public static void main(String[] args) {

        // Read words from the file and determine table size
        ArrayList<String> words = readFile("words.txt");
        int tableSize = calcTableSize(words);

        // Declare two tables (one for each hashing method) and fill with zeros
        int[] table1 = new int[tableSize];
        int[] table2 = new int[tableSize];
        Arrays.fill(table1, 0);
        Arrays.fill(table2, 0);

        // Populate table1 with hash method defined below and print results
        populateTable(words, table1, "Hash Function");
        printReport(table1, "Hash Function");

        // Populate table2 with standard hashCode method and print results
        populateTable(words, table2, "HashCode Method");
        printReport(table2, "HashCode Method");

    } // end main

    /**
     * Reads a file and puts each line into an ArrayList as a String
     *
     * @param filename of type String
     * @return ArrayList<String>
     */
    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
            reader.close();
            return wordList;
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s", filename);
            return null;
        }
    } // end readfile

    public static int calcTableSize(ArrayList<String> list) {
        int N = list.size();
        return closestPrime(N * 100 / 65);
    } // end calcTableSize

    /**
     * Calculates and returns the closest prime number to the parameter. -1 is
     * returned if the search fails.
     *
     * @param num focal point of search. Type int
     * @return closest int prime number
     */
    public static int closestPrime(int num) {
        // If parameter is prime number return it
        if (isPrime(num)) {
            return num;
        }
        // Iterate through number num-1 to 2 and return first that is prime
        for (int i = num; i <= i * 2; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1; // return -1 if search fails
    } // end closestPrime

    public static boolean isPrime(int num) {
        // If parameter is less than 2 then it cannot be prime
        if (num <= 1) {
            return false;
        }
        // Test divisibility of parameter by positive integers from 2 to num-1
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true; // return success
    } // end isPrime

    /**
     * For each word in the ArrayList; determine its hash value, modulo the
     * result by the table size and increment the corresponding entry in the
     * table by 1. Offset negative indices by the table size to obtain a
     * positive index.
     *
     * @param list String ArrayList containing the words
     * @param table int array
     * @param method String indicating with hashing method to evaluate
     */
    public static void populateTable(ArrayList<String> list, int[] table, String method) {
        for (String word : list) {
            int hashValue;
            if (method.equals("Hash Function")) {
                hashValue = hash(word);
            } else {
                hashValue = word.hashCode();
            }
            int index = hashValue % table.length;
            if (index < 0) {
                index += table.length;
            }
            table[index] += 1;
        }
    } // end populateTable

    public static void printReport(int[] table, String method) {
        DecimalFormat df = new DecimalFormat("###.##");

        System.out.println("\t" + method.toUpperCase());
        System.out.println(String.format("%-30s", "Table Size:") + table.length);
        System.out.println(String.format("%-30s", "Percentage of empty slots:")
                + df.format(percentEmpty(table)) + "%");
        System.out.println(String.format("%-30s", "Maximum collisions per slot:")
                + maxCollisions(table));
        System.out.println(String.format("%-30s", "Average collisions per slot: ")
                + df.format(avgCollisions(table)) + "\n");
    } // end printReport

    /**
     * Finds that maximum valued element in the int[] passed as parameter.
     *
     * @param table int[]
     * @return maximum element
     */
    public static int maxCollisions(int[] table) {
        int max = 0;
        for (int i : table) {
            if (i - 1 > max) {
                max = i - 1;
            }
        }
        return max;
    } // end maxCollisions

    /**
     * Calculates the average value of the elements in the table.
     *
     * @param table int[]
     * @return average of type double
     */
    public static double avgCollisions(int[] table) {
        int sum = 0;
        for (int i : table) {
            if (i > 1) {
                sum += (i - 1);
            }
        }
        int num_empty = 0;
        for (int i : table) {
            if (i == 0) {
                num_empty++;
            }
        }
        double average = ((double) sum / (table.length - num_empty));
        return average;
    } // end avgCollisions

    /**
     * Calculates the percentage of zero valued elements in array
     *
     * @param table of type int[]
     * @return double percent
     */
    public static double percentEmpty(int[] table) {
        int count = 0;
        for (int i : table) {
            if (i == 0) {
                count++;
            }
        }
        double percent = (((double) count / table.length) * 100);
        return percent;
    } // end percentEmpty

    public static int hash(String key) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal += key.charAt(i);
        }
        return hashVal;
    } // end hash

} // end Tutorial2
