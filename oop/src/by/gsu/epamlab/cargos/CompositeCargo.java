package by.gsu.epamlab.cargos;

import by.gsu.epamlab.interfaces.AbstractCargo;
import by.gsu.epamlab.interfaces.ITransportable;

public class CompositeCargo extends AbstractCargo implements ITransportable {
	private double mass;

	public CompositeCargo() {
	}

	public CompositeCargo(String name, double mass) {
		super(name);
		this.mass = mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public int id() {
		return 3;
	}

	@Override
	public String toString() {
		return "CompositeCargo" + super.toString() + ";" + getMass();
	}

}
