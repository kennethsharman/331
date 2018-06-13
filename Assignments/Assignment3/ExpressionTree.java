
import java.text.ParseException;
import java.util.Stack;

/**
 * Contains methods that parses an arithmetic expression given in infix notation
 * into a binary expression tree. Supports binary operators {'+', '-', '*',
 * '?'}, numbers (in integer format), as well as parenthesis (which may be
 * nested). Once an expression has been parsed into an expression tree,
 * pre-order and post-order traversals can be performed and evaluated.
 *
 * June 12, 2018
 *
 * @author Kenneth Sharman
 */
public class ExpressionTree {

    // Root node of expression tree
    private ExpTreeNode root = null;

    /**
     * Retrieves the root node
     *
     * @return of type ExpTreeNode
     */
    public ExpTreeNode getRoot() {
        return root;
    }

    /**
     * Constructor to build an empty parse tree.
     */
    public ExpressionTree() {
    }

    /**
     * Parse an expression from a string.
     *
     * @param line String containing the expression
     *
     * @throws ParseException If an error occurs during parsing. The location of
     * the error is included in the thrown exception.
     */
    public void parse(String line) throws ParseException {
        // Initial check if input is empty
        if (line.equals("")) {
            throw new ParseException("Empty Line. Nothing to compute in parse().", 0);
        }

        // Split the input into individual tokens and place in array
        String[] tokens = line.trim().split("\\s+");
        // Stack to implement Dijkstra's shunting yard algorithm
        Stack<String> stack = new Stack<>();
        // Fill the stack with expression tokens
        for (int i = tokens.length - 1; i >= 0; i--) {
            stack.push(tokens[i]);
        }
        // parse the stack
        root = parseExpression(stack);
    } // end parse

    /**
     * Method follows provided grammar and parses expression into a term,
     * followed by an number of +/- operands and another term.
     *
     * @param stack containing String tokens
     * @return ExpTreeNode
     * @throws java.text.ParseException if invalid syntax is encountered
     */
    private static ExpTreeNode parseExpression(Stack<String> stack) throws ParseException {
        ExpTreeNode leftNode = parseTerm(stack);

        while (!stack.empty()) {
            String oper = stack.peek();
            if ((oper.equals("+")) | (oper.equals("-"))) {
                stack.pop();
                ExpTreeNode rightNode = parseTerm(stack);
                leftNode = new ExpTreeNode(oper, leftNode, rightNode);
            } else {
                return leftNode;
            }
        }
        return leftNode;
    } // end parseExpression

    /**
     * Method follows provided grammar and parses a term into a factor, followed
     * by an number of mult./ div. and another factor.
     *
     * @param stack containing String tokens
     * @return ExpTreeNode
     * @throws java.text.ParseException if invalid syntax is encountered
     */
    private static ExpTreeNode parseTerm(Stack<String> stack) throws ParseException {
        ExpTreeNode leftNode = parseFactor(stack);

        if (!stack.empty() && isInt(stack.peek())) {
            throw new ParseException("Two consecutive ints found when parsing: " + stack.peek(), 0);
        }
        if (!stack.empty() && !isOper(stack.peek())) {
            throw new ParseException("Invalid Operator in parseTerm: " + stack.peek(), 1);
        }

        while (!stack.empty()) {
            String oper = stack.peek();
            if ((oper.equals("*")) | (oper.equals("/"))) {
                stack.pop();
                ExpTreeNode rightNode = parseFactor(stack);
                leftNode = new ExpTreeNode(oper, leftNode, rightNode);
            } else {
                return leftNode;
            }
        }
        return leftNode;
    } // end parseTerm

