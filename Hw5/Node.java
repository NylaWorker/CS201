/* by Nyla worker
This is a class that will create the linked implementation
*/

public class Node<T>{
	private T data;
	private Node <T> next;

	public Node(){//constructor
	    next = null;
	    data = null;
	}

	public Node (T item){
	    next= null;
	    data= item;
	}
	
	public void setData(T item){
	    data= item;
	}

	public void setNext (Node<T> node){
	    next= node;
	}
	
	public T getData(){
	    return data;
	}
	public Node<T> getNext(){
	    return next;
	}
}
