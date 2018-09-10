package by.gsu.epamlab.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
	private String login;
	private String test;
	private Date date;
	private int mark;

	public Result() {
	}

	public Result(String login, String test, String dateStr, int mark) {
		this.login = login;
		this.test = test;
		setDate(dateStr);
		this.mark = mark;
	}

	public Result(String login, String test, String dateStr, String markStr) {
		this.login = login;
		this.test = test;
		setDate(dateStr);
		setMark(markStr);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(String dateStr) {
		final String DATE_FORMAT = "yyyy-mm-dd";
		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			final String ARGUMENT_ERROR = ": Illegal date format \"" + DATE_FORMAT + "\" within the XML file ";
			throw new IllegalArgumentException(ARGUMENT_ERROR + e);
		}
		this.date = date;
	}

	private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public String getStringDate() {
		return OUTPUT_DATE_FORMAT.format(date);
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	// convert the mark String to int value
	public void setMark(String markStr) {
		final String SPLITTER = "\\.";
		final int INDEX_BEFORE_SPLITTER = 0;
		final int INDEX_AFTER_SPLITTER = 1;
		String[] markSplitted = markStr.split(SPLITTER);
		int valueBefore = Integer.parseInt(markSplitted[INDEX_BEFORE_SPLITTER]);
		int valueAfter = Integer.parseInt(markSplitted[INDEX_AFTER_SPLITTER]);
		int markInt = valueBefore * 10 + valueAfter;
		this.mark = markInt;
	}

	// returns a mark as String
	public String getStringMark() {
		final String DELIMITER = ".";
		final int VALUE_BEFORE_SPLITTER = mark / 10;
		final int VALUE_AFTER_SPLITTER = mark % 10;
		StringBuilder markToString = new StringBuilder();
		markToString.append(VALUE_BEFORE_SPLITTER).append(DELIMITER).append(VALUE_AFTER_SPLITTER);
		return markToString.toString();
	}

	@Override
	public String toString() {
		final String DELIMITER = ";";
		return login + DELIMITER + test + DELIMITER + getStringDate() + DELIMITER + getStringMark();
	}

}
