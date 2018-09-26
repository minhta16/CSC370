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
import java.util.*;

public class HangmanGame {
	private int guessLeft;
	private String displayPattern;
	private TreeSet<String> currentWords;
	private TreeSet<Character> letterGuessed;
	private TreeMap<String, TreeSet<String>> currentPatternMap;

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
		return displayPattern;
	}
	
	public int recordGuess(char guess) {
		if (guessLeft <= 0 || currentWords.isEmpty()) {
			throw new IllegalStateException("Game is over / Word set is empty.");
		}
		if (letterGuessed.contains(guess)) {
			throw new IllegalArgumentException("Character already guessed.");
		}
		letterGuessed.add(guess);
		currentPatternMap = makeGuessTree(guess);
		
		displayPattern = findMaxWordSetKey();
		currentWords = currentPatternMap.get(displayPattern);
		
		int numGuessedChar = displayPattern.length() - displayPattern.replaceAll(Character.toString(guess), "").length();
		if (numGuessedChar <= 0) {
			guessLeft--;
		}
		return numGuessedChar;
	}
	
	private TreeMap<String, TreeSet<String>> makeGuessTree(char guess) {
		TreeMap<String, TreeSet<String>> map = new TreeMap<String, TreeSet<String>>();
		for (String word : currentWords) {
			// making word pattern
			String wordPattern = makeWordPattern(word);
			
			// if the word set of the pattern is already in the map, add the word into the set. Otherwise, create a new set.
			if (map.containsKey(wordPattern)) {
				map.get(wordPattern).add(word);
			} else {
				TreeSet<String> newWordSet = new TreeSet<String>();
				newWordSet.add(word);
				map.put(wordPattern, newWordSet);
			}
		}
		return map;
	}
	
	private String findMaxWordSetKey() {
		String biggestSet = "";
		for (String key : currentPatternMap.keySet()) {
			if (biggestSet.equals("") || currentPatternMap.get(key).size() > currentPatternMap.get(biggestSet).size()) {
				biggestSet = key;
			}
		}
		return biggestSet;
	}
	
	private String makeWordPattern(String word) {
		String wordPattern = "";
		for (int i = 0; i < word.length() - 1; i++) {
			if (letterGuessed.contains(word.charAt(i))) {
				wordPattern += word.charAt(i) + " ";
			} else {
				wordPattern += "- ";
			}
		}

		if (letterGuessed.contains(word.charAt(word.length() - 1))) {
			wordPattern += word.charAt(word.length() - 1);
		} else {
			wordPattern += "-";
		}
		return wordPattern;
	}
	
}
