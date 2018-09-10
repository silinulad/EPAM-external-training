package by.gsu.epamlab;

import java.util.Locale;

public final class Utils {
	public static final String format(double cost) {
		return String.format(Locale.ENGLISH, "%.3f", cost / 100);
	}

	public static final String convert(int sum) {
		return sum / 100 + "." + sum % 100 / 10 + sum % 10;
	}

}
