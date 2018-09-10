package by.gsu.epamlab.interfaces.implementations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.SAXException;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.interfaces.IResultCreatable;
import by.gsu.epamlab.xml.sax.SAXReader;

public class ResultFromXML implements IResultCreatable<Result> {
	private List<Result> results;
	private Iterator<Result> resultIterator;

	public ResultFromXML(final String FILE_NAME) throws IOException, SAXException {
		try {
			results = SAXReader.getListResults(FILE_NAME);
			resultIterator = results.iterator();
		} catch (FileNotFoundException e) {
			throw new IOException(e);
		}
	}

	public List<Result> getResults() {
		return results;
	}

	@Override
	public void closeFileSourse() {
		results.clear();
	}

	@Override
	public boolean hasLine() {
		return resultIterator != null && resultIterator.hasNext();
	}

	@Override
	public Result getResult() {
		return resultIterator.next();
	}
}
