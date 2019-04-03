/*
   Created by Jacob Maniscalco, Shaun Guyette, and Ben Thompson
*/
public class BinarySearchTree extends BinaryTreeBasis implements SearchTreeInterface
{ 
	private int size = 0;
	
   public BinarySearchTree()
   {
      super();
   }

   public KeyedItem retrieve(Comparable searchKey) 
   {
      return retrieveItem(getRootNode(), searchKey);
   }  

   public void insert(KeyedItem item) throws TreeException
   {
      setRootNode(insertItem(getRootNode(), item));
	  size++;
   }  

   public void delete(Comparable searchKey) throws TreeException 
   {
      setRootNode(deleteItem(getRootNode(), searchKey));
	  size--;
   }  

   protected KeyedItem retrieveItem(TreeNode tNode, Comparable searchKey)
   {
      //disallow duplicates so that you always know which object to retrieve (or delete)
      //you could return a list with all duplicate search keys (but delete is still a problem)
      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveItem(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveItem(tNode.getRight(), searchKey);
         }  
      }  
      return treeItem;
   }  

   protected TreeNode insertItem(TreeNode tNode, KeyedItem item) throws TreeException
   {

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         throw new TreeException ("Cannot add duplicate.");
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = insertItem(tNode.getLeft(), item);
         tNode.setLeft(subtree);
		 assert validateBSTProperty() : "Error inserting " + item.toString() + ". BST Property invalidated";
		 assert validateSize() : "Error inserting " + item.toString() + ". Size error";
		 assert isBalanced() : "Error inserting " + item.toString() + ". Tree not balanced";
      }
      else 
      { 
         // search the right subtree
         subtree = insertItem(tNode.getRight(), item);
         tNode.setRight(subtree);
		 assert validateBSTProperty() == true : "Error inserting " + item.toString() + ". BST Property invalidated";
		 assert validateSize() : "Error inserting " + item.toString() + ". Size error";
		 assert isBalanced() : "Error inserting " + item.toString() + ". Tree not balanced";
      }  

      return tNode;
   }  

   protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) throws TreeException
   {

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = deleteItem(tNode.getLeft(), searchKey);
         tNode.setLeft(subtree);
		 assert validateBSTProperty() == true : "Error deleting " + nodeItem.toString() + ". BST Property invalidated";
		 assert validateSize() : "Error deleting " + nodeItem.toString() + ". Size error";
		 assert isBalanced() : "Error deleting " + nodeItem.toString() + ". Tree not balanced";
      }
      else 
      { 
         // search the right subtree
         subtree = deleteItem(tNode.getRight(), searchKey);
         tNode.setRight(subtree);
		 assert validateBSTProperty() == true : "Error deleting " + nodeItem.toString() + ". BST Property invalidated";
		 assert validateSize() : "Error deleting " + nodeItem.toString() + ". Size error";
		 assert isBalanced() : "Error deleting " + nodeItem.toString() + ". Tree not balanced";
      } 

      return tNode;
   }  

   protected TreeNode deleteNode(TreeNode tNode) 
   {
      // Algorithm note: There are four cases to consider:
      //   1. The tNode is a leaf.
      //   2. The tNode has no left child.
      //   3. The tNode has no right child.
      //   4. The tNode has two children.
      // Calls: findLeftmost and deleteLeftmost

      // test for a leaf --  this test is taken care of by the next two
      if ((tNode.getLeft() == null) && (tNode.getRight() == null)) 
      {
         return null;
      }  

      // test for no left child
      else if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      } 

      // test for no right child
      else if (tNode.getRight() == null) 
      {
         return tNode.getLeft();
      } 

      // there are two children:
      // retrieve and delete the inorder successor
      else 
      {
         KeyedItem replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         TreeNode subtree = deleteLeftmost(tNode.getRight());
         tNode.setRight(subtree);
         return tNode;
      } 
   }  

   protected KeyedItem findLeftmost(TreeNode tNode)  
   {
      if (tNode.getLeft() == null) 
      {
         return (KeyedItem)tNode.getItem();
      }
      else 
      {
         return findLeftmost(tNode.getLeft());
      }  
   } 

   protected TreeNode deleteLeftmost(TreeNode tNode) 
   {
      if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      }
      else 
      {
         TreeNode subtree = deleteLeftmost(tNode.getLeft());
         tNode.setLeft(subtree);
         return tNode;
      }  
   } 

   protected TreeNode rotateLeft(TreeNode tNode)
   {
      TreeNode right = tNode.getRight();
      TreeNode rightleft = right.getLeft();

      tNode.setRight(rightleft);
      right.setLeft(tNode);

      return right;
   }

   protected TreeNode rotateRight(TreeNode tNode)
   {
      TreeNode left = tNode.getLeft();
      TreeNode leftright = left.getRight();

      tNode.setLeft(leftright);
      left.setRight(tNode);

      return left;
   }
   
   public int height() {
	   return getHeight(getRootNode());
   }
   
   protected int getHeight(TreeNode tNode) {
	   if (tNode == null) {
		   return 0;
	   }
	   
	   int height;
	   int leftHeight = getHeight(tNode.getLeft());
	   int rightHeight = getHeight(tNode.getRight());
	   if (leftHeight >= rightHeight) {
		   height = leftHeight + 1;
	   }
	   else {
		   height = rightHeight + 1;
	   }
	   
	   return height;
   }

    /**
    *    Checks whether the tree maintains the proper BST order
    *     Precondition: none
    *     Postcondition: returns false if inorder traversal is out of order, returns true if the BST maintains proper inorder throughout
    *     Throws: none
    */
   public boolean validateBSTProperty() {
       BinaryTreeIterator check = new BinaryTreeIterator(getRootNode());
	   check.setInorder();
       Object first = null;
	   Object second = null;
	   
	   if (check.hasNext()) {
		   first = check.next();
           first = (CD)first;
	   }
	   while (check.hasNext()) {
		   second = check.next();
           second = (CD)second;
		   int comp = first.toString().compareTo(second.toString());
		   if (comp > 0) {
			   return false;
		   }
		   first = second;
	   }
	   return true;
   }

    /**
    *   Checks whether the size instance variable is consistent with a function that manually checks the size
    *   Precondition: none
    *   Postcondition: returns true if the size is consistent, otherwise returns false
    */
   public boolean validateSize() {
	   if (size == size()) {
		   return true;
	   }
	   else {
		   return false;
	   }
   }

   /**
    * Purpose: Returns the size of the Binary Search Tree using BinaryTreeIterator
    * Precondition: N/A
    * Postcondition: Returns an integer containing the Binary Search Tree size
    * Throws: N/A
    * Calls: BinaryTreeIterator / methods

   */
	public int size() {
		if (getRootNode() == null) {
			return 0;
		}
		BinaryTreeIterator iter = new BinaryTreeIterator(getRootNode());
		iter.setInorder();
		Object item;
		int i = 0;
		while (iter.hasNext()) {
			item = iter.next();
			i++;
		}
		return i;
	}


	/**
     *   Purpose: Tests Binary Search Tree to determine if the tree is balanced
	 *   Precondition: N/A
	 *   Postcondition: Returns a boolean - true if the tree is balanced / false if not
	 *   Throws: N/A
	 *   Calls: checkBalance()
	*/
    public boolean isBalanced()
    {
        return checkBalance(getRootNode());
    }

    /**
    *   Purpose: Checks if the Binary Search Tree is balanced
    *    Precondition: TreeNode tNode to determine balance at a given node
    *    Postcondition: Returns a boolean - true if the tree is balanced / false if not
    *    Throws: N/A
    *    Calls: N/A
    */
    protected boolean checkBalance(TreeNode tNode)
    {
        if (tNode == null)
        {
            return true;
        }

        boolean leftBal = checkBalance(tNode.getLeft());
        boolean rightBal = checkBalance(tNode.getRight());

        int leftHeight = getHeight(tNode.getLeft());
        int rightHeight = getHeight(tNode.getRight());
        int diff = leftHeight - rightHeight;

        if (diff < -1 || diff > 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

   
} 