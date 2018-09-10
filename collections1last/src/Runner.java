import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import by.gsu.epamlab.beans.enums.WeekDay;
import by.gsu.epamlab.beans.factories.PurchaseFactory;
import by.gsu.epamlab.beans.interfaces.EntryChecker;
import by.gsu.epamlab.beans.purchases.PriceDiscountPurchase;
import by.gsu.epamlab.beans.purchases.Purchase;
import by.gsu.epamlab.finance.Byn;

import static by.gsu.epamlab.constants.Constants.*;

public class Runner{
	private static <K, V> void printMap(final Map<K, V> map, final String title) {
		System.out.println(NEW_LINE + title);
		for (final Map.Entry<K, V> pair : map.entrySet()) {
			System.out.println(pair.getKey() + EQUALLY + pair.getValue());
		}
	}

	// desired purchases
	private static Purchase bread_155 = new Purchase(NAME_PURCHASE, FIRST_DESIRED_PRICE, ZERO_NUMBER_PURCHASES);
	private static Purchase bread_170 = new Purchase(NAME_PURCHASE, SECOND_DESIRED_PRICE, ZERO_NUMBER_PURCHASES);

	private static <K, V> void search(Map<K, V> map, K key) {
		System.out.println(NEW_LINE + key + EQUALLY + map.get(key));
	}

	private static <K,V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
		Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			if (checker.check(iterator.next())) {
				iterator.remove();
			}	
		}
	}

	private static Byn getTotalCost(List<? extends Purchase> purchases) {
		Byn totalCost = new Byn(0);
		for (Purchase purchase : purchases) {
			totalCost = totalCost.add(purchase.getCost());
		}
		return totalCost;
	}

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(new FileReader(FILE_PATH))) {
			Map<Purchase, WeekDay> purhasesMapLast = new HashMap<>();
			Map<Purchase, WeekDay> purhasesMapFirst = new HashMap<>();
			List<PriceDiscountPurchase> discountPurchasesList = new ArrayList<>();
			Map<WeekDay, List<Purchase>> enumeratedMapByDay = new EnumMap<>(WeekDay.class);
			
			while (sc.hasNextLine()) {
				Purchase purchase = PurchaseFactory.getPurchaseFromFactory(sc);
				WeekDay weekDay = WeekDay.valueOf(sc.nextLine());

				// 1
				purhasesMapLast.put(purchase, weekDay);

				// 3
				if (!purhasesMapFirst.containsKey(purchase))
					purhasesMapFirst.put(purchase, weekDay);

				// 10
				if (purchase instanceof PriceDiscountPurchase) {
					discountPurchasesList.add((PriceDiscountPurchase) purchase);
				}

				// 12
				List<Purchase> daysList = enumeratedMapByDay.get(weekDay);
				if (daysList == null) {
					enumeratedMapByDay.put(weekDay, daysList = new ArrayList<>());
				}
				daysList.add(purchase);
			
			}

			// 2
			printMap(purhasesMapLast, TITLE_LAST_MAP);

			// 4
			printMap(purhasesMapFirst, TITLE_FIRST_MAP);

			// 5

			// finds the first weekday for bread with price 1.55;
			search(purhasesMapFirst, bread_155);

			// finds the last weekday for bread with price 1.55;
			search(purhasesMapLast, bread_155);

			// 6
			search(purhasesMapLast, bread_170);

			final String removedByKey = "meat";
			final WeekDay removedByValue = WeekDay.FRIDAY;

			// 7
			removeEntries(purhasesMapLast, new EntryChecker<Purchase, WeekDay>() {
				@Override
				public boolean check(Entry<Purchase, WeekDay> entry) {
					return entry.getKey().getName().equals(removedByKey);
				}
			});
			
			// 8
			removeEntries(purhasesMapFirst, new EntryChecker<Purchase, WeekDay>() {
				@Override
				public boolean check(Entry<Purchase, WeekDay> entry) {
					return entry.getValue().equals(removedByValue);
				}
			});

			// 9
			printMap(purhasesMapLast, TITLE_LAST_MAP);
			printMap(purhasesMapFirst, TITLE_FIRST_MAP);

			// 11
			final String MESSAGE = "PriceDiscountPurchase";
			final String TOTAL_COST = "Total cost of ";

			System.out.println(NEW_LINE + TOTAL_COST + MESSAGE + EQUALLY + getTotalCost(discountPurchasesList));

			// 13
			printMap(enumeratedMapByDay, TITLE_ENUM_MAP);

			// 14
			for (WeekDay key : enumeratedMapByDay.keySet()) {
				System.out.println(NEW_LINE + TOTAL_COST + key + EQUALLY + getTotalCost(enumeratedMapByDay.get(key)));
			}

			// 15
			search(enumeratedMapByDay, WeekDay.MONDAY);

		} catch (FileNotFoundException e) {
			System.err.println(ERROR_FILE_IS_NOT_FOUND);
		}

	


	}

}
