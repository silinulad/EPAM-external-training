package by.gsu.epamlab;

public class Purchase implements Comparable<Purchase> {
	public final static String NAME_PURCHASE = "Sausage";
	public final static int PRICE = 500;
	private int numPurchasedUnits;
	private double discountPercent;
	private WeekDay day;

	public Purchase(int numPurchasedUnits, double discountPercent, WeekDay day) {
		this.numPurchasedUnits = numPurchasedUnits;
		this.discountPercent = discountPercent;
		this.day = day;
	}

	public Purchase(int numPurchasedUnits, double discountPercent, int day) {
		this(numPurchasedUnits, discountPercent, WeekDay.values()[day]);
	}

	public Purchase() {
		this(0, 0.0, null);
	}

	public int getNumPurchasedUnits() {
		return numPurchasedUnits;
	}

	public void setNumPurchasedUnits(int numPurchasedUnits) {
		this.numPurchasedUnits = numPurchasedUnits;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public WeekDay getDay() {
		return day;
	}

	public void setDay(WeekDay day) {
		this.day = day;
	}

	public int getCost() {
		return (int) (Math.round(PRICE * numPurchasedUnits * (100 - discountPercent) / 100. / 100)) * 100;

	}

	@Override
	public int compareTo(Purchase another) {
		return numPurchasedUnits - another.numPurchasedUnits;
	}

	@Override
	public String toString() {
		return numPurchasedUnits + ";" + discountPercent + ";" + day + ";" + Utils.convert(getCost());
	}
	
	

}
