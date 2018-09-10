/**
 * 
 */
package by.gsu.epamlab.finance;

/**
 * @author 102334
 *
 */
public class Byn implements Comparable<Byn> {

	private int amount;

	public Byn(int amount) {
		this.amount = amount;
	}

	public Byn(int rubles, int kopecks) {
		this.amount = rubles * 100 + kopecks;
	}

	public Byn(Byn byn) {
		this.amount = byn.amount;
	}

	public Byn add(Byn other) {
		amount += other.amount;
		return this;
	}

	public Byn sub(Byn other) {
		amount -= other.amount;
		return this;
	}

	public Byn mul(int value) {
		amount *= value;
		return this;
	}

	// d - the exponent of 10. It can take only positive integer value.
	public Byn mul(double value, Rounding rounding, int d) {
		amount = rounding.roundTo(amount * value, d);
		return this;
	}

	// Rounds the amount according to various rules, such as 0,01, 0,10, 1,00,
	// 10,00
	public Byn round(Rounding rounding, int d) {
		amount = rounding.roundTo(amount,d);
		return this;
	}

	public int getAmount() {
		return amount;
	}

	public int getRubles() {
		return amount / 100;
	}

	public int getKopecks() {
		return amount - getRubles() * 100;
	}

	@Override
	public int compareTo(Byn other) {
		return amount - other.amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Byn))
			return false;
		Byn other = (Byn) obj;
		if (amount != other.amount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getRubles() + "." + String.format("%02d", getKopecks());
	}


}
