package by.gsu.epamlab.beans;

public class NumLen{
	private int length;
	private int number;

	public NumLen(int length, int number) {
		this.length = length;
		this.number = number;
	}

	public int getLenth() {
		return length;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return length + ";" + number;
	}
}
