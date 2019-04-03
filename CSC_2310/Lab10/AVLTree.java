/*
   Created by Jacob Maniscalco, Shaun Guyette, and Ben Thompson
*/
public final class AVLTree extends BinarySearchTree {
   //keep checking balancing rules?
   private boolean avlFlag;

   public AVLTree() {
      super();
   }

   public void insert(KeyedItem item) throws TreeException {
      super.insert(item);
      //super.setRootNode(insertItem(getRootNode(), item));
      avlFlag = false;

      assert isBalanced () :"balance is not correct after insertion of key : " + item.getKey();
   }

   public void delete(Comparable sk) throws TreeException {
      super.delete(sk);
      //super.setRootNode(deleteItem(getRootNode(), searchKey));
      avlFlag = false;

      assert isBalanced () :"balance is not correct after insertion of key : " + sk;
   }


   protected TreeNode insertLeft(TreeNode tNode, KeyedItem item) {
      tNode = super.insertLeft(tNode, item);
      AVLTreeNode avlTNode = (AVLTreeNode) tNode;

      if (avlFlag) {
         avlTNode = avlFixAddLeft(avlTNode);
      }

      return avlTNode;
   }

   protected TreeNode insertRight(TreeNode tNode, KeyedItem item) {
      tNode = super.insertRight(tNode, item);
      AVLTreeNode avlTNode = (AVLTreeNode) tNode;

      if (avlFlag) {
         avlTNode = avlFixAddRight(avlTNode);
      }

      return avlTNode;
   }

  /* protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) {
      TreeNode subtree;
      if (tNode == null) {
         throw new TreeException("Item not found");
      }

      KeyedItem nodeItem = (KeyedItem) tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) {
         // item is in the root of some subtree
         //found something to delete so set avlFlag to true
         avlFlag = true;  //will delete a node from the tree, need to check balancing
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) {
         // search the left subtree
         subtree = deleteItem(tNode.getLeft(), searchKey);
         tNode.setLeft(subtree);

         //check balance factors and rotate if necessary
         if (avlFlag) {
            tNode = avlFixDelete((AVLTreeNode) tNode, 1);  //came from left
         }
      } else {
         // search the right subtree
         subtree = deleteItem(tNode.getRight(), searchKey);
         tNode.setRight(subtree);

         //check balance factors and rotate if necessary
         if (avlFlag) {
            tNode = avlFixDelete((AVLTreeNode) tNode, 2);  //came from right
         }
      }

      return tNode;
   }
*/

   protected TreeNode deleteItem(TreeNode tNode, Comparable sk) throws TreeException
   {
      if (tNode == null)
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = sk.compareTo(nodeItem.getKey());

      if (comparison == 0)
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0)
      {
         tNode = deleteLeft(tNode, sk);
      }
      else
      {
         tNode = deleteRight(tNode, sk);
      }

