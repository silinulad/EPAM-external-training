import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.Byn;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchaseFactory;

public class Runner {

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader("src\\in.txt"));
			sc.useLocale(Locale.ENGLISH);
			final int PURCHASES_NUMBER = 6;
			//1
			Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
			
			//2-5
			Byn purchaseMaxCost = new Byn(0);
			int indexMaxCost = 0;	
			boolean areEgual = true;
			for (int i = 0; i < purchases.length; i++) {
				purchases[i] = PurchaseFactory.getPurchaseFromFactory(sc);
				System.out.println(purchases[i]);
				
				//4
						
				if (purchases[i].getCost().compareTo(purchaseMaxCost) > 0) {
					purchaseMaxCost = purchases[i].getCost();
					indexMaxCost = i;
				}
				
				//5
				if (areEgual) {
					areEgual = purchases[i].equals(purchases[0]);
				} 

			}
			
			//6
			System.out.println("\nThe purchase with max cost is:");
			System.out.println(purchases[indexMaxCost]);

			System.out.print("\nAll purchases are ");
			System.out.println(areEgual == true ? "equal." : "not equal.");
			
		} catch (FileNotFoundException e) {
			System.err.println("Input file is not found");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}
