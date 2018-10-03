/**
 * Simple test program for the methods of HangmanGame
 * 
 * @author Minh Ta
 * @date 09/24/18
 * @version CSC370-Program4
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HangmanSimpleTest {

	public static void main(String[] args) throws FileNotFoundException{
		// open the dictionary file and read dictionary into an ArrayList
		Scanner input = new Scanner(new File("dictionary2.txt"));
		List<String> dictionary = new ArrayList<String>();
		while (input.hasNext()) {
			dictionary.add(input.next().toLowerCase());
		}
		HangmanGame game = new HangmanGame(dictionary, 4, 10);
		
		input.close();
		
		try {
			System.out.println("\nillegal length test: ");
			new HangmanGame(dictionary, -1, 10);
 		} catch (IllegalArgumentException e) {
			System.out.println("\tpassed");
			System.out.println("\tthrew exception: " + e);
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}
		
		try {
			System.out.println("\nillegal number of guesses test: ");
			new HangmanGame(dictionary, 4, -420);
 		} catch (IllegalArgumentException e) {
			System.out.println("\tpassed");
			System.out.println("\tthrew exception: " + e);
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}
		
		try {
			System.out.println("\nwordsLeft test: ");
			Set<String> wordsLeft = game.wordsLeft();
			Set<String> result = new TreeSet<String>();
			result.add("ally");
			result.add("beta");
			result.add("cool");
			result.add("deal");
			result.add("else");
			result.add("flew");
			result.add("good");
			result.add("hope");
			result.add("ibex");
			if (result.equals(wordsLeft)) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed");
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("...failed");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in line#" + line);
			System.exit(1);
		}
		
		try {
			System.out.println("\nnumGuessesLeft test: ");
			int numGuesses = game.numGuessesLeft();
			if (numGuesses == 10) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed. numGuesses returns: " + numGuesses + " instead of: 10");
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}

		try {
			System.out.println("\nguessedLetters test without guessing: ");
			Set<Character> guessedLetters = game.guessedLetters();
			if (guessedLetters.equals(new TreeSet<Character>())) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed");
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}
		
		try {
			System.out.println("\nguessedLetters test by adding 'a': ");
			game.recordGuess('a');
			Set<Character> guessedTest = new TreeSet<Character>();
			guessedTest.add('a');
			if (game.guessedLetters().equals(guessedTest)) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed. guessedLetters returns: " + game.guessedLetters() + " instead of: " + guessedTest);
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}
		
		try {
			System.out.println("\nguessTree test by adding 'a': ");
			TreeMap<String, TreeSet<String>> guessedMapTest = new TreeMap<String, TreeSet<String>>();
			TreeSet<String> set1 = new TreeSet<String>();
			set1.add("cool");
			set1.add("else");
			set1.add("flew");
			set1.add("good");
			set1.add("hope");
			set1.add("ibex");
			guessedMapTest.put("- - - -", set1);
			TreeSet<String> set2 = new TreeSet<String>();
			set2.add("ally");
			guessedMapTest.put("a - - -", set2);
			TreeSet<String> set3 = new TreeSet<String>();
			set3.add("beta");
			guessedMapTest.put("- - - a", set3);
			TreeSet<String> set4 = new TreeSet<String>();
			set4.add("deal");
			guessedMapTest.put("- - a -", set4);
			if (game.currentPatternMap.equals(guessedMapTest)) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed. currentPatternMap is: " + game.currentPatternMap + " instead of: " + guessedMapTest);
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}

		try {
			System.out.println("\n recordGuess number of occurences test by adding 'o' and 'e': ");
			game.recordGuess('o');
			int numOcc = game.recordGuess('e');
			if (numOcc == 1) {
				System.out.println("\tpassed");
			} else {
				System.out.println("\tfailed. recordGuess returns: " + numOcc + " instead of: " + 1);
				System.exit(1);
			}
 		} catch (Exception e) {
			System.out.println("\tfailed");
			System.out.println("\t\tthrew exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("\t\tin line#" + line);
			System.exit(1);
		}
		
		System.out.println("\nAll test passed!");
	}
	
	

}