    /**
     * Method follows provided grammar and parses factor to a number or an
     * expression.
     *
     * @param stack containing String tokens
     * @return ExpreTreeNode
     * @throws java.text.ParseException if invalid syntax is encountered
     */
    private static ExpTreeNode parseFactor(Stack<String> stack) throws ParseException {
        ExpTreeNode node;

        if (stack.empty()) {
            throw new ParseException("Entered parseFactor method with empty stack", 2);
        }

        String oper = stack.peek();
        if ((oper.equals("(")) | (oper.equals(")"))) {
            stack.pop();
            node = parseExpression(stack);
            if (stack.empty()) {
                throw new ParseException("Expected Close Parenthesis, stack empty.", 3);
            }
            if (!stack.peek().equals(")")) {
                throw new ParseException("Expected Close Parenthesis, observed: " + stack.peek(), 4);
            }
            stack.pop();
        } else {
            if (!isInt(oper)) {
                throw new ParseException("Operand is not valid number: " + oper, 5);
            }
            node = new ExpTreeNode(stack.pop());
        }
        return node;
    } // end parseFactor

    /**
     * Evaluate the expression tree and return the integer result. An empty tree
     * returns 0. Utilizes overloaded evaluate helper method.
     *
     * @return expression evaluation of type int
     */
    public int evaluate() {
        return evaluate(root);
    } // end evaluate

    /**
     * Helper method for overloaded evaluate method. Series of if statements
     * decides how to arithmetically relate the nodes of expression tree.
     *
     * @param node of type ExpTreeNode
     * @return result of type int
     */
    private int evaluate(ExpTreeNode node) {

        if (node.isLeaf()) {
            // and isn't a number then expression is malformed
            return Integer.parseInt(node.el);
        } else if (node.el.equals("+")) {
            return evaluate(node.left) + evaluate(node.right);
        } else if (node.el.equals("-")) {
            return evaluate(node.left) - evaluate(node.right);
        } else if (node.el.equals("*")) {
            return evaluate(node.left) * evaluate(node.right);
        } else if (node.el.equals("/")) {
            //check to see if divide by zero
            return evaluate(node.left) / evaluate(node.right);
        } else { // Shouldn't reach here
            return 0;
        }
    } // end evaluate

    /**
     * Returns a postfix representation of the expression. Tokens are separated
     * by whitespace. An empty tree returns a zero length string. Utilizes
     * helper method postOrder().
     *
     * @return postfix expression of type String
     */
    public String postfix() {
        return postOrder(root, "").trim();
    } // end postfix

    /**
     * Postorder visits left and right children, then the parent node.
     * Concatenates element value to the return String at every node visit.
     * Helper method for postfix().
     *
     * @param node of type ExpTree
     * @param s of type String
     * @return String
     */
    private String postOrder(ExpTreeNode node, String s) {
        if (node != null) {
            s = postOrder(node.left, s);
            s = postOrder(node.right, s);
            s = s.concat(node.el + " ");
        }
        return s;
    } // end postOrder

    /**
     * Returns a prefix representation of the expression. Tokens are separated
     * by whitespace. An empty tree returns a zero length string. Utilizes
     * helper method preOrder().
     *
     * @return String
     */
    public String prefix() {
        return preOrder(root, "").trim();
    } // end prefix

    /**
     * Pre-order visits parent node, then left and right children. Concatenates
     * element value to the return String at every node visit. Helper method for
     * prefix().
     *
     * @param node of type ExpTreeNode
     * @param s of type String
     * @return String
     */
    private String preOrder(ExpTreeNode node, String s) {
        if (node != null) {
            s = s.concat(node.el + " ");
            s = preOrder(node.left, s);
            s = preOrder(node.right, s);
        }
        return s;
    } // end preOrder

    /**
     * Evaluates the input String to see if it is represents and integer.
     *
     * @param s input String
     * @return boolean result
     */
    private static boolean isInt(String s) {
        boolean isInt = false;

        // Attempt to parse String to Integer. If successfull return true.
        try {
            Integer.parseInt(s);
            isInt = true;
            // If parse fails, then String does not represent and Integer, return false    
        } catch (NumberFormatException e) {
        }
        return isInt;
    } // end isInt

    /**
     * Evaluates the input String to see if it represents a valid operator.
     * Valid operator types are +, - , * , /.
     *
     * @param s input String
     * @return boolean result
     */
    private static boolean isOper(String s) {
        boolean isOper = false;
        if ((s.equals("+")) || (s.equals("-")) || (s.equals("*")) || (s.equals("/"))) {
            isOper = true;
        }
        return isOper;
    } // end isOPer

} // end class ExpressionTree
