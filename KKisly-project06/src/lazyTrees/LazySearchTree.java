package lazyTrees;

import java.util.NoSuchElementException;

/**
 * 
 * @author Intrepid
 * Class responsible for creation objects containing LazySTNode objects and Soft / Hard counters
 * Defining set of methods requred for handling operations with LazySTNode
 * @param <E>
 */

public class LazySearchTree<E extends Comparable< ? super E > >
implements Cloneable

{
	protected int mSize;
	protected int mSizeHard;
	protected LazySTNode mRoot;

	int sizeHard() {
		return mSizeHard;	 
	}

	public LazySearchTree() 
	{ 
		clear(); 
	}

	public boolean empty() 
	{ 
		return (mSize == 0); 
	}

	public int size() 
	{ 
		return mSize; 
	}

	public void clear() 
	{ 
		mSize = 0; 
		mRoot = null; 
	}

	public int showHeight() 
	{ 
		return findHeight(mRoot, -1); 
	}

	public E findMin() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMin(mRoot).data;
	}

	public E findMinHard() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMinHard(mRoot).data;
	}

	public E findMax() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMax(mRoot).data;
	}

	public E findMaxHard() 
	{
		if (mRoot == null)
			throw new NoSuchElementException();
		return findMaxHard(mRoot).data;
	}

	public E find( E x )
	{
		LazySTNode resultNode;
		resultNode = find(mRoot, x);
		if (resultNode == null)
			throw new NoSuchElementException();
		return resultNode.data;
	}
	public boolean contains(E x)  
	{ return find(mRoot, x) != null; }

	public boolean insert( E x )
	{
		int oldSize = mSize;
		mRoot = insert(mRoot, x);
		return (mSize != oldSize);
	}

	public boolean removeHard( E x )
	{
		int oldSize = mSizeHard;
		mRoot = removeHard(mRoot, x);
		return (mSizeHard != oldSize);
	}

	public boolean remove( E x )
	{
		int oldSize = mSize;
		mRoot = remove(mRoot, x);
		return (mSize != oldSize);
	}

	public boolean collectGarbage() {
		int oldSize = mSizeHard;
		collectGarbage(mRoot);
		return (mSizeHard != oldSize);

	}

	public < F extends Traverser<? super E > >
	void traverseSoft(F func) {
		traverseSoft(func, mRoot);	
	}

	public < F extends Traverser<? super E > > 
	void traverseHard(F func)
	{
		traverseHard(func, mRoot);
	}

	public Object clone() throws CloneNotSupportedException
	{
		LazySearchTree<E> newObject = (LazySearchTree<E>)super.clone();
		newObject.clear();  // can't point to other's data

		newObject.mRoot = cloneSubtree(mRoot);
		newObject.mSize = mSize;

		return newObject;
	}

	// private helper methods ----------------------------------------
	// ---------------------------------------------------------------
	/**
	 *  * Find the smallest item in the tree.
	 *  
	 * @return smallest item or null if empty.
	 * @param root
	 */
	private LazySTNode findMin( LazySTNode root ) 
	{
		if (root == null)
			return null;
		LazySTNode leftChild = findMin(root.lftChild);
		// if the left side does exist
		if (leftChild != null)
			return leftChild;
		if (!root.deleted)
			return root;
		// if this entire subtree does not have min, move to the right side
		return findMin(root.rtChild);
	}

	/**
	 * Same as findMin
	 * @param root
	 * @return
	 */
	private LazySTNode findMinHard( LazySTNode root)
	{
		if (root == null)
			return null;
		else if (root.lftChild == null)
			return root;
		return findMinHard(root.lftChild);
	}
	/**
	 *  * Find the biggest item in the tree.
	 * @return biggest item or null if empty.
	 * @param root
	 */
	private LazySTNode findMax( LazySTNode root ) 
	{
		if (root == null)
			return null;
		LazySTNode rightChild = findMax(root.rtChild);
		if (rightChild != null)
			return rightChild;
		if (!root.deleted)
			return root;
		// if this entire subtree does not have max, move to the left side
		return findMax(root.lftChild);
	}

	/**
	 * Same as findMax
	 * @param root
	 * @return
	 */
	private LazySTNode findMaxHard( LazySTNode root)
	{
		if (root == null)
			return null;
		else if (root.rtChild == null)
			return root;
		return findMaxHard(root.rtChild);
	}

	/**
	 * Internal method to perform an actual insertion.
	 * 
	 * @param root
	 * @param x
	 * @return
	 */
	private LazySTNode insert( LazySTNode root, E x )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
		{
			mSizeHard++;
			mSize++;
			return new LazySTNode(x, null, null, false);
		}

		compareResult = x.compareTo(root.data); 
		if ( compareResult < 0 )
			root.lftChild = insert(root.lftChild, x);
		else if ( compareResult > 0 )
			root.rtChild = insert(root.rtChild, x);
		else if (root.deleted == true)
			root.deleted = false;
		//else;
		return root;
	}

	/**
	 * Actual Hard removal from the Tree 
	 * @param root
	 * @param x the item to remove
	 * @return
	 */
	private LazySTNode removeHard( LazySTNode root, E x  )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if ( compareResult < 0 ){
			root.lftChild = removeHard(root.lftChild, x);
		} else if ( compareResult > 0 ) {
			root.rtChild = removeHard(root.rtChild, x);
		}
		// found the node
		else if (root.lftChild != null && root.rtChild != null) {
			root.data = findMin(root.rtChild).data;
			root.rtChild = removeHard(root.rtChild, root.data);
		} else {
			root = (root.lftChild != null) ? root.lftChild : root.rtChild;
			mSizeHard--;
		}

		return root;
	}

	/**
	 * Marking Node as "deleted", Soft removal
	 * @param root
	 * @param x the item to mark as deleted
	 * @return
	 */
	private LazySTNode remove(LazySTNode root, E x)
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data);

		if ( compareResult < 0 ){
			root.lftChild = remove(root.lftChild, x);
		}
		else if ( compareResult > 0 ){
			root.rtChild = remove(root.rtChild, x);
		}
		// found the node
		else
		{
			if (!root.deleted){
				root.deleted = true;
				mSize--;
			}
		}
		return root;
	}

	/**
	 * Calls hardremove methods that cleans up Tree by actually deleting previous soft removed items  
	 * @param root
	 */
	private void collectGarbage(LazySTNode root)
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null){
			return;
		}

		collectGarbage(root.lftChild);

		collectGarbage(root.rtChild);

		if (root.deleted){
			removeHard(mRoot, root.data);
		}
	}
	/**
	 * Traverse through the tree not taking in account nodes that marked as deleted
	 * 
	 * @param func
	 * @param treeNode
	 */
	private <F extends Traverser<? super E>> 
	//void traverseSoft(PrintObject<Item> printObject) {
	//that traverses the tree
	//       and displays only nodes that have not been lazily deleted. 
	void traverseSoft(F func, LazySTNode treeNode) {

		if (treeNode != null)
		{	//return;

			traverseSoft(func, treeNode.lftChild);
			if (treeNode.deleted == false)
				func.visit(treeNode.data);
			else;
			traverseSoft(func, treeNode.rtChild);
		}

	}
	/**
	 * Traverse through the tree taking in account all nodes
	 * @param func
	 * @param treeNode
	 */
	private <F extends Traverser<? super E>> 
	void traverseHard(F func, LazySTNode treeNode)
	{
		if (treeNode == null)
			return;

		traverseHard(func, treeNode.lftChild);
		func.visit(treeNode.data);
		traverseHard(func, treeNode.rtChild);
	}

	/**
	 * Find an item in the tree
	 * @param root
	 * @param x item to search
	 * @return
	 */
	protected LazySTNode find( LazySTNode root, E x )
	{
		int compareResult;  // avoid multiple calls to compareTo()

		if (root == null)
			return null;

		compareResult = x.compareTo(root.data); 
		if (compareResult < 0)
			return find(root.lftChild, x);
		if (compareResult > 0)
			return find(root.rtChild, x);
		if (root.deleted == true)
			return null;
		else
			return root;   // found
	}
	/**
	 * Clones subtree
	 * @param root
	 * @return
	 */
	protected LazySTNode cloneSubtree(LazySTNode root)
	{
		LazySTNode newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller
		newNode = new LazySTNode
				(
						root.data, 
						cloneSubtree(root.lftChild), 
						cloneSubtree(root.rtChild), false
						);
		return newNode;
	}
	/**
	 * Find tree height
	 * 
	 * @param treeNode
	 * @param height
	 * @return
	 */
	protected int findHeight( LazySTNode treeNode, int height ) 
	{
		int leftHeight, rightHeight;
		if (treeNode == null)
			return height;
		height++;
		leftHeight = findHeight(treeNode.lftChild, height);
		rightHeight = findHeight(treeNode.rtChild, height);
		return (leftHeight > rightHeight)? leftHeight : rightHeight;
	}

	/**
	 * Implementation of generic node
	 */
	private class LazySTNode
	{
		// use public access so the tree or other classes can access members 
		public boolean deleted;

		public LazySTNode lftChild, rtChild;
		public E data;
		public LazySTNode myRoot;  // needed to test for certain error

		public LazySTNode( E d, LazySTNode lft, LazySTNode rt, boolean del)
		{
			lftChild = lft; 
			rtChild = rt;
			data = d;
			deleted = del;
		}

		public LazySTNode()
		{
			this(null, null, null, false);
		}
	}

}
