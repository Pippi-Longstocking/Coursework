public class Cone
{
	private int type;

	public Cone(int choice)
	{
		if (choice < 1 || choice > 3)
			type = -1;

		type = choice;

	}


	double public getConePrice()
	{
		//Default Cone
		if (type == -1)
			return 0.59;

		//Sugar Cone
		else if (type == 1)
			return 0.59;

		//Waffle Cone
		else if (type == 2)
			return 0.79;

		//Cup
		else
			return 0;

	}

	String public toString()
	{
		String coneString;

		if (type == -1)
			coneString = "Default Cone";

		else if (type == 1)
			coneString = "Sugar Cone";
		
		else if (type == 2)
			coneString = "Waffle Cone";

		else 
			coneString = "Cup";

		return coneString;
	}
}