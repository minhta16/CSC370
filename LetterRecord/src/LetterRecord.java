/**
 * @author Minh Ta
 * @date 08/25/18
 * @version CSC370 Program 1
 * 
 * Description:
 * 
 * Cite assistance (who and what):
 * 
 */
public class LetterRecord {
	private final int NUMBER_OF_CHARACTERS = 26;
	
	private int[] letterCount;
	private int size;
	
	/**
	 * 
	 * @param string The string needed to be letter count.
	 */
	public LetterRecord(String string) {
		letterCount = new int[NUMBER_OF_CHARACTERS];
		if (!string.equals(null)) {
			for (int i = 0; i < string.length(); i++) {
				char curChar = string.toLowerCase().charAt(i);
				if ('a' <= curChar && curChar < 'a' + NUMBER_OF_CHARACTERS) {
					letterCount[curChar - 'a']++;
					size++;
				}
			}
		}
		
	}
	
	/**
	 * @param letter - A single character
	 * @return - Number of times the character appears in the string
	 */
	public int get(char letter) {
		char lowercase = Character.toLowerCase(letter);
		if (lowercase < 'a' || 'a' + NUMBER_OF_CHARACTERS < lowercase) {
			throw new IllegalArgumentException();
		}
		return letterCount[lowercase - 'a'];
	}
	
	/**
	 * - Sets the count for the given letter to the given value
	 * @param letter
	 * @param value
	 */
	public void set(char letter, int value) {
		char lowercase = Character.toLowerCase(letter);
		size -= get(letter);
		if (lowercase < 'a' || 'a' + NUMBER_OF_CHARACTERS < lowercase) {
			throw new IllegalArgumentException();
		}
		letterCount[lowercase - 'a'] = value;
		size += value;
	}
	
	/**
	 * 
	 * @return - The overall count of the alphabetic characters in this record.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @return true if this record has no alphabetic characters.
	 */
	public boolean isEmpty() {
		for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
			if (letterCount[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * - Returns a string presentation of the object.
	 */
	public String toString() {
		String string  = "[";
		for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
			for (int j = 0; j < letterCount[i]; j++) {
				string += (char) ('a' + i);
			}
		}
		string += "]";
		return string;
	}
	
	/**
	 * 
	 * @param other - Another LetterRecord object to be added.
	 * @return - a LetterRecord array with elements are the sum of the two LetterRecords
	 */
	public LetterRecord add(LetterRecord other) {
		LetterRecord result = new LetterRecord("");
		for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
			result.letterCount[i] = letterCount[i];
			
			if (other != null) {
				result.letterCount[i] += other.letterCount[i];
			}
				
			result.size += result.letterCount[i];
		}
		return result;
	}
	
	/**
	 * 
	 * @param other - Another LetterRecord object to be subtracted
	 * @return - a LetterRecord array with elements are the difference of the two LetterRecords
	 */
	public LetterRecord subtract(LetterRecord other) {
		LetterRecord result = new LetterRecord("");
		for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
			result.letterCount[i] = letterCount[i];
			
			if (other != null) {
				if (letterCount[i] - other.letterCount[i] < 0) {
					result.size = 0;
					return null;
				}
				result.letterCount[i] -= other.letterCount[i];
			}
			
			result.size += result.letterCount[i];
		}
		return result;
	}
	
}
