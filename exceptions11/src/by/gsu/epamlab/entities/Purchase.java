package by.gsu.epamlab.entities;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.finance.Byn;

public class Purchase {
	private String name;
	private Byn price;
	private int numPurchasedUnits;

	public Purchase(String name, Byn price, int numPurchasedUnits) {
		setName(name);
		setPrice(new Byn(price));
		setNumPurchasedUnits(numPurchasedUnits);
	}

	public Purchase() {
		this(null, null, 0);
	}

	public Byn getPrice() {
		return price;
	}

	public void setPrice(Byn price) {
		if (price.getAmount() <= 0) {
			throw new IllegalArgumentException(Constants.ERROR_SUBZERO + price.getAmount() + " in price");
		}
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(Constants.ERROR_NULL_NAME);
		}
		if ("".equals(name)) {
			throw new IllegalArgumentException(Constants.ERROR_EMPTY_NAME);
		}
		this.name = name;
	}

	public int getNumPurchasedUnits() {
		return numPurchasedUnits;
	}

	public void setNumPurchasedUnits(int numPurchasedUnits) {
		if (numPurchasedUnits <= 0) {
			throw new IllegalArgumentException(Constants.ERROR_SUBZERO + numPurchasedUnits + " in number");
		}
		this.numPurchasedUnits = numPurchasedUnits;
	}

	public Byn getCost() {
		Byn cost = new Byn(price).mul(numPurchasedUnits);
		return cost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Purchase))
			return false;
		Purchase other = (Purchase) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	protected String fieldsToString() {
		return name + ";" + price + ";" + numPurchasedUnits;
	}

	@Override
	public String toString() {
		return fieldsToString() + ";" + getCost();
	}

}
