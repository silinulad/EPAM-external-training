package by.gsu.epamlab;

public enum Rounding {
	MATH_ROUND {
		double roundFunction(double value) {
			return Math.round(value);
		}
	},
	FLOOR {
		double roundFunction(double value) {
			return Math.floor(value);
		}
	},
	CEIL {
		double roundFunction(double value) {
			return Math.ceil(value);
		}
	};
	abstract double roundFunction(double value);

	public int roundTo(double value, int d) {
		int[] tenPow = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
		int result = (int) roundFunction(roundFunction(value / tenPow[d]) * tenPow[d]);
		return result;
	}

}
