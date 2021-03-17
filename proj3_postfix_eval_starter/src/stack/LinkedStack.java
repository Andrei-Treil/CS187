package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure to allow for
 * unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
  private LLNode<T> head;
  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    if (head == null) {
      throw new StackUnderflowException("The stack is empty.");
   }
   T headData = head.getData();
   head = head.getNext();
   return headData;
  }

  /** {@inheritDoc} */
      // TODO: Implement the stack operation for `top`!
  @Override
  public T top() throws StackUnderflowException {
    if(head == null){
      throw new StackUnderflowException("The stack is empty.");
    }
    T headData = head.getData(); 
    return headData;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    return(head == null);
    // TODO: Implement the stack operation for `isEmpty`!
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    // TODO: Implement the stack operation for `size`!
    LLNode<T> curr = head;
    int count = 0;
    while(curr != null){
      count++;
      curr = curr.getNext();
    }
    return count;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    LLNode<T> newHead = new LLNode<T>(elem);
    newHead.setNext(head);
    head = newHead;
    // TODO: Implement the stack operation for `push`!
  }
}
