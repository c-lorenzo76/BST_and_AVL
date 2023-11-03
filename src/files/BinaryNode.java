package files;


/**
 * Represents a node in a Tree.
 * 
 * @author cristianlorenzo
 * @param <AnyType>
 * 
 */
public class BinaryNode<AnyType> {
	
	AnyType element; 			// data in node
	BinaryNode<AnyType> left;	// left child
	BinaryNode<AnyType> right;  // right child
	
	/**
	 * BinaryNode constructor. 
	 * @param theElement
	 * @param lt
	 * @param rt
	 */
	BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt){
		element = theElement;
		left = lt;
		right = rt;
	}
	
	/**
	 * Creates instance of BinaryNode. 
	 * @param theElement
	 */
	BinaryNode(AnyType theElement){
		this(theElement, null, null);
	}
	
	/**
	 * Calculates height of a node. 
	 * @return height
	 */
	public int height() {
		return this.element == null ? -1 
				: 1 + Math.max(height(this.left), height(this.right));
		
	}
	
	/**
	 * Internal method for calculating the height of a node. 
	 * @param t
	 * @return the height of the node
	 */
	protected int height(BinaryNode<AnyType> t) {
		return t == null ? -1
                : 1 + Math.max(height(t.left), height(t.right));
	}
	
	
//// Getters and setters ////
	/**
	 * Getter: element.
	 * @return element
	 */
	public AnyType getElement() {
		return element;
	}
	
	/**
	 * Setter: element.
	 * @param element
	 */
	public void setElement(AnyType element) {
		this.element = element;
	}

	/**
	 * Getter: left. 
	 * @return left
	 */
	public BinaryNode<AnyType> getLeft() {
		return left;
	}

	/**
	 * Setter: left.
	 * @param left
	 */
	public void setLeft(BinaryNode<AnyType> left) {
		this.left = left;
	}
	
	/**
	 * Getter: right. 
	 * @return right
	 */
	public BinaryNode<AnyType> getRight() {
		return right;
	}

	/**
	 * Setter: right. 
	 * @param right
	 */
	public void setRight(BinaryNode<AnyType> right) {
		this.right = right;
	}

	
}
