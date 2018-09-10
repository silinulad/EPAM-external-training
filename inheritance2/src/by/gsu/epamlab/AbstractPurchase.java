package by.gsu.epamlab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
	private Product product;
	private int numPurchasedUnits;

	public AbstractPurchase(Product product, int numPurchasedUnits) {
		this.product = product;
		this.numPurchasedUnits = numPurchasedUnits;
	}

	public AbstractPurchase() {
		this(null, 0);
	}

	public Product getProduct() {
		return product;
	}

	public int getNumPurchasedUnits() {
		return numPurchasedUnits;
	}

	public void setNumPurchasedUnits(int numPurchasedUnits) {
		this.numPurchasedUnits = numPurchasedUnits;
	}

	protected abstract Byn getFinalCost(Byn baseCost);

	public Byn getCost() {
		Byn baseCost = product.getPrice().mul(numPurchasedUnits);
		Byn finalCost = getFinalCost(baseCost);
		return finalCost.round(Rounding.FLOOR, 2); 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractPurchase))
			return false;
		AbstractPurchase other = (AbstractPurchase) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public int compareTo(AbstractPurchase other) {
		return other.getCost().compareTo(getCost());
	}

	protected String fieldsToString() {
		return product + ";" + numPurchasedUnits;
	}

	@Override
	public String toString() {
		return fieldsToString() + ";" + getCost();
	}

}
