package by.gsu.epamlab;

import java.util.Scanner;

public class Purchase {

	private String name;
	private Byn price;
	private int numPurchasedUnits;

	public Purchase(String name, Byn price, int numPurchasedUnits) {
		this.name = name;
		this.price = new Byn(price);
		this.numPurchasedUnits = numPurchasedUnits;
	}

	public Purchase(Scanner sc) {
		this(sc.next(), new Byn(sc.nextInt()), sc.nextInt());
	}

	public Purchase() {
		this(null, null, 0);
	}

	public Byn getPrice() {
		return price;
	}

	public void setPrice(Byn price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumPurchasedUnits() {
		return numPurchasedUnits;
	}

	public void setNumPurchasedUnits(int numPurchasedUnits) {
		this.numPurchasedUnits = numPurchasedUnits;
	}

	public Byn getCost() {
		return new Byn(price).mul(numPurchasedUnits);
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
