package files;


/**
 * Implements a self-balancing AVL Tree. 
 * 
 * @author cristianlorenzo
 * @param <AnyType>
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>> extends BinarySearchTree<AnyType> {
	
	public AVLTree() {
		super();
	}
	
	private static final int ALLOWED_IMBALANCE = 1;
	
	/**
	 * Balancing the AVL tree. 
	 * 
	 * @param t, the root of the AVL tree 
	 * @return
	 */
	private BinaryNode<AnyType>balance(BinaryNode<AnyType> t){
		if (t == null) {
			return t;
		}
		
		if(height(t.left)- height(t.right) > ALLOWED_IMBALANCE) {
			if (height(t.left.left) > height(t.left.right)) {
                t = rotateRightWithLeftChild(t); // left outside
                // System.out.println("Single rotate right with left child: " + t.element);
            } 
			else {
                t = doubleWithLeftChild(t); // left inside
                //  System.out.println("Double rotate with left child: " + t.element);
            }
		}
		
		 if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
	            if (height(t.right.right) > height(t.right.left)) {
	                t = rotateLeftWithRightChild(t); // right outside
	                // System.out.println("Single rotate left with right child: " + t.element);
	            } 
	            else {
	                t = doubleWithRightChild(t); // right inside
	                // System.out.println("double rotate with right child: " + t.element);
	            }
		 }
		
		return t;
	}
	
	/**
	 * Checks balance.
	 */
    public void checkBalance() {
        checkBalance(root);
    }

    private int checkBalance(BinaryNode<AnyType> t) {
        if (t == null) {
            return -1;
        } else {
            int heightLeft = checkBalance(t.left);
            int heightRight = checkBalance(t.right);
            if (Math.abs(height(t.left) - height(t.right)) > 1
                    || height(t.left) != heightLeft || height(t.right) != heightRight) {
                System.out.println("OOPS!!");
            }
        }

        return t.height();
    }

    /**
     * Rotate binary tree node with left child. For AVL trees, this is a single
     * rotation for case 1.
     */
    private BinaryNode<AnyType> rotateRightWithLeftChild(BinaryNode<AnyType> k2) {
        BinaryNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single
     * rotation for case 4.
     */
    private BinaryNode<AnyType> rotateLeftWithRightChild(BinaryNode<AnyType> k1) {

        BinaryNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child. For AVL trees, this is a double
     * rotation for case 2.
     */
    private BinaryNode<AnyType> doubleWithLeftChild(BinaryNode<AnyType> k3) {

        k3.left = rotateLeftWithRightChild(k3.left);

        return rotateRightWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child. For AVL trees, this is a double
     * rotation for case 3.
     */
    private BinaryNode<AnyType> doubleWithRightChild(BinaryNode<AnyType> k1) {

        k1.right = rotateRightWithLeftChild(k1.right);

        return rotateLeftWithRightChild(k1);
    }
    

	/**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x, the item to insert.
     */
    @Override
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x, the item to insert.
     * @param t, the node that roots the subtree.
     * @return the new root of the subtree.
     */
    @Override
    protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        return balance(super.insert(x, t));
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    @Override
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    @Override
    protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {

        return balance(super.remove(x, t));
    }


	
}
