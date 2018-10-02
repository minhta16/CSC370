/**
 * LetterRecord stores the count of the number of each alphabetic letter in a String
 * 
 * @author Diane Mueller
 * @date 8/5/2017
 * @version CSC370 Program 1
 * 
 */
public class LetterRecord {

	private static final int NUM_ALPHABETIC = 26;
	private int[] letterCount;
	private int totalCount;

	/**
	 * creates a count of each letter in the string ignoring case.
	 * Non-alphabetic characters are ignored.
	 * 
	 * @param data
	 *            - the string to record the count of each alphabetic letter
	 */
	public LetterRecord(String data) {
		letterCount = new int[NUM_ALPHABETIC];
		totalCount = 0;
		String lowerCaseVersion = data.toLowerCase();

		if (data != null) {
			int overAllCount = 0;
			for (int i = 0; i < lowerCaseVersion.length(); i++) {
				char letter = lowerCaseVersion.charAt(i);
				if (letter >= 'a' && letter <= 'z') {
					letterCount[letter - 'a']++;
					totalCount++;
				}
			}
		}
	}
	
	// private constructor to improve efficiency
	private LetterRecord(){
		letterCount = new int[NUM_ALPHABETIC];
		totalCount = 0;
	}

	/**
	 * @param letter - the character whose count of occurrences in this record is saught
	 * @return - the count of the occurrences of letter in this record
	 * @throws IllegalArgumentException if letter is not an alphabetic character
	 */
	public int get(char letter) {
		char lowerCase = Character.toLowerCase(letter);
		if (lowerCase < 'a' || lowerCase > 'z') {
			throw new IllegalArgumentException();
		}
		return letterCount[lowerCase - 'a'];
	}

	/**
	 * 
	 * @param letter - the character whose count of occurrences in this record is to be updated
	 * @param value - the count to set as the count of the letter
	 * @throws IllegalArguementException if letter is not an alphabetic character or value is negative
	 */
	public void set(char letter, int value) {
		char lowerCase = Character.toLowerCase(letter);
		if (lowerCase < 'a' || lowerCase > 'z' || value < 0) {
			throw new IllegalArgumentException();
		}
		int countToRemove = letterCount[lowerCase - 'a'];
		letterCount[lowerCase - 'a'] = value;
		totalCount = totalCount - countToRemove + value;

	}

	/**
	 * @return - the total count of alphabetic characters in this record
	 */
	public int size() {
		return totalCount;
	}

	/**
	 * @return - true if this record has no alphabetic characters, false otherwise
	 */
	public boolean isEmpty() {
		return (totalCount == 0);
	}

	/**
	 * @return - a String representation of this record with alphabetic characters with
	 * count > 1 repeated count times in the string
	 */
	public String toString() {
		String output = "[";
		for (int i = 0; i < letterCount.length; i++) {
			int numRepeats = letterCount[i];
			for (int j = 0; j < numRepeats; j++) {
				output = output + (char) (i + 'a');
			}
		}

		return output + "]";
	}

	/**
	 * adds the this records record of letters to that of other
	 * @param other - the record of a second String
	 * @return a new record which is the sum of this' and other's record
	 */
	public LetterRecord add(LetterRecord other) {
		if (other == null) {
			return this;
		} else {
			LetterRecord result = new LetterRecord();
			for (int i = 0; i < letterCount.length; i++) {
				result.letterCount[i] = this.letterCount[i] + other.letterCount[i];
			}
			result.totalCount = this.totalCount + other.totalCount;
			return result;
		}
	}

	/**
	 * subtracts other's record from this' record
	 * @param other - the record of a second String
	 * @return the difference of other's record from this' record or null if the difference
	 * of any character is less than zero.
	 */
	public LetterRecord subtract(LetterRecord other) {
		if (other == null) {
			return this;
		} else {
			LetterRecord result = new LetterRecord();
			for (int i = 0; i < letterCount.length; i++) {
				result.letterCount[i] = this.letterCount[i] - other.letterCount[i];
				if (result.letterCount[i] < 0) {
					return null;
				}
			}
			result.totalCount = this.totalCount - other.totalCount;
			return result;
		}
	}
}
