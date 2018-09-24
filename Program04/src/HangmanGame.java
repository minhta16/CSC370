/**
 * Description: HangmanGame stores and operates Hangman.
 * 
 * @author Minh Ta
 * @date 09/24/18
 * @version CSC370-Program4
 * 
 * Cite Assistance:
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HangmanGame {
	private int guessLeft;
	private ArrayList<Character> displayPattern;
	private TreeSet<String> currentWords;
	private TreeSet<Character> letterGuessed;
	private TreeMap<ArrayList<Character>, TreeSet<String>> curPattern;

	public HangmanGame (List<String> dictionary, int length, int maximum) {
		if (length <= 1 || maximum <= 0) {
			throw new IllegalArgumentException("Illegal length / maximum guesses.");
		}
		currentWords = new TreeSet<String>();
		for (String word : dictionary) {
			if (word.length() == length) {
				currentWords.add(word);
			}
		}
		for (int i = 0; i < length; i++) {
			displayPattern.add('-');
		}
		guessLeft = maximum;
		letterGuessed = new TreeSet<Character>();
	}
	
	public Set<String> wordsLeft() {
		return currentWords;
	}
	
	public int numGuessesLeft() {
		return guessLeft;
	}
	
	public Set<Character> guessedLetters() {
		return letterGuessed;
	}
	
	public String pattern() {
		if (currentWords.isEmpty()) {
			throw new IllegalStateException("Word set is empty.");
		}
		String returnString = "";
		for (int i = 0; i < displayPattern.size() - 1; i++) {
			returnString += Character.toString(displayPattern.get(i)) + " ";
		}
		returnString += Character.toString(displayPattern.get(displayPattern.size() - 1));
		
		return returnString;
	}
	
	public int recordGuess(char guess) {
		if (guessLeft <= 0 || currentWords.isEmpty()) {
			throw new IllegalStateException("Game is over / Word set is empty.");
		}
		if (letterGuessed.contains(guess)) {
			throw new IllegalArgumentException("Character already guessed.");
		}
		letterGuessed.add(guess);
		for (String word : currentWords) {
			
		}
		return 0;
	}
	
	
}
