import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		final String FILE_PATH = "src/in.csv";
		final String SEPARATOR = ";";
		final String MINUS = " - ";
		final String PLUS = " + ";
		final int PLUS_LENGTH = PLUS.length();

		String line = "";
		Scanner sc = null;
		double sum = 0.0;
		int numLine = 0;

		StringBuilder resultSumString = new StringBuilder();
		StringBuilder outputString = new StringBuilder("result(");

		try {
			sc = new Scanner(new FileReader(FILE_PATH));
			sc.useLocale(Locale.ENGLISH);

			while (sc.hasNextLine()) {

				int initialIndex = 0;
				line = sc.nextLine();
				double summand = 0;
				String[] lines = line.split(SEPARATOR);
				int firstElement = 0;

				try {
					firstElement = Integer.parseInt(lines[initialIndex]);
					summand = Double.parseDouble(lines[firstElement]);
					sum += summand;
					double summandAbs = Math.abs(summand);
					resultSumString.append(summand < 0 ? MINUS : PLUS).append(summandAbs);

				}
				catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
					numLine++;
				}

			}

			String line1 = resultSumString.toString();
			if (line1.length() > 0) {
				if (line1.substring(0, PLUS_LENGTH).equals(PLUS)) {
					line1 = line1.substring(PLUS_LENGTH);
				} else {
					line1 = MINUS.trim() + line1.substring(PLUS_LENGTH);
				}
			}

			outputString.append(line1).append(") = ").append(sum).append("\nerror-lines = ").append(numLine);

			System.out.println(outputString);

		}
		catch (FileNotFoundException e) {
			System.err.println("Input file is not found!");

		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}

	}

}
