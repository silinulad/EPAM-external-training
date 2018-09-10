package by.gsu.epamlab.comparators;

import java.util.Comparator;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.entities.PriceDiscountPurchase;
import by.gsu.epamlab.entities.Purchase;

public class PurchaseComparatorV1 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase p1, Purchase p2) {
		int result = p1.getName().compareTo(p2.getName());
	        if (result == Constants.EQUAL_RESULT) {
	            if ((p1 instanceof PriceDiscountPurchase)  && !(p2 instanceof PriceDiscountPurchase)) {
	                result = Constants.POSITIVE_RESULT;
	            } else if ((p2 instanceof PriceDiscountPurchase) && !(p1 instanceof PriceDiscountPurchase)) {
	                result = Constants.NEGATIVE_RESULT;
	            } else {
	                result = p1.getCost().compareTo(p2.getCost());
	            }
	        }
		return result;
	}

}
