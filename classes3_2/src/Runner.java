import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.Utils;
import by.gsu.epamlab.WeekDay;

public class Runner {

	private static final void printPurchases(Purchase[] purchases) {
		System.out.print("NAME_PURCHASE = " + Purchase.NAME_PURCHASE + "; ");
		System.out.println("PRICE = " + Utils.convert(Purchase.PRICE) + " BYN");
		for (Purchase purchase : purchases) {
			System.out.println(purchase);
		}
		System.out.println("\n");
	}

	public static void main(String[] args) {

		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader("src/in.txt"));
			sc.useLocale(Locale.ENGLISH);

			// 1
			final int PURCHASES_NUMBER = sc.nextInt();

			Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

			// 2
			for (int i = 0; i < purchases.length; i++) {
				purchases[i] = new Purchase(sc.nextInt(), sc.nextDouble(), sc.nextInt());
			}

			// 3
			printPurchases(purchases);

			// 4
			int totalCost = 0;
			double meanCost = 0.0;
			int mondayTotalCost = 0;
			int maxCost = 0;
			WeekDay maxCostDay = null;
			for (Purchase purchase : purchases) {
				int cost = purchase.getCost();
				totalCost += cost;
				if (cost > maxCost) {
					maxCost = cost;
					maxCostDay = purchase.getDay();
				}
				if (purchase.getDay() == WeekDay.MONDAY) {
					mondayTotalCost += cost;
				}
			}
			if (purchases.length > 0) {
				meanCost = (double) totalCost / purchases.length;
			}

			System.out.println("Mean cost = " + Utils.format(meanCost));
			System.out.println("The total cost on Monday = " + Utils.convert(mondayTotalCost));
			System.out.println("The day with the maximum cost purchase is " + maxCostDay + "\n");

			// 5
			Arrays.sort(purchases);

			// 6
			printPurchases(purchases);

			int index = Arrays.binarySearch(purchases, new Purchase(5, 0.0, null));

			System.out.println(index >= 0 ? purchases[index] : "Required purchase is not found");

		} catch (FileNotFoundException e) {
			System.err.println("Input file is not found!");

		} finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}
