import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.gsu.epamlab.constants.Constants.*;

/**
 * @author Silin Uladzislau
 *
 */
public class Runner {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new FileReader(FILE_PATH))) {
			Map<Integer, Integer> mapNumLen = new HashMap<>();
			while (sc.hasNext()) {
				// get segment coordinates
				final String LINE = sc.nextLine();
				final String[] COORDINATE = LINE.split(REGEX);
				final double X1 = Double.parseDouble(COORDINATE[1]);
				final double Y1 = Double.parseDouble(COORDINATE[2]);
				final double X2 = Double.parseDouble(COORDINATE[3]);
				final double Y2 = Double.parseDouble(COORDINATE[4]);

				// calculate the segment length
				final int LENGTH = (int) Math.round(Math.sqrt(((X1 - X2) * (X1 - X2)) + ((Y1 - Y2) * (Y1 - Y2))));

				// form a map - len;num
				Integer value = mapNumLen.get(LENGTH);
				if (value != null) {
					value++;
				} else {
					value = 1;
				}
				mapNumLen.put(LENGTH, value);
			} 
			
			// create a list Map.Entry  from the map created above
			List<Map.Entry<Integer, Integer>> listEntry = new ArrayList<>(mapNumLen.entrySet());
			
			//sorting the list by value in descending order
			Collections.sort(listEntry, new Comparator<Map.Entry<Integer, Integer>>() {
				@Override
				public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});

			// print the listEntry
			for (Map.Entry<Integer, Integer> list : listEntry) {
				System.out.println(list.getKey() + DELIMETER + list.getValue());
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
