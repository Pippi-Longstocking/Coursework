/**
 *  This interface defines fundamental Matrix operations such as setting and retrieving individual elements
 *  and obtaining the Matrix dimensions.
 *  Matrix indices start with 1.
 *  @author Mark A. Boshart
 */

public interface BasicMatrixInterface
{

   /** 
    * Method to return the number of rows in the Matrix. <br> 
    * <b>Preconditions</b>: None. <br>
    * <b>Postconditions</b>: The number of rows in the Matrix is returned. <br>
    * <b>Throws</b>: None.<br>
    * @return The number of rows in the Matrix.
    */
   public int getNumRows();

   /** 
    * Method to return the number of columns in the Matrix. <br> 
    * <b>Preconditions</b>: None. <br>
    * <b>Postconditions</b>: The number of columns in the Matrix is returned. <br>
    * <b>Throws</b>: None.<br>
    * @return The number of columns in the Matrix.
    */
   public int getNumColumns();

   /** 
    * Method to return the value at a specified element in the Matrix. <br> 
    * <b>Preconditions</b>:  row is the row of the Matrix, column is the column. <br>
    *                 The row and the column must be inside the matrix (indices start with 1). <br>
    * <b>Postconditions</b>: The double contained at the specified location in the matrix is returned. <br>
    * <b>Throws</b>: MatrixException if (rows or columns < 1) or (rows or columns > the valid range).<br>
    * @throws MatrixException if (rows or columns < 1) or (rows or columns > the valid range--  the max size specified upon construction).<br>
    * @param row The row in the Matrix to get the element.<br>
    * @param column The column in the Matrix to get the element.<br>
    * @return The value at the specified location in the Matrix.
    */
   public double getElement(int row, int column) throws MatrixException;

   /** 
    * Method to allow the setting of individual Matrix elements. <br> 
    * <b>Preconditions</b>:  row is the row of the Matrix, column is the column, and value is the number to insert into the matrix. <br>
    *                 The row and the column must be inside the matrix (indices start with 1). <br>
    * <b>Postconditions</b>: The Matrix element is set to the specified double. <br>
    * <b>Throws</b>: MatrixException if (rows or columns < 1) or (rows or columns > the valid range--  the max size specified upon construction).<br>
    * @throws MatrixException if (rows or columns < 1) or (rows or columns > the valid range--  the max size specified upon construction).<br>
    * @param row The row in the Matrix to set the element.<br>
    * @param column The column in the Matrix to set the element.<br>
    * @param value The value to be set into the Matrix.
    */
   public void setElement(int row, int column, double value) throws MatrixException;

}

