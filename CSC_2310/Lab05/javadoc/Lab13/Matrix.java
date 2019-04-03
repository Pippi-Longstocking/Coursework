import java.text.DecimalFormat;

/**
 *  This class extends the functionality of the BasicMatrix by implementing the common
 *  Matrix operations specified in MatrixOperationsInterface.  Direct access to the 2-D
 *  array is not possible (declared private in BasicMatrix), 
 *  but use of the public BasicMatrix methods is all that is required to perform
 *  the operations.
 */
public class Matrix extends BasicMatrix implements MatrixOperationsInterface
{

   /** 
    * Constructor to create the Matrix with the given size. <br> 
    * <b>Preconditions</b>: Valid number of rows and columns specified. <br>
    * <b>Postconditions</b>: The Matrix object is created and is ready for use. <br>
    * <b>Throws</b>: MatrixException if rows or columns < 1. <br>
    * Note: Ragged Matrices are not allowed (Matrices will always be rectangular).
    */
   public Matrix(int rows, int columns) throws MatrixException
   {
      super(rows,columns);
   }

   public MatrixOperationsInterface add(MatrixOperationsInterface otherMatrix) throws MatrixException
   {
      MatrixOperationsInterface result=null;

      if( otherMatrix==null || getNumRows()!=otherMatrix.getNumRows() || getNumColumns()!=otherMatrix.getNumColumns())
         throw new MatrixException ("Cannot add these two matrices.");
      else 
         result=new Matrix(getNumRows(),getNumColumns());    

      for (int i=1;i<=getNumRows();i++)
      {
         for (int j=1;j<=getNumColumns();j++)
         {
            result.setElement(i,j,getElement(i,j)+otherMatrix.getElement(i,j));
         }
      }

      return result;  
   }

   public MatrixOperationsInterface multiply(MatrixOperationsInterface otherMatrix) throws MatrixException
   {
      MatrixOperationsInterface result=null;
      double x=0;

      if (otherMatrix==null || getNumColumns() != otherMatrix.getNumRows())
         throw new MatrixException ("Cannot multiply these two matrices.");
      else 
         result=new Matrix(getNumRows(),otherMatrix.getNumColumns());

      //loop over all elements of resulting matrix
      for (int i=1;i<=result.getNumRows();i++)
      {
         for (int j=1;j<=result.getNumColumns();j++)
         {
            x=0;
            //sum up multiplying matrices to obtain value placed in new matrix
            for (int k=1;k<=getNumColumns();k++)
            {
               x = x + getElement(i,k)*otherMatrix.getElement(k,j);
            }
            result.setElement(i,j,x);
         }
      }

      return result;
   }

   public MatrixOperationsInterface transpose()
   {
      MatrixOperationsInterface result=new Matrix(getNumColumns(),getNumRows());

      for (int i=1;i<=getNumRows();i++)
      {
         for (int j=1;j<=getNumColumns();j++)
         {
            result.setElement(j,i,getElement(i,j));
         }
      }
    
      return result;
   }

   public double determinant()
   {
      // 1) det A = a11A11 + ... + a1jA1j
      // 2) Aij= (-1)^(i+j) det(Mij)
      // Mij obtained by striking out row i and column j
      if ( getNumRows()!=getNumColumns() ) //must be a square matrix
         throw new MatrixException("Determinant not defined.");

      double det=detRec(this);  //initial call
      return det;
   }

   /**
    * Recursive method for calculating the determinant. <br>
    * Preconditions:  matrix is not null.<br>
    * Postconditions: The determinant of matrix is returned.
    */
   private static double detRec(MatrixOperationsInterface matrix)
   {
      double det=0;
      if (matrix.getNumRows()==1)
         return matrix.getElement(1,1);
      else if (matrix.getNumRows()==2)  //base case
      {
         return matrix.getElement(1,1)*matrix.getElement(2,2)-matrix.getElement(1,2)*matrix.getElement(2,1);
      }

      else
      {
         Matrix cofact=new Matrix(matrix.getNumRows(),1);
         Matrix otherMatrix=new Matrix(matrix.getNumRows()-1,matrix.getNumColumns()-1);

         for (int j=1;j<=matrix.getNumColumns();j++)  //loop over first row
         {
            //reducing Matrix size
            for (int i=2;i<=matrix.getNumRows();i++)  //throw away row 1
            {
               for (int k=1;k<=matrix.getNumColumns();k++)
               {
                  if (k<j)
                  {
                     otherMatrix.setElement(i-1,k,matrix.getElement(i,k));
                  }
                  else if (k>j)
                  {
                     otherMatrix.setElement(i-1,k-1,matrix.getElement(i,k));
                  }
               }
            }
            cofact.setElement(j,1,Math.pow(-1,1+j)*detRec(otherMatrix));
            det+=matrix.getElement(1,j)*cofact.getElement(j,1);
         }
      }

      return det;
   }

   public MatrixOperationsInterface inverse() throws MatrixException
   {
      double det=determinant();
      if (det==0)
         throw new MatrixException("Matrix cannot be inverted.");

      MatrixOperationsInterface cofact=new Matrix(getNumRows(),getNumColumns());
      Matrix otherMatrix=new Matrix(getNumRows()-1,getNumColumns()-1);

      for (int i=1;i<=getNumRows();i++)
      {
         for (int j=1;j<=getNumColumns();j++)
         {

            for (int l=1;l<=getNumRows();l++)  //create the smaller matrix
            {
               for (int k=1;k<=getNumColumns();k++)
               {
                  if (k!=j && l!=i)  //throw away column j and row i
                  {
                     if (k>j)
                     {
                        if (l>i)
                        {
                           otherMatrix.setElement(l-1,k-1,getElement(l,k));
                        }
                        else
                        {
                           otherMatrix.setElement(l,k-1,getElement(l,k));
                        }
                     }
                     else
                     {
                        if (l>i)
                        {
                           otherMatrix.setElement(l-1,k,getElement(l,k));
                        }
                        else
                        {
                           otherMatrix.setElement(l,k,getElement(l,k));
                        }
                     }
                  }
               }
            }
            
            cofact.setElement(i,j,Math.pow(-1,i+j)*detRec(otherMatrix)/det);
         }
      }
      cofact=cofact.transpose();
      return cofact;
   }

}

