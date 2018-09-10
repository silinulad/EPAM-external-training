package by.gsu.epamlab.utils;

import by.gsu.epamlab.constants.Constants;

public class MarkDec extends MarkInt {

	public MarkDec() {
		super();
	}

	public MarkDec(int mark) {
		super(mark);
	}

	public MarkDec(String mark) {
		super(mark);
	}

	@Override
	protected String markToString() {
		final String DELIMITER = ".";
		final int VALUE_BEFORE_DELIMITER = getMark() / Constants.MULTIPLIER;
		final int VALUE_AFTER_DELIMITER = getMark() % Constants.MULTIPLIER;
		return VALUE_BEFORE_DELIMITER + DELIMITER + VALUE_AFTER_DELIMITER;
	}

}