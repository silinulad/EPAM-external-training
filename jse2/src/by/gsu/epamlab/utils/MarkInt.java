package by.gsu.epamlab.utils;

import by.gsu.epamlab.constants.Constants;

/**
 * @author Silin Uladzislau
 *
 */
public class MarkInt implements Comparable<MarkInt> {
	private int mark;

	public MarkInt() {
	}

	public MarkInt(int mark) {
		this.mark = mark;
	}

	public MarkInt(String mark) {
		setMark(mark);
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	// converts a mark from String to int
	protected int markToNumber(String mark) {
		int value = (int) (Double.parseDouble(mark) * Constants.MULTIPLIER);
		return value;
	}

	// takes a String parameter
	public void setMark(String mark) {
		this.mark = markToNumber(mark);
	}

	@Override
	public int compareTo(MarkInt other) {
		return mark - other.mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + mark;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MarkInt))
			return false;
		MarkInt other = (MarkInt) obj;
		if (mark != other.mark)
			return false;
		return true;
	}

	protected String markToString() {
		// to obtain a mark in the range 0 ... tens
		return String.valueOf(mark / Constants.MULTIPLIER);
	}

	@Override
	public String toString() {

		return markToString();
	}
}
