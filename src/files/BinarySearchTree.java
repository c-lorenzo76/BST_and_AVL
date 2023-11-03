package files;

/**
 * Implements the Binary Search Tree data structure. 
 * 
 * @author cristianlorenzo
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{

    protected BinaryNode<AnyType> root;
    
    
    public BinarySearchTree() {
        root = null;
    }
    
    /**
     * Prints the tree in preorder traversal. 
     * 
     * @param t, root of the tree. 
     */
    protected void preOrderTraversal(BinaryNode<AnyType> t) {

        System.out.println( t.element );
        if( t.left != null )
            preOrderTraversal(t.left);
        if( t.right != null )
            preOrderTraversal(t.right);
       
    }

    /**
     * Prints the tree in postorder traversal. 
     * 
     * @param t, the root of the tree.
     */
    protected void postOrderTraversal(BinaryNode<AnyType> t) {
        
        if (t.left != null)
            postOrderTraversal(t.left);
        if (t.right != null)
            postOrderTraversal(t.right);
        System.out.println( t.element );
                    
    }
    
    /**
     * Prints the tree in inorder traversal. 
     * 
     * @param t, the root of the tree. 
     */
    protected void inOrderTraversal(BinaryNode<AnyType> t) {
        
        if(t.left != null)
            inOrderTraversal(t.left);
        System.out.println(t.element);
        if(t.right != null)
            inOrderTraversal(t.right);
        
    }

    // Integer incValue = Integer.valueOf((int) value + 1);
    
    /**
     * Increments the value of each node by 1. 
     * 
     * @param t, the root of the tree
     */
    protected void binNodeInc(BinaryNode<AnyType> t) {
        if(t == null)
            return;
        
        Object tempValue = t.element;
        Integer newValue = (int) tempValue + 1;
       
        if(t != null){
            t.setElement((AnyType) newValue);
            binNodeInc(t.left);
            binNodeInc(t.right);
        }
        
    }

    /**
     * Sets the value of each node to its depth. 
     * 
     * @param t, the root of the tree.
     * @param depth
     */
    protected void binNodeDepth(BinaryNode<AnyType> t, int depth) {
        
        if(t != null){
            Integer temp = depth;

            t.setElement((AnyType)temp);
            binNodeDepth(t.left, depth+1);
            binNodeDepth(t.right, depth+1);
        }    
            
            
    }

    /**
     * Counts the number of leaf nodes in this tree. 
     * 
     * @param t, the root of the tree. 
     * @return 
     */
    protected int binTreeLeafCounter(BinaryNode<AnyType> t) {
        if(t == null)
            return 0;
        
        if(t.right == null && t.left == null)
            return 1;
        
        
        else 
            return binTreeLeafCounter(t.left) + binTreeLeafCounter(t.right);
            
    }

    /**
     * Checks if the tree is a full tree. 
     * 
     * @param t, the root of the tree. 
     * @return
     */
    protected boolean isFull(BinaryNode<AnyType> t) {
        
        if(t == null)
            return true;
        
        if(t.left == null && t.right == null)
            return true;
        
        if((t.left != null) && (t.right != null))
            return isFull(t.left) && isFull(t.right);
        
        return false; 
        

    }


    /**
     * Prints elements in pre-order
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Prints elements in post-order.
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * prints elements in-order.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Increments by 1 each value in the tree.We assume the tree stores
     * integers.
     *
     */
    public void binNodeInc() {
        binNodeInc(root);
    }

    /**
     * Sets the value of each node to its depth.
     */
    public void binNodeDepth() {
        binNodeDepth(root, 0);
    }

    /**
     * Counts the number of leaf nodes in a tree.
     *
     * @return
     */
    public int binTreeLeafCounter() {
        return binTreeLeafCounter(root);
    }

    /**
     * Checks if the tree is full.
     *
     * @return
     */
    public boolean isFull() {
        return isFull(root);
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Internal method to perform insertion.
     *
     * @param x
     * @param t
     * @return
     */
    protected BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {

        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        // Find the insertion point.
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // Duplicate: do nothing.
        }

        return t;
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
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
    protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        // If item not found, do nothing.
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } // Two children.
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } // Zero or one child.
        else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Check if an item is in the tree.
     *
     * @param x
     * @param t
     * @return
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true; // Match with current node
        }
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     * @throws Exception
     */
    public AnyType findMin() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return findMin(root).element;
    }

    /**
     * Internal method to Find the smallest item in the tree.
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t; // found the leftmost node
        } else {
            return findMin(t.left);
        }
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     * @throws Exception
     */
    public AnyType findMax() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return findMax(root).element;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Calculate the height of the tree.
     *
     * @return the height.
     */
    public int height() {

        return height(this.root);

    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     * @return
     */
    protected int height(BinaryNode<AnyType> t) {
        return t == null ? -1
                : 1 + Math.max(height(t.left), height(t.right));
    }

    public BinaryNode<AnyType> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<AnyType> root) {
        this.root = root;
    }

	
	
	
	
	
	
	
	
	
	
}
