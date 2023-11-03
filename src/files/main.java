package files;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates BST and AVL Trees and compares their performances. 
 * 
 * @author cristianlorenzo
 *
 */
public class main {

	/**
	 * 
	 * @param args, the cmd line args
	 */
	public static void main(String[] args) {
		//demoTreePrinter();
		testBST();
		testAVL();
		benchmark();
		
	}

	/**
	 *  Demos TreePrinter. 
	 */
	private static void demoTreePrinter() {
		BinarySearchTree<Integer> binST = new BinarySearchTree<>();
		 binST.insert(32);
	        binST.insert(15);
	        binST.insert(5);
	        binST.insert(8);
	        binST.insert(40);
	        binST.insert(68);
	        binST.insert(18);
	        binST.insert(25);
	        binST.insert(2);
	        binST.insert(98);
	        binST.insert(55);
	        binST.insert(60);
	        
	        TreePrinter printer = new TreePrinter(binST);
	        printer.print("Binary Search Tree");
		
	}

	/**
	 *  Creates a BST and prints it out. 
	 */
	private static void testBST() {
		List<Integer> intList = new ArrayList<>();
		
		//Generate 60 random integers and insert them into a BST
		intList.addAll(ThreadLocalRandom.current().ints(10,99).distinct().limit(60).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		
		BinarySearchTree<Integer> binST = new BinarySearchTree<>();
		
		int height = 0;
		int i =0;
		
		while(height <= 4 && i < intList.size()) {
			binST.insert(intList.get(i));
			i++;
			height = binST.height();
		}
		
		TreePrinter printer = new TreePrinter(binST);
		
		printer.print("Binary Search Tree");
		System.out.println("Pre-Order:");
		binST.preOrderTraversal();
		System.out.println("\nPost-Order:");
		binST.postOrderTraversal();
		System.out.println("\nIn-Order:");
		binST.inOrderTraversal();
		
		System.out.println("\nIs the BST full?: " + binST.isFull());
		System.out.println("Number of leaf nodes in BST: " + binST.binTreeLeafCounter());
		
		binST.binNodeInc();
		printer.print("Node Values Incremented by 1");
		
		binST.binNodeDepth();
		printer.print("Node Values Set To Depth Value");
		
		
	}
	
	/**
	 * Creates a AVL Tree and prints it out after removing the root. 
	 */
	private static void testAVL() {
		List<Integer> intList = new ArrayList<>();
		
		//Generate 20 random integers and insert them into an AVL 
		intList.addAll(ThreadLocalRandom.current().ints(10, 99).distinct().limit(20).collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		
		AVLTree<Integer> avltree = new AVLTree();
		
		int height = 0;
		int i = 0;
		
		while (height <=4 && i < intList.size()) {
			avltree.insert(intList.get(i));
			avltree.checkBalance();
			i++;
			height = avltree.height();
	
		}
		
		TreePrinter avlPrinter = new TreePrinter(avltree);
		avlPrinter.print("AVL Tree");
		
		while(!avltree.isEmpty()) {
			avltree.remove(avltree.getRoot().element);
			avlPrinter.print("Remove root");
		}
		
	}
	
	/**
	 * Compares the performances of BST vs AVL. 
	 * 
	 */
	private static void benchmark() {
		List<List<Integer>> listofInsertLists = new ArrayList<>();
		List<List<Integer>> listofSearchLists = new ArrayList<>();
		
		for(int n = 1000; n <= 1000000; n = n*10) {
			List<Integer> insertList = new ArrayList<>();
	        insertList.addAll(ThreadLocalRandom.current().ints(1, n * 5).distinct().limit(n).collect(ArrayList::new, ArrayList::add,ArrayList::addAll));
	        listofInsertLists.add(insertList);

	        List<Integer> searchList = new ArrayList<>();
	        searchList.addAll(ThreadLocalRandom.current().ints(1, n * 5).limit(n).collect(ArrayList::new, ArrayList::add,ArrayList::addAll));
	        listofSearchLists.add(searchList);
		}
		
		System.out.println("BST");
		for(int i = 0; i < listofInsertLists.size(); i++) {
			bstTime(listofInsertLists.get(i), listofSearchLists.get(i));
		}
		
		System.out.println("----------------------------------------------------------");
		System.out.println("AVL");
		for(int i = 0; i < listofInsertLists.size(); i++) {
			avlTime(listofInsertLists.get(i), listofSearchLists.get(i));
		}

	}
	
	/**
	 * Inserts a list of integers into a BST and measures how long it takes. 
	 * Also searches the same BST for a different list of integers. 
	 * 
	 * @param insertList
	 * @param searchList
	 */
	private static void bstTime(List<Integer> insertList, List<Integer> searchList) {
		
		int n = insertList.size();
	    System.out.println("n=" + n);
	    
	    //insert
	    BinarySearchTree<Integer> binST = new BinarySearchTree<>();
	    Instant startInsert = Instant.now();
	    for (int i = 0; i < insertList.size(); i++) {
	    	binST.insert(insertList.get(i));
	    }
	    Instant finishInsert = Instant.now();
	    long timeElapsedInsert = Duration.between(startInsert, finishInsert).toNanos();
	    System.out.println(" BST Insert :" + timeElapsedInsert + " nanoseconds.");

	    //search
	    Instant startSearch = Instant.now();
	    for (int i = 0; i < n; i++) {
	    	binST.contains(searchList.get(i));
	    }
	    Instant finishSearch = Instant.now();
	    long timeElapsedSearch = Duration.between(startSearch, finishSearch).toNanos();
	    System.out.println(" BST Search :" + timeElapsedSearch + " nanoseconds.");
	    
	}
	
	/**
	 * Inserts a list of integers into an AVL and measures how long it takes. 
	 * Also searches the same AVL for a different list of integers. 
	 * 
	 * @param insertList
	 * @param searchList
	 */
	private static void avlTime(List<Integer> insertList, List<Integer> searchList) {
		int n = insertList.size();
	    System.out.println("n=" + n);
	    //insert
	    AVLTree<Integer> avltree = new AVLTree<>();
	    Instant startInsert = Instant.now();
	    for (int i = 0; i < insertList.size(); i++) {
	         avltree.insert(insertList.get(i));
	    }
	    Instant finishInsert = Instant.now();
	    long timeElapsedInsert = Duration.between(startInsert, finishInsert).toNanos();
	    System.out.println(" AVL Insert :" + timeElapsedInsert + " nanoseconds.");

	    //search
	    Instant startSearch = Instant.now();
	    for (int i = 0; i < n; i++) {
            avltree.contains(searchList.get(i));    
	    }
	    Instant finishSearch = Instant.now();
	    long timeElapsedSearch = Duration.between(startSearch, finishSearch).toNanos();
        System.out.println(" AVL Search :" + timeElapsedSearch + " nanoseconds.");

	    }
}







