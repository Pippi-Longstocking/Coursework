import java.awt.Graphics;
import java.awt.Image;

//you will need to implement Drawable
public abstract class DecoratedPizza
{
   /** 
    *  ImageLoader is responsible for loading images.
    *  Call the getImage passing the file name and store the result in an Image object.
    *  Use the drawImage in the Graphics class to draw the image.
    */
   private static final ImageLoader il = ImageLoader.getImageLoader();
   protected DecoratedPizza pizza;

   //Pizza will use the default constructor
   public DecoratedPizza()
   {
      pizza = null;
   }

   public DecoratedPizza(DecoratedPizza pizza)
   {
      this.pizza = pizza;
   }

   /* Child classes will need access to their reference (the next DecoratedPizza in the linked list). */
   protected DecoratedPizza getPizza()
   {
      return pizza;
   }
   
   public abstract double pizzaCost();
   public abstract String getImage();
   
   
}