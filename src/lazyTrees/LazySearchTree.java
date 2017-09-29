
package lazyTrees;

import java.util.*;


/**
 * The Class LazySearchTree stores Items in a BST, and performs lazy deletions of Items.
 * It prints both lazy deleted and non lazy deleted. The find max and min methods considering
 * only the lazy deleted Items.
 * @param <T>  generic type
 */
public class LazySearchTree<T extends Comparable<? super Item>> implements
		Cloneable {

	/** The size of the nodes treated for lazy deletion */
	protected int softSize;
	
	/** The real size. */
	protected int mSize;
	
	/** The root of the tree. */
	protected LazySTNode mRoot;

	/**
	 * Instantiates a new lazy search tree.
	 */
	public LazySearchTree() {
		clear();
	}

	/**
	 * checks for Empty tree
	 *
	 * @return true, if tree is empty
	 */
	public boolean empty() {
		return (mSize == 0);
	}

	/**
	 * Size of The size of the nodes treated for lazy deletion
	 *
	 * @return The size of the nodes treated for lazy deletion
	 */
	public int size() {
		return softSize;
	}

	/**
	 * The real size of the tree
	 *
	 * @return real size of the tree
	 */
	public String sizeHard() {
		return "" + mSize;
	}

	/**
	 * Clears the tree.
	 */
	public void clear() {
		mSize = 0;
		mRoot = null;
	}

	/**
	 * Shows height.
	 *
	 * @return the height of the tree
	 */
	public int showHeight() {
		return findHeight(mRoot, -1);
	}

	/**
	 * Finds min value of the tree.
	 *
	 * @return the min value
	 */
	public Item findMin() {
		if (mRoot == null) {
			throw new NoSuchElementException();
		}
		if (findMin(mRoot) == null) {
			throw new NoSuchElementException();
		} else {
			return findMin(mRoot).data;
		}
	}

	/**
	 * Finds max value of the tree.
	 *
	 * @return the max value
	 */
	public Item findMax() {
		if (mRoot == null) {
			throw new NoSuchElementException();
		}
		if (findMax(mRoot) == null) {
			throw new NoSuchElementException();
		} else {
			return findMax(mRoot).data;
		}
	}

	/**
	 * Find Items in the tree
	 *
	 * @param x the Item
	 * @return the item
	 */
	public Item find(Item x) {

		find(mRoot, x);

		if (find(mRoot, x) == null) {
			throw new NoSuchElementException();
		} else {

			return find(mRoot, x).data;
		}
	}

	/**
	 * checks for Containing an Item 
	 *
	 * @param x the Item
	 * @return true, if contains
	 */
	public boolean contains(Item x) {
		return find(mRoot, x) != null && !find(mRoot, x).deleted;
	}

	/**
	 * Inserts in the tree.
	 *
	 * @param x the Item
	 * @return true, if inserted
	 */
	public boolean insert(Item x) {
		softSize++;
		int oldSize = mSize;
		mRoot = insert(mRoot, x);
		return (mSize != oldSize);
	}

	/**
	 * Removes the.
	 *
	 * @param x the Item
	 * @return true, if removed
	 */
	public boolean remove(Item x) {
		int oldSize = mSize;

		LazySTNode node = find(mRoot, x);
		if (node != null) {

			node.deleted = true;
			softSize--;
			return true;

		} else {
			remove(mRoot, x);
		}
		return mSize != oldSize;
	}

	/**
	 * Traverses the tree.
	 *
	 * @param <F> the generic type
	 * @param func the func
	 */
	public <F extends Traverser<? super Item>> void traverse(F func) {
		traverse(func, mRoot);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		LazySearchTree newObject = (LazySearchTree) super.clone();
		newObject.clear(); // can't point to other's data

		newObject.mRoot = cloneSubtree(mRoot);
		newObject.mSize = mSize;

		return newObject;
	}

	/**
	 * The Class LazySTNode.
	 */
	private class LazySTNode {
		
		/** The right child. */
		// use public access so the tree or other classes can access members
		public LazySTNode lftChild, rtChild;
		
		/** The data. */
		public Item data;
		
		/** The root. */
		public LazySTNode myRoot; // needed to test for certain error
		
		/** Variable for deleted nodes. */
		public boolean deleted;

		/**
		 * Instantiates a new lazy st node.
		 *
		 * @param d the data
		 * @param lft the leftChild
		 * @param rt the rightChild
		 * @param dlt the deleted node
		 */
		public LazySTNode(Item d, LazySTNode lft, LazySTNode rt, boolean dlt) {
			lftChild = lft;
			rtChild = rt;
			data = d;
			deleted = dlt;
		}

		/**
		 * Instantiates a new lazy st node.
		 */
		public LazySTNode() {
			this(null, null, null, false);
		}
	}//////////////////////End of inner class LazySTNode

	/**
	 * Instantiates a new lazy search tree.
	 *
	 * @param c the c
	 */
	public LazySearchTree(Comparator<? super Item> c) {
		mRoot = null;
		mSize = 0;

	}
	
	/**
	 * Traverses all the nodes
	 *
	 * @param printObject the print object
	 */
	public void traverseHard(PrintObject<Item> printObject) {
		if (mSize == 0) {
			System.err.println("Empty  here in hard..");
		}
		printHard(mRoot, printObject);
	}

	/**
	 * Prints the all the nodes
	 *
	 * @param root the root
	 * @param printObject the print object
	 */
	private void printHard(LazySTNode root, PrintObject<Item> printObject) {
		if (root != null) {

			printHard(root.lftChild, printObject);

			printObject.visit(root.data);

			printHard(root.rtChild, printObject);

		}
	}

	/**
	 * Traverses soft.
	 *
	 * @param printObject the print object
	 */
	public void traverseSoft(PrintObject<Item> printObject) {
		if (softSize == 0) {
			System.err.println("Empty here in soft...");
		}
		printSoft(mRoot, printObject);

	}

	/**
	 * Prints the nodes treated for lazy deletion.
	 *
	 * @param root the root
	 * @param printObject the print object
	 */
	private void printSoft(LazySTNode root, PrintObject<Item> printObject) {
		if (root != null) {

			printSoft(root.lftChild, printObject);
			if (!root.deleted) {
				printObject.visit(root.data);
			}
			printSoft(root.rtChild, printObject);

		}
	}

	

	/**
	 * Find min.
	 *
	 * @param root the root
	 * @return the lazy st node
	 */
	protected LazySTNode findMin(LazySTNode root) {

		if (root == null) {
			return root;
		}

		LazySTNode current = findMin(root.lftChild);

		if (current != null) {
			return current;
		}

		if (!root.deleted) {
			return root;
		}

		return findMin(root.rtChild);

	}

	/**
	 * Find max.
	 *
	 * @param root the root
	 * @return the node
	 */
	protected LazySTNode findMax(LazySTNode root) {
		if (root == null) {
			return root;
		}

		LazySTNode current = findMax(root.rtChild);

		if (current != null) {
			return current;
		}

		if (!root.deleted) {
			return root;
		}

		return findMax(root.lftChild);
	}

	/**
	 * Insert.
	 *
	 * @param root the root
	 * @param x the x
	 * @return the lazy st node
	 */
	protected LazySTNode insert(LazySTNode root, Item x) {

		int compareResult; // avoid multiple calls to compareTo()

		if (root == null) {
			mSize++;
			return new LazySTNode(x, null, null, false);
		}

		compareResult = x.compareTo(root.data);

		if (compareResult < 0) {
			if (root.lftChild != null) {
				if (isReinserted(root.lftChild, x)) {
					root.lftChild.deleted = false;
				}
			}

			root.lftChild = insert(root.lftChild, x);

		} else if (compareResult > 0) {
			if (root.rtChild != null) {
				if (isReinserted(root.rtChild, x)) {
					root.rtChild.deleted = false;
				}
			}

			root.rtChild = insert(root.rtChild, x);

		} else if (root != null) {
			if (isReinserted(root, x)) {
				root.deleted = false;
			}
		}
		return root;
	}

	/**
	 * Checks if a node is reinserted.
	 *
	 * @param node the node
	 * @param itm the item
	 * @return true, if is reinserted
	 */
	private boolean isReinserted(LazySTNode node, Item itm) {
		return node.data
				.toString()
				.substring(0, node.data.toString().indexOf(":"))
				.equals(itm.toString()
						.substring(0, itm.toString().indexOf(":")));
	}

	/**
	 * Removes the node
	 *
	 * @param root the root
	 * @param x the item
	 */
	protected void remove(LazySTNode root, Item x) {

		int compareResult; // avoid multiple calls to compareTo()

		if (root == null) {
			return;
		}

		compareResult = x.compareTo(root.data);
		if (compareResult < 0) {
			remove(root.lftChild, x);

		} else if (compareResult > 0) {
			remove(root.rtChild, x);

		} else if (root.lftChild != null && root.rtChild != null) {
			root.data = findMin(root.rtChild).data;
			remove(root.rtChild, root.data);

		} else {
			root = (root.lftChild != null) ? root.lftChild : root.rtChild;

		}
	}

	/**
	 * Traverses.
	 *
	 * @param <F> the generic type
	 * @param func the func
	 * @param treeNode the tree node
	 */
	protected <F extends Traverser<? super Item>> void traverse(F func,
			LazySTNode treeNode) {
		if (treeNode == null)
			return;

		traverse(func, treeNode.lftChild);
		func.visit(treeNode.data);
		traverse(func, treeNode.rtChild);
	}

	/**
	 * Find.
	 *
	 * @param root the root
	 * @param x the item
	 * @return the node
	 */
	protected LazySTNode find(LazySTNode root, Item x) {
		int compareResult;

		if (root == null) {
			return null;
		}

		compareResult = x.compareTo(root.data);
		if (compareResult < 0) {
			return find(root.lftChild, x);
		}
		if (compareResult > 0) {
			return find(root.rtChild, x);
		}

		return root;

	}

	/**
	 * Clone subtree.
	 *
	 * @param root the root
	 * @return the node
	 */
	protected LazySTNode cloneSubtree(LazySTNode root) {
		LazySTNode newNode;
		if (root == null)
			return null;

		// does not set myRoot which must be done by caller
		newNode = new LazySTNode(root.data, cloneSubtree(root.lftChild),
				cloneSubtree(root.rtChild), false);
		return newNode;
	}

	/**
	 * Finds height.
	 *
	 * @param treeNode the tree node
	 * @param height the height
	 * @return the int height
	 */
	protected int findHeight(LazySTNode treeNode, int height) {
		int leftHeight, rightHeight;
		if (treeNode == null)
			return height;
		height++;
		leftHeight = findHeight(treeNode.lftChild, height);
		rightHeight = findHeight(treeNode.rtChild, height);
		return (leftHeight > rightHeight) ? leftHeight : rightHeight;
	}

	

}