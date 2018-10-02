/**
 * AnagramMain is a client program that prompts a user for the name of a
 * dictionary file and then gives the user the opportunity to find anagrams of
 * various phrases.  It constructs an MultiWordAnagrams object to do the actual
 * search for anagrams that match the user's phrases.
 */
import java.io.*;
import java.util.*;

public class AnagramMain {
    // dictionary file to use for input (change to dict1, dict2, dict3)
    private static final String DICTIONARY_FILE = "dict2.txt";
    
    // set to true to test runtime
    private static final boolean TIMING = true;
    
    // set to true to see the reduced dictionary
    private static final boolean DEBUG = false;
    
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Multiple Word Anagram Solver");
        System.out.println("Using dictionary file " + DICTIONARY_FILE + ".");

        // read dictionary into a set
        Scanner input = new Scanner(new File(DICTIONARY_FILE));
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNextLine()) {
            dictionary.add(input.nextLine());
        }
        dictionary = Collections.unmodifiableList(dictionary);   // read-only

        // create Anagrams object for, well, solving anagrams
        MultiWordAnagrams solver = new MultiWordAnagrams(dictionary);

        // get first phrase to solve
        Scanner console = new Scanner(System.in);
        String phrase = getPhrase(console);
        
        // loop to get/solve each phrase
        while (phrase.length() > 0) {
        	if (DEBUG){
	            System.out.println("All words found in \"" + phrase + "\":");
	            Set<String> allWords = solver.getWords(phrase);
	            System.out.println(allWords);
	            System.out.println();
        	}
            
            System.out.print("Max words to include (Enter for no max)? ");
            String line = console.nextLine().trim();
            
            System.out.println("\nAll anagrams of \"" + phrase + "\" are:");
            long startTime = System.currentTimeMillis();
            if (line.length() > 0) {
                // use a max
                int max = new Scanner(line).nextInt();
                solver.printAnagrams(phrase, max);   // print all anagrams of phrase with max or less words
            } else {
                // no max
                solver.printAnagrams(phrase);        // print all anagrams of phrase
            }
            long endTime = System.currentTimeMillis();
            System.out.println();
            
            // show elapsed time
            if (TIMING) {
                long elapsed = endTime - startTime;
                System.out.println(elapsed + " ms elapsed");
            }
            

            // get next phrase to solve
            phrase = getPhrase(console);
        }
    }
    
    // Helper to prompt for a phrase to generate anagrams.
    public static String getPhrase(Scanner console) {
        System.out.println();
        System.out.print("Find anagrams for this phrase: (Enter to quit)? ");
        return console.nextLine().trim();
    }
}
