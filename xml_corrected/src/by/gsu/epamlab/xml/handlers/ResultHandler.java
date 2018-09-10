package by.gsu.epamlab.xml.handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.gsu.epamlab.beans.Result;

/**
 * @author Silin Uladzislau
 *
 */
public class ResultHandler extends DefaultHandler {
	private List<Result> results;
	private Result result;
	private String login;

	private static enum XMLElement {
		NAME(0), DATE(1), MARK(2), RESULTS(3), STUDENT(4), LOGIN(5), TESTS(6), TEST(7);

		private final int index;

		private XMLElement(int index) {
			this.index = index;
		}
	}

	public ResultHandler() {
		results = new ArrayList<>();
	}

	public List<Result> getResults() {
		return results;
	}

	public Result getResult() {
		return result;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException, IllegalArgumentException {
		final XMLElement TAG = XMLElement.valueOf(localName.toUpperCase());
		if (TAG == XMLElement.TEST) {
			result = new Result();
			result.setLogin(login);
			result.setTest(attributes.getValue(XMLElement.NAME.index));
			result.setDate(attributes.getValue(XMLElement.DATE.index));
			result.setMark(attributes.getValue(XMLElement.MARK.index));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		final XMLElement TAG = XMLElement.valueOf(localName.toUpperCase());
		if (TAG == XMLElement.TEST) {
			results.add(result);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		final String COOL = "cool";
		final String CLEVER = "clever";
		final String LOGIN = new String(ch, start, length);
		if (COOL.equals(LOGIN) || CLEVER.equals(LOGIN)) {
			this.login = LOGIN;
		}
	}

}
