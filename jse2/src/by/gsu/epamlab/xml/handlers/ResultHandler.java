package by.gsu.epamlab.xml.handlers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.utils.MarkDec;

/**
 * @author Silin Uladzislau
 *
 */
public class ResultHandler extends DefaultHandler {
	private List<Result> results = new ArrayList<>();
	private TagEnum currentElement;
	private String login;

	private static enum TagEnum {
		RESULTS, STUDENT, LOGIN, TESTS, TEST;
	}

	private static enum AttributeEnum {
		NAME(0), DATE(1), MARK(2);

		private final int index;

		private AttributeEnum(int index) {
			this.index = index;
		}
	}

	public ResultHandler() {
	}

	public List<Result> getResults() {
		return results;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		currentElement = TagEnum.valueOf(localName.toUpperCase());
		if (currentElement == TagEnum.TEST) {
			Result result = new Result(login, attr.getValue(AttributeEnum.NAME.index),
					Date.valueOf(attr.getValue(AttributeEnum.DATE.index)),
					new MarkDec(attr.getValue(AttributeEnum.MARK.index)));
			results.add(result);
		}
	}

	private static boolean empty(final String str) {
		return str == null || str.trim().isEmpty();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentElement == TagEnum.LOGIN) {
			String login = new String(ch, start, length);
			if (!empty(login)) {
				this.login = login;
			}
		}
	}

}
