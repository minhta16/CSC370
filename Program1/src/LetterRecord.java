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
	private int[] letterCount;
	
	/**
	 * 
	 * @param string The string needed to be letter count.
	 */
	public LetterRecord(String string) {
		letterCount = new int[26];
		if (!string.equals(null)) {
			for (int i = 0; i < string.length(); i++) {
				if (0 <= string.toLowerCase().charAt(i) - 'a' && string.toLowerCase().charAt(i) - 'a' < letterCount.length)
				letterCount[string.toLowerCase().charAt(i) - 'a']++;
			}
		}
		
	}
	
	/**
	 * @param letter - A single character
	 * @return - Number of times the character appears in the string
	 */
	public int get(char letter) {
		char lowercase = Character.toLowerCase(letter);
		if (lowercase - 'a' >= 26 || lowercase - 'a' < 0) {
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
		if (lowercase - 'a' > 26 || lowercase - 'a' < 0) {
			throw new IllegalArgumentException();
		}
		letterCount[lowercase - 'a'] = value;
	}
	
	/**
	 * 
	 * @return - The overall count of the alphabetic characters in this record.
	 */
	public int size() {
		int sum = 0;
		for (int i = 0; i < letterCount.length; i++) {
			sum += letterCount[i];
		}
		return sum;
	}
	
	/**
	 * 
	 * @return true if this record has no alphabetic characters.
	 */
	public boolean isEmpty() {
		for (int i = 0; i < letterCount.length; i++) {
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
		for (int i = 0; i < letterCount.length; i++) {
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
		for (int i = 0; i < letterCount.length; i++) {
			if (other != null) {
				result.set((char) ('a' + i), letterCount[i] + other.get( (char) ('a' + i)));
			} else {
				result.set((char) ('a' + i), letterCount[i]);
			}
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
		for (int i = 0; i < letterCount.length; i++) {
			if (other != null) {
				if (letterCount[i] - other.get( (char) ('a' + i)) < 0) {
					return null;
				}
				result.set((char) ('a' + i), letterCount[i] - other.get( (char) ('a' + i)));
			} else {
				result.set((char) ('a' + i), letterCount[i]);
			}
		}
		return result;
	}
	
}
