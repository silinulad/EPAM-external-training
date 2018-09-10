/**
 * 
 */
package epamlab.entities;

import java.util.regex.Matcher;

import epamlab.AbstractCorrector;
import epamlab.constants.Constants;

/**
 * @author Silin Uladzislau
 *
 */
public class ByrCorrector extends AbstractCorrector {

	public ByrCorrector(String regex) {
		super(regex);
	}

	@Override
	protected String modificatorType(String subString, Matcher matcher) {
		String groupFirst = matcher.group(Constants.GROUP_FIRST_BYR);
		String space = matcher.group(Constants.GROUP_SPACE_BYR);
		String groupTail = matcher.group(Constants.GROUP_TAIL_BYR);
		String emptyString = Constants.EMPTY_STRING;
		return groupFirst.replaceAll(space, emptyString) + space + groupTail;
	}

}
