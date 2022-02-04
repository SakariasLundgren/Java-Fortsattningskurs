package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
  
	public static void main (String[] args) {
		BSTVisualizer tree = new BSTVisualizer("Träd", 800, 800);
		BinarySearchTree<Integer> binaryTree = new BinarySearchTree((a, b) -> (((Integer) b).compareTo((Integer)a))); 
		
		binaryTree.add(3);
		binaryTree.add(4);
		binaryTree.add(5);
		binaryTree.add(2);
		binaryTree.add(7);
		binaryTree.add(7);
		binaryTree.add(9);
		binaryTree.add(1);
		
		//binaryTree.rebuild();
		tree.drawTree(binaryTree);
	}
	
  
	public BinarySearchTree() {
		root = null;
		comparator = (e1, e2) -> ((Comparable) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
		root = null;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) { //Om root är tom läggs den till
			root = new BinaryNode<>(x);
			size++;
			return true;
		} else {
			return addRec(x, root);
		}
	}
	
	private boolean addRec(E x, BinaryNode<E> y) {
		
		// om x är större än y.element
		if(comparator.compare(x, y.element) > 0) { 
			if(y.right != null) {
				return addRec(x, y.right);
			} else {
				y.right = new BinaryNode<>(x);
				size++;
				return true;
			}		
			
		// om x är mindre än y.element
		} else if (comparator.compare(x, y.element) < 0) {
			if(y.left != null) {
				return addRec(x, y.left);
			} else {
				y.left = new BinaryNode<>(x);
				size++;
				return true;
			}
			
		//Om x redan finns i trädet.
		} else { 
			return false;
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		if (size == 0) {
			return 0;
		} else {
			return Math.max(heightCheck(root.left), heightCheck(root.right));
		}
	}
	
	private int heightCheck(BinaryNode<E> x) {
		if(x == null) {
			return 1;
		}
			return 1 + Math.max(heightCheck(x.left), heightCheck(x.right));
	}
	
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		if(root != null) {
			clearRec(root);
		}
		size = 0;
	}
	
	private boolean clearRec(BinaryNode<E> rootRec) {
		if(rootRec.left == null && rootRec.right == null) {
			return true;
		}	
		clearRec(rootRec.right);
		clearRec(rootRec.left);
		rootRec.element = null;
		return true;
		
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
	}
	
	private void print(BinaryNode<E> n) {
		if (n != null) {
			System.out.println(n.element);
			print(n.left);
			print(n.right);
		}
	}


	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList sorted = new ArrayList<>();
		toArray(root, sorted);
		root = buildTree(sorted, 0, size - 1);
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		int mid = first + ((last-first)/2);
		if (first>last) {
			return null;
		} else {
			BinaryNode<E> temp = new BinaryNode<>(sorted.get(mid));
			temp.left = buildTree(sorted, first, mid-1);
			temp.right = buildTree(sorted, mid+1, last);
			return temp;
		}
	}


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}




