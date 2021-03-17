package structures;

public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    if (t == null) throw new NullPointerException();
		upperBound++;
		BSTNode<T> newNode = new BSTNode<T>(t, null, null);
		root = addToSubtree(root, newNode);
		if (height() > Math.log(upperBound) / Math.log((double)3/2)) {
			BSTNode<T> child = newNode;
			BSTNode<T> par = newNode.parent;
			while ((double)subtreeSize(child)/ subtreeSize(par) <= (double)2/3) {
				par = par.parent;
				child = child.parent;
			}
			ScapegoatTree<T> subtree = new ScapegoatTree<T>();
			subtree.root = par;
			BSTNode<T> orgParent = par.parent;
			subtree.balance();
			if (orgParent.getLeft() == par) orgParent.setLeft(subtree.root);
			else orgParent.setRight(subtree.root);
		}
  }

  @Override
  public boolean remove(T element) {
    // TODO: Implement the remove() method
    if (element == null) {
			throw new NullPointerException();
		}
		boolean result = contains(element);
		if (result) {
			root = removeFromSubtree(root, element);
		}
		if (upperBound > 2*size()) {
			balance();
			upperBound = size();
		}
		return result;
  }
}
