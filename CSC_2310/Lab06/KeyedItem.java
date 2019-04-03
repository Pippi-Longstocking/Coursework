package ki;

public abstract class KeyedItem
{
	//create single instance variable of type Comparable
	Comparable item;
  
   public KeyedItem(Comparable key) 
   {
      item = key;
   }  

   public Comparable getKey() 
   {   
      return item;
   }  

	//Use Comparable toString() method
   public String toString()
   {
      return item.toString();
   }
}