package tutorial;

/**
 *
 * @author Ken
 */
public class PairDemo {

    public static void main(String[] args) {
        Pair<Integer, Integer> pair1 = new Pair<>(1, 2);
        Pair<Double, Double> pair2 = new Pair<>(0.5, 3.14);
        Pair<Double, String> pair3 = new Pair<>(2.71, "What am I doing with my life?");
        

        System.out.println("Pair 1 = (" + pair1.getX() + ", " + pair1.getY() + ")");
        System.out.println("Pair 2 = (" + pair2.getX() + ", " + pair2.getY() + ")");
        System.out.println("Pair 3 = (" + pair3.getX() + ", " + pair3.getY() + ")");

    } // end main
} // PairDemo
