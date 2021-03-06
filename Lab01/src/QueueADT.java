import java.util.NoSuchElementException;

/**
 * An interface that describes a queue abstract data type
 *
 */


public interface QueueADT<E> {
    /**
     * Add an item to the back of the queue
     * @param item the data item to add (of type E)
     */
    void add(E item);

    /**
     * Remove the item from the front of the queue
     * @return the first element is the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E remove() throws NoSuchElementException;

    /**
     * Return the first item from the front of the queue without removing it
     * @return the first item from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    E peek() throws NoSuchElementException;

    /**
     * Find how many items are in the queue
     * @return the number of items in the queue
     */
    int size();

    /**
     * Determine if the queue is empty
     * @return true if the size is 0, false otherwise
     */
    boolean isEmpty();

    /**
     * Clear out the data structure
     */
    void clear();

}


