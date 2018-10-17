/**
 * Description: A class that handle the guessing game tree by creating
 * 				a guess tree and progress down the tree as the user 
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

	public GuessingGameTree() {
		overallRoot = new TreeNode("computer");
		console = new Scanner(System.in);
		computerWon = false;
	}
	
	public boolean getWinner() {
		return computerWon;
	}
	
	public void play() {
		play(overallRoot, overallRoot, false);
		
		
	}
	
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
	
	private void assertResult(TreeNode node, TreeNode prevNode, boolean lastLeft) {
		if (computerWon) {
			System.out.println("I win!");
		} else {
			System.out.print("I lose. What is your object? ");
			String newObj = console.nextLine();
			System.out.print("Type a yes/no question to distinguish " + newObj + " from " + node.text + ": ");
			String newQues = console.nextLine();
			boolean answeredYes = yesTo("And what is the answer to your question for a cat? ");
			TreeNode newBranch = new TreeNode(newQues);
			TreeNode newLeaf = new TreeNode(newObj);
			if (answeredYes) {
				newBranch.left = newLeaf;
				newBranch.right = node;	
			} else {
				newBranch.left = node;
				newBranch.right = newLeaf;
			}
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
	
	public void save(PrintStream output) {
		if (output == null) {
			throw new IllegalArgumentException("Invalid PrintStream.");
		}
		save(output, overallRoot);
	}
	
	private void save(PrintStream output, TreeNode node) {
		if (node.left == null && node.right == null) {
			output.println("A:" + node.text);
		} else {
			output.println("Q:" + node.text);
			save(output, node.left);
			save(output, node.right);
		}
	}
	
	public void load(Scanner input) {
		overallRoot = createTree(input);
	}
	
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
