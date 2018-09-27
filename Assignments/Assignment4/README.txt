Main develiverable is ShortestPath.java

Files needed to compile and run my program:

1 - ShortestPath.java
Contains a main method that will instantiate MazeVisualizer, plot maze, and plot shortest unweighted paths for provided queries.
In main method there are two different mazes to plot- RandomMaze and Hilbertmaze (both require corresponding text file).
Initially the RandomMaze is set to run. To plot the Hilbert maze simply comment out the first two lines in the main method
and delete comment slashes from the next two lines.
Note that only the unweighted path is determined. I was planning on doing the bonus, but ran out of time. As such, the code is
set up to handle 2-tuples in the adjacency matrix (for destination and weight). This was implemented using the Pair class.

2 - Node
I created a simple Node class as a helper to my breadth first search method BFS(). For each vertex visited, a corresponding Node
is created and added to a list. The Node tracks the vertex number and predecessor. This is used to backtrack and compile the path
once the key (end) vertex is found.

3 - Pair
As mentioned this class was intended to be used as a helper class, when determining the shortest weighted path, however I did not
end up doing the bonus.

4 - MazeVisualizer, RandomMaze.txt, RandQuery.txt, HilbertMaze.txt, HilbertQuery.txt
Supplied files from the assignment.
