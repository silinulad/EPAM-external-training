package epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Silin Uladzislau
 *
 */
public abstract class AbstractCorrector {

	private final String	regex;
	private final Pattern	pattern;

	public AbstractCorrector(String regex) {
		this.regex = regex;
		pattern = Pattern.compile(regex);
	}

	/**
	 * @return the regular expression
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @return the pattern is compiled using the regular expression
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @param the
	 *            string to be adjusted
	 * @return the adjusted string
	 */
	public String modifier(String modifiableString) {
		Matcher dataMatcher = getPattern().matcher(modifiableString);
		while (dataMatcher.find()) {
			String subString = dataMatcher.group();
			modifiableString = modifiableString.replace(subString, modificatorType(subString, dataMatcher));
		}
		return modifiableString;
	}

	protected abstract String modificatorType(String subString, Matcher matcher);
}
