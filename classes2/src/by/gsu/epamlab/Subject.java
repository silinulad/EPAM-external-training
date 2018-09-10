package by.gsu.epamlab;

import by.gsu.epamlab.Material;

public class Subject {
	private String name;
	private Material material;
	private double volume;

	public Subject() {
		super();
	}

	public Subject(String name, Material material, double volume) {
		super();
		this.name = name;
		this.material = material;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getMass() {

		return material.getDensity() * volume;
	}

	@Override
	public String toString() {
		return name + ";" + material + ";" + volume + ";" + getMass();
	}

}
