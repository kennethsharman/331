package hw4;

/**
 * Class is responsible for the creation of 2-tuples and is implemented in
 * ShortestPath.java
 *
 * Date: June 24, 2018
 *
 * @author Kenneth Sharman
 *
 * @param <T1> element 1
 * @param <T2> element 2
 */
public class Pair<T1, T2> {

    T1 x;
    T2 y;

    public Pair(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }

    public T1 getX() {
        return x;
    }

    public T2 getY() {
        return y;
    }

} // end class Pair

