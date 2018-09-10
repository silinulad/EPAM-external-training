package by.gsu.epamlab.interfaces;

public abstract class AbstractCargo {
	private String name;

	public AbstractCargo() {
	}

	public AbstractCargo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
