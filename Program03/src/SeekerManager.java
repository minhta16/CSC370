
/**
 * Class Description:
 * 
 * 
 * @author 
 * @date
 * @version CSC370 Program 2
 * 
 * Cite assistance (who and what):
 * 
 * 
 * 
 */

import java.util.List;

public class SeekerManager {

	
	
	
	
	

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
