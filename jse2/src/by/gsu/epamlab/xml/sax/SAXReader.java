package by.gsu.epamlab.xml.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.xml.handlers.ResultHandler;

public class SAXReader {

	private SAXReader() {
	}

	public static List<Result> getListResults(final String FILE_NAME) throws SAXException, IOException {
		XMLReader readerXML = null;
		ResultHandler handler = null;
		List<Result> results = null;

		readerXML = XMLReaderFactory.createXMLReader();
		handler = new ResultHandler();
		readerXML.setContentHandler(handler);
		readerXML.parse(FILE_NAME);
		results = handler.getResults();

		return results;
	}
}
