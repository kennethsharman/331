package tutorial;

/**
 *
 * @author Ken
 * @param <T1>
 * @param <T2>
 */
class Pair<T1, T2> {

    T1 x; // x is of type T1
    T2 y; // y is of type T2

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

    public void setX(T1 x) {
        this.x = x;
    }

    public void setY(T2 y) {
        this.y = y;
    }
}
