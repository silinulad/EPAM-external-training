package by.gsu.epamlab.factories;

import java.io.IOException;

import org.xml.sax.SAXException;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.interfaces.IResultCreatable;
import by.gsu.epamlab.interfaces.implementations.ResultFromXML;
import by.gsu.epamlab.utils.MarkDec;
import by.gsu.epamlab.utils.MarkInt;

public class XMLResultFactory extends ResultFactory {
	
	@Override
	public MarkInt getMarkFromFactory(int mark) {
		return new MarkDec(mark);
	}

	@Override
	public IResultCreatable<Result> getResultDAOFromFactory(String fileName) throws IOException, SAXException {
		return new ResultFromXML(fileName);
	}

}
