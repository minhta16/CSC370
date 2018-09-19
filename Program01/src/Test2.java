
/**
 * This program tests stage 2 of the LetterRecord class. The program reads
 * from the file test2.txt which has a series of test cases with the correct answers.
 * 
 * @author Diane Mueller modified from Stuart Reges
 * @date 8/5/2017
 */

import java.util.*;
import java.io.*;

public class Test2 {
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("test2.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("You must copy test2.txt to this directory" + " before running the testing program.");
			System.exit(1);
		}
		LetterRecord tester = new LetterRecord("");
		System.out.println("Starting with empty record");
		check(tester, input);
		while (input.hasNext()) {
			char ch = input.next().charAt(0);
			int count = input.nextInt();
			System.out.println("setting count for " + ch + " to " + count);
			try {
				tester.set(ch, count);
			} catch (Exception e) {
				System.out.println("failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}
			check(tester, input);
		}
		System.out.println("All tests passed.");
	}

	/**
	 * Tests the size, toString, isEmpty, and get methods of the NameRecord
	 * class reporting if each test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord that will be used in testing the methods
	 * @param input
	 *            - facilitate file input, positioned at the beginning of a
	 *            2-line test case that contains the state of the given record
	 *            should be in after performing the call on each method
	 */
	public static void check(LetterRecord tester, Scanner input) {
		testSize(tester, input);
		testToString(tester, input);
		testIsEmpty(tester, input);
		testGet(tester, input);
		System.out.println("size, toString, isEmpty, and count all passed");
		System.out.println();
	}

	/**
	 * Tests the toString method of the LetterRecord class reporting whether or
	 * not the test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord to be tested
	 * @param input
	 *            - facilitates file input, positioned to read the correct
	 *            toString result for the record being tested
	 */
	public static void testToString(LetterRecord tester, Scanner input) {
		String correct = input.next();
		System.out.println("record = " + correct);
		String test = "";
		try {
			test = tester.toString();
		} catch (Exception e) {
			System.out.println("toString failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		if (!correct.equals(test)) {
			System.out.println("toString failed");
			System.out.println("    correct toString = " + correct);
			System.out.println("    your toString    = " + test);
			System.exit(1);
		}
	}

	/**
	 * Tests the size method of the LetterRecord class reporting whether or not
	 * the test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord to be tested
	 * @param input
	 *            - facilitates file input, positioned to read the correct size
	 *            result for the record being tested
	 */
	public static void testSize(LetterRecord tester, Scanner input) {
		int correct = input.nextInt();
		int test = 0;
		try {
			test = tester.size();
		} catch (Exception e) {
			System.out.println("size failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		if (correct != test) {
			System.out.println("size failed");
			System.out.println("    correct size = " + correct);
			System.out.println("    your size    = " + test);
			System.exit(1);
		}
	}

	/**
	 * Tests the isEmpty method of the LetterRecord class reporting whether or
	 * not the test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord to be tested
	 * @param input
	 *            - facilitates file input, positioned to read the correct
	 *            isEmpty result for the record being tested
	 */
	public static void testIsEmpty(LetterRecord tester, Scanner input) {
		boolean correct = input.nextBoolean();
		boolean test = false;
		try {
			test = tester.isEmpty();
		} catch (Exception e) {
			System.out.println("isEmpty failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		if (correct != test) {
			System.out.println("isEmpty failed");
			System.out.println("    correct isEmpty = " + correct);
			System.out.println("    your isEmpty    = " + test);
			System.exit(1);
		}
	}

	/**
	 * Tests the get method of the LetterRecord class for each letter of the
	 * alphabet reporting whether or not the test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord to be tested
	 * @param input
	 *            - facilitates file input, positioned to read the correct get
	 *            result for the record being tested
	 */
	public static void testGet(LetterRecord tester, Scanner input) {
		for (char ch = 'a'; ch <= 'z'; ch++) {
			int correct = input.nextInt();
			int test = 0;
			try {
				test = tester.get(ch);
			} catch (Exception e) {
				System.out.println("get failed for '" + ch + "'");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}
			if (correct != test) {
				System.out.println("get failed for '" + ch + "'");
				System.out.println("    correct get = " + correct);
				System.out.println("    your get    = " + test);
				System.exit(1);
			}
		}
	}
}
