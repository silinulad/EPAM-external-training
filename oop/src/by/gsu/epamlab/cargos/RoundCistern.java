package by.gsu.epamlab.cargos;

import by.gsu.epamlab.interfaces.AbstractDrive;
import by.gsu.epamlab.interfaces.AbstractMaterial;

public class RoundCistern extends AbstractDrive {
	private final static double VOLUME = 1.5;// in m3
	private final static double DRIVE_MASS = 237.5; // in kg

	public RoundCistern() {
	}

	public RoundCistern(LiquidMaterial liquidMaterial) {
		super(liquidMaterial, DRIVE_MASS);
	}

	@Override
	public int id() {
		return 4;
	}

	@Override
	public double getVolume() {
		return VOLUME;
	}

}
