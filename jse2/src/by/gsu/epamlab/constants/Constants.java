package by.gsu.epamlab.constants;

import java.text.SimpleDateFormat;

public class Constants {
	public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	public static final String FILE_CSV = "src/results.csv";
	public static final String FILE_CSV_DEC = "src/results_dec.csv";
	public static final String FILE_XML = "src/results.xml";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URI = "jdbc:mysql://localhost/results";
	public static final String DB_LOGIN = "jse";
	public static final String DB_PASSWORD = "jse";
	
	//to obtain a mark in the range 0 ... hundreds
	public static final int MULTIPLIER = 10;
}
