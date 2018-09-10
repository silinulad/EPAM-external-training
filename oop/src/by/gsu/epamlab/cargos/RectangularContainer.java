package by.gsu.epamlab.cargos;

import by.gsu.epamlab.interfaces.AbstractDrive;
import by.gsu.epamlab.interfaces.AbstractMaterial;

public class RectangularContainer extends AbstractDrive {
	private final static double VOLUME = 2.7;// in m3
	private final static double DRIVE_MASS = 353.7; // in kg

	public RectangularContainer() {
	}

	public RectangularContainer(SolidMaterial solidMaterial) {
		super(solidMaterial, DRIVE_MASS);
	}

	@Override
	public int id() {
		return 2;
	}

	@Override
	public double getVolume() {
		return VOLUME;
	}

}
