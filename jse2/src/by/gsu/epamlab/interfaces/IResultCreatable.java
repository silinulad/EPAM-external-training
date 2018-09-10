package by.gsu.epamlab.interfaces;

import java.text.ParseException;

import by.gsu.epamlab.beans.Result;

public interface IResultCreatable<T extends Result> {
	void closeFileSourse();
	boolean hasLine();
	Result getResult() throws ParseException;
}
