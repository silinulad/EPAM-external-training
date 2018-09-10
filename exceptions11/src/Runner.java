import java.security.ProviderException;
import java.util.Comparator;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.entities.Purchase;
import by.gsu.epamlab.entities.PurchasesList;

public class Runner {
	public static void main(String[] args) {
		final int ARGS_NUMBER = 3;
		if (args.length < ARGS_NUMBER || args.length > ARGS_NUMBER) {
			throw new ProviderException(Constants.ERROR_FORMAT_NUMBER);
		}

		Comparator<Purchase> COMPARATOR = null;

		try {
			COMPARATOR = (Comparator<Purchase>) Class
					.forName(Constants.NAME_PACKAGE_COMPARATORS + args[Constants.COMPARATOR_FILE_INDEX]).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
		// 1
		PurchasesList inPurchases = new PurchasesList(args[Constants.MAIN_FILE_INDEX]);

		// 2
		inPurchases.printWrongPurchasesList(inPurchases.getWrongPurchases());
		inPurchases.printPurchasesList(inPurchases.getPurchases());

		// 3
		PurchasesList addonPurchases = new PurchasesList(args[Constants.ADDON_FILE_INDEX]);

		// 4
		Purchase lastElement = addonPurchases.getPurchases().get(addonPurchases.getPurchases().size() - 1);
		final int INSERT_INDEX_0 = 0;
		inPurchases.insertPurchase(INSERT_INDEX_0, lastElement);

		// PurchasesList.printPurchasesList(inPurchases.getPurchases());

		// 5
		Purchase initialElement = addonPurchases.getPurchases().get(0);
		final int INSERT_INDEX_1000 = 1000;
		inPurchases.insertPurchase(INSERT_INDEX_1000, initialElement);

		// PurchasesList.printPurchasesList(inPurchases.getPurchases());

		// 6
		Purchase addElement = addonPurchases.getPurchases().get(2);
		final int INSERT_INDEX_2 = 2;
		inPurchases.insertPurchase(INSERT_INDEX_2, addElement);

		// PurchasesList.printPurchasesList(inPurchases.getPurchases());

		// 7
		final int DELETE_INDEX_FIRST = 3;
		int DELETE_INDEX_NEXT = 10;
		int DELETE_INDEX_LAST = -5;
		inPurchases.deletePurchase(DELETE_INDEX_FIRST);
		inPurchases.deletePurchase(DELETE_INDEX_NEXT);
		inPurchases.deletePurchase(DELETE_INDEX_LAST);

		// 8
		inPurchases.printPurchasesList(inPurchases.getPurchases());

		// 9
		inPurchases.sortList(COMPARATOR);

		// 10
		inPurchases.printPurchasesList(inPurchases.getPurchases());

		// 11
		final int FIND_INDEX_FIRST = 1;
		final int FIND_INDEX_LAST = 3;
		inPurchases.searchPurchase(addonPurchases.getPurchases().get(FIND_INDEX_FIRST), COMPARATOR);
		inPurchases.searchPurchase(addonPurchases.getPurchases().get(FIND_INDEX_LAST), COMPARATOR);

	}

}
