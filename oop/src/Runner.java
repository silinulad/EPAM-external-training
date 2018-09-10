import by.gsu.epamlab.cargos.CompositeCargo;
import by.gsu.epamlab.cargos.LiquidMaterial;
import by.gsu.epamlab.cargos.RectangularContainer;
import by.gsu.epamlab.cargos.RoundCistern;
import by.gsu.epamlab.cargos.SolidMaterial;
import by.gsu.epamlab.interfaces.ITransportable;
import by.gsu.epamlab.passengers.Passenger;
import by.gsu.epamlab.transport.Ferry;

public class Runner {

	public static void main(String[] args) {
		
		
	ITransportable[] heyOnFerry = {
			new Passenger("Jack", 78.5), 
			new Passenger("Vera", 65.2), 
			new RectangularContainer(new SolidMaterial("Sand", 2650)), 
			new CompositeCargo("Guitar",  5.5),
			new CompositeCargo("Pump", 123.7),
			new RectangularContainer(new SolidMaterial("Sugar", 1556.7)), 
			new RoundCistern(new LiquidMaterial("H2O5OH", 873.6))		
	};
		for (ITransportable tr : heyOnFerry){
			System.out.println(tr);
		}
	
	Ferry ferry = new Ferry(heyOnFerry);
		
		
	}

}
