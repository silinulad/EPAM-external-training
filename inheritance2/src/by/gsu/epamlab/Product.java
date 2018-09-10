package by.gsu.epamlab;

public final class Product {
	private final String name;
	private final Byn price;

	private Product(String name, Byn price) {
		this.name = name;
		this.price = price;
	}
	
	public static Product newInstance(String name, Byn price) {
		return new Product(name, price);
	}

	public String getName() {
		return name;
	}

	public Byn getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + ";" + price;
	}

}
