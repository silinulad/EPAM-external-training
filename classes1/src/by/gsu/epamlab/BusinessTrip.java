package by.gsu.epamlab;

public class BusinessTrip {
	private final static int DAILY_RATE = 700;
	private String accountEmployee;
	private int transportExpenses;
	private int dayNumber;

	public BusinessTrip() {
		super();
	}

	public BusinessTrip(String accountEmployee, int transportExpenses, int dayNumber) {
		super();
		this.accountEmployee = accountEmployee;
		this.transportExpenses = transportExpenses;
		this.dayNumber = dayNumber;
	}

	public String getAccountEmployee() {
		return accountEmployee;
	}

	public void setAccountEmployee(String accountEmployee) {
		this.accountEmployee = accountEmployee;
	}

	public double getTransportExpenses() {
		return transportExpenses;
	}

	public void setTransportExpenses(int transportExpenses) {
		this.transportExpenses = transportExpenses;
	}

	public double getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public int getTotal() {
		return (transportExpenses + DAILY_RATE * dayNumber);
	}

	private String kopToByn(int sum) {
		return sum / 100 + "." + sum % 100 / 10 + sum % 10;
	}

	public void show() {
		int dailyRate = DAILY_RATE;
		System.out.println("dailyAllowanceRate = " + kopToByn(dailyRate));
		System.out.println("accountEmployee = " + accountEmployee);
		System.out.println("transportExpenses = " + kopToByn(transportExpenses));
		System.out.println("dayNumber = " + dayNumber);
		System.out.println("totalExpenses = " + kopToByn(getTotal()));

	}

	@Override
	public String toString() {
		return accountEmployee + ";" + kopToByn(transportExpenses) + ";" + dayNumber + ";" + kopToByn(getTotal());
	}

}
