package by.gsu.epamlab.passengers;

import by.gsu.epamlab.interfaces.ITransportable;

public class Passenger implements ITransportable {
	private String name;
	private double mass;

	public Passenger() {
	}

	public Passenger(String name, double mass) {
		this.name = name;
		this.mass = mass;
	}

	@Override
	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int id() {
		return 1;
	}

	@Override
	public String toString() {
		return "Passenger" + name + ";" + getMass();
	}

}
