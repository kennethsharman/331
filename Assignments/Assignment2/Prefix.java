
import java.util.Scanner;

/**
 * Class provides recursive method that evaluates prefix expressions.
 *
 * Date: June 2, 2018
 *
 * @author Kenneth Sharman
 */
public class Prefix {

    /**
     * Recursive method evaluates a valid prefix expression and returns the
     * result.
     *
     * @param sc of type Scanner
     * @return type int
     */
    public static int evaluate(Scanner sc) {

        // Operators that come first in prefix notation ae evaluated last
        // Scanner object is read a single character at a time. Coupling switch
        // with recursive calls evaluates the expression in the reuqired manner.
        String token = sc.next(); // prefix expression ex: "+ * 3 2 6" --> 12

        switch (token) {
            case "*": {
                int a = evaluate(sc);
                int b = evaluate(sc);
                return a * b;
            }
            case "/": {
                int a = evaluate(sc);
                int b = evaluate(sc);
                return a / b;
            }
            case "+": {
                int a = evaluate(sc);
                int b = evaluate(sc);
                return a + b;
            }
            case "-": {
                int a = evaluate(sc);
                int b = evaluate(sc);
                return a - b;
            }
            default:
                return Integer.parseInt(token);
        }

    } // end evaluate

} // end Prefix
