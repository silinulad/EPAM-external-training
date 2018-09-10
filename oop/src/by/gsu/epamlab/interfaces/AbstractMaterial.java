package by.gsu.epamlab.interfaces;

public abstract class AbstractMaterial extends AbstractCargo {
	private double density;

	public AbstractMaterial() {
	}

	public AbstractMaterial(String name, double density) {
		super(name);
		this.density = density;
	}

	public double getDensity() {
		return density;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + density;
	}

}
