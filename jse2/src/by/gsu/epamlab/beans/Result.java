package by.gsu.epamlab.beans;

import java.sql.Date;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.utils.MarkInt;

public class Result {
	private String login;
	private String test;
	private Date date;
	private MarkInt mark;

	public Result() {
	}

	public Result(String login, String test, Date date, MarkInt mark) {
		this.login = login;
		this.test = test;
		this.date = date;
		this.mark = mark;
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

	public void setDate(Date date) {
		this.date = date;
	}

	public MarkInt getMark() {
		return mark;
	}

	public void setMark(MarkInt mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		final String DELIMITER = ";";
		return login + DELIMITER + test + DELIMITER + Constants.OUTPUT_DATE_FORMAT.format(date) + DELIMITER + mark;
	}
}
