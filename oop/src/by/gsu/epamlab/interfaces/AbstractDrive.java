package by.gsu.epamlab.interfaces;

public abstract class AbstractDrive implements ITransportable {
	private AbstractMaterial material;
	private double driveMass;

	public AbstractDrive() {

	}

	public AbstractDrive(AbstractMaterial material, double driveMass) {
		this.material = material;
		this.driveMass = driveMass;
	}

	public abstract double getVolume();

	@Override
	public double getMass() {
		return driveMass + material.getDensity() * getVolume();
	}

	@Override
	public String toString() {
		return material + ";" + driveMass + ";" + getVolume() + ";" + getMass();
	}

}
