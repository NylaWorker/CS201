/***
 * ArrayStack.java 
 * Written by Nyla Worker and Sarah Ogle 4/15/2016
 * This is an implementation of an stack using arrays.
 * Using an interface written by Andy Exley  
 */

import java.util.*;

public class ArrayStack<T> implements StackADT<T> {
    
    private T[] data= (T[]) new Object[10];
    private int position=0;  
      
   /* Adds an item to the top of this stack.
   * @param item
   */
    public void push(T item) {
	    if (position == data.length) {
	        T[] dataT =  (T[]) new Object[data.length*2];
	        for (int i = 0; i < data.length; i++) {
		        dataT[i] = data[i];
	        }
	        data=dataT;
	    }
	    data[position] = item;
	    position++;
    }

  /* *************
   * Removes and returns the item from the top of this stack. 
   * @return the top of the stack
   */
    public T pop() {
	    if (position == 0) {
	        throw new EmptyStackException();
	    }
	    position--;
	    T item = data[position];
	    data[position] = null;
	    return item;
    }
	  
  /* *************
   * Returns the item on top of the stack, without removing it
   * @return the top of the stack
   */
    public T peek() {
	    if (position == 0) {
	        throw new EmptyStackException();
	    }
	    return data[position-1];
    }
	
  /* *************
   * Returns true is the stack is empty
   * @return whether the stack is empty
   */
    public boolean isEmpty() {
	    if (position == 0) {
	        return true;
	    }
	    else {
	        return false;
	    }
    }
	
  /* ***************
   * Clears the stack
   */
    public void clear() {
	    T[] dataT = (T[]) new Object[10];
	    data = dataT;
    }

  /* ************
   * Returns the number of items in the stack
   * @return the number of items
   */
    public int size() {
	    return position;
    }
}

    

