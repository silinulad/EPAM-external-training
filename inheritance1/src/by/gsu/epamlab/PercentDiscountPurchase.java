package by.gsu.epamlab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {
	private static final int DISCOUNT_PURCHASE_UNITS = 3;
	private double percentDiscount;

	public PercentDiscountPurchase(String name, Byn price, int numPurchasedUnits, double percentDiscount) {
		super(name, price, numPurchasedUnits);
		this.percentDiscount = percentDiscount;
	}

	public PercentDiscountPurchase(Scanner sc) {
		super(sc);
		this.percentDiscount = sc.nextDouble();
	}

	public PercentDiscountPurchase() {
		super(null, null, 0);
		this.percentDiscount = 0.0;
	}

	public double getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(double percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public int getDiscountPurchasesUnits() {
		return DISCOUNT_PURCHASE_UNITS;
	}

	@Override
	public Byn getCost() {
		Byn cost = super.getCost();
		if (getNumPurchasedUnits() > DISCOUNT_PURCHASE_UNITS) {
			cost.mul(1-percentDiscount/100, Rounding.FLOOR, 0);
		}
		return cost;
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + percentDiscount;
	}

}
