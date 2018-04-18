import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>  {
	  private int N;             // size of the stack
	    private Node first;
	    private Node last;
	    
	    // helper linked list class
	    private class Node {
	        private Item item;
	        private Node prev;
	        private Node next;
	    }
	    
	    // construct an empty deque
	    public Deque()  {                        
	         N = 0;
	         first = null;
	         last = null;
	    }
	    
	    // is the deque empty?
	    public boolean isEmpty()  {               
	         return N == 0;
	    }
	    
	    // return the number of items on the deque
	    public int size()  {                      
	         return N;
	    }
	    
	    //Throw a java.lang.NullPointerException if the client attempts to add a null item
	    public void addFirst(Item item)  {        // add the item to the front
	        if(item == null) {
	            throw new NullPointerException("add a null item");
	        }
	        Node oldfirst = first;
	        first = new Node();
	        first.item = item;
	        first.next = oldfirst;
	        first.prev = null;
	        if(isEmpty()) {
	           last = first;
	        } else {
	           oldfirst.prev = first;
	        }
	        N++;
	    }
	    
	    //Throw a java.lang.NullPointerException if the client attempts to add a null item
	    public void addLast(Item item)   {        // add the item to the end
	        if(item == null) {
	            throw new NullPointerException("add a null item");
	        }
	        Node oldlast = last;
	        last = new Node();
	        last.item = item;
	        last.next = null;
	        last.prev = oldlast;
	        if(isEmpty()) {
	            first = last;
	        }else {
	            oldlast.next = last;
	        }
	        N++;
	    }
	    
	    //remove and return the item from the front
	    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
	    public Item removeFirst()   {            
	        if (isEmpty()) {
	            throw new NoSuchElementException("Deque underflow"); 
	        }
	        Item item = first.item; // save item to return
	        Node oldfirst = first;
	        first = first.next;  // delete first node
	        oldfirst = null;
	        if(first == null) { 
	            last = null;
	        } else {
	           first.prev = null;
	        }
	        N--;
	        return item;
	    }
	    
	    // remove and return the item from the end
	    // throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
	    public Item removeLast()   {              
	        if (isEmpty()) {
	            throw new NoSuchElementException("Deque underflow"); 
	        }
	        Item item = last.item; //save item to return
	        Node oldlast = last;
	        last = last.prev;
	        oldlast.prev = null;
	        
	        if(last != null) {
	            last.next = null;
	        }else {
	            first = null;
	        }
	        oldlast = null;
	        N--;
	        return item;       
	    }
	    
	    // return an iterator over items in order from front to end
	    public Iterator<Item> iterator()   {      
	        return new ListIterator();
	    }
	    
	    private class ListIterator implements Iterator<Item> {
	        private Node current = first;
	        
	        public boolean hasNext()  {
	            return current != null; 
	        }
	        public void remove()  { 
	            throw new UnsupportedOperationException(); 
	        }
	        
	        public Item next() {
	            if (!hasNext()) { 
	                throw new NoSuchElementException(); 
	            }
	            Item item = current.item;
	            current = current.next;
	            return item;    
	        } 
	    }
}
	  
	    
