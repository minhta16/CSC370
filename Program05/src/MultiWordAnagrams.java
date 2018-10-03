import java.util.*;
/**
 * Description: This class checks and makes all possible anagrams from a given string of characters.
 * 
 * @author Minh Ta
 * @date 10/02/2018
 * @version CSC370-Program5
 * 
 * Cite Assistance:
 * 
 */

public class MultiWordAnagrams {
	private Map<String, LetterRecord> lrMap;	// a map that has [K]: a word
												//				  [V]: that word's LetterRecord
	
	/**
	 * Construct a new MultiWordAnagrams object. This object will get all possible anagram words
	 * in a given dictionary into a map, then print out anagram phrases consists of those words.
	 * 
	 * @param dictionary - a list of words in a given dictionary.
	 */
	public MultiWordAnagrams(List<String> dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException("Dictionary is null.");
		}
		if (dictionary.size() == 0) {
			throw new IllegalArgumentException("Dictionary is empty.");
		}
		lrMap = new HashMap<String, LetterRecord>();
		for (String word: dictionary) {
			lrMap.put(word, new LetterRecord(word));
		}
	}
	
	/**
	 * Get the set of all words which could be constructed using the characters in a given phrase.
	 * 
	 * @param phrase - the phrase to get words contain the characters in such phases.
	 * @return a set of words which could be constructed using the characters in a given phrase.
	 */
	public Set<String> getWords(String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException("Passed in phrase is null.");
		}
		LetterRecord phraseLR = new LetterRecord(phrase);
		Set<String> result = new TreeSet<String>();
		for (String word: lrMap.keySet()) {
			if (phraseLR.subtract(lrMap.get(word)) != null) {
				result.add(word);
			}
		}
		return result;
	}
	
	/**
	 * Print out all anagrams that could be made using the given phrase's characters.
	 * @param phrase - the phrase to get anagrams out of.
	 */
	public void printAnagrams(String phrase) {
		printAnagrams(phrase, 0);
	}
	
	/**
	 * Print out all anagrams that could be made using the given phrase's characters.
	 * @param phrase - the phrase to get anagrams out of.
	 * @param max - the maximum number of words in the anagram phrases.
	 */
	public void printAnagrams(String phrase, int max) {
		Set<String> wordSet = getWords(phrase);
		LetterRecord phraseLR = new LetterRecord(phrase);
		List<String> result = new ArrayList<String>();
		printAnagramsHelper(max, 0, wordSet, phraseLR, result);
	}
	
	/*
	 *  Recurse through the set of word and print out all possible combination of the words
	 *  that could be the anagram phrase.
	 */
	
	private void printAnagramsHelper(int max, int curIndex, Set<String> wordSet, LetterRecord phraseLR, List<String> result) {
		if (curIndex == max || phraseLR.isEmpty()) {
			if (phraseLR.isEmpty()) {
				System.out.println(result);
			}
		} else {
			for (String word: wordSet) {
				LetterRecord newPhraseLR = phraseLR.subtract(lrMap.get(word));
				if (newPhraseLR != null && (curIndex < max || max == 0)) {
					result.add(word);
					printAnagramsHelper(max, curIndex + 1, wordSet, newPhraseLR, result);
					result.remove(word);
				}
			}
		}
	}
}
