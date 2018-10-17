/**
 * A basic text interface for a guessing game where the computer
 * tries to guess an object that the user is thinking of. The
 * game can either begin with one answer or from a file of questions
 * and answers. When the game ends after stats are given, the
 * user has the opportunity to save the questions and answers to
 * a file to use to begin a subsequent game.
 * 
 * @author Diane Mueller
 * @date   08/10/2017
 * @version  CSC370-Program 6
 */

import java.io.*;
import java.util.Scanner;

public class GuessingGameMain {
	
    private static final String PLAY_AGAIN_MESSAGE = "Challenge me again? ";
    private static final String SAVE_MESSAGE = "Shall I remember these games? ";
    private static final String LOAD_MESSAGE = "Shall I recall a previous game? ";
    private static final String SAVE_LOAD_FILENAME_MESSAGE = "What is the file name? ";
    private static final String BANNER_MESSAGE = "Think of an item, and I will guess it.";
    
     
    public static void main(String[] args) {
    	Scanner console = new Scanner(System.in);
    	GuessingGameTree tree = new GuessingGameTree();
    	int numGamesPlayed = 0;
    	int numComputerWins = 0;
    	
    	 System.out.println("Ready to play a Guessing Game!\n");
         load(console, tree);
         
         System.out.println("\n" + BANNER_MESSAGE);
             
         do {
              // play one complete game
             System.out.println();      // blank line between games
             numGamesPlayed++;
             tree.play();
             System.out.println();
             if (tree.getWinner()){
            	 numComputerWins++;
             }
         } while (yesTo(console, PLAY_AGAIN_MESSAGE));   // prompt to play again
        
         // print overall stats
         System.out.println("\nWe played " + numGamesPlayed + " games.");
         System.out.println("You won " + (numGamesPlayed - numComputerWins) 
        		 + " and I won " + numComputerWins + ".\n\n");

         save(console, tree);
     }
     
     // common code for asking the user whether they want to save or load
     private static void load(Scanner console, GuessingGameTree tree) {
        if (yesTo(console, LOAD_MESSAGE)) {
             System.out.print(SAVE_LOAD_FILENAME_MESSAGE);
             String filename = console.nextLine();
             try {
                 Scanner input = new Scanner(new File(filename));
                 tree.load(input);
             } catch (FileNotFoundException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }

     }
     
     // common code for asking the user whether they want to save or load
     private static void save(Scanner console, GuessingGameTree tree) {
            if (yesTo(console, SAVE_MESSAGE)) {
             System.out.print(SAVE_LOAD_FILENAME_MESSAGE);
             String filename = console.nextLine();
             try {
                 PrintStream out = new PrintStream(new File(filename));
                 tree.save(out);
                 out.close();
             } catch (FileNotFoundException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }
     }
    
 	// post: asks the user a question, forcing an answer of "y" or "n"
 	// returns true if the answer is yes, returns false otherwise
 	private static boolean yesTo(Scanner console, String prompt) {
 		System.out.print(prompt + " (y/n)? ");
 		String response = console.nextLine().trim().toLowerCase();
 		while (!response.equals("y") && !response.equals("n")) {
 			System.out.println("Please answer y or n.");
 			System.out.print(prompt + " (y/n)? ");
 			response = console.nextLine().trim().toLowerCase();
 		}
 		return response.equals("y");
 	}  
}
