import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.brunchboy.util.swing.relativelayout.*;

public class EasyRelative extends RelativeLayout
{

   public EasyRelative()
   {
      super();
   }

   /**
    * An easier method for using RelativeLayout. <br>
    * Identify two control edges and indicate how they are to be related to one another.<br>
    * <b>Preconditions</b>: specify the nickname and attribute that is being aligned for the first component (the component being placed) --  primaryNickName and primaryAttribute (not null)<br>
    *                possible attribute values are top, bottom, left, right (as Strings)<br>
    *                specify the nickname and attribute that is being aligned to for the second component (already placed) -- secondaryNickName and secondaryAttribute (not null)<br>
    *                specify the direction of displacement and the number of pixels of displacement (not null)<br>
    *                possible directions are up, down, left, right (as Strings)<br>
    *                not to be used with horizontal and vertical centering as the displacement is specified<br>
    * <b>Postconditions</b>: the relative positioning will be added to the layout manager
    *
    */
   public void addRelationship(String primaryNickName, String primaryAttribute, String secondaryNickName, String secondaryAttribute, String direction,int pixels)
   {
      AttributeType att1;
      AttributeType att2;

      if (primaryAttribute.equals("top"))
      {
         att1=AttributeType.TOP;
      }
      else if (primaryAttribute.equals("bottom"))
      {
         att1=AttributeType.BOTTOM;
      }
      else if (primaryAttribute.equals("left"))
      {
         att1=AttributeType.LEFT;
      }
      else if (primaryAttribute.equals("right"))
      {
         att1=AttributeType.RIGHT;
      }
      else
      {
         att1=AttributeType.TOP;
      }

      if (secondaryAttribute.equals("top"))
      {
         att2=AttributeType.TOP;
      }
      else if (secondaryAttribute.equals("bottom"))
      {
         att2=AttributeType.BOTTOM;
      }
      else if (secondaryAttribute.equals("left"))
      {
         att2=AttributeType.LEFT;
      }
      else if (secondaryAttribute.equals("right"))
      {
         att2=AttributeType.RIGHT;
      }
      else
      {
         att2=AttributeType.TOP;
      }

      if (secondaryNickName.equals("container"))
      {
         secondaryNickName=DependencyManager.ROOT_NAME;
      }

      if (direction.equals("up"))
      {
         pixels=-1*pixels;
      }
      else if (direction.equals("left"))
      {
         pixels=-1*pixels;
      }

      addConstraint(primaryNickName, att1, new AttributeConstraint(secondaryNickName, att2, pixels));
   }

   /**
    * An easier method for using RelativeLayout. <br>
    * Identify two control edges and indicate how they are to be related to one another.<br>
    * <b>Preconditions</b>: specify the nickname and attribute (vertical or horizontal) that is being aligned for the first component (the component being placed) --  primaryNickName and primaryAttribute (not null)<br>
    *                specify the nickname and attribute (vertical or horizontal) that is being aligned to for the second component (already placed) -- secondaryNickName and secondaryAttribute (not null)<br>
    *                possible attributes are horizontal and vertical (as Strings)<br>
    *                this method is used for horizontal and vertical centering<br>
    * <b>Postconditions</b>: the relative positioning will be added to the layout manager
    *
    */
   public void addRelationship(String primaryNickName, String primaryAttribute, String secondaryNickName, String secondaryAttribute)
   {
      AttributeType att1;
      AttributeType att2;

      if (primaryAttribute.equals("horizontal"))
      {
         att1=AttributeType.HORIZONTAL_CENTER;
      }
      else if (primaryAttribute.equals("vertical"))
      {
         att1=AttributeType.VERTICAL_CENTER;
      }
      else
      {
         att1=AttributeType.HORIZONTAL_CENTER;
      }

      if (secondaryAttribute.equals("horizontal"))
      {
         att2=AttributeType.HORIZONTAL_CENTER;
      }
      else if (secondaryAttribute.equals("vertical"))
      {
         att2=AttributeType.VERTICAL_CENTER;
      }
      else
      {
         att2=AttributeType.HORIZONTAL_CENTER;
      }

      if (secondaryNickName.equals("container"))
      {
         secondaryNickName=DependencyManager.ROOT_NAME;
      }

      addConstraint(primaryNickName, att1, new AttributeConstraint(secondaryNickName, att2));
   }
  
}