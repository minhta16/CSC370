/**
 * Description: A class that handle the guessing game tree by creating
 * 				a guess tree and progress down the tree as the player 
 * 				answers yes/no questions.
 * 
 * @author Minh Ta
 * @date 10/17/18
 * @version CSC370-Program6
 * 
 * Cite Assistance:
 * 
 */

import java.io.*;
import java.util.Scanner; // for Scanner


public class GuessingGameTree {
	private TreeNode overallRoot; // overall root
	private Scanner console;
	private boolean computerWon;

	/**
	 * Construct a new GuessingGameTree
	 */
	public GuessingGameTree() {
		overallRoot = new TreeNode("computer");
		console = new Scanner(System.in);
		computerWon = false;
	}
	
	/**
	 * @return true if the computer win the game. false otherwise.
	 */
	public boolean getWinner() {
		return computerWon;
	}
	
	/**
	 * Play the guessing game. The method will process down the tree as the user
	 * enter the answer to questions.
	 */
	public void play() {
		play(overallRoot, overallRoot, false);
	}
	
	/*
	 *  Recursively ask questions. The base case is when the current node reaches
	 *  any leaf of the tree.
	 */
	private void play(TreeNode node, TreeNode prevNode, boolean lastLeft) {
		if (node.left == null && node.right == null) {
			computerWon = yesTo("Would your object happen to be " + node.text + "?");
			assertResult(node, prevNode, lastLeft);
		} else {
			boolean wentLeft = yesTo(node.text);
			if (wentLeft) {
				play(node.left, node, wentLeft);
			} else {
				play(node.right, node, wentLeft);
			}
		}
	}
	
	/*
	 * Assert the result of the leaf. If the object in the leaf is correct, then the
	 * computer wins. Otherwise, the computer loses and it will ask the user for
	 * the true object, the question to distinguish between the old and the new object,
	 * and the answer to that question.
	 */
	private void assertResult(TreeNode node, TreeNode prevNode, boolean lastLeft) {
		if (computerWon) {
			System.out.println("I win!");
		} else {
			System.out.print("I lose. What is your object? ");
			String newObj = console.nextLine();
			TreeNode newLeaf = new TreeNode(newObj);
			
			System.out.print("Type a yes/no question to distinguish " + newObj + " from " + node.text + ": ");
			String newQues = console.nextLine();
			TreeNode newBranch = new TreeNode(newQues);
			
			boolean answeredYes = yesTo("And what is the answer to your question for a " + newObj + "? ");
			if (answeredYes) {
				newBranch.left = newLeaf;
				newBranch.right = node;	
			} else {
				newBranch.left = node;
				newBranch.right = newLeaf;
			}
			
			// special case where the tree only consists of one node, then replace that node
			if (prevNode == overallRoot && prevNode.left == null && prevNode.right == null) {
				overallRoot = newBranch;
			} else {
				if (lastLeft) {
					prevNode.left = newBranch;
				} else {
					prevNode.right = newBranch;
				}
			}
		}
	}
	
	/**
	 * Save the current game to a text file that could later be loaded to continue game progress.
	 * @param output - the PrintStream linked to the file to be created.
	 * @throws IllegalArgumentException when output is null.
	 */
	public void save(PrintStream output) {
		if (output == null) {
			throw new IllegalArgumentException("Invalid PrintStream.");
		}
		save(output, overallRoot);
	}
	
	/*
	 * Recursively save each node of the tree to the file. Base case is when the current node
	 * reaches any leaf of the tree.
	 * 
	 * post: save a file which has data of a tree on it.
	 */
	private void save(PrintStream output, TreeNode node) {
		if (node.left == null && node.right == null) {
			output.println("A:" + node.text);
		} else {
			output.println("Q:" + node.text);
			save(output, node.left);
			save(output, node.right);
		}
	}
	
	/**
	 * Load a game from a text file.
	 * @param input - the Scanner linked to the file to be loaded.
	 */
	public void load(Scanner input) {
		overallRoot = createTree(input);
	}
	
	/*
	 * Recursively load each node of the tree from the file. Base case is when the Scanner reaches any
	 * line starting with 'A', which is a leaf of the tree.
	 * 
	 * return: a tree constructed from an input
	 */
	private TreeNode createTree(Scanner input) {
		String newLine = input.nextLine();
		if (newLine.charAt(0) == 'A') {
			return new TreeNode(newLine.substring(2));
		} else {
			TreeNode newNode = new TreeNode(newLine.substring(2));
			newNode.left = createTree(input);
			newNode.right = createTree(input);
			return newNode;
		}
	}
	
	/**
	 * prints the binary tree sideways in the console window
	 */
	public void printSideways() {
		printSideways(overallRoot, "");
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
			System.out.println(indent + root.text);
			printSideways(root.left, indent + "    ");
		}
	}

	// post: asks the user a question, forcing an answer of "y" or "n"
	// returns true if the answer is yes, returns false otherwise
	private boolean yesTo(String prompt) {
		System.out.print(prompt + " (y/n)? ");
		String response = console.nextLine().trim().toLowerCase();
		while (!response.equals("y") && !response.equals("n")) {
			System.out.println("Please answer y or n.");
			System.out.print(prompt + " (y/n)? ");
			response = console.nextLine().trim().toLowerCase();
		}
		return response.equals("y");
	}

	// A TreeNode object represents a single question/answer in the game tree.
	private class TreeNode {
		public String text; // question for branch, answer for leaf
		public TreeNode left; // for yes answers
		public TreeNode right; // for no answers
		
		public TreeNode(String text) {
			this.text = text;
			left = null;
			right = null;
		}
	}

}
