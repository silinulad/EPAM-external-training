package by.gsu.epamlab.interfaces.implementations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.NoSuchElementException;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.interfaces.IResultCreatable;
import by.gsu.epamlab.utils.MarkDec;
import by.gsu.epamlab.utils.MarkInt;

public class ResultFromCSV implements IResultCreatable<Result> {
	private BufferedReader scanner = null;
	private String lineFromFile;

	public ResultFromCSV(final String file) throws IOException {
		try {
			scanner = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new IOException(e);
		}
	}

	private static MarkInt getMarkFromString(String mark) {
		final String REGEX = "\\d+\\.\\d+";
		final MarkInt MARK;
		if (mark.matches(REGEX)) {
			MARK = new MarkDec(mark);
		} else {
			MARK = new MarkInt(mark);
		}
		return MARK;
	}

	private static Date convertDate(String dateStr) {
		java.util.Date date = null;
		try {
			date = Constants.OUTPUT_DATE_FORMAT.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		return new Date(date.getTime());
	}

	private enum FieldEnum {
		LOGIN(0), TEST(1), DATE(2), MARK(3);
		private int index;

		private FieldEnum(int index) {
			this.index = index;
		}
	}

	@Override
	public boolean hasLine() {
		boolean result = false;
		try {
			lineFromFile = scanner.readLine();
		} catch (IOException e) {
			throw new NoSuchElementException(e.getMessage());
		}
		if (lineFromFile != null) {
			result = true;
		}
		return result;
	}

	@Override
	public Result getResult(){
		final String SPLITTER = ";";
		String[] resultFields = lineFromFile.split(SPLITTER);
		final String LOGIN = resultFields[FieldEnum.LOGIN.index];
		final String TEST = resultFields[FieldEnum.TEST.index];
		final String DATE_STR = resultFields[FieldEnum.DATE.index];
		final String MARK_STR = resultFields[FieldEnum.MARK.index];
		final Date DATE = convertDate(DATE_STR);
		final MarkInt MARK = getMarkFromString(MARK_STR);
		Result result = new Result(LOGIN, TEST, DATE, MARK);
		return result;
	}

	@Override
	public void closeFileSourse() {
		if (scanner != null) {
			try {
				scanner.close();
			} catch (IOException e) {
				System.err.println("Resource closing problem: " + e);
			}
		}
	}
}
