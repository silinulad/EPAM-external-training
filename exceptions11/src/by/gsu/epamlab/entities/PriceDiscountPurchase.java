package by.gsu.epamlab.entities;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.finance.Byn;

public class PriceDiscountPurchase extends Purchase {
	private Byn discount;
	private Byn cost;

	public PriceDiscountPurchase(String name, Byn price, int numPurchasedUnits, Byn discount) {
		super(name, price, numPurchasedUnits);
		setDiscount(discount);
		setCost(price, numPurchasedUnits, discount);
	}

	public PriceDiscountPurchase() {
		super(null, null, 0);
		setDiscount(discount);
	}

	public Byn getDiscount() {
		return discount;
	}

	public void setDiscount(Byn discount) {
		if (discount.getAmount() <= 0) {
			throw new IllegalArgumentException(Constants.ERROR_SUBZERO + discount.getAmount() + " in discount");
		}
		this.discount = discount;
	}
	
	public void setCost(Byn price, int numPurchasedUnits, Byn discount) {
		Byn cost = new Byn(getPrice()).sub(discount).mul(getNumPurchasedUnits());
		if (cost.getAmount() <= 0) {
			throw new IllegalArgumentException(Constants.ERROR_SUBZERO + cost.getAmount() + " in cost");
		}
		this.cost = cost;
	}
	
	@Override
	public Byn getCost() {
		return cost;
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + discount;
	}

}
