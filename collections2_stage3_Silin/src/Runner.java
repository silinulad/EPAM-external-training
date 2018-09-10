import java.util.*;

import by.gsu.epamlab.beans.Segment;
import by.gsu.epamlab.tools.*;

public class Runner {

	private static final int TO_MS = 1_000_000;
	private static final String EX_TIME = "Execution time of ";
	private static final String EQUAL = "\t= ";
	private static final String TIME_UNIT = " ms.";

	// Testing execution time using ArrayList
	private static void exTimeOfArrayList(final List<Integer> lenArray) {
		long startTimer = System.nanoTime();
		List<Segment> lenList = new ArrayList<>();
		for (Integer number : lenArray) {
			Segment segment = new Segment(number);
			int index = Collections.binarySearch(lenList, segment);
			if (index >= 0) {
				lenList.get(index).countLen();
			} else {
				lenList.add(-index - 1, segment);
			}
		}
		lenList.sort(new MyComparatorByNumLen());
		long endTimer = (System.nanoTime() - startTimer) / TO_MS;
		System.out.println(EX_TIME + lenList.getClass().getSimpleName() + EQUAL + endTimer + TIME_UNIT);
	}

	// Testing execution time using TreeSet
	private static void exTimeOfTreeSet(final List<Integer> lenArray) {
		long startTimer = System.nanoTime();
		NavigableSet<Segment> treeSetSegments = new TreeSet<>(new Comparator<Segment>() {
			@Override
			public int compare(Segment o1, Segment o2) {
				int diff = o1.getLength() - o2.getLength();
				if (diff == 0 && o1 != o2) {
					o2.countLen();
				}
				return diff;
			}
		});

		for (Integer number : lenArray) {
			Segment segment = new Segment(number);
			treeSetSegments.add(segment);
		}
		List<Segment> segmentsList = new ArrayList<>(treeSetSegments);
		segmentsList.sort(new MyComparatorByNumLen());
		long endTimer = (System.nanoTime() - startTimer) / TO_MS;
		System.out.println(EX_TIME + treeSetSegments.getClass().getSimpleName() + EQUAL + endTimer + TIME_UNIT);
	}

	// Testing execution time using HashSet
	private static void exTimeOfHashSet(final List<Integer> lenArray) {
		long startTimer = System.nanoTime();
		Set<Segment> hashSetSegments = new HashSet<Segment>();

		for (Integer number : lenArray) {
			Segment segment = new Segment(number);
			hashSetSegments.add(segment);
		}
		List<Segment> segmentsList = new ArrayList<>(hashSetSegments);
		segmentsList.sort(new MyComparatorByNumLen());
		long endTimer = (System.nanoTime() - startTimer) / TO_MS;
		System.out.println(EX_TIME + hashSetSegments.getClass().getSimpleName() + EQUAL + endTimer + TIME_UNIT);
	}

	public static void main(String[] args) {
		final int LEN_NUMBERS = 100000;
		final int INITIAL_INDEX = LEN_NUMBERS / 100;
		final int SHUFFLE_LEN_NUM = INITIAL_INDEX * 60;

		// Testing execution time using an array of segments - a)
		List<Integer> lenArrayA = LenGenerator.createRandomLenArray(LEN_NUMBERS);
		final String MESSAGE_A = "\nAn array of segments - a) with array size " + lenArrayA.size() + ": \n";
		System.out.println(MESSAGE_A);
		exTimeOfArrayList(lenArrayA);
		exTimeOfTreeSet(lenArrayA);
		exTimeOfHashSet(lenArrayA);

		// Testing execution time using an array of segments - b
		List<Integer> lenArrayB = LenGenerator.createOrderedArrayAsc(LEN_NUMBERS);
		final String MESSAGE_B = "\nAn array of segments - b) with array size " + lenArrayB.size() + ": \n";
		System.out.println(MESSAGE_B);
		exTimeOfArrayList(lenArrayB);
		exTimeOfTreeSet(lenArrayB);
		exTimeOfHashSet(lenArrayB);

		// Testing execution time using an array of segments - c
		List<Integer> lenArrayC = LenGenerator.createOrderedArrayDesc(LEN_NUMBERS);
		final String MESSAGE_C = "\nAn array of segments - c) with array size " + lenArrayC.size() + ": \n";
		System.out.println(MESSAGE_C);
		exTimeOfArrayList(lenArrayC);
		exTimeOfTreeSet(lenArrayC);
		exTimeOfHashSet(lenArrayC);
	
		// Testing execution time using an array of segments - d
		List<Integer> lenArrayD = LenGenerator.createAlmostOrderedArrayAsc(LEN_NUMBERS, INITIAL_INDEX, SHUFFLE_LEN_NUM);
		final String MESSAGE_D = "\nAn array of segments - d) with array size " + lenArrayD.size() + ": \n";
		System.out.println(MESSAGE_D);
		exTimeOfArrayList(lenArrayD);
		exTimeOfTreeSet(lenArrayD);
		exTimeOfHashSet(lenArrayD);

		// Testing execution time using an array of segments - e
		List<Integer> lenArrayE = LenGenerator.createUniqueNumLen(LEN_NUMBERS);
		final String MESSAGE_E = "\nAn array of segments - e) with array size " + lenArrayE.size() + ": \n";
		System.out.println(MESSAGE_E);
		exTimeOfArrayList(lenArrayE);
		exTimeOfTreeSet(lenArrayE);
		exTimeOfHashSet(lenArrayE);
	}

}
