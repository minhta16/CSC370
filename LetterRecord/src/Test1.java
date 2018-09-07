
/**
 * This program tests stage 1 of the LetterRecord class. The program reads
 * from the file test1.txt which has a series of test cases with the correct answers.
 * 
 * @author Diane Mueller modified from Stuart Reges
 * @date 8/5/2017
 */

import java.util.*;
import java.io.*;

public class Test1 {
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("You must copy test1.txt to this directory" + " before running the testing program.");
			System.exit(1);
		}
		int cases = input.nextInt();
		input.nextLine(); // to throw away end-of-line
		String[] strings = new String[cases];
		LetterRecord[] records = new LetterRecord[cases];
		testConstructor(records, input, strings);
		testSize(records, input, strings);
		testIsEmpty(records, input, strings);
		testGet(records, input, strings);
		testToString(records, input, strings);
		System.out.println("All tests passed.");
	}

	/**
	 * Tests the constructor of the LetterRecord class populating the records
	 * and strings arrays if no error is encountered
	 * 
	 * @param records
	 *            - array of the letter record for each of the file test cases
	 * @param input
	 *            - facilitates reading the file
	 * @param strings
	 *            - array of test cases
	 */
	public static void testConstructor(LetterRecord[] records, Scanner input, String[] strings) {
		System.out.println("Testing constructor...");
		for (int i = 0; i < strings.length; i++) {
			strings[i] = input.nextLine();
			System.out.print("    testing \"" + strings[i] + "\"");
			try {
				records[i] = new LetterRecord(strings[i]);
				System.out.println("...passed");
			} catch (Exception e) {
				System.out.println("...failed");
				System.out.println("        threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("        in LetterInventory line#" + line);
				System.exit(1);
			}
		}
		System.out.println("passed");
		System.out.println();
	}

	/**
	 * Tests the size method of LetterRecord by comparing the size method return
	 * value with correct results in the file for each test case printing the
	 * result of testing, exiting the program if an error is seen
	 * 
	 * @param records
	 *            - array of the letter record for each of the file test cases
	 * @param input
	 *            - facilitates file input, positioned at a sequence of correct
	 *            sizes for the given test cases
	 * @param strings
	 *            - array of test cases
	 */
	public static void testSize(LetterRecord[] records, Scanner input, String[] strings) {
		System.out.println("Testing size...");
		for (int i = 0; i < strings.length; i++) {
			System.out.print("    testing \"" + strings[i] + "\"");
			int correct = input.nextInt();
			int test = 0;
			try {
				test = records[i].size();
			} catch (Exception e) {
				System.out.println("...failed");
				System.out.println("        threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("        in LetterRecord line#" + line);
				System.exit(1);
			}
			if (correct == test) {
				System.out.println("...passed");
			} else {
				System.out.println("...failed");
				System.out.println("        correct size = " + correct);
				System.out.println("        your size    = " + test);
				System.exit(1);
			}
		}
		System.out.println("passed");
		System.out.println();
	}

	/**
	 * Tests the toString method of LetterRecord by comparing the toString
	 * method return value with correct results in the file for each test case
	 * printing the result of testing, exiting the program if an error is seen
	 * 
	 * @param records
	 *            - array of the letter record for each of the file test cases
	 * @param input
	 *            - facilitating file input, positioned at a sequence of correct
	 *            toString entries for the given test cases
	 * @param strings
	 *            - array of test cases
	 */
	public static void testToString(LetterRecord[] records, Scanner input, String[] strings) {
		System.out.println("Testing toString...");
		for (int i = 0; i < strings.length; i++) {
			System.out.print("    testing \"" + strings[i] + "\"");
			String correct = input.next();
			String test = "";
			try {
				test = records[i].toString();
			} catch (Exception e) {
				System.out.println("...failed");
				System.out.println("        threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("        in LetterRecord line#" + line);
				System.exit(1);
			}
			if (correct.equals(test)) {
				System.out.println("...passed");
			} else {
				System.out.println("...failed");
				System.out.println("        correct toString = " + correct);
				System.out.println("        your toString    = " + test);
				System.exit(1);
			}
		}
		System.out.println("passed");
		System.out.println();
	}

	/**
	 * Tests the get method of LetterRecord by comparing the get method return
	 * value with correct results in the file for each test case printing the
	 * result of testing, exiting the program if an error is seen
	 * 
	 * @param records
	 *            - array of the letter record for each of the file test cases
	 * @param input
	 *            - facilitating file input, positioned at a sequence of correct
	 *            get entries for the given test cases (26 counts for each case
	 *            for each of the 26 letters)
	 * @param strings
	 *            - array of test cases
	 */
	public static void testGet(LetterRecord[] records, Scanner input, String[] strings) {
		System.out.println("Testing get...");
		for (int i = 0; i < strings.length; i++) {
			System.out.print("    testing \"" + strings[i] + "\"");
			for (char ch = 'a'; ch <= 'z'; ch++) {
				int correct = input.nextInt();
				testLetter(ch, correct, records[i]);
				testLetter(Character.toUpperCase(ch), correct, records[i]);
			}
			System.out.println("...passed");
		}
		System.out.println("passed");
		System.out.println();
	}

	/**
	 * Tests whether a call on the get method for the given character returns
	 * the given count
	 * 
	 * @param ch
	 *            - the letter of the alphabet being tested
	 * @param correct
	 *            - the correct count of that letter
	 * @param record
	 *            - the LetterRecord being tested
	 */
	public static void testLetter(char ch, int correct, LetterRecord record) {
		int test = 0;
		try {
			test = record.get(ch);
		} catch (Exception e) {
			System.out.println("...failed for get on '" + ch + "'");
			System.out.println("        threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("        in LetterRecord line#" + line);
			System.exit(1);
		}
		if (correct != test) {
			System.out.println("...failed for get on '" + ch + "'");
			System.out.println("        correct get = " + correct);
			System.out.println("        your get    = " + test);
			System.exit(1);
		}
	}

	/**
	 * Tests the isEmpty method of the LetterRecord class by comparing its
	 * return value to the correct value from the file printing the result of
	 * testing, exiting the program if an error is seen.
	 * 
	 * @param records
	 *            - array of the letter record for each of the file test cases
	 * @param input
	 *            - facilitates reading the file, positioned at a sequence of
	 *            correct isEmpty entries for the given test cases
	 * @param strings
	 *            - array of test cases
	 */
	public static void testIsEmpty(LetterRecord[] records, Scanner input, String[] strings) {
		System.out.println("Testing isEmpty...");
		for (int i = 0; i < strings.length; i++) {
			System.out.print("    testing \"" + strings[i] + "\"");
			boolean correct = input.nextBoolean();
			boolean test = false;
			try {
				test = records[i].isEmpty();
			} catch (Exception e) {
				System.out.println("...failed");
				System.out.println("        threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("        in LetterRecord line#" + line);
				System.exit(1);
			}
			if (correct == test) {
				System.out.println("...passed");
			} else {
				System.out.println("...failed");
				System.out.println("        correct isEmpty = " + correct);
				System.out.println("        your isEmpty    = " + test);
				System.exit(1);
			}
		}
		System.out.println("passed");
		System.out.println();
	}
}
