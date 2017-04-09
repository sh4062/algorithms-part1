import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] rq;
	private int n = 0;

    public RandomizedQueue() {
    	rq = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
    	return n == 0;
    }


    public int size() {
    	return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (n == rq.length) resize(2*rq.length);

        rq[n++] = item;
    }

    /**
     * delete and return a random item
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = (int) (Math.random()*n);

        Item item = rq[index];

        if (index != n-1) rq[index] = rq[n-1];

        rq[n-1] = null;

        n--;

        if (n > 0 && n == rq.length/4) resize(rq.length/2);

        return item;
    }

    /**
     * return (but do not delete) a random item
     * @return
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = (int) (Math.random()*n);

        Item item = rq[index];

        return item;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

    		private int index = 0;

    		private Item[] r;

    		public RandomizedQueueIterator() {

    		r = (Item[]) new Object[n];

    		for(int i = 0; i < n; i++)

    		r[i] = rq[i];

    		StdRandom.shuffle(r);

    		}



		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < n;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
	  		if (!hasNext()) throw new java.util.NoSuchElementException();

    		Item item = r[index++];

    		return item;
		}
    	}

   
    private void resize(int Max) {

    	assert Max >= n;

    	Item[] temp = (Item[]) new Object[Max];

    	for(int i=0; i < n; i++){

    	temp[i] = rq[i];

    	}

    	rq = temp;

    	}

    /**
     * return an independent iterator over items in random order
     * @return
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
}