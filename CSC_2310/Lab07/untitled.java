public class ExceptionPractice
{
	
	public static void main(String[] args)
	{

		int x = 5;
		int y = 0;


		try
		{
			System.out.println(x / y);
		throw new mathException("Divide by zero");
		}

		catch(mathException e)
		{
			System.out.println(e.getMessage());
		}
	}
}