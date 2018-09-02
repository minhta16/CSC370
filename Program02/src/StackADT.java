import java.util.NoSuchElementException;

/**
 * An interface that describes a stack abstract data type
 *
 */

public interface StackADT<E> {
	/**
	 * Add an item onto the stack
	 * 
	 * @param item
	 *            the data item to add (of type E)
	 */
	public void push(E item);

	/**
	 * Remove the top item from the stack
	 * 
	 * @return the top item in the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 */
	public E pop() throws NoSuchElementException;

	/**
	 * Return the top item from the stack without removing it
	 * 
	 * @return the top item in the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Find how many items are in the stack
	 * 
	 * @return the number of items in the stack
	 */
	public int size();

	/**
	 * Determine if the stack is empty
	 * 
	 * @return true if the size is 0, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Clear out the data structure
	 */
	public void clear();

}
