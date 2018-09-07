/*
 * Name(s): Minh Ta
 * Course: CSC370--Lab 1
 * Date: 08/29/18
 */

/**
 * An array implementation of a stack data structure
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements StackADT<E>{
	
	// data fields
    private E[] elementData; // list of values
    private int size;        // current number of elements in the stack
    
    public static final int DEFAULT_CAPACITY = 4;
    
    public ArrayStack() {
    	this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
    	if (capacity <= 0) {
    		throw new IllegalArgumentException("Capacity must be non-negative. Capacity: " + capacity);
    	}
    	elementData = (E[]) new Object[capacity];
    	size = 0;
    }
   
    /**
     * @return a string representation of the stack.
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    
    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    private void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

	@Override
    /**
     * Add an item onto the stack
     * @param item the data item to add (of type E)
     */
	public void push(E item) {
		ensureCapacity(size + 1);
		elementData[size] = item;
		size++;	
	}


	@Override
    /**
     * Remove the top item from the stack
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
	public E pop() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Stack is Empty.");
		}
		size--;
		return elementData[size];
	}

	@Override
	/**
     * Return the top item from the stack without removing it
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
	public E peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("Stack is Empty.");
		}
		return elementData[size - 1];
	}

	@Override
	/**
     * Find how many items are in the stack
     * @return the number of items in the stack
     */
	public int size() {
		return size;
	}

	@Override
	/**
     * Determine if the stack is empty
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
	}

}
