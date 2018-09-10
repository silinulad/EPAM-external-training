package by.gsu.epamlab.beans.purchases;

import by.gsu.epamlab.finance.Byn;

public class PriceDiscountPurchase extends Purchase {
	private Byn discount;

	public PriceDiscountPurchase(String name, Byn price, int numPurchasedUnits, Byn discount) {
		super(name, price, numPurchasedUnits);
		this.discount = new Byn(discount);
	}
	public PriceDiscountPurchase(String name, int price, int numPurchasedUnits, int discount) {
		super(name, price, numPurchasedUnits);
		this.discount = new Byn(discount);
	}

	public PriceDiscountPurchase() {
		this(null, null, 0, null);
	}

	public Byn getDiscount() {
		return discount;
	}

	public void setDiscount(Byn discount) {
		this.discount = discount;
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + discount;
	}

	@Override
	public Byn getCost() {
		return new Byn(getPrice()).mul(getNumPurchasedUnits()).sub(getDiscount());
	}

}
