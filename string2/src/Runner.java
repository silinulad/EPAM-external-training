import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Runner {

	public static void main(String[] args) {
		final String RESOURCE = "in";

		double sum = 0.0;
		int numLineError = 0;

		ResourceBundle rb = ResourceBundle.getBundle(RESOURCE);
		Enumeration<String> keys = rb.getKeys();

		while (keys.hasMoreElements()) {
			final String KEY = keys.nextElement().trim();
			final String KEY_VALUE = rb.getString(KEY).trim();
			final String VALUE = "value";
			final String REGEXP_INDEX = "^(index)([a-z0-9]*)$";
			final String REGEXP_KEY_VALUE = "^([1-9])([0-9]*)$";

			Pattern patternIndex = Pattern.compile(REGEXP_INDEX);
			Matcher matcherIndex = patternIndex.matcher(KEY);

			Pattern patternKeyValue = Pattern.compile(REGEXP_KEY_VALUE);
			Matcher matcherKeyValue = patternKeyValue.matcher(KEY_VALUE);

			try {
				// check the keys beginning with "index" + any characters
				if (matcherIndex.matches()) {

					// take out a substring excluding the "index"
					String indexI = matcherIndex.group(2);
					int i = 0;

					// check for no leading zeros within line - indexI
					if (Pattern.matches("^([^0]+)([a-z0-9])*$", indexI)) {
						i = Integer.parseInt(indexI);
					}
					// check the key value
					if (matcherKeyValue.matches()) {
						String indexJ = KEY_VALUE;
						String valueIJ = VALUE + i + indexJ;
						Double keyValue = Double.parseDouble(rb.getString(valueIJ));
						sum += keyValue;
					} else {
						numLineError++;
						System.err.println("er-else");
					}

				}

			} catch (NumberFormatException | IllegalStateException | StringIndexOutOfBoundsException
					| MissingResourceException | PatternSyntaxException e) {
				numLineError++;
				System.err.println("er-throw");
			}

		}
		System.out.println("sum = " + sum + "\nerror-lines = " + numLineError);
	}

}
