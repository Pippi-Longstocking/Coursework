import java.util.Iterator;
import bst.*;
import ki.KeyedItem;

public class TreeSort
{
   //convenience method
   public static KeyedItem[] treeSort(KeyedItem[] sort)
   {
      //Creates a binary search tree
      BinaryTree keyTree = new BinarySearchTree(true, true);
      
      //Creates a tree iterator to traverse the binary search tree
      TreeIterator bstIterator = new TreeIterator();

      //New 
      KeyedItem[] newArray = new KeyedItem[sort.length()];

      //Determines object type of sort array
      Class arrClass = sort.getClass();

      //Determines object type of sort array
      Class componentType = arrClass.getComponentType();

      //Creates new Array of type componentType (the same type of sort)
      //componentType[] newArray = new componentType[sort.length()];

      newArray.newInstance(componentType, sort.length());

      //Adds items from newArray to Binary Search Tree
      for (componentType item : newArray)
      {
         keyTree.insert(item);
      }

      //Sets the traversal to be in order
      bstIterator.setInorder();



   }

   public static KeyedItem[] treeSort(KeyedItem[] sort, int n)
   {
      //easier to use a KeyedItem array than Comparable
      

      Class componentType = sort.getComponentType();

      componentType[] newArray = new componentType[n];
	  
	  
	  
	  //create a new Binary Search Tree
      //a balanced tree ensures logn inserts for nlogn sort
   	  BinarySearchTree keyTree = new BinarySearchTree(true, true);
	  
	  
	  
	  
      //need to use the Class class as the actual array type may be a subtype of KeyedItem
      //Class[] newArray = new Class[];
	  
	  
 
      // fill up the search tree
      

	  
	  
      //pull sorted stuff out of the tree
      
	  
	  
	  
	  
   }
}
