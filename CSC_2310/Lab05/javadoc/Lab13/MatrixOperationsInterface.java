/**
 *  This interface specifies common Matrix operations.  Typically, two Matrices are needed.
 *  The active Matrix is used and a passed in Matrix.  A new Matrix is created and returned after
 *  the requested operation has been performed.  The methods could be made static with two matrices passed in.
 *  @author Mark A. Boshart
 */

public interface MatrixOperationsInterface extends BasicMatrixInterface
{

   /** 
    * Method to add two matrices together. <br> 
    * <b>Preconditions</b>: A Matrix with dimensions compatible to <b>this</b> is passed in (not null). <br>
    * <b>Postconditions</b>: A new Matrix that is the result of adding <b>this</b> with the passed in Matrix is returned. The original Matrices are unaffected. <br>
    * @throws MatrixException if the two matrices cannot be added together (dimensions are not compatible). <br>
    * @param otherMatrix The Matrix to be added to the active Matrix.<br>
    * @return A new Matrix which is the result of adding the active Matrix with otherMatrix.
    */
   public MatrixOperationsInterface add(MatrixOperationsInterface otherMatrix) throws MatrixException;

   /** 
    * Method to multiply two matrices together. <br> 
    * <b>Preconditions</b>: A Matrix with dimensions compatible to <b>this</b> is passed in (not null). <br>
    * <b>Postconditions</b>: A new Matrix that is the result of mulitplying <b>this</b> with the passed in Matrix is returned (the parameter Matrix is on the right). The original Matrices are unaffected. <br>
    * <b>Throws</b>: MatrixException if the two matrices cannot be multiplied together (dimensions are not compatible). <br>
    * Note: This method could be made static if two Matrices are passed in.<br>
    * @param otherMatrix The Matrix to be mulitplied (on the right) to the active Matrix.<br>
    * @return A new Matrix which is the result of multiplying the active Matrix with otherMatrix (otherMatrix is on the right).
    */
   public MatrixOperationsInterface multiply(MatrixOperationsInterface otherMatrix) throws MatrixException;

   /** 
    * Method to return the transpose of the active Matrix. <br> 
    * <b>Preconditions</b>: None. <br>
    * <b>Postconditions</b>: A new Matrix that is the result of taking the transpose of <b>this</b> is returned. The original Matrix is unaffected. <br>
    * <b>Throws</b>: None. <br>
    * @return A new Matrix which is the transpose of the active Matrix.
    */
   public MatrixOperationsInterface transpose();

   /** 
    * Method to return the determinant of the active Matrix. <br> 
    * <b>Preconditions</b>: The active Matrix must be square. <br>
    * <b>Postconditions</b>: The determinant of the active Matrix is returned as a double. <br>
    * <b>Throws</b>: MatrixException if the active Matrix is not square. <br>
    * Note: One should not mention how the determinant calculation is implemented (in our case, recursively). <br>
    * @return The determinant of the Matrix.
    */
   public double determinant();

   /** 
    * Method to return the inverse of the active Matrix. <br> 
    * <b>Preconditions</b>: The active Matrix must have a nonzero determinant (which means that it also must be square). <br>
    * <b>Postconditions</b>: The inverse of the active Matrix is returned. The original Matrix is unaffected. <br>
    * @throws MatrixException if the active Matrix has a zero determinant or is not square. <br>
    * @return The inverse of the Matrix.
    */
   public MatrixOperationsInterface inverse() throws MatrixException;

}

