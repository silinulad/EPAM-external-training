package by.gsu.epamlab.cargos;

import by.gsu.epamlab.interfaces.AbstractMaterial;

public class LiquidMaterial extends AbstractMaterial {

	public LiquidMaterial() {
	}

	public LiquidMaterial(String name, double density) {
		super(name, density);
	}

	@Override
	public String toString() {
		return "LiguidMaterial" + super.toString();
	}

}
