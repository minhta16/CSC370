
/**
 * Class Description: The class is used to manage the game of Seeker.
 * 
 * 
 * @author Minh Ta
 * @date 09/12/18
 * @version CSC370 Program 2
 * 
 * Cite assistance (who and what):
 * 
 * 
 * 
 */

import java.util.List;

public class SeekerManager {
	private SeekerNode frontRing;			// a SeekerNode that keeps track of the front of the Ring
	private SeekerNode frontCaptured;		// a SeekerNode that keeps track of the front of the captured list
	
	/**
	 * Construct a new Seeker Manager, importing all names to a seeker ring.
	 * @param names A list of names of Seeker Game's participants.
	 * @throws IllegalArgumentException when either name is null or name contains nothing.
	 */
	public SeekerManager(List<String> names) {
		if (names == null || names.size() == 0) {
			throw new IllegalArgumentException("Invalid names.");
		}
		
		frontRing = new SeekerNode(names.get(0));
		SeekerNode current = frontRing;
		for(int i = 1; i < names.size(); i++) {
			SeekerNode newNode = new SeekerNode(names.get(i));
			current.next = newNode;
			current = current.next;
		}
		frontCaptured = null;
	}
	
	/**
	 * Print out the Seekers Ring.
	 */
	public void printSeekingRing() {
		SeekerNode current = frontRing;
		
		// prints out the winner if the list has 1 element
		if (frontRing.next == null) {
			System.out.println(current.name + " won the game!");
		} else {
			while (current.next != null) {
				System.out.println(current.name + " is seeking " + current.next.name);
				current = current.next;
			}
			// special case for the last element
			System.out.println(current.name + " is seeking " + frontRing.name);
		}
	}
	
	/**
	 * Print the captured list.
	 */
	public void printCapturedList() {
		if (frontCaptured != null) {
			SeekerNode current = frontCaptured;
			System.out.println("  " + current.name + " was captured by " + current.capturedBy);
			while (current.next != null) {
				current = current.next;
				System.out.println("  " + current.name + " was captured by " + current.capturedBy);
			}
		}
	}
	
	/**
	 * Check to see if the Seekers Ring contains a name on it.
	 * @param name a name to check.
	 * @return true if the Seekers Ring contains name.
	 */
	public boolean seekingRingContains(String name) {
		boolean contain = false;
		SeekerNode current = frontRing;
		
		// special case for 1st element
		if (current.name.equalsIgnoreCase(name)) {
			return true;
		}
		
		while (current.next != null) {
			current = current.next;
			if (current.name.equalsIgnoreCase(name)) {
				contain = true;
			}
		}
		return contain;
	}

	/**
	 * Check to see if the captured list contains a name on it.
	 * @param name a name to check.
	 * @return true if the captured list contains name.
	 */
	public boolean capturedListContains(String name) {
		boolean contain = false;
		SeekerNode current = frontCaptured;
		
		if (current != null) {
			while (current.next != null) {
				if (current.name.equalsIgnoreCase(name)) {
					contain = true;
				}
				current = current.next;
			}
		}
		return contain;
	}
	
	/**
	 * @return true if game is over (when the Seekers Ring only has 1 name left).
	 */
	public boolean gameOver() {
		return frontRing.next == null;
	}
	
	/**
	 * Removes a name from the Seekers Ring, then put it in front of the captured list.
	 * @param name a name to remove.
	 * @throws IllegalStateException when the game is already over.
	 * @throws IllegalArgumentException when the name does not exist in the Seekers Ring.
	 */
	public void captured(String name) {
		if (frontRing.next == null) {
			throw new IllegalStateException("The game is over.");
		}

		int FALSE = 0, FRONT = 1, MIDDLE = 2;
		int personCaptured = FALSE;
		
		// if the captured is in the front of the ring, first add them to the captured list.
		if (frontRing.name.equalsIgnoreCase(name)) {
			SeekerNode temp = frontRing.next;
			frontRing.next = frontCaptured;
			frontCaptured = frontRing;
			frontRing = temp;
			personCaptured = FRONT;
		}

		SeekerNode current = frontRing;
		// the loop still goes on if the captured is in front of the ring because we need to
		//	find the last person in the ring and blame them for the capture of the person in front.
		while (current.next != null && personCaptured != MIDDLE) {
			SeekerNode prevNode = current;
			current = current.next;
			
			// captured will be labeled as MIDDLE if the person got captured is
			// 	in the middle of the ring. This if checks if the next person is
			//	going to be captured, then set capturedBy to the current person.
			if (personCaptured == FALSE && current.name.equalsIgnoreCase(name)) {
				personCaptured = MIDDLE;
				
				// if current.next.next is the last element then just eliminate it, else
				//	skip current.next
				SeekerNode temp = current.next;
				current.next = frontCaptured;
				frontCaptured = current;
				prevNode.next = temp;
				frontCaptured.capturedBy = prevNode.name;
			}
		}
		
		// if the captured is in front of the list, set capturedBy to the last node of the list
		if (personCaptured == FRONT) {
			frontCaptured.capturedBy = current.name;
		}
		
		// if the loop went through the ring and captured is still FALSE, then the name does not
		//	match anyone in our ring.
		if (personCaptured == FALSE) {
			throw new IllegalArgumentException("The name does not match that of any person in the ring.");
		}
	}
	
	
	// The SeekerNode class is used to store the information for one
	// player in the game of seeker. Initially the "capturedBy" field
	// is set to null, but when the person is captured, this should be
	// set to the name of the captor.

	private static class SeekerNode {
		private String name; // this person's name
		private String capturedBy; // name of who captured this person (null if
									// still in the seeking ring)
		private SeekerNode next; // next node in the list

		private SeekerNode(String name) {
			this(name, null);
		}

		private SeekerNode(String name, SeekerNode next) {
			this.name = name;
			this.capturedBy = null;
			this.next = next;
		}
	}

}
