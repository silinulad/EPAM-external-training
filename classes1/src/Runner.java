import by.gsu.epamlab.BusinessTrip;

public class Runner {

	public static void main(String[] args) {

		// 1
		BusinessTrip[] trips = { new BusinessTrip("Peter Ivanov", 305, 4), new BusinessTrip("Ivan Petrov", 70, 3),
				null, new BusinessTrip("Kolya Chief", 200, 6), new BusinessTrip() };

		// 2
		for (BusinessTrip trip : trips) {
			if (trip == null) {
				System.out.println("null \n");
				continue;
			}
			trip.show();
			System.out.println("");
		}

		// 3
		trips[trips.length - 1].setAccountEmployee("Vasily Chapaev");
		trips[trips.length - 1].setTransportExpenses(201);
		trips[trips.length - 1].setDayNumber(5);

		// 4
		System.out.println("The total duration of the trip the two employees = " + (int)(trips[0].getDayNumber() + trips[1].getDayNumber()) + " days \n");

		// 5
		for (BusinessTrip trip : trips) {
			System.out.println(trip);
		}
		
		
		
	}

}
