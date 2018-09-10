package by.gsu.epamlab;

import java.util.Scanner;

public class DiscountPurchase extends Purchase {
	private Byn discount;

	public DiscountPurchase(String name, Byn price, int numPurchasedUnits, Byn discount) {
		super(name, price, numPurchasedUnits);
		this.discount = discount;
	}

	public DiscountPurchase(Scanner sc) {
		super(sc);
		this.discount = new Byn(sc.nextInt());
	}

	public DiscountPurchase() {
		super(null, null, 0);
		this.discount = null;
	}

	public Byn getDiscount() {
		return discount;
	}

	public void setDiscount(Byn discount) {
		this.discount = discount;
	}

	@Override
	public Byn getCost() {
		return new Byn(getPrice()).sub(discount).mul(getNumPurchasedUnits());
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + discount;
	}

}
