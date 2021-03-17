package structures;

import java.util.Iterator;
public class RecursiveList<T> implements ListInterface<T>{
    /**
   * Returns the number of elements in this {@link ListInterface}. This method runs in O(1) time.
   *
   * @return the number of elements in this {@link ListInterface}
   */
  protected int numElements;
  private LLNode<T> list;
  
  @Override
  public int size(){
      return numElements;
  }

  /**
   * Adds an element to the front of this {@link ListInterface}. This method runs in O(1) time. For
   * convenience, this method returns the {@link ListInterface} that was modified.
   *
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return The modified {@link ListInterface}
   */
  @Override
  public ListInterface<T> insertFirst(T elem){
        return insertAt(0, elem);
  }

  /**
   * Adds an element to the end of this {@link ListInterface}. This method runs in O(size) time. For
   * convenience, this method returns the {@link ListInterface} that was modified.
   *
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return the modified {@link ListInterface}
   */
  @Override
  public ListInterface<T> insertLast(T elem){
      return insertAt(numElements, elem);
  }

  /**
   * Adds an element at the specified index such that a subsequent call to {@link
   * ListInterface#get(int)} at {@code index} will return the inserted value. This method runs in
   * O(index) time. For convenience, this method returns the {@link ListInterface} that was
   * modified.
   *
   * @param index the index to add the element at
   * @param elem the element to add
   * @throws NullPointerException if {@code elem} is {@code null}
   * @throws IndexOutOfBoundsException if {@code index} is less than 0 or greater than {@link
   *     ListInterface#size()}
   * @return The modified {@link ListInterface}
   */
  @Override
  public ListInterface<T> insertAt(int index, T elem){
    if (index < 0 || index > numElements){
        throw new IndexOutOfBoundsException();
    } 
    if (elem == null){
        throw new NullPointerException();
    }
    LLNode<T> curr = new LLNode<T>(elem);
    if (index == 0) {
        curr.setNext(list);
        list = curr;
    }
    else insertHelper(list, index, curr);
    numElements++;
    return this;
  }

  //helper method for insertAt
    private void insertHelper(LLNode<T> currList, int index, LLNode<T> curr) {
        if (index == 1) {
            curr.setNext(currList.getNext());
            currList.setNext(curr);
        }
        else insertHelper(currList.getNext(), index - 1, curr);
    }

  /**
   * Removes the first element from this {@link ListInterface} and returns it. This method runs in
   * O(1) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the removed element
   */
  @Override
  public T removeFirst(){
      if(isEmpty()){
          throw new IllegalStateException();
      }
      return removeAt(0);
  }

  /**
   * Removes the last element from this {@link ListInterface} and returns it. This method runs in
   * O(size) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the removed element
   */
  @Override
    public T removeLast(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return removeAt(numElements - 1);
    }

  /**
   * Removes the ith element in this {@link ListInterface} and returns it. This method runs in O(i)
   * time.
   *
   * @param i the index of the element to remove
   * @throws IndexOutOfBoundsException if {@code i} is less than 0 or {@code i} is greater than or
   *     equal to {@link ListInterface#size()}
   * @return The removed element
   */
  @Override
    public T removeAt(int i){
        if (i < 0 || i >= numElements){
            throw new IndexOutOfBoundsException();
        } 
        T elem;
        if (i == 0) {
            elem = list.getData();
            list = list.getNext();
        }
        else elem = removeHelper(list, i);
        numElements--;
        return elem;
    }

    //helper method for removeAt
    private T removeHelper(LLNode<T> curr, int i) {
        if (i == 1) {
            T elem = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            return elem;
        }
        return removeHelper(curr.getNext(), i - 1);
    }

  /**
   * Returns the first element in this {@link ListInterface}. This method runs in O(1) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the first element in this {@link ListInterface}.
   */
  @Override
  public T getFirst(){
      if(isEmpty()){
          throw new IllegalStateException();
      }
      return get(0);
  }

  /**
   * Returns the last element in this {@link ListInterface}. This method runs in O(size) time.
   *
   * @throws IllegalStateException if the {@link ListInterface} is empty.
   * @return the last element in this {@link ListInterface}.
   */
  @Override
    public T getLast(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return get(numElements - 1);
    }

  /**
   * Returns the ith element in this {@link ListInterface}. This method runs in O(i) time.
   *
   * @param i the index to lookup
   * @throws IndexOutOfBoundsException if {@code i} is less than 0 or {@code i} is greater than or
   *     equal to {@link ListInterface#size()}
   * @return the ith element in this {@link ListInterface}.
   */
  @Override
    public T get(int i){
        if (i < 0 || i >= numElements) throw new IndexOutOfBoundsException();
        return getHelper(list, i);
    }

    //helper method for get
    private T getHelper(LLNode<T> curr, int i) {
        if (i == 0){
            return curr.getData();
        } 
        return getHelper(curr.getNext(), i - 1);
    }

  /**
   * Removes {@code elem} from this {@link ListInterface} if it exists. If multiple instances of
   * {@code elem} exist in this {@link ListInterface} the one associated with the smallest index is
   * removed. This method runs in O(size) time.
   *
   * @param elem the element to remove
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return {@code true} if this {@link ListInterface} was altered and {@code false} otherwise.
   */
  @Override
  public boolean remove(T elem){
      int index = indexOf(elem);
      if(index == -1){
          return false;
      }
      removeAt(index);
      return true;
  }

  /**
   * Returns the smallest index which contains {@code elem}. If there is no instance of {@code elem}
   * in this {@link ListInterface} then -1 is returned. This method runs in O(size) time.
   *
   * @param elem the element to search for
   * @throws NullPointerException if {@code elem} is {@code null}
   * @return the smallest index which contains {@code elem} or -1 if {@code elem} is not in this
   *     {@link ListInterface}
   */
  @Override
  public int indexOf(T elem){
      if(elem == null){
          throw new NullPointerException();
      }
      return indexHelper(elem, list, 0);
  }

  //helper method for indexOf
  private int indexHelper(T target, LLNode<T> suspect, int currIndex){
      if(suspect == null){
          return -1;
      }
      if(suspect.getData().equals(target)){
          return currIndex;
      }
      return indexHelper(target, suspect.getNext(), currIndex + 1);
  }


  /**
   * Returns {@code true} if this {@link ListInterface} contains no elements and {@code false}
   * otherwise. This method runs in O(1) time.
   *
   * @return {@code true} if this {@link ListInterface} contains no elements and {@code false}
   *     otherwise.
   */
  @Override
  public boolean isEmpty(){
      return (list == null);
  }

  @Override
  public Iterator<T> iterator(){
      return new LinkedIterator<T>(list);
  }
}