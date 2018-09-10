import java.util.*;

/**
 * @author(s): Minh Ta
 * @date: 09/10/18
 * @version: CSC370 Lab2 
 *
 * An implementation of a singly linked list with a dummy front node,
 * a reference to the last node, and a size field
 */
 public class SentinelList<E> implements ListADT<E> {
	//data fields
	private ListNode<E> front;			// the dummy null node of the list, before the 1st node.
	private ListNode<E> back;			// the last node of the list.
	private int size;					// number of elements other than the dummy node on the list.
	
	/**
	 * Construct a blank SentinelList. 
	 */
	public SentinelList () {
		front = new ListNode<E>();
		back = front;
		size = 0;
	}
	
	@Override
	/**
	 * @return a string representation of the list.
	 */
	public String toString() {
		String string = "[";
		ListNode<E> current = front;
		if (size == 0) {
			return "[]";
		}
		for (int i = 0; i < size - 1; i++) {
			current = current.next;
			string += current.data + ", ";
		}
		string += current.next.data + "]";
		return string;
	}

	@Override
	/**
	 * @return true if list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	/**
	 * Add an element to the end of the list.
	 */
	public void add(E value) {
		ListNode<E> newNode = new ListNode<E>(value);
		back.next = newNode;
		back = newNode;
		size++;
	}

	@Override
	/**
	 * Add an element to the designated index in the list.
	 * @param index - the index for the element to be added.
	 * @param value - the value of new element.
	 * @throws IndexOutOfBoundsException when index is negative or is greater than or equal to the size.
	 */
	public void add(int index, E value) {
		// separate case because index can be equal to size
		if (index < 0 || size < index) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		if (size == 0) {
			front.next = new ListNode<E>(value);
			back = front.next;
		}
		else {
			ListNode<E> behindCurrent = nodeAt(index - 1);
			behindCurrent.next = new ListNode<E>(value, behindCurrent.next);
		}
		size++;
	}

	
	@Override
	/**
	 * @return the size of the list.
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * Get the value of the node at a designated index.
	 * @param index - the index needed to get.
	 * @throws IndexOutOfBoundsException when index is negative or is greater than or equal to the size.
	 */
	public E get(int index) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		return current.data;
	}

	@Override
	/**
	 * @return the index of a value. -1 if the value doesn't exist in the list.
	 */
	public int indexOf(E value) {
		ListNode<E> current = front;
		for (int i = 0; i < size; i++) {
			current = current.next;
			if (current.data.equals(value)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	/**
	 * @return true if the list contains a value.
	 * @param value - the value to be checked.
	 */
	public boolean contains(E value) {
		return indexOf(value) != -1;
	}

	@Override
	/**
	 * Remove a node at a designated index.
	 * @param index - the index of the node to be removed.
	 * @throws IndexOutOfBoundsException when index is negative or is greater than or equal to size.
	 */
	public void remove(int index) {
		checkIndex(index);
		ListNode<E> behindCurrent = nodeAt(index - 1);
		
		//  current.next == null
		if (behindCurrent.next.next == null) {
			back = behindCurrent;
			
			// current = null
			behindCurrent.next = null;
		} else {
			// current = current.next
			behindCurrent.next = behindCurrent.next.next;
		}
		size--;
	}

	@Override
	/**
	 * Set a node's data to the data of value.
	 * @param index - the index of the node to be set.
	 * @param value - the value to set.
	 */
	public void set(int index, E value) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		current.data = value;	
	}

	@Override
	/**
	 * Remove all elements from the list.
	 */
	public void clear() {
		front.next = null;
		back = front; 
		size = 0;
	}
	
	private void checkIndex(int index) {
		if (index < 0 || size <= index) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
	}
	
	private ListNode<E> nodeAt(int index) {
		ListNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.next;
		}
		return current;
	}
	

	// ListNode is a class for storing a single node of a linked list.
	@SuppressWarnings("hiding")
	private class ListNode<E> {
		private E data;
		private ListNode<E> next;
		
		// default constructor with null data and a null link
		private ListNode() {
			this(null, null);
		}

	 	// constructs a node with this data and a null link
		private ListNode(E data) {
			this(data, null);
		}
		
		// constructs a node with this data and this link
		private ListNode(E data, ListNode<E> next) {
			this.data = data;
			this.next = next;
		}
	}


	 // LinkedIterator provides an iterator for the SentinelList class
	class LinkedIterator implements Iterator<E> {
		private ListNode<E> current;
		private ListNode<E> before;
		private boolean nextCalled;

		private LinkedIterator() {
			current = front;
			before = front;
			nextCalled = false;
		}
		
		@Override
		/**
		 * @return true if there is a next element
		 */
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		/**
		 * @return the next element of the list.
		 * @throws NoSuchElementException when there is no element next in the list.
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no element next in the list.");
			}
			nextCalled = true;
			before = current;
			current = current.next;
			return current.data;
		}
		
		@Override
		/**
		 * Remove the node previously returned by next().
		 * @throws IllegalStateException when next() has not been called when remove() is called.
		 */
		public void remove() {
			if (!nextCalled) {
				throw new IllegalStateException("next() has not been called.");
			} else {
				before.next = current.next;
				nextCalled = false;
			}
		}
		
		
	}


	@Override
	/**
	 * @return an iterator of the list.
	 */
	public Iterator<E> iterator() {
		return new LinkedIterator();
	}


}
