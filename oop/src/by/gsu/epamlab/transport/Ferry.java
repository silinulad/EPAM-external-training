package by.gsu.epamlab.transport;

import by.gsu.epamlab.interfaces.ITransportable;

public class Ferry {
	public static final double CARRYING = 20000.0;
	ITransportable[] transportable;

	public Ferry() {
	}

	public Ferry(ITransportable[] transportable) {
		this.transportable = transportable;
	}

	public ITransportable[] getTransportable() {
		return transportable;
	}

	public void setTransportable(ITransportable[] transportable) {
		this.transportable = transportable;
	}

	
}
