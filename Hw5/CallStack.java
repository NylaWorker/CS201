

/*The following code was created by Nyla Worker.
It will implement StackADT and will use linked implementation

First I create a node class within the class in order to  do the implementation and then I use the StackADT comands.

Throw the empty stack error
*/

import java.util.*;

public class CallStack implements StackADT<Method> {

    private int count;
    private Node<Method> first;
    private SimulationComponent A;
    
    public CallStack(SimulationComponent simmulator) {//constructor
       A = simmulator;//add or remove to the graphical component 
    } 
    
    public void push(Method item){

		Node<Method> anode = new Node<Method>(item);
	    
		anode.setData(item);
		anode.setNext(first);
		first= anode;
		count ++;
		A.addMethodToGraphicalStack(item);

    }

    
    public Method pop(){
		if(count== 0){
	    	throw new EmptyStackException();
		}
		Method var = first.getData();//holds on to the data doesn't delete it
		first=first.getNext();//checks what this refers to// skips a node
		count--;
		A.removeMethodFromGraphicalStack(var);  
		return var; 
    }

    public Method peek(){
		return first.getData();
    }

    public boolean isEmpty(){
        if (count==0){
	    	return true;
		}
		else{
	    	return false;
		}
    }

    public int size(){
        return count;
    }

    public void clear(){
		first=null;
		count=0;
        
    }
}
