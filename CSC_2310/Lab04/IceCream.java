public class IceCream
{

	private int numberOfScoops;
	private Flavor iceCreamFlavor;
	private Topping iceCreamTopping;

	public IceCream(int numScoops, Flavor flav, Topping top)
	{
		if (numScoops < 1 || numScoops > 3)
			numScoops = 1;

		else if (!flav)
		{
			Flavor flavo = new Flavor();
			newFlavor.chocolate;
		}

		else
			flavor = flav;

		if (!top)
			Topping toppo = new Topping();
			newTopping.no_topping;

		else
			topping = top;


	}


	public double getPrice()
	{
		double basePrice = 1.99;

		if (numerOfScoops > 1)
			basePrice += ((numberOfScoop - 1) * 0.75);

		


	}


	public void listIceCream()
	{
		System.out.println("Flavor: " + iceCreamFlavor.getFlavor());
		System.out.println("Toppings: " + iceCreamTopping.getToppings());
	}


}