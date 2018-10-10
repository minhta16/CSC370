/**
 * Program to test the implementation of ExpressionTree class
 * 
 * @author Diane Mueller
 * @date 10/06/2018
 * @version CSC370 - Lab5
 */
import java.util.*;

public class ExpressionTreeMain {
	public static void main(String[] args) {
		System.out.println("Numeric Expression Converter and Evaluator\n");
		
		Scanner console = new Scanner(System.in);
		ExpressionTree tree = new ExpressionTree();

		String postfix = "";
		boolean isPostfix = true;
		do{
			try{
				System.out.print("Enter a postfix arithmetic expression: ");
				postfix = console.nextLine();
				isPostfix = true;
				tree.constructTree(postfix);
			} catch(IllegalArgumentException e){
				isPostfix = false;
				System.out.println("Invalid expression. Re-enter postfix expression.\n");
			} 
		} while (!isPostfix);
		
		System.out.println("\nVisualize the expression tree:" );
		tree.printSideways();
		System.out.println("\n\nPostfix Expression: " + postfix);
		System.out.println("Prefix Expression:  " + tree.getPrefixExpression());
		System.out.println("Infix Expression:   " + tree.getInfixExpression());
//		System.out.println("Evaluation: " + tree.evaluate());
	}
}