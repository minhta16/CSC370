/**
 * Simple test program for the methods of HangmanGame
 * 
 * @author
 * @date
 * @version CSC370-Program4
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanSimpleTest {

	public static void main(String[] args) throws FileNotFoundException{
		// open the dictionary file and read dictionary into an ArrayList
		Scanner input = new Scanner(new File("dictionary2.txt"));
		List<String> dictionary = new ArrayList<String>();
		while (input.hasNext()) {
			dictionary.add(input.next().toLowerCase());
		}
		HangmanGame game = new HangmanGame(dictionary, 4, 10);
		
		input.close();

	}

}
