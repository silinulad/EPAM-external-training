import java.util.Arrays;

import by.gsu.epamlab.*;

public class Runner {
	
	//the method prints purchases array
	private static final void printPurchases(AbstractPurchase[] purchases) {
		for (AbstractPurchase purchase : purchases) {
			System.out.println(purchase);
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {
		
		//1 - Creating a unique product using the static generation method
		final Product BREAD = Product.newInstance("Bread", new Byn(125));
		
		//2 - Creating an array for 6 different purchases
		AbstractPurchase[] purchases = new AbstractPurchase[] {
				new DiscountPurchase(BREAD, 7, new Byn(10)),
				new DiscountPurchase(BREAD, 4, new Byn(0)), 
				new PercentDiscountPurchase(BREAD, 10, 3.325),
				new PercentDiscountPurchase(BREAD, 8, 5.0),
				new TransportPurchase(BREAD, 5, new Byn(200)),
				new TransportPurchase(BREAD, 7, new Byn(500))
		};
	
		//3 
		printPurchases(purchases);
		
		//4 Sort the purchases array in descending order
		Arrays.sort(purchases);
		
		//5
		printPurchases(purchases);
		
		//6 Printing the purchase with minimum cost
		System.out.println("Minimum cost = " + purchases[purchases.length - 1].getCost());
		System.out.println(" ");
		
		//7 - Finding and print some purchase with cost equaled to 5.00 BYN
		final Byn REQUIRED_COST = new Byn(5,0);
		final AbstractPurchase REQUIRED_PURCHASE = new AbstractPurchase() {
			
			@Override
			public Byn getCost() {
				return REQUIRED_COST;
			}

			@Override
			protected Byn getFinalCost(Byn baseCost) {
				return null;
			}
		};

		int index = Arrays.binarySearch(purchases, REQUIRED_PURCHASE);
		
		if (index >= 0) {
			System.out.println(purchases[index]);
		} else {
			System.out.println("Required purchase is not found");
		}

	}
}

