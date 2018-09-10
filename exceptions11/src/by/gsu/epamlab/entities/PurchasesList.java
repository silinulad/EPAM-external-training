package by.gsu.epamlab.entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import by.gsu.epamlab.entities.PurchaseFactory;
import by.gsu.epamlab.exceptions.CsvLineException;
import by.gsu.epamlab.finance.Byn;
import by.gsu.epamlab.constants.Constants;

public class PurchasesList {

	private List<Purchase> purchases;
	private List<String> wrongPurchases;

	public PurchasesList() {
		purchases = new ArrayList<Purchase>();
	}

	public PurchasesList(String fileName) {
		fileName = Constants.PREVIOUS_FILE_PATH + fileName + Constants.FILE_EXTENSION;
		purchases = new ArrayList<Purchase>();
		wrongPurchases = new ArrayList<String>();
		try (Scanner scanner = new Scanner(new FileReader(fileName))) {
			while (scanner.hasNextLine()) {
				try {
					Purchase purchase = PurchaseFactory.getPurchaseFromFactory(scanner);
					purchases.add(purchase);
				} catch (CsvLineException e) {
					wrongPurchases.add(e.getScvLine() + Constants.ERROR_TAB + e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(Constants.ERROR_CSV_NOT_FOUND);
		}
	}

	public List<String> getWrongPurchases() {
		return wrongPurchases;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	// inserts a purchase into the collection at specified index.
	// If the index < 0, method inserts the purchase to the initial
	// position of the collection, otherwise method inserts the purchase
	// to the end of the collection.
	public void insertPurchase(int index, Purchase purchase) {
		if (index > purchases.size()) {
			purchases.add(purchase);
		} else if (index < Constants.INITIAL_INDEX) {
			purchases.add(Constants.INITIAL_INDEX, purchase);
		} else {
			purchases.add(index, purchase);
		}
	}

	public void deletePurchase(int index) {
		try {
			purchases.remove(index);
		} catch (IndexOutOfBoundsException e) {
			if (index < Constants.INITIAL_INDEX || index >= purchases.size()) {
				System.err.println(Constants.ERROR_WRONG_INDEX + e.getMessage());
			} else {
				System.err.println(Constants.ERROR_MISSING_ELEMENT + e.getMessage());
			}
		}
	}

	// calculates the total cost of purchases
	public Byn totalCost() {
		Byn totalCost = new Byn(0);
		for (Purchase purchase : purchases) {
			totalCost.add(purchase.getCost());
		}
		return totalCost;
	}

	public void sortList(Comparator<Purchase> comparator) {
		Collections.sort(purchases, comparator);
	}

	public void searchPurchase(Purchase purchase, Comparator<Purchase> comparator) {
		String result = "";
		int foundIndex = Collections.binarySearch(purchases, purchase, comparator);
		if (foundIndex > Constants.FAIL_INDEX) {
			result = Constants.FOUND_PURCHASE_POSITION + foundIndex;
		} else {
			result = Constants.NOT_FOUND_PURCHASE_POSITION;
		}
		System.out.println(Constants.PURCHASE + purchase + result + "\n");
	}

	// prints the list of the purchases in a table format
	public void printPurchasesList(List<Purchase> purchases) {
		Formatter formatter = new Formatter();
		formatter.format(Constants.TITLE_PATTERN, Constants.NAME, Constants.PRICE, Constants.NUMBER,
				Constants.DISCOUNT, Constants.COST);
		System.out.println(formatter);
		formatter = new Formatter();
		for (Purchase purchase : purchases) {
			try {
				formatter.format(Constants.TABLE_PATTERN, purchase.getName(), purchase.getPrice().toString(),
						purchase.getNumPurchasedUnits(), ((PriceDiscountPurchase) purchase).getDiscount(),
						purchase.getCost());
			} catch (ClassCastException e) {
				formatter.format(Constants.TABLE_PATTERN, purchase.getName(), purchase.getPrice().toString(),
						purchase.getNumPurchasedUnits(), Constants.CLASS_WITHOUT_DISCOUNT, purchase.getCost());
			}
		}
		formatter.format(Constants.TOTAL_COST_PATTERN, totalCost().toString());
		System.out.println(formatter);
		formatter.close();
	}

	public void printWrongPurchasesList(List<String> wrongPurchases) {
		for (String WrongPurchase : wrongPurchases) {
			System.err.println(WrongPurchase);
		}
		System.err.println("");
	}
}
