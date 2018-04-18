import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
   public static void main(String[] args) {
	   RandomizedQueue<String> queue = new RandomizedQueue<String>();
	   int k = Integer.parseInt(args[0]);
	   while (!StdIn.isEmpty()) {
		   String item = StdIn.readString();
		   queue.enqueue(item);
	   }
	    for (String s : queue) {
	    	   if (k == 0) {
	    		    break;
	    	   }
	    	      StdOut.println(s);
	    	      k--;
	    	   }
	
	   }
   }
