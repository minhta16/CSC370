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

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HangmanGame {
	private int guessLeft;										// number of guess remaining
	private String displayPattern;								// current pattern that is displayed to the player
	private TreeSet<String> currentWords;						// the set of words that are viable to be the answer
	private TreeSet<Character> letterGuessed;					// the set of all guessed character
	protected TreeMap<String, TreeSet<String>> currentPatternMap;	// the map of all remaining patterns of words

	
	/**
	 * Construct a new HangmanGame object.
	 * 
	 * @param dictionary - the list of all words used in the game
	 * @param length - the length of the answer
	 * @param maximum - the maximum number of guesses before game over
	 */
	public HangmanGame (List<String> dictionary, int length, int maximum) {
		if (length <= 1 || maximum <= 0) {
			throw new IllegalArgumentException("Illegal length / maximum guesses.");
		}
		displayPattern = "-";
		for (int i = 1; i < length; i++) {
			displayPattern += " -";
		}
		currentWords = new TreeSet<String>();
		currentPatternMap = new TreeMap<String, TreeSet<String>>();
		for (String word : dictionary) {
			if (word.length() == length) {
				currentWords.add(word);
			}
		}
		currentPatternMap.put(displayPattern, currentWords);
		
		guessLeft = maximum;
		letterGuessed = new TreeSet<Character>();
	}
	
	/**
	 * @return a set of words that are viable to be the answer 
	 */
	public Set<String> wordsLeft() {
		return currentWords;
	}
	
	/**
	 * @return the number of guesses left before game over
	 */
	public int numGuessesLeft() {
		return guessLeft;
	}
	
	/**
	 * @return a set of all characters which have been guesses by the player
	 */
	public Set<Character> guessedLetters() {
		return letterGuessed;
	}
	
	/**
	 * @return a string of pattern which represent the answer (e.g. "b a - a -")
	 */
	public String pattern() {
		if (currentWords.isEmpty()) {
			throw new IllegalStateException("Word set is empty.");
		}
		return displayPattern;
	}
	
	/**
	 * This method takes in a character and then returns the number of occurrence of that character in the answer.
	 * Note that this method will construct a tree of all patterns of answers containing the guessed character, then
	 * pick the best pattern in order to make the player lose.
	 * 
	 * @param guess - the character the player guessed
	 * @return the number of occurrence of that character
	 */
	public int recordGuess(char guess) {
		if (guessLeft <= 0 || currentWords.isEmpty()) {
			throw new IllegalStateException("Game is over / Word set is empty.");
		}
		if (letterGuessed.contains(guess)) {
			throw new IllegalArgumentException("Character already guessed.");
		}
		letterGuessed.add(guess);
		makeGuessTree(guess);
		
		displayPattern = findMaxWordSetKey();
		currentWords = currentPatternMap.get(displayPattern);
		
		// create a new string without the guessed character, then compare the length of that string to the initial string
		int numGuessedChar = displayPattern.length() - displayPattern.replaceAll(Character.toString(guess), "").length();
		if (numGuessedChar == 0) {
			guessLeft--;
		}
		return numGuessedChar;
	}
	
	// Make a new guess tree based on the pattern of words.
	private void makeGuessTree(char guess) {
		currentPatternMap.clear();
		for (String word : currentWords) {
			// making word pattern
			String wordPattern = makeWordPattern(word);
			
			// if the word set of the pattern is already in the map, add the word into the set. Otherwise, create a new set.
			if (currentPatternMap.containsKey(wordPattern)) {
				currentPatternMap.get(wordPattern).add(word);
			} else {
				TreeSet<String> newWordSet = new TreeSet<String>();
				newWordSet.add(word);
				currentPatternMap.put(wordPattern, newWordSet);
			}
		}
	}
	
	// Create and return a pattern based on the word.
	private String makeWordPattern(String word) {
		String wordPattern = "";
		for (int i = 0; i < word.length(); i++) {
			if (letterGuessed.contains(word.charAt(i))) {
				wordPattern += word.charAt(i);
			} else {
				wordPattern += "-";
			}
			if (i != word.length() - 1) {
				wordPattern += " ";
			}
		}
		return wordPattern;
	}
	
	
	// Find and return the pattern which contains the biggest set of words.
	private String findMaxWordSetKey() {
		String biggestSet = "";
		for (String key : currentPatternMap.keySet()) {
			if (biggestSet.equals("") || currentPatternMap.get(key).size() > currentPatternMap.get(biggestSet).size()) {
				biggestSet = key;
			}
		}
		return biggestSet;
	}
	
}
