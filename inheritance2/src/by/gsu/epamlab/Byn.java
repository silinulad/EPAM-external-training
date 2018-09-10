package by.gsu.epamlab;

public class Byn implements Comparable<Byn> {

	private final int amount;

	public Byn(int amount) {
		this.amount = amount;
	}

	public Byn(int rubles, int kopecks) {
		this(rubles * 100 + kopecks);
	}

	public Byn(Byn byn) {
		this(byn.amount);
	}

	public Byn sum(Byn other) {
		return new Byn(amount + other.amount);

	}

	public Byn sub(Byn other) {
		return new Byn(amount - other.amount);
	}

	public Byn mul(int value) {
		return new Byn(amount * value);
	}

	// d - the exponent of 10. It can take only positive integer value.
	public Byn mul(double value, Rounding rounding, int d) {
		return new Byn(rounding.roundTo(amount * value, d));
	}

	// Rounds the amount according to various rules, such as 0,01, 0,10, 1,00,
	// 10,00
	public Byn round(Rounding rounding, int d) {
		return new Byn(rounding.roundTo(amount, d));
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Byn)) {
			return false;
		} else {
			return amount == ((Byn) obj).amount;
		}
	}

	@Override
	public String toString() {
		return String.format("%01d", getRubles()) + "." + String.format("%02d", getKopecks());
	}

}
