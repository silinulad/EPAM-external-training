package by.gsu.epamlab.beans.purchases;

import by.gsu.epamlab.beans.purchases.Purchase;
import by.gsu.epamlab.finance.Byn;

public class Purchase implements Comparable<Purchase>{

	private String name;
	private Byn price;
	private int numPurchasedUnits;

	public Purchase(String name, Byn price, int numPurchasedUnits) {
		this.name = name;
		this.price = new Byn(price);
		this.numPurchasedUnits = numPurchasedUnits;
	}
	
	public Purchase(String name, int price, int numPurchasedUnits) {
		this.name = name;
		this.price = new Byn(price);
		this.numPurchasedUnits = numPurchasedUnits;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
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
	
	@Override
	public int compareTo(Purchase another) {
		int rank = name.compareTo(another.getName());
		if(rank == 0){
			rank = price.compareTo(another.getPrice());
		}
		return rank;
	}

	protected String fieldsToString() {
		return name + ";" + price + ";" + numPurchasedUnits;
	}

	@Override
	public String toString() {
		return fieldsToString() + ";" + getCost();
	}
}
