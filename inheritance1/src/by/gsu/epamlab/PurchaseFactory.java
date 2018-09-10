package by.gsu.epamlab;

import java.util.Scanner;

public class PurchaseFactory {

	private static enum PurchaseKind {
		PURCHASE {
			Purchase getPurchase(Scanner sc) {
				return new Purchase(sc);
			}
		},
		DISCOUNT_PURCHASE {
			Purchase getPurchase(Scanner sc) {
				return new DiscountPurchase(sc);
			}
		},
		PERCENT_DISCOUNT_PURCHASE {
			Purchase getPurchase(Scanner sc) {
				return new PercentDiscountPurchase(sc);
			}

		};

		abstract Purchase getPurchase(Scanner sc);
	}

	public static Purchase getPurchaseFromFactory(Scanner sc) {
		String id = sc.next();
		return PurchaseKind.valueOf(id).getPurchase(sc);
	}
}
