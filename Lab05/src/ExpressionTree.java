import java.util.*;

/**
 * Constructs a binary expression tree from a postfix expression.
 * The postfix expression must have only the binary operations
 * +, -, *, / and operands that are single digits '0' - '9'.
 * The prefix and infix expression can be created, as well as
 * the evaluation of the expression.
 * 
 * @author Minh Ta
 * @date 08/11/18
 * @version CSC370 - Lab5
 */
public class ExpressionTree {

	public static final char SPACE = ' ';

	private TreeNode expressionRoot;

	/**
	 * Construct a new ExpressionTree
	 */
	public ExpressionTree() {
		expressionRoot = null;
	}

	/**
	 * This method takes in a postfix expression and construct a binary tree of operations and
	 * make expressionRoot refers to the root of that tree.
	 * @param expression the postfix expression to be processed
	 * @throws IllegalArgumentException when the postfix expression is invalid
	 */
	public void constructTree(String expression) {
		StackADT<TreeNode> stack = new ArrayStack<TreeNode>();
		expression = expression.replaceAll(" ", "");
		constructTree(expression.toCharArray(), stack);
	}
	
	// a helper method that use the strategy on the book to construct a binary tree of operations
	// recursively.
	private void constructTree(char[] expression, StackADT<TreeNode> stack) {
		for (int i = 0; i < expression.length; i++) {
			if (isDigit(expression[i])) {
				stack.push(new TreeNode(expression[i]));
			} else if (isOperator(expression[i])){
				TreeNode rightChild;
				TreeNode leftChild;
				try {
					rightChild = stack.pop();
					leftChild = stack.pop();
				} catch (NoSuchElementException e) {
					throw new IllegalArgumentException("Invalid postfix string");
				}
				TreeNode branch = new TreeNode(expression[i], leftChild, rightChild);
				stack.push(branch);
			} else {
				throw new IllegalArgumentException("Invalid postfix string");
			}
		}
		if (stack.size() != 1) { // when there are more operands than operators
			throw new IllegalArgumentException("Invalid postfix string");
		} else {	// when the tree is complete
			expressionRoot = stack.pop();
		}
	}
	
	/**
	 * @return a prefix expression based on the constructed operations binary tree
	 */
	public String getPrefixExpression() {
		return getPrefixExpression(expressionRoot);
	}
	
	/*
	 * a helper method that recursively construct a prefix expression from the operations
	 * binary tree
	 */
	private String getPrefixExpression(TreeNode node) {
		if (node != null) {
			return Character.toString(node.data) + SPACE + getPrefixExpression(node.left) + getPrefixExpression(node.right);
		} else {
			return "";
		}
	}
	
	/** 
	 * @return a infix expression based on the constructed operations binary tree
	 */
	public String getInfixExpression() {
		return getInfixExpression(expressionRoot);
	}
	
	/* 
	 * a helper method that recursively construct a infix expression from the operations
	 * binary tree
	 */
	private String getInfixExpression(TreeNode node) {
		if (node.left != null || node.right != null) { // if the current node is NOT the leaf then add
														// parenthesis.
			return "(" + getInfixExpression(node.left) + SPACE + node.data + SPACE + getInfixExpression(node.right) + ")";
		} else {
			return Character.toString(node.data);
		}
	}

	/**
	 * @return the result of the arithmetic expression
	 */
	public double evaluate() {
		return evaluate(expressionRoot);
	}

	/* 
	 * a helper method that recursively calculate the result of the expression from the
	 * operations binary tree
	 */
	private double evaluate(TreeNode node) {
		if (node.left == null && node.right == null) {
			return convertToDigit(node.data);
		} else {
			switch(node.data) {
			case '+': return evaluate(node.left) + evaluate(node.right);
			case '-': return evaluate(node.left) - evaluate(node.right);
			case '*': return evaluate(node.left) * evaluate(node.right);
			case '/': return evaluate(node.left) / evaluate(node.right);
			}
		}
		// to satisfy java complier.
		return 0;
	}
	/**
	 * prints the binary tree sideways in the console window
	 */
	public void printSideways() {
		printSideways(expressionRoot, "");
	}

	/**
	 * Helper method to print the binary tree in the console window
	 * 
	 * @param root
	 *            - root of the tree to print
	 * @param indent
	 *            - the spaces that the node is indented
	 */
	private void printSideways(TreeNode root, String indent) {
		if (root != null) {
			printSideways(root.right, indent + "    ");
			System.out.println(indent + root.data);
			printSideways(root.left, indent + "    ");
		}
	}
	
	/**
	 * checks if char is a binary operator +, -, *, /
	 * 
	 * @param c
	 *            - the char to check if it is an operator
	 * @return true if the specified character is one of the four basic binary
	 *         arithmetic operations
	 */
	private boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	}

	/**
	 * checks if the char is a digit 0-9
	 * 
	 * @param c
	 *            - the char to check if it is a digit
	 * @return true if the specified character is a single digit 0-9
	 */
	private boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}

	/**
	 * converts a char to a digit
	 * @param c
	 *            - the char to be converted
	 * @return the digit 0-9 corresponding to the character
	 * @throws IllegalArgumentException if c is not a digit
	 */
	private int convertToDigit(char c) {
		if (!isDigit(c)){
			throw new IllegalArgumentException("char not a digit");
		}
		return c - '0';
	}

	/**
	 * TreeNode represents one node of an expression tree whose data is a
	 * character representing a binary operation or single digit data
	 */
	private static class TreeNode {
		// data fields
		private char data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(char data) {
			this(data, null, null);
		}
		
		public TreeNode(char data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

}
