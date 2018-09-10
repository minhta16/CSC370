/**
 * Interface for a sequential list of elements
 * 
 * @param <E>
 *            - generic type of element
 */

public interface ListADT<E> extends Iterable<E>{

	/**
	 * @return the number of elements in this list
	 */
	public int size();

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            - the index of the element to return
	 * @return - the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             - if the index is out of range (index < 0 || index >= size())
	 */
	public E get(int index);

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param value
	 *            - the element to search for in the list
	 * @return - the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element.
	 */
	public int indexOf(E value);

	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return - true if this list contains no elements
	 */
	public boolean isEmpty();

	/**
	 * returns whether the value is in this list
	 * 
	 * @param value
	 *            - the specified value to find in this list
	 * @return true if the specified value is in this list
	 */
	public boolean contains(E value);

	/**
	 * Appends the specified value to the end of this list
	 * 
	 * @param value--
	 *            the value to be appended
	 */
	public void add(E value);

	/**
	 * Inserts the specified value at the specified position in this list
	 * 
	 * @param index
	 *            - the specified position at which the specified value is to be
	 *            inserted
	 * @param value
	 *            - value to be inserted
	 * @throws IndexOutOfBoundsException
	 *             - if the index is out of range (index < 0 || index > size())
	 */
	public void add(int index, E value);


	/**
	 * Removes the element at the specified position in this list
	 * 
	 * @param index
	 *            - the specified position of the element to remove
	 * @throws IndexOutOfBoundsException
	 *             - if the index is out of range (index < 0 || index >= size())
	 */
	public void remove(int index);

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element
	 * 
	 * @param index
	 *            - the specified position of the element to be replaced
	 * @param value
	 *            - the specified element that will be placed in the list
	 * @throws IndexOutOfBoundsException
	 *             - if the index is out of range (index < 0 || index >= size())
	 */
	public void set(int index, E value);

	/**
	 * Removes all of the elements from this list
	 */
	public void clear();
}
