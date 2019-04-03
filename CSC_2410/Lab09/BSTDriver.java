/*
   Created by Jacob Maniscalco, Shaun Guyette, and Ben Thompson
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.*;

public class BSTDriver
{
   public static void main(String[] args)
   {
      Random rIndex = new Random();
      //call the height and isBalanced methods and display the results with all items inserted

      //Random randomGenerator = new Random();
      Scanner keyboard = new Scanner(System.in);

      //Gets CD file from user
      System.out.println("Enter file name containing CD information: ");
      String fileName = keyboard.next();

      //reads CD data from file into CD array
      CD[] cds = readMusic(fileName);

      BinarySearchTree bst = new BinarySearchTree();

      /*
         Testing Binary Search Tree when no nodes are present
      */
      System.out.println("-------------------------------------------------------------");
      System.out.println("\nPRINTING BINARY SEARCH TREE DATA (EMPTY BINARY SEARCH TREE) : ");
      System.out.println("TESTING isEmpty() : " + bst.isEmpty());
      System.out.println("TESTING size() : " + bst.size());

      //SHOULD CAUSE TREE EXCEPTION!!
      System.out.println("TESTING getRootItem() : " + bst.getRootItem().toString());
      System.out.println("TESTING validateBSTProperty() : " + bst.validateBSTProperty());


      System.out.println("TESTING validateSize() : " + bst.validateSize());
      System.out.println("TESTING isBalanced() : " + bst.isBalanced());
      System.out.println("\n-------------------------------------------------------------");

      /*
         Inserting all CDs into Binary Search Tree
       */
      System.out.println("\n\nAdding all CDs into the Binary Search Tree...\n\n");
      for (int i = 0; i < cds.length; i++)
      {
         bst.insert(cds[i]);
      }

      /*
         Testing SearchTreeInterface methods after all CDs have been inserted into the BST
      */
      System.out.println("-------------------------------------------------------------");
      System.out.println("\nPRINTING BINARY SEARCH TREE DATA (FULL BINARY SEARCH TREE) : ");
      System.out.println("TESTING isEmpty() : " + bst.isEmpty());
      System.out.println("TESTING size() : " + bst.size());
      System.out.println("TESTING getRootItem() : " + bst.getRootItem().toString());
      System.out.println("TESTING validateBSTProperty() : " + bst.validateBSTProperty());
      System.out.println("TESTING validateSize() : " + bst.validateSize());
      System.out.println("TESTING isBalanced() : " + bst.isBalanced());
      System.out.println("\n-------------------------------------------------------------");

      /*
         Clear Binary Search Tree/Test makeEmpty() and isEmpty() methods
      */
      System.out.println("\n\nCLEARING BINARY SEARCH TREE : ");
      bst.makeEmpty();
      System.out.println("\nTESTING isEmpty() : " + bst.isEmpty());

      //SHOULD CAUSE TREE EXCEPTION!!
      System.out.println("\n\nATTEMPTING TO ADD DUPLICATE ITEMS : ");
      bst.insert(cds[0]);
      bst.insert(cds[0]);
      bst.makeEmpty();

      System.out.println("\n\nINSERTING RANDOM CDS INTO BINARY SEARCH TREE: ");


      ArrayList<CD> cdList = new ArrayList<CD>(Arrays.asList(cds));
      /*
         Randomly inserts elements from cdList into cds
      */
      while(!cdList.isEmpty())
      {
         int i = cdList.size();
         int random = rIndex.nextInt(i);

         bst.insert(cdList.get(random));
         cdList.remove(random);
      }
      System.out.println("-------------------------------------------------------------");
      System.out.println("\nPRINTING BINARY SEARCH TREE DATA (FULL BINARY SEARCH TREE) : ");
      System.out.println("TESTING isEmpty() : " + bst.isEmpty());
      System.out.println("TESTING size() : " + bst.size());
      System.out.println("TESTING getRootItem() : " + bst.getRootItem().toString());
      System.out.println("TESTING validateBSTProperty() : " + bst.validateBSTProperty());
      System.out.println("TESTING validateSize() : " + bst.validateSize());
      System.out.println("TESTING isBalanced() : " + bst.isBalanced());
      System.out.println("\n-------------------------------------------------------------");

   }

   private static CD[] readMusic(String fileName)
   {
      FileIO file = new FileIO(fileName, FileIO.FOR_READING);
      String str = file.readLine();
      ArrayList<CD> cds = new ArrayList<CD>();
      while (!file.EOF())
      {
         String title = file.readLine();
         int year = Integer.parseInt(file.readLine());
         int rating = Integer.parseInt(file.readLine());
         int numTracks = Integer.parseInt(file.readLine());
         CD cd = new CD(title, str, year, rating, numTracks);

         cds.add(cd);
         int tracks = 1;

         while (tracks <= numTracks)
         {
            String temp = file.readLine();
            String[] line = temp.split(",");
            String len = line[0];
            String songTitle = line[1];
            cd.addSong(songTitle, len);
            tracks++;
         }

         str = file.readLine();
      }

      CD[] cds_array = new CD[cds.size()];
      int i = 0;
      for(CD cd : cds)
      {
         cds_array[i] = cds.get(i);
         i++;
      }
      return cds_array;
   }
}
