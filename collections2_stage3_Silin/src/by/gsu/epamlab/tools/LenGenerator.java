package by.gsu.epamlab.tools;

import java.util.*;

public class LenGenerator {
	private LenGenerator() {
	}

	// a
	public static List<Integer> createRandomLenArray(int genNumbers) {
		ArrayList<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < genNumbers; i++) {
			list.add(random.nextInt(genNumbers));
		}
		return list;
	}

	// b
	public static List<Integer> createOrderedArrayAsc(int genNumbers) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < genNumbers; i++) {
			list.add(i);
		}
		return list;
	}

	// c
	public static List<Integer> createOrderedArrayDesc(int genNumbers) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < genNumbers; i++) {
			list.add(i);
		}
		Collections.reverse(list);
		return list;
	}

	// d
	public static List<Integer> createAlmostOrderedArrayAsc(int genNumbers, int index, int shuffleNumbers) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < genNumbers; i++) {
			list.add(i);
		}
		// Shuffle part of list
		List<Integer> subList = list.subList(index, index + shuffleNumbers);
		Collections.shuffle(subList);
		return list;
	}

	// e
	public static List<Integer> createUniqueNumLen(int uniqueNumbers) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= uniqueNumbers; i++) {
			for (int n = 0; n < i; n++) {
				if(list.size() == 100_000)
					break;
				list.add(i);
			}
		}
		//Collections.shuffle(list); // Random order
		Collections.reverse(list); // ordered
		return list;
	}
}
