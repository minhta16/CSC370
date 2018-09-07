/*
 * Name(s): Minh Ta
 * Course: CSC370--Lab 1
 * Date: 08/29/18
 */

/**
 * PalindromeTester allows the user to enter a word or phrase from the
 * console window and tests whether the input is a palindrome. A palindrome has
 * the same letters forwards and backwards ignoring case and punctuation marks.
 */

import java.util.*;

public class PalindromeTester {

	public static void main(String[] args) {
		System.out.println("Palindrome Tester\n");
		Scanner console = new Scanner(System.in);
		
		do {
			System.out.print("Enter a word or sentence: ");
			String sentence = console.nextLine();
			if (palinTester(sentence)) {
				System.out.println("You entered a palindrome.");
			} else {
				System.out.println("Nope, that's not a palindrome.");
			}			
			
		}while (yesTo("\nDo you want to enter another word or sentence?", console));
		
		console.close();

	}
	
	/**
	 * asks the user a question, forcing an answer of "y" or "n";
	 * @param prompt String for the question prompt
	 * @param console Scanner to facilitate input
	 * @return  true if the answer was y, returns false otherwise
	 */
    public static boolean yesTo(String prompt, Scanner console) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    /**
     * This method tests if a sentence is a palindrome or not, ignoring non-alphabetic letters
     * @param sentence The sentence needed to be tested
     * @return true if the sentence is a palindrome
     */
    public static boolean palinTester(String sentence) {
    	ArrayStack<Character> stack = new ArrayStack<Character>();
    	// Eliminate all non-alphabetic characters
    	String lowcaseSent = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
    	
    	if (lowcaseSent.length() == 1) {
    		return true;
    	} else if (lowcaseSent.length() == 0) {
    		System.out.println("You might need a real word/sentence...\nHere's one: " + getSentence());
    		return false;
    	}
    	
    	for (int i = lowcaseSent.length() - 1; i >= 0; i--) {
    		if (lowcaseSent.charAt(i) - 'a' < 0 || 26 < lowcaseSent.charAt(i) - 'a') {
    			lowcaseSent = lowcaseSent.substring(0, i) + lowcaseSent.substring(i + 1, lowcaseSent.length());
    		}
    	}
    	
    	// Pushing half of the sentence to the stack
    	for (int i = 0; i < lowcaseSent.length() / 2; i++) {
    		stack.push(lowcaseSent.charAt(i));
    	}
    	
    	// Checking if the other half matches the character in the stack
    	for (int i = lowcaseSent.length() / 2; i < lowcaseSent.length(); i++) {
    		
    		// Get correct index for odd length sentences.
    		if (i == lowcaseSent.length() / 2 && lowcaseSent.length() % 2 != 0) {
    			i++;
    		}
    		
    		if (lowcaseSent.charAt(i) != stack.pop()) {
    			return false;
    		}
    	}
    	return true;
    }

	private static String getSentence() {
		Random rand = new Random();
		String sentence = "";
		switch (rand.nextInt(4)) {
		case 0: sentence = "As one black bug, bled blue, black blood.\n\t    The other black bug bled blue."; break;
		case 1: sentence = "Aluminum, linoleum, molybdenum, aluminum, \n\t    linoleum, molybdenum, aluminum, linoleum, molybdenum"; break;
		case 2: sentence = "Pete's pa pete poked to the pea patch to pick\n\t    a peck of peas for the poor pink pig in the pine hole pig-pen."; break;
		case 3: sentence = "Picky people pick Peter Pan Peanut-Butter,\n\t    'tis the peanut-butter picky people pick.";
		}
		return sentence;
	}
}
