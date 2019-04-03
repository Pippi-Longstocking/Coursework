/**  
 * This class is designed to abstract 2-D arrays.  The main features are that indices start with one
 * rather than zero, and methods make 2-D array operations like multiplication much simpler for the user. 
 * This class only supports rectangular 2-D arrays, however. 
 */

public class BasicMatrix implements BasicMatrixInterface
{

   /**
    * The 2-D array that we are abstracting. <br>
    */
   private double[][] matrix;

   /** 
    * Constructor to create the Matrix with the given size. <br> 
    * <b>Preconditions</b>: Valid number of rows and columns specified. <br>
    * <b>Postconditions</b>: The Matrix object is created and is ready for use. <br>
    * <b>Throws</b>: MatrixException if rows or columns < 1. <br>
    * Note: Ragged Matrices are not allowed (Matrices will always be rectangular).
    */
   public BasicMatrix(int rows, int columns) throws MatrixException
   {
      if (rows<1 || columns <1)
         throw new MatrixException("Invalid row or column.");

      matrix=new double[rows][columns];
   }

   public int getNumRows()
   {
      return matrix.length;
   }

   public int getNumColumns()
   {
      return matrix[0].length;
   }

   public void setElement(int row, int column, double value) throws MatrixException
   {
      if (row>getNumRows() || row<1 || column>getNumColumns() || column<1)
         throw new MatrixException("Invalid row or column.");

      matrix[row-1][column-1]=value;
   }

   public double getElement(int row, int column) throws MatrixException
   {
      if (row>getNumRows() || row<1 || column>getNumColumns() || column<1)
         throw new MatrixException("Invalid row or column.");

      return matrix[row-1][column-1];
   }

}

