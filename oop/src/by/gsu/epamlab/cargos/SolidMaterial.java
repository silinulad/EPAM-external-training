package by.gsu.epamlab.cargos;

import by.gsu.epamlab.interfaces.AbstractMaterial;

public class SolidMaterial extends AbstractMaterial {

	public SolidMaterial() {
	}

	public SolidMaterial(String name, double density) {
		super(name, density);
	}

	@Override
	public String toString() {
		return "SolidMaterial" + super.toString();
	}

}
