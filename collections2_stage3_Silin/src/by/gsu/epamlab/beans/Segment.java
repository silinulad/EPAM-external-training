package by.gsu.epamlab.beans;

public class Segment implements Comparable<Segment> {
	private static final String DELIMITER = ";";
	private final int length;
	private int numLen;

	public Segment(int length) {
		this.length = length;
		numLen = 1;
	}

	public int getLength() {
		return length;
	}

	public int getNumLen() {
		return numLen;
	}

	public void countLen() {
		numLen++;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + length;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (length != other.length)
			return false;
		other.numLen++;
		return true;
	}

	@Override
	public int compareTo(Segment o) {
		return length - o.length;
	}

	@Override
	public String toString() {
		return length + DELIMITER + numLen;
	}
}
