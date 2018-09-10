/**
 * 
 */
package epamlab.constants;

/**
 * @author Silin Uladzislau
 *
 */
public final class Constants {

	public static final String	FILE_IN					= "src\\in.txt";
	public static final String	FILE_OUT				= "src\\out.txt";

	// constants for the ByrCorrector class
	public static final String	REGEXP_BYR				= "(\\d{1,3}(\\s)+(\\d{3}\\2+)*)\\b(blr|belarusian roubles)";
	public static final int		GROUP_FIRST_BYR			= 1;
	public static final int		GROUP_SPACE_BYR			= 2;
	public static final int		GROUP_NEXT_DIGITS_BYR	= 3;
	public static final int		GROUP_TAIL_BYR			= 4;
	public static final String	EMPTY_STRING			= "";

	// constants for the DateCorrector class
	public static final String	REGEXP_DATE				= "\\b(0?[1-9]|[12]?\\d|3[01])([./-])(0?[1-9]|1?[012])\\2(\\d\\d){1,2}\\b";
	public static final int		DAY_GROUP_DATE			= 1;
	public static final int		DELIMITER_GROUP_DATE	= 2;
	public static final int		MONTH_GROUP_DATE		= 3;
	public static final int		YEAR_GROUP_DATE			= 4;
	public static final String	DAY_FORMAT_DATE			= "d";
	public static final String	MONTH_FORMAT_DATE		= "M";
	public static final String	YEAR_FORMAT_DATE		= "y";

}
