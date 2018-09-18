import java.util.*;
/**
 * MatchingGame plays a solitaire type game where adjacent pairs of numbers 
 * with the same 10s digit or the same 1s digit  in a list are removed. 
 * If all numbers are removed from the list, you win!
 * 
 * @author Minh Ta
 * @date 09/14/2018
 * @version  CSC370 Lab3--Matching Game
 *
 */
public class MatchingGame {

	public static final int LIST_NUM = 20;
	public static final int RANGE = 90;
	public static final int LOW_NUM = 10;
	
	public static void main(String[] args) {
		
		// Create a list of random integers to begin the game

		List<Integer> numbers;
		numbers = createList();
		play(numbers);
	}

	// Create a list of random integers to begin the game
	public static List<Integer> createList() {
		List<Integer> numbers = new LinkedList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < LIST_NUM; i++){
			numbers.add(rand.nextInt(RANGE) + LOW_NUM);
		}
		System.out.println("Matching Game");
		System.out.println("Initial list: " +  numbers);
		return numbers;
	}

	//implement the game using the ListIterator
	public static void play(List<Integer> numbers) {
		ListIterator<Integer> itr;
		boolean eliminated = false;
		// Keep going if there are pairs left
		do {
			// Get a ListIterator to process the list	
			itr = numbers.listIterator();
			eliminated = false;
			while (!eliminated && itr.hasNext()) {
				
				// check if the pair has the same 10th digits or 1st digits	
				int curNum = itr.next();
				if (itr.hasNext()) {
					int nextNum = itr.next();
					
					if (curNum % 10 == nextNum % 10 || curNum / 10 == nextNum / 10) {
						eliminated = true;
						System.out.println("\nRemoved: " + curNum + ", " + nextNum);
						itr.remove();
						itr.previous();
						itr.remove();
						System.out.println("Updated list: " + numbers);
					} else {
						itr.previous();
					}
				}
			}
		} while (eliminated && numbers.size() != 0);
		
		// Output the results
		if (numbers.isEmpty()){
			System.out.println("\nYou Win!");
		} else{
			System.out.println("\nTry Again. There were " + numbers.size() + " values left.");
		}
	}
	
	

}
