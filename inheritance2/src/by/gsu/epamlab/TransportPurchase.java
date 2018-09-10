package by.gsu.epamlab;

public class TransportPurchase extends AbstractPurchase {
	private Byn transportExpenses;

	public TransportPurchase(Product product, int numPurchasedUnits, Byn transportExpenses) {
		super(product, numPurchasedUnits);
		this.transportExpenses = transportExpenses;
	}

	public TransportPurchase() {
		this(null, 0, null);
	}

	public Byn getTransportExpenses() {
		return transportExpenses;
	}

	public void setTransportExpenses(Byn transportExpenses) {
		this.transportExpenses = transportExpenses;
	}

	@Override
	protected Byn getFinalCost(Byn baseCost) {
		return baseCost.sum(transportExpenses);
	}

	@Override
	protected String fieldsToString() {
		return super.fieldsToString() + ";" + transportExpenses;
	}

}
