package by.gsu.epamlab.beans.factories;

import java.util.Scanner;

import static by.gsu.epamlab.constants.Constants.*;

import by.gsu.epamlab.beans.purchases.PriceDiscountPurchase;
import by.gsu.epamlab.beans.purchases.Purchase;
import by.gsu.epamlab.finance.Byn;

public class PurchaseFactory {
	private static enum PurchaseKind {

		PURCHASE {
			Purchase getPurchase(String[] fieldsOfPurchase) {
				return new Purchase(fieldsOfPurchase[NAME_INDEX],
						new Byn(Integer.parseInt(fieldsOfPurchase[PRICE_INDEX])),
						Integer.parseInt(fieldsOfPurchase[NUMBER_PURCHASES_INDEX]));
			}
		},
		DISCOUNT_PURCHASE {
			Purchase getPurchase(String[] fieldsOfPurchase) {
				return new PriceDiscountPurchase(fieldsOfPurchase[NAME_INDEX],
						new Byn(Integer.parseInt(fieldsOfPurchase[PRICE_INDEX])),
						Integer.parseInt(fieldsOfPurchase[NUMBER_PURCHASES_INDEX]),
						new Byn(Integer.parseInt(fieldsOfPurchase[PRICE_DISCOUNT_INDEX])));
			}
		};
		abstract Purchase getPurchase(String[] fieldsOfPurchase);
	}

	public static Purchase getPurchaseFromFactory(Scanner scanner) {
		PurchaseKind kindOfPurchase = null;
		String lineFromSource = scanner.nextLine();
		String[] fieldsOfPurchase = lineFromSource.split(DELIMETER);
		int elementsNumber = fieldsOfPurchase.length;

		if (elementsNumber == CSV_FIELDS_NUMBER) {
			kindOfPurchase = PurchaseKind.PURCHASE;
		} else {
			kindOfPurchase = PurchaseKind.DISCOUNT_PURCHASE;
		}
		return kindOfPurchase.getPurchase(fieldsOfPurchase);

	}
}
