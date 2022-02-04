package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private QueueNode<E> temp;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if(size == 0) {
			last = n;
			last.next = n;
			size++;
			return true;
		} else if (size == 1) { 
			temp = last;
			last.next = n;
			n.next = last;
			last = n;
			size++;
			return true;
		} else if (size > 1){
			temp = last;
			n.next = last.next;
			last.next = n;
			last = n;
			size++;
			return true;
		} 
		return false;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size == 0) {
			return null;
		} else if (size == 1){
			return last.element;
		} else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size == 0) {
			return null;
		} else if (size == 1) {
			temp = last;
			last = null;
			size--;
			return temp.element;
		} else {
			temp = last.next;
			last.next = last.next.next;
			size--;
			return temp.element;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	public void append(FifoQueue<E> q) {
		if(q.last == null) {
			//do nothing
		} else if(this.last == null) {
			this.last = q.last;
			size = q.size + size;
		} else if(this.last == q.last){
			throw new IllegalArgumentException();
		} else {
			temp = last.next;
			last.next = q.last.next;
			last = q.last;
			last.next = temp;
			size = q.size + size;
		}
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		/* Konstruktor */
		private QueueIterator() {
			if (last != null) {
				pos = last.next;
			} else {
				pos = null;
			}
		}	
		
		public boolean hasNext() {
			if (pos != null) {
				return true;
			} 
				return false;
		}
		
		public E next() {
			if (hasNext()) {
				QueueNode<E> temp = pos;
				if (pos == last) {
					pos = null;
				
				} else {
					pos = pos.next;
				}
				return temp.element;
			} else {
				throw new NoSuchElementException();
			}
		}
		
	}

	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}

