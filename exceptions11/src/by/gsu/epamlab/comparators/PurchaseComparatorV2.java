package by.gsu.epamlab.comparators;

import java.util.Comparator;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.entities.PriceDiscountPurchase;
import by.gsu.epamlab.entities.Purchase;

public class PurchaseComparatorV2 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase p1, Purchase p2) {
		int result = p1.getName().compareTo(p2.getName());
		if (result == Constants.EQUAL_RESULT) {
			if (p1.getClass() == p2.getClass()) {
				result = p1.getCost().compareTo(p2.getCost());
			} else {
				result = (p1.getClass() == PriceDiscountPurchase.class) ? Constants.POSITIVE_RESULT
						: Constants.NEGATIVE_RESULT;
			}
		}
		return result;
	}

}