      return tNode;
   }
   //determine if the delete is an easy case or a hard case
   protected TreeNode deleteNode(TreeNode tNode) {
      avlFlag = true;
      tNode = super.deleteNode(tNode);
      return tNode;
   }

   //find the inorder successor
   //this method is overridden to allow checking for balancing
   protected TreeNode deleteLeftmost(TreeNode tNode) {
      tNode = super.deleteLeftmost(tNode);
      AVLTreeNode avltn = (AVLTreeNode) tNode;
      if (avlFlag) {
         avltn = avlFixDeleteLeft(avltn);
      }
      return avltn;
   }


   protected AVLTreeNode avlFixAddLeft(AVLTreeNode tNode) {
      tNode.insertLeft();

      AVL factor = tNode.getBalanceFactor();

      if (factor == AVL.BALANCED) {
         avlFlag = false;
         return tNode;
      } else if (factor == AVL.LEFT) {
         return tNode;
      }

      AVLTreeNode left = tNode.getLeft();

      if (left.getBalanceFactor() == AVL.RIGHT)
         tNode = doubleLeftRight(tNode);
      else
         tNode = singleRight(tNode);

      avlFlag = false;

      return tNode;
   }

   protected AVLTreeNode avlFixAddRight(AVLTreeNode tNode) {
      tNode.insertRight();

      AVL factor = tNode.getBalanceFactor();

      if (factor == AVL.BALANCED) {
         avlFlag = false;
         return tNode;
      } else if (factor == AVL.RIGHT) {
         return tNode;
      }

      AVLTreeNode right = tNode.getRight();

      if (right.getBalanceFactor() == AVL.LEFT) {
         tNode = doubleRightLeft(tNode);
      } else {
         tNode = singleLeft(tNode);
      }

      avlFlag = false;
      return tNode;


   }


   protected AVLTreeNode avlFixDeleteLeft(AVLTreeNode tNode) {
      AVL factor = tNode.getBalanceFactor();
      tNode.deleteLeft();
      if (factor == AVL.BALANCED)  //change from zero--  STOP
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      factor = tNode.getBalanceFactor();

      if (factor == AVL.RIGHT || factor == AVL.BALANCED) {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }

      //rotations necessary for deleting a node
      AVLTreeNode right = tNode.getRight();
      AVL bF = right.getBalanceFactor();

      if (bF == AVL.BALANCED) {
         tNode = singleLeft0(tNode);
      } else if (bF == AVL.RIGHT) {
         tNode = singleLeft(tNode);
      } else {
         tNode = doubleRightLeft(tNode);
      }

      return tNode;
   }


   protected AVLTreeNode AVLFixDeleteRight(AVLTreeNode tNode) {
      AVL factor = tNode.getBalanceFactor();
      tNode.deleteRight();
      if (factor == AVL.BALANCED) //change from zero--  STOP
      {
         avlFlag = false; //no more to do this time around
         return tNode;
      }
      factor = tNode.getBalanceFactor();


      if (factor == AVL.LEFT || factor == AVL.BALANCED) {
         return tNode;  //need to keep checking, but no rotations necessary as yet
      }

      //rotations necessary for deleting a node
      AVLTreeNode left = tNode.getLeft();
      AVL bF = left.getBalanceFactor();

      if (bF == AVL.BALANCED) {
         tNode = singleRight0(tNode);
      } else if (bF == AVL.LEFT) {
         tNode = singleRight(tNode);
      } else {
         tNode = doubleLeftRight(tNode);
      }

      return tNode;
   }


   private AVLTreeNode singleRight(AVLTreeNode tNode) {
      AVLTreeNode left = tNode.getLeft();

      tNode.setBalanceFactor(AVL.BALANCED);
      left.setBalanceFactor(AVL.BALANCED);

      tNode = (AVLTreeNode) rotateRight(tNode);
      return tNode;
   }

   private AVLTreeNode singleLeft(AVLTreeNode tNode) {
      AVLTreeNode right = tNode.getRight();

      tNode.setBalanceFactor(AVL.BALANCED);
      right.setBalanceFactor(AVL.BALANCED);

      tNode = (AVLTreeNode) rotateLeft(tNode);
      return tNode;
   }

   private AVLTreeNode singleLeft0(AVLTreeNode tNode) {
      AVLTreeNode right = tNode.getRight();

      tNode.setBalanceFactor(AVL.RIGHT);
      right.setBalanceFactor(AVL.LEFT);

      tNode = (AVLTreeNode) rotateLeft(tNode);
      avlFlag = false;

      return tNode;
   }

   private AVLTreeNode singleRight0(AVLTreeNode tNode) {
      AVLTreeNode left = tNode.getLeft();

      tNode.setBalanceFactor(AVL.LEFT);
      left.setBalanceFactor(AVL.RIGHT);

      tNode = (AVLTreeNode) rotateRight(tNode);
      avlFlag = false;

      return tNode;
   }

   private AVLTreeNode doubleLeftRight(AVLTreeNode tNode) {
      AVLTreeNode left = tNode.getLeft();
      AVLTreeNode leftRight = left.getRight();


      AVL bF = leftRight.getBalanceFactor();

      leftRight.setBalanceFactor(AVL.BALANCED);
      tNode.setBalanceFactor(AVL.BALANCED);
      left.setBalanceFactor(AVL.BALANCED);

      if (bF == AVL.RIGHT) {
         left.setBalanceFactor(AVL.LEFT);
      } else if (bF == AVL.LEFT) {
         tNode.setBalanceFactor(AVL.RIGHT);
      }


      AVLTreeNode temp = (AVLTreeNode) rotateLeft(left);
      tNode.setLeft(temp);
      tNode = (AVLTreeNode) rotateRight(tNode);

      return tNode;
   }

   private AVLTreeNode doubleRightLeft(AVLTreeNode tNode) {
      AVLTreeNode right = tNode.getRight();
      AVLTreeNode rightLeft = right.getLeft();


      AVL bF = rightLeft.getBalanceFactor();

      rightLeft.setBalanceFactor(AVL.BALANCED);
      tNode.setBalanceFactor(AVL.BALANCED);
      right.setBalanceFactor(AVL.BALANCED);

      if (bF == AVL.RIGHT) {
         tNode.setBalanceFactor(AVL.LEFT);
      } else if (bF == AVL.LEFT) {
         right.setBalanceFactor(AVL.RIGHT);
      }

      AVLTreeNode temp = (AVLTreeNode) rotateRight(right);
      tNode.setRight(temp);
      tNode = (AVLTreeNode) rotateLeft(tNode);

      return tNode;
   }

   protected TreeNode createNode(KeyedItem item)
   {
      TreeNode tNode = new AVLTreeNode(item);
      avlFlag = true;
      return tNode;
   }
}

