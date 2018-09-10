package by.gsu.epamlab;

public class Material {
	private final String NAME;
	private final double DENSITY;

	public Material(String NAME, double DENSITY) {
		super();
		this.NAME = NAME;
		this.DENSITY = DENSITY;
	}

	public String getName() {
		return NAME;
	}

	public double getDensity() {
		return DENSITY;
	}

	@Override
	public String toString() {
		return NAME + ";" + DENSITY;
	}

}
