package by.gsu.epamlab.entities;

import java.util.Scanner;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.CsvLineException;
import by.gsu.epamlab.finance.Byn;

public class PurchaseFactory {
	private static enum PurchaseKind {

		PURCHASE {
			Purchase getPurchase(String[] fieldsOfPurchase) {
				return new Purchase(fieldsOfPurchase[Constants.NAME_INDEX],
						new Byn(Integer.parseInt(fieldsOfPurchase[Constants.PRICE_INDEX])),
						Integer.parseInt(fieldsOfPurchase[Constants.NUMBER_PURCHASES_INDEX]));
			}
		},
		DISCOUNT_PURCHASE {
			Purchase getPurchase(String[] fieldsOfPurchase) {
				return new PriceDiscountPurchase(fieldsOfPurchase[Constants.NAME_INDEX],
						new Byn(Integer.parseInt(fieldsOfPurchase[Constants.PRICE_INDEX])),
						Integer.parseInt(fieldsOfPurchase[Constants.NUMBER_PURCHASES_INDEX]),
						new Byn(Integer.parseInt(fieldsOfPurchase[Constants.PRICE_DISCOUNT_INDEX])));
			}
		};
		abstract Purchase getPurchase(String[] fieldsOfPurchase);
	}

	public static Purchase getPurchaseFromFactory(Scanner scanner) throws CsvLineException {
		PurchaseKind kindOfPurchase = null;
		String lineFromSource = scanner.nextLine();
		String[] fieldsOfPurchase = lineFromSource.split(Constants.DELIMETER);
		int elementsNumber = fieldsOfPurchase.length;
		if (elementsNumber < Constants.CSV_FIELDS_NUMBER || elementsNumber > Constants.CSV_FIELDS_NUMBER_DISCOUNT) {
			throw new CsvLineException(Constants.ERROR_WRONG_NUMBER, lineFromSource);
		}
		try {
			if (elementsNumber == Constants.CSV_FIELDS_NUMBER) {
				kindOfPurchase = PurchaseKind.PURCHASE;
			} else if (elementsNumber == Constants.CSV_FIELDS_NUMBER_DISCOUNT) {
				kindOfPurchase = PurchaseKind.DISCOUNT_PURCHASE;
			}
			return kindOfPurchase.getPurchase(fieldsOfPurchase);
		} catch (NumberFormatException e) {
			throw new CsvLineException(Constants.ERROR_FORMAT_NUMBER, lineFromSource);
		} catch (IllegalArgumentException e) {
			throw new CsvLineException(e.getMessage(), lineFromSource);
		}

	}
}
