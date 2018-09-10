package by.gsu.epamlab.factories;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;

import org.xml.sax.SAXException;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.interfaces.IResultCreatable;
import by.gsu.epamlab.interfaces.implementations.ResultFromCSV;
import by.gsu.epamlab.utils.MarkInt;

public class ResultFactory {

	public ResultFactory() {
	}

	public Result getResultFromFactory(String login, String test, Date date, MarkInt mark) {
		return new Result(login, test, date, mark);
	}

	public MarkInt getMarkFromFactory(int mark) {
		return new MarkInt(mark);
	}

	public IResultCreatable<Result> getResultDAOFromFactory(final String fileName) throws FileNotFoundException, IOException, SAXException {
		return new ResultFromCSV(fileName);
	}

}
