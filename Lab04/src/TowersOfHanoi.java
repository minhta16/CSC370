/** Class that solves Towers of Hanoi problem.
 * 
 *  @author 
 *  @date
 *  @version CSC370-Lab4
 **/
import java.util.*;

public class TowersOfHanoi {
	
	public static final char INITIAL_PEG = 'L';
	public static final char DESTINATION_PEG = 'R';
	public static final char AUX_PEG = 'M';

    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	System.out.println("I'll give you the moves to solve the Tower of Hanoi puzzle.\n");

    	System.out.println("The disks will begin on peg " + INITIAL_PEG + " and end on");
    	System.out.println("peg " + DESTINATION_PEG + " using peg " + AUX_PEG + " in the process.");
    	System.out.println("Disk 1 is the smallest disk.\n");
    	
    	
    	int numDisks = 0;
    	do{
    		try{
    			System.out.print("How many disks? ");
    			numDisks = Integer.parseInt(console.nextLine());
    			if (numDisks < 1){
        			System.out.println("The number of disks must be at least 1.\n");
        		}
    		} catch (Exception e){
    			System.out.println("Error. Enter an integer.\n");
    		}
    		
    	} while (numDisks < 1);
    	System.out.println("\nHere are the moves:");
    	
    	outputMoves(numDisks, INITIAL_PEG, DESTINATION_PEG, AUX_PEG);
    	
    }   
    
    /**
     * Recursive method for displaying "moving" disks.
     * @pre startPeg, destPeg, tempPeg are different.
     * @param numDisks is the number of disks
     * @param startPeg is the starting peg
     * @param destPeg is the destination peg
     * @param auxPeg is the auxiliary peg
     */
    public static void outputMoves(int numDisks, char startPeg,
            char destPeg, char auxPeg) {
    	if (numDisks == 1) {
    		System.out.println("Move disk " + numDisks + " from peg " + startPeg + " to peg " + destPeg);
    	} else {
    		outputMoves(numDisks - 1, startPeg, auxPeg, destPeg);
    		System.out.println("Move disk " + numDisks + " from peg " + startPeg + " to peg " + destPeg);
    		outputMoves(numDisks - 1, auxPeg, destPeg, startPeg);
    	}
    	
    	
    }
}
