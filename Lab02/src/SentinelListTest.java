import java.util.Iterator;

/*
 * Tests the implementation of the SentinelList class
 */
public class SentinelListTest {

	public static void main(String[] args) {
		ListADT<String> list = new SentinelList<String>();

		System.out.println("\n\n*****Testing toString for empty list*****");
		System.out.println("An empty list: " + list);
		if (list.toString().equals("[]")) {
			System.out.println("   passed.");
		} else {
			System.out.println("   failed.");
			System.out.println("    list should have been []");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing isEmpty*****");
		if (list.isEmpty()) {
			System.out.println("   passed. The list is empty.");
		} else {
			System.out.println("   failed. The list should be empty.");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing appending add and using toString*****");
		list.add("alpha");
		System.out.println("List after one node added: " + list);
		if (list.toString().equals("[alpha]")) {
			System.out.println("   passed adding first node");
		} else {
			System.out.println("    failed");
			System.out.println("    list should have been [alpha]");
			System.exit(1);
		}
		list.add("beta");
		System.out.println("List after two nodes added: " + list);
		if (list.toString().equals("[alpha, beta]")) {
			System.out.println("   passed adding second node");
		} else {
			System.out.println("    failed");
			System.out.println("    list should have been [alpha, beta]");
			System.exit(1);
		}	
		list.add("gamma");
		list.add("delta");
		list.add("epsilon");

		System.out.println("A list with 5 elements: " + list);
		if (list.toString().equals("[alpha, beta, gamma, delta, epsilon]")) {
			System.out.println("   passed adding more nodes");
		} else {
			System.out.println("    failed");
			System.out.println("    list should have been [alpha, beta, gamma, delta, epsilon]");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing isEmpty*****");
		if (!list.isEmpty()) {
			System.out.println("   passed. The list has data.");
		} else {
			System.out.println("    failed. The list has data.");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing size*****");
		System.out.println("The list has " + list.size() + " elements.");
		if (list.size() == 5) {
			System.out.println("   passed.");
		} else {
			System.out.println("    failed.");
			System.out.println("    size should have been 5");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing get*****");
		System.out.println("The element at index 3 is " + list.get(3));
		if (list.get(3).equals("delta")) {
			System.out.println("   passed.");
		} else {
			System.out.println("    failed.");
			System.out.println("    The element should have been delta");
			System.exit(1);
		}
		System.out.println("The element at index 4 is " + list.get(4));
		if (list.get(4).equals("epsilon")) {
			System.out.println("   passed.");
		} else {
			System.out.println("    failed.");
			System.out.println("    The element should have been epsilon");
			System.exit(1);
		}
		System.out.println("The element at index 0 is " + list.get(0));
		if (list.get(0).equals("alpha")) {
			System.out.println("   passed.");
		} else {
			System.out.println("    failed.");
			System.out.println("    The element should have been alpha");
			System.exit(1);
		}

		System.out.println("\nTesting list.get with invalid index");
		try {
			System.out.println("\nThe element at index 5 " + list.get(5));
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("    failed");
			System.exit(1);
		}

		System.out.println("\nTesting list.get with invalid index");
		try {
			System.out.println("\nThe element at index -1 " + list.get(-1));
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("    failed");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing indexOf*****");
		System.out.println("Index of alpha: " + list.indexOf("alpha"));
		if (list.indexOf("alpha") == 0) {
			System.out.println("    passed");
		} else {
			System.out.println("    failed");
			System.out.println("    alpha should be been found at index 0");
			System.exit(1);
		}
		System.out.println("Index of beta: " + list.indexOf("beta"));
		if (list.indexOf("beta") == 1) {
			System.out.println("    passed");
		} else {
			System.out.println("    failed");
			System.out.println("    beta should be been found at index 1");
			System.exit(1);
		}
		System.out.println("Index of epsilon: " + list.indexOf("epsilon"));
		if (list.indexOf("epsilon") == 4) {
			System.out.println("    passed");
		} else {
			System.out.println("    failed");
			System.out.println("    epsilon should be been found at index 4");
			System.exit(1);
		}

		System.out.println("\nTesting list.indexOf with invalid value");
		if (list.indexOf("phi") == -1) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    phi should have returned -1");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing contains*****");
		System.out.println("Contains for element in list.");
		if (list.contains("beta")) {
			System.out.println("    passed");
		} else {
			System.out.println("    failed.");
			System.exit(1);
		}
		System.out.println("Contains for element not in list.");
		if (list.contains("phi")) {
			System.out.println("    failed");
			System.exit(1);
		} else {
			System.out.println("    passed");
		}

		System.out.println("\n\n*****Testing add(index, value)*****");
		System.out.println("Adding an element at index 0");
		list.add(0, "first");
		System.out.println("With first added at index 0: " + list);
		if (list.toString().equals("[first, alpha, beta, gamma, delta, epsilon]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [first, alpha, beta, gamma, delta, epsilon]");
			System.exit(1);
		}

		System.out.println("\nAdding an element at index 1");
		list.add(1, "second");
		System.out.println("With second added at index 1: " + list);
		if (list.toString().equals("[first, second, alpha, beta, gamma, delta, epsilon]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [first, second, alpha, beta, gamma, delta, epsilon]");
			System.exit(1);
		}

		System.out.println("\nAdding an element at index 7");
		list.add(7, "last");
		System.out.println("With last added at index 7: " + list);
		if (list.toString().equals("[first, second, alpha, beta, gamma, delta, epsilon, last]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [first, second, alpha, beta, gamma, delta, epsilon, last]");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing remove(index)*****");
		System.out.println("Removing an element at index 0");
		list.remove(0);
		System.out.println("With element at index 0 removed: " + list);
		if (list.toString().equals("[second, alpha, beta, gamma, delta, epsilon, last]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [second, alpha, beta, gamma, delta, epsilon, last]");
			System.exit(1);
		}

		System.out.println("\nRemoving an element at index 3");
		list.remove(3);
		System.out.println("With element at index 3 removed: " + list);
		if (list.toString().equals("[second, alpha, beta, delta, epsilon, last]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [second, alpha, beta, delta, epsilon, last]");
			System.exit(1);
		}

		System.out.println("\nRemoving an element at index 5");
		list.remove(5);
		System.out.println("With element at index 5 removed: " + list);
		if (list.toString().equals("[second, alpha, beta, delta, epsilon]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [second, alpha, beta, delta, epsilon]");
			System.exit(1);
		}

		System.out.println("\nTesting list.remove with invalid index");
		try {
			list.remove(5);
			System.out.println("\nList with element at invalid index 5 removed: " + list);
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		}
		
		
		System.out.println("\n\n*****Testing add(value) after remove*****");
		list.add("end");
		System.out.println("Adding end as the last element: " + list);
		if (list.toString().equals("[second, alpha, beta, delta, epsilon, end]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [second, alpha, beta, delta, epsilon, end]");
			System.exit(1);
		}

		System.out.println("\n\n*****Testing set(index, value)*****");
		list.set(0, "newZero");
		System.out.println("Testing list.set at index 0: " + list);
		if (list.toString().equals("[newZero, alpha, beta, delta, epsilon, end]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [newZero, alpha, beta, delta, epsilon, end]" );
			System.exit(1);
		}

		list.set(5, "newFive");
		System.out.println("\nTesting list.set at index 5: " + list);
		if (list.toString().equals("[newZero, alpha, beta, delta, epsilon, newFive]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be [newZero, alpha, beta, delta, epsilon, newFive]");
			System.exit(1);
		}
		
		System.out.println("\nTesting list.set with invalid index");
		try {
			list.set(6, "newEnd");
			System.out.println("\nList with element at invalid index 6 set: " + list);
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		}

//		System.out.println("\n\n*****Testing iterator implementation*****");	
//		Iterator<String> itr = list.iterator();
//		System.out.println("Iterating through the list with a while loop:");
//		String result = "";
//		while (itr.hasNext()) {
//			String element= itr.next();
//			System.out.println(element);
//			result = result + element;
//		}
//		if (result.equals("newZeroalphabetadeltaepsilonnewFive")) {
//			System.out.println("   passed.");
//		} else {
//			System.out.println("   failed.");
//			System.exit(1);
//		}
//		
//		itr = list.iterator();
//		System.out.println("\nIterating through the list with a for each loop:");
//		result = "";
//		for (String element : list) {
//			result = result + element;
//			System.out.println(element);
//		}
//		if (result.equals("newZeroalphabetadeltaepsilonnewFive")) {
//			System.out.println("   passed.");
//		} else {
//			System.out.println("   failed.");
//			System.exit(1);
//		}

		System.out.println("\n\n*****Testing clear*****");
		list.clear();
		System.out.println("After clearing: " + list);
		if (list.toString().equals("[]")) {
			System.out.println("   passed.");
		} else {
			System.out.println("   failed.");
			System.out.println("list should be []");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Testing remove from empty list*****");
		try {
			list.remove(0);
			System.out.println("\nList with element at invalid index 0 removed: " + list);
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		}

		System.out.println("\n\n*****Testing get from empty list*****");
		try {
			System.out.println("\nThe element at index 0 " + list.get(0));
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("    failed");
			System.exit(1);
		}
		
		
		System.out.println("\n\n*****Testing indexOf with empty list*****");
		if (list.indexOf("phi") == -1) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    phi should have returned -1");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Testing contains with empty list*****");
		if (list.contains("phi")) {
			System.out.println("    failed");
			System.exit(1);
		} else {
			System.out.println("    passed");
		}
		
		System.out.println("\n\n*****Testing set with empty list*****");
		try {
			list.set(0, "newEnd");
			System.out.println("\nList with element at invalid index 0 set: " + list);
			System.out.println("    failed");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw IndexOutOfBoundException " + e.getMessage());
			System.out.println("    passed");
		}
		
		System.out.println("\n\n*****Testing size with empty list*****");
		System.out.println("The list has " + list.size() + " elements.");
		if (list.size() == 0) {
			System.out.println("   passed.");
		} else {
			System.out.println("    failed.");
			System.out.println("    size should have been 5");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Testing add after clear*****");
		list.add("alpha");
		System.out.println("List after one add: " + list);
		if (list.toString().equals("[alpha]")) {
			System.out.println("   passed.");
		} else {
			System.out.println("   failed.");
			System.out.println("   list should be [alpha, beta]");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Testing remove for one element list*****");
		System.out.println("Removing an element at index 0");
		list.remove(0);
		System.out.println("With element at index 0 removed: " + list);
		if (list.toString().equals("[]")) {
			System.out.println("   passed");
		} else {
			System.out.println("    failed");
			System.out.println("    list should be []");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Testing add after removing all elements*****");
		list.add("done");
		System.out.println("List after one add: " + list);
		if (list.toString().equals("[done]")) {
			System.out.println("   passed.");
		} else {
			System.out.println("   failed.");
			System.out.println("   list should be [done]");
			System.exit(1);
		}
		
		System.out.println("\n\n*****Passed all tests!*****");
	}

}
