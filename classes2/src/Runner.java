import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {

	public static void main(String[] args) {

		Material steel = new Material("steel", 7850.0);

		Subject steelWire = new Subject("wire", steel, 0.03);

		System.out.println(steelWire);

		Material copper = new Material("copper", 8500.0);

		Subject copperWire = new Subject("wire", copper, 0.03);

		System.out.println("The copper wire mass is " + copperWire.getMass() + " kg.");

	}

}
