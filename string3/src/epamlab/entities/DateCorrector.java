/**
 * 
 */
package epamlab.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;

import epamlab.AbstractCorrector;
import epamlab.constants.Constants;

/**
 * @author Silin Uladzislau
 *
 */
public class DateCorrector extends AbstractCorrector {

	public DateCorrector(String regex) {
		super(regex);
	}

	@Override
	protected String modificatorType(String subString, Matcher matcher) {
		String delimiter = matcher.group(Constants.DELIMITER_GROUP_DATE);
		DateFormat dateFormat = new SimpleDateFormat(Constants.DAY_FORMAT_DATE + delimiter + Constants.MONTH_FORMAT_DATE
				+ delimiter + Constants.YEAR_FORMAT_DATE);
		Date date = null;
		try {
			date = dateFormat.parse(subString);
			dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
		}
		catch (ParseException e) {
			System.err.println("Error position: " + e.getErrorOffset());
		}
		return dateFormat.format(date);
	}

}
