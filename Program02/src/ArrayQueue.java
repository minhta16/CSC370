/*
 * Name(s): Minh Ta
 * Course: CSC370--Lab 1
 * Date: 08/29/18
 */

/**
 * An circular array implementation of a queue data structure
 */

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E> {
	
	// data fields
    private E[] elementData; // list of values
    private int size;        // current number of elements in the queue
    private int front;		// index of the element at the front of the queue
    private int rear;		// index where the next element should be added in the queue
    
    public static final int DEFAULT_CAPACITY = 4;
    
    public ArrayQueue() {
    	this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
    	if (capacity < 0) {
    		throw new IllegalArgumentException("Capacity must be non-negative. Capacity: ." + capacity);
    	}
    	elementData = (E[]) new Object[capacity];
    	size = 0;
    	front = 0;
    	rear = 0;
    }
    
    /**
     * @return a String representation of the queue
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
        	String retStr = "[";
        	if (front < rear) {
            	for (int i = front; i <= rear - 2; i++) {
            		retStr += elementData[i] + ", ";
            	}
        	} else {
        		// from front to end of the array
        		for (int i = front; i <= elementData.length - 1; i++) {
            		retStr += elementData[i] + ", ";
            	}
        		
        		// from start of the array to rear
        		for (int i = 0; i < rear - 1; i++) {
            		retStr += elementData[i] + ", ";
            	}
        		
        	}
        	if (rear == 0) {
            	retStr += elementData[elementData.length - 1] + "]";
        	} else {
            	retStr += elementData[rear - 1] + "]";
        	}
        	return retStr;
        }
    }


	@SuppressWarnings("unchecked")
    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled plus 1 (or more if given capacity is even larger)
	private void ensureCapacity(int capacity) {
        if (capacity >= elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            
            
            // Instantiate a new larger array with the elements copied to it
            
            E[] newArray = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
            	int index = front + i;
            	if (index >= elementData.length) {
            		index -= elementData.length;
            	}
            	newArray[i] = elementData[index];
            }
            elementData = newArray;
            front = 0;
            rear = size;
        }
    }

	@Override
	/**
     * Add an item to the back of the queue
     * @param item the data item to add (of type E)
     */
	public void add(E item) {
		ensureCapacity(size + 1);
		elementData[rear] = item;
		rear++;
		if (rear == elementData.length && front != 0) {
			rear = 0;
		}
		size++;
	}

	@Override
	/**
     * Remove the item from the front of the queue
     * @return the first element is the queue
     * @throws NoSuchElementException if the queue is empty
     */
	public E remove() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Empty Queue");
		}
		front++;
		if (front == elementData.length) {
			front = 0;
		}
		size--;
		
		if (front == 0) {
			return elementData[elementData.length - 1];
		}
		return elementData[front - 1];
	}

	@Override
	/**
     * Return the first item from the front of the queue without removing it
     * @return the first item from the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
	public E peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Empty Queue");
		}
		return elementData[front];
	}

	@Override
	/**
     * Find how many items are in the queue
     * @return the number of items in the queue
     */
	public int size() {
		return size;
	}

	@Override
    /**
     * Determine if the queue is empty
     * @return true if the size is 0, false otherwise
     */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	/**
     * Clear out the data structure
     */
	public void clear() {
    	elementData = (E[]) new Object[DEFAULT_CAPACITY];
    	size = 0;
    	front = 0;
    	rear = 0;
	}

}
