package by.gsu.epamlab;

public class DiscountPurchase extends AbstractPurchase {
	private Byn discount;

	public DiscountPurchase(Product product, int numPurchasedUnits, Byn discount) {
		super(product, numPurchasedUnits);
		this.discount = discount;
	}

	public DiscountPurchase() {
		this(null, 0, null);
	}

	public Byn getDiscount() {
		return discount;
	}

	public void setDiscount(Byn discount) {
		this.discount = discount;
	}

	@Override
	protected Byn getFinalCost(Byn baseCost) {
		return baseCost.sub(discount.mul(getNumPurchasedUnits()));
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + discount;
	}

}
