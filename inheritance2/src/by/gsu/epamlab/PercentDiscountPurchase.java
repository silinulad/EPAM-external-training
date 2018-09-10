package by.gsu.epamlab;

public class PercentDiscountPurchase extends AbstractPurchase {
	private static final int DISCOUNT_PURCHASE_UNITS = 3;
	private double percentDiscount;

	public PercentDiscountPurchase(Product product, int numPurchasedUnits, double percentDiscount) {
		super(product, numPurchasedUnits);
		this.percentDiscount = percentDiscount;
	}

	public PercentDiscountPurchase() {
		this(null, 0, 0.0);
	}

	public double getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(double percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public static int getDiscountPurchasesUnits() {
		return DISCOUNT_PURCHASE_UNITS;
	}

	@Override
	protected Byn getFinalCost(Byn baseCost) {
		if (getNumPurchasedUnits() > DISCOUNT_PURCHASE_UNITS) {
			baseCost = baseCost.mul(1 - percentDiscount / 100, Rounding.FLOOR, 2);
		}
		return baseCost;
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + percentDiscount;
	}

}