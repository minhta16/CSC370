
/**
 * This program tests stage 3 of the LetterRecord class.  The program reads
 * from the file test3.txt which has a series of test cases with the correct
 *  answers.
 *  
 *  @author Diane Mueller modified from Stuart Reges
 *  @data 8/5/2017
 */
import java.util.*;
import java.io.*;

public class Test3 {
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("test3.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("You must copy test3.txt to this directory" + " before running the testing program.");
			System.exit(1);
		}
		while (input.hasNext()) {
			String s1 = input.nextLine();
			String s2 = input.nextLine();
			
			System.out.println("Testing these two strings:");
			System.out.println("    i1: \"" + s1 + "\"");
			System.out.println("    i2: \"" + s2 + "\"");
			System.out.println("constructing i1 and i2");
			LetterRecord i1 = null;
			LetterRecord i2 = null;
			try {
				i1 = new LetterRecord(s1);
				i2 = new LetterRecord(s2);
			} catch (Exception e) {
				System.out.println("failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}

			System.out.println("constructing and testing i1.add(i2)");
			try {
				LetterRecord test = i1.add(i2);
				check(test, input);
			} catch (Exception e) {
				System.out.println("add failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}

			System.out.println("constructing and testing i2.add(i1)");
			try {
				LetterRecord test = i2.add(i1);
				check(test, input);
			} catch (Exception e) {
				System.out.println("add failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}

			System.out.println("constructing and testing i1.subtract(i2)");
			try {
				LetterRecord test = i1.subtract(i2);
				check(test, input);
			} catch (Exception e) {
				System.out.println("subtract failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}

			System.out.println("constructing and testing i2.subtract(i1)");
			try {
				LetterRecord test = i2.subtract(i1);
				check(test, input);
			} catch (Exception e) {
				System.out.println("subtract failed");
				System.out.println("    threw exception: " + e);
				int line = e.getStackTrace()[0].getLineNumber();
				System.out.println("    in LetterRecord line#" + line);
				System.exit(1);
			}
			if (input.hasNextLine())
				input.nextLine(); // to skip end-of-line
			System.out.println();
		}
		
		LetterRecord i1 = null;
		LetterRecord i2 = null;
		
		try {
			i1 = new LetterRecord("Last test.");
		} catch (Exception e) {
			System.out.println("failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		
		System.out.println("constructing and testing i1.add(i2) when i2 is null");
		try {
			LetterRecord test = i1.add(i2);
			if (test == i1) {
				System.out.println("failed because result of add should not be a reference to i1");
				System.exit(1);
			}

			if (test.size() != i1.size()) {
				System.out.println("failed because the deep copy of i1 did not have the same size");
				System.exit(1);
			}
			test.set('i', 5);
			if (test.toString().equals(i1.toString())){
				System.out.println("failed because the array of the result of add should not be a reference to i1's array");
				System.exit(1);
			}

		} catch (Exception e) {
			System.out.println("add failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		
		System.out.println("constructing and testing i1.subtract(i2) when i2 is null");
		try {
			LetterRecord test = i1.subtract(i2);
			if (test == i1) {
				System.out.println("failed because result of subtract should not be a reference to i1");
				System.exit(1);
			}
			if (test.size() != i1.size()) {
				System.out.println("failed because the deep copy of i1 did not have the same size");
				System.exit(1);
			}
			test.set('i', 5);
			if (test.toString().equals(i1.toString())){
				System.out.println("failed because the array of the result of subtract should not be a reference to i1's array");
				System.exit(1);
			}


		} catch (Exception e) {
			System.out.println("add failed");
			System.out.println("    threw exception: " + e);
			int line = e.getStackTrace()[0].getLineNumber();
			System.out.println("    in LetterRecord line#" + line);
			System.exit(1);
		}
		
		
		
		System.out.println("\n\nAll tests passed.");
	}

	/**
	 * Tests the size, toString, isEmpty, and get methods of the NameRecord
	 * class reporting if each test was passed
	 * 
	 * @param tester
	 *            - the LetterRecord that will be used in testing the methods
	 * @param input
	 *            - facilitate file input, positioned at the beginning of a test
	 *            case that contains the state of the given record should be in
	 *            after performing the call on each method
	 */
	public static void check(LetterRecord tester, Scanner input) {
		String text = input.next();
		if (text.equals("null")) {
			if (tester == null) {
				System.out.println("passed because record is null");
			} else {
				System.out.println("failed because record should be null");
				System.exit(1);
			}
		} else {
			testToString(tester, input, text);
			testSize(tester, input);
			testIsEmpty(tester, input);
			testGet(tester, input);
			System.out.println("toString, size, isEmpty, and count all passed");
		}

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
	public static void testToString(LetterRecord tester, Scanner input, String correct) {
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
