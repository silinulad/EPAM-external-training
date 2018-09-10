import java.io.*;
import java.util.Scanner;

import epamlab.AbstractCorrector;
import epamlab.constants.Constants;
import epamlab.entities.ByrCorrector;
import epamlab.entities.DateCorrector;

/**
 * @author Silin Uladzislau
 *
 */
public class Runner {

	public static void main(String[] args) {

		AbstractCorrector dateCorrector = new DateCorrector(Constants.REGEXP_DATE);
		AbstractCorrector byrCorrector = new ByrCorrector(Constants.REGEXP_BYR);
		try (Scanner scanner = new Scanner(new File(Constants.FILE_IN));
				PrintWriter out = new PrintWriter(new FileWriter(new File(Constants.FILE_OUT)))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				line = byrCorrector.modifier(line);
				line = dateCorrector.modifier(line);
				out.println(line);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File is not found!");
		}
		catch (IOException e) {
			System.out.println("There is something wrong!");
		}
	}

}
