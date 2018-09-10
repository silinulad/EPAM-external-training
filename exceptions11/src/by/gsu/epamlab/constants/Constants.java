package by.gsu.epamlab.constants;

/**
 * @author 102334
 *
 */
public final class Constants {
	public static final String FILE_EXTENSION = ".csv";
	public static final String PREVIOUS_FILE_PATH = "src/";
	public static final String DELIMETER = ";";
	public static final String NAME_PACKAGE_COMPARATORS = "by.gsu.epamlab.comparators.";
	public static final String ERROR_WRONG_NUMBER = "wrong number of arguments!";
	public static final String ERROR_FORMAT_NUMBER = "wrong format of arguments";
	public static final String ERROR_NULL_NAME = "null name ";
	public static final String ERROR_EMPTY_NAME = "empty name ";
	public static final String ERROR_SUBZERO = "non positive value ";
	public static final String ERROR_EXCEPTION_DELIMETER = "wrong delimeter ";
	public static final String ERROR_CSV_NOT_FOUND = "csv file is not found ";
	public static final String ERROR_WRONG_INDEX = "wrong index ";
	public static final String ERROR_MISSING_ELEMENT = "missing element at the position ";
	public static final String ERROR_TAB = "\t-> ";
	public static final int NAME_INDEX = 0;
	public static final int PRICE_INDEX = 1;
	public static final int NUMBER_PURCHASES_INDEX = 2;
	public static final int PRICE_DISCOUNT_INDEX = 3;
	public static final int CSV_FIELDS_NUMBER = 3;
	public static final int CSV_FIELDS_NUMBER_DISCOUNT = 4;
	public static final int MAIN_FILE_INDEX = 0;
	public static final int ADDON_FILE_INDEX = 1;
	public static final int COMPARATOR_FILE_INDEX = 2;

	// constants for comparators
	public final static int EQUAL_RESULT = 0;
	public final static int NEGATIVE_RESULT = -1;
	public final static int POSITIVE_RESULT = 1;

	// constants for the PurchasesList class
	public final static int INITIAL_INDEX = 0;
	public final static int FAIL_INDEX = -1;
	public final static String CLASS_WITHOUT_DISCOUNT = "-";
	public final static String FOUND_PURCHASE_POSITION = " is found at position ";
	public final static String NOT_FOUND_PURCHASE_POSITION = " is not found";
	public final static String TITLE_PATTERN = "\n%s\t\t%s\t%s\t%s\t%s\n";
	public final static String TABLE_PATTERN = "%s\t\t%5s\t%6d\t%8s\t%5s\n";
	public final static String TOTAL_COST_PATTERN = "\nTotal cost\t\t\t\t\t%s\n";
	
	//fields of purchase classes
	public static final String PURCHASE = "Purchase ";
	public static final String NAME = "Name";
	public static final String PRICE = "Price";
	public static final String NUMBER = "Number";
	public static final String DISCOUNT = "Discount";
	public static final String COST = "Cost";

}
