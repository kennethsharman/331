package hw4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;

public class ShortestPath {

    public static void main(String[] args) {

        ArrayList<String> mazeData = readFile("RandomMaze.txt");
        ArrayList<String> query = readFile("RandomQuery.txt");
        //ArrayList<String> mazeData = readFile("HilbertMaze.txt");
        //ArrayList<String> query = readFile("HilbertQuery.txt");

        int n = getMazeSize(mazeData); // Number of columns in matrix
        Pair[][] adj = initArray(n); // Initialize adjacency matrix filled with -1s
        populateMatrix(mazeData, adj, n); // Use data from maze file to fill matrix
        //print2DArray(adj, n);

        // Calculate Shortest Path
        ArrayList<LinkedList<Integer>> unweighted_paths = calculatePaths(query, adj, n);

        // Display Maze and shortest paths (if path is possible)
        printMaze(adj, unweighted_paths, n);

    } // end main

    /**
     * Reads a file and puts each line into an ArrayList as a String
     *
     * @param filename of type String
     * @return String ArrayList
     */
    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> mazeData = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                mazeData.add(line.trim());
            }
            reader.close();
            return mazeData;
        } catch (IOException e) {
            System.err.format("Exception occurred trying to read '%s", filename);
            return null;
        }
    } // end readfile

    /**
     * Saves the first integer of mazeData to be n^2 x n maze size. Removes the
     * first line from the ArrayList, as remaining lines (elements) are expected
     * to be edge information
     *
     * @param mazeData String ArrayList
     * @return integer maze size
     */
    public static int getMazeSize(ArrayList<String> mazeData) {
        int n = Integer.parseInt(mazeData.get(0));
        mazeData.remove(0);
        return n;
    } // end getMazeSize

    /**
     * Creates a 2D Pair array of size n x n. Initializes all elements to -1
     *
     * @param n size of n^2 x n matrix
     * @return Pair[][]
     */
    public static Pair[][] initArray(int n) {
        Pair[][] adj = new Pair[n * n][n];
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = new Pair(-1, -1);
            }
        }
        return adj;
    }

    /**
     * Method prints the contents of 2D Pair array
     *
     * @param arr of type Pair[][]
     * @param n size of n^2 x n matrix
     */
    public static void print2DArray(Pair[][] arr, int n) {
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("(" + arr[i][j].getX() + "," + arr[i][j].getY() + ")");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method saves mazeData information in 2D Pair array. Row numbers represent
     * the vertices. The columns represent the possible edge connections. Each
     * element is a pair containing the destination vertex and the edge weight.
     *
     * @param mazeData String ArrayList
     * @param adj Pair[][]
     * @param n number of columns
     * @return Pair[][]
     */
    public static Pair[][] populateMatrix(ArrayList<String> mazeData, Pair[][] adj, int n) {
        for (int i = 0; i < mazeData.size(); i++) {
            String[] tokens = mazeData.get(i).trim().split("\\s+");
            int src = Integer.parseInt(tokens[0]);
            int dest = Integer.parseInt(tokens[1]);
            int w = Integer.parseInt(tokens[2]);

            for (int j = 0; j < n; j++) {
                if ((Integer) adj[src - 1][j].getX() == -1) {
                    adj[src - 1][j].x = dest;
                    adj[src - 1][j].y = w;
                    break;
                }
            }
        }
        return adj;
    } // end populateMatrix

    /**
     * Method passes the start/ end points for desired paths to breadth first
     * search method. Resulting path (if successful is appended to return list.
     *
     * @param query String ArrayList containing path queries
     * @param adj Adjacency matrix of type Pair[][]
     * @param n number of columns in adjacency matrix
     * @return ArrayList<LinkedList<Integer>> containing shortest paths of
     * queries
     */
    public static ArrayList<LinkedList<Integer>> calculatePaths(ArrayList<String> query, Pair[][] adj, int n) {
        ArrayList<LinkedList<Integer>> paths = new ArrayList<>();

        for (int i = 0; i < query.size(); i++) {
            String[] tokens = query.get(i).trim().split("\\s+");
            if ((!tokens[0].equals("")) && (!tokens[1].equals(""))) {
                paths.add(BFS(adj, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), n));
            }
        }
        return paths;
    } // end calculatePaths

    /**
     * Prints maze and shortest path results of queries using MazeVisualizer
     * class.
     *
     * @param adj Pair[][] adjacency matrix
     * @param paths list of shortest paths
     * @param n number of columns in adjacency matrix
     */
    public static void printMaze(Pair[][] adj, ArrayList<LinkedList<Integer>> paths, int n) {

        // Instantiate MazeVisualizer using class provided code
        JFrame f = new JFrame("MazeVisualizer");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        MazeVisualizer applet = new MazeVisualizer(n);

        // Add edges to the maze
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n; j++) {
                if ((Integer) adj[i][j].getX() != -1) {
                    applet.addEdge(i + 1, (Integer) adj[i][j].getX());
                }
            }
        }

        // Add shortest path to the queries in file
        for (LinkedList<Integer> path : paths) {
            applet.addPath(path);
        }

        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setBackground(Color.WHITE);
        f.setSize(new Dimension(512, 512));
        f.setVisible(true);

    } // end printMaze

    /**
     * Method performs breadth first search to find shortest unweighted path.
     *
     * @param adj Pair[][] adjacency matrix
     * @param start beginning vertex
     * @param end final destination
     * @param n number of columns in adjacency matrix
     * @return Integer LinkedList containing vertices of shortest path
     */
    public static LinkedList<Integer> BFS(Pair[][] adj, int start, int end, int n) {

        // If start and end points are the same we are automatically done
        if (start == end) {
            return new LinkedList<>(Arrays.asList(start));
        }

        int[] visitOrder = new int[adj.length]; // array tracks order in which vertices are visited
        int count = 1; // visit number
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(start)); // Begin by visiting the start vertex
        visitOrder[start - 1] = count++; // count start vertex as visited

        // Use node list to track vertices visted and their predecessors
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(start));

        boolean foundKey = false; // Indicates if end vertex was found during search

        outerloop:
        {
            while (!queue.isEmpty()) {
                int vertex = queue.remove();
                // Find position in nodeList so that adjacent vertices can use
                // this location as their predecessor
                Node predecessor = nodeList.get(findNode(nodeList, vertex));

                // For every adjaceny vertex to the current...
                for (int i = 0; i < n; i++) {
                    int adjVertex = (Integer) adj[vertex - 1][i].getX();

                    // ... check to see if it is the endpoint of search...
                    if (adjVertex == end) {
                        nodeList.add(new Node(adjVertex, predecessor));
                        foundKey = true;
                        break outerloop;
                    }

                    // ... if it is not endpoint then check if there is a connecting
                    // edge and if the vertex is unvisited. If both yes, then
                    // add to queue.
                    if ((adjVertex != -1) && (visitOrder[adjVertex - 1] == 0)) {
                        visitOrder[adjVertex - 1] = count++;
                        queue.add(adjVertex);
                        nodeList.add(new Node(adjVertex, predecessor));
                    }
                }

            }
        } // end outerloop

        if (!foundKey) {
            System.out.println(start + " NO PATH " + end);
            return new LinkedList<>(Arrays.asList(start));
        }

        // Backtrack from endpoing using predecessors to determine shortest
        // unweighted path
        LinkedList<Integer> path = new LinkedList<>();
        Node node = nodeList.get(nodeList.size() - 1);
        do {
            path.add(0, node.data);
            node = node.predecessor;
        } while (node.data != start);
        path.add(0, start);

        printPath(path);
        return path;

    } // end BFS

    /**
     * Method determines the node position for which the vertex value is stored
     *
     * @param nodeList Node ArrayList to be search
     * @param vertex key
     * @return position of key
     */
    public static int findNode(ArrayList<Node> nodeList, int vertex) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).data == vertex) {
                return i;
            }
        }
        return -1;
    } // end findNode

    /**
     * Prints the contents of integer linked lists representing the shortest
     * unweighted path between two specified vertices.
     *
     * @param path LinkedList
     */
    public static void printPath(LinkedList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    } // end printPath

} // end ShortestPath
