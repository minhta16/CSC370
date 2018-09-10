// Class LinkedIntList can be used to store a list of integers.

public class LinkedIntList{
	private ListNode front; // first value in the list
	private int size;

	// post: constructs an empty list
	public LinkedIntList() {
		front = null;
		size = 0;
	}

	// post: returns the current number of elements in the list
	public int size() {
		return size;
	}

	// post: returns the integer at the given index in the list
	public int get(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException("invalid index");
		}
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	// post: creates a comma-separated, bracketed version of the list
	public String toString() {
		if (size == 0) {
			return "[]";
		} else {
			String result = "[" + front.data;
			ListNode current = front.next;
			while (current != null) {
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}

	// post : returns the position of the first occurrence of the given
	// value (-1 if not found)
	public int indexOf(int value) {
		int index = 0;
		ListNode current = front;
		while (current != null) {
			if (current.data == value) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	// post: appends the given value to the end of the list
	public void add(int value) {
		if (front == null) {
			front = new ListNode(value);
		} else {
			ListNode current = front;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(value);
		}
		size++;
	}

	// post: inserts the given value at the given index
	public void add(int index, int value) {
		if (index < 0 || index > size){
			throw new IndexOutOfBoundsException("index " + index);
		}
		if (index == 0) {
			front = new ListNode(value, front);
		} else {
			ListNode current = front;
			for (int i = 0; i < index-1; i++) {
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
		}
		size++;
	}

	// post: removes value at the given index
	public void remove(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException("invalid index");
		}
		if (index == 0) {
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < index -1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
		size--;
	}
	
	// post: returns true if this list has no data
	public boolean isEmpty() {
		return (size == 0);
	}
	
	// post: returns true if this list contains this value
	public boolean contains(int value) {
		int index = 0;
		ListNode current = front;
		while (current != null) {
			if (current.data == value) {
				return true;
			}
			index++;
			current = current.next;
		}
		return false;
	}
	
	// post: changes data at this index to this value
	public void set(int index, int value) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException("invalid index");
		}
		
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		current.data = value;
	}
	
	// post: removes all data from this list
	public void clear() {
		front = null;
		size = 0;
	}

	// ListNode is a class for storing a single node of a linked
	// list. This node class is for a list of integer values.

	private class ListNode {
		private int data; // data stored in this node
		private ListNode next; // link to next node in the list

		// post: constructs a node with data 0 and null link
		private ListNode() {
			this(0, null);
		}

		// post: constructs a node with given data and null link
		private ListNode(int data) {
			this(data, null);
		}

		// post: constructs a node with given data and given link
		private ListNode(int data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
