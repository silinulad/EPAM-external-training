package by.gsu.epamlab.exceptions;

import java.util.Locale;

import by.gsu.epamlab.constants.Constants;

/**
 * @author 102334
 *
 */
public class CsvLineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String scvLine;

	public CsvLineException() {
		super();
	}

	/**
	 * @param exception
	 * @param scvLine
	 */
	public CsvLineException(Exception exception, String scvLine) {
		super(exception);
		this.scvLine = scvLine;
	}

	/**
	 * @param cause
	 * @param scvLine
	 */
	public CsvLineException(String cause, String scvLine) {
		super(cause);
		this.scvLine = scvLine;
	}

	/**
	 * @return the scvLine
	 */
	public String getScvLine() {
		return scvLine;
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH, "%.20s", scvLine) + Constants.ERROR_TAB + getMessage();
	}

	

}
