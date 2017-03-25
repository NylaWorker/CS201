/******************
 * This is the Stack Abstract Data type
 * 
 */
public interface StackADT<T> {
		
  /***************
   * Adds an item to the top of this stack.
   * @param item
   */
  public void push(T item);

  /**************
   * Removes and returns the item from the top of this stack. 
   * @return the top of the stack
   */
  public T pop();
	  
  /**************
   * Returns the item on top of the stack, without removing it
   * @return the top of the stack
   */
  public T peek();
	
  /**************
   * Returns true is the stack is empty
   * @return whether the stack is empty
   */
  public boolean isEmpty();
	
  /****************
   * Clears the stack
   */
  public void clear();

  /*************
   * Returns the number of items in the stack
   * @return the number of items
   */
  public int size();

}
