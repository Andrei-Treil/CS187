package structures;
import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E>{
    
    private LLNode<E> itr;
	
	public LinkedIterator(LLNode<E> head) {
		itr = head;
	}

	public boolean hasNext() {
		return itr != null;
	}

	public E next() {
		if (!hasNext()){
            return itr.getData();
        }
		E data = itr.getData();
		itr = itr.getNext();
		return data;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
