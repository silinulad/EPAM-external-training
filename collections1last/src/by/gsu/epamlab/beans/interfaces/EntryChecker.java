package by.gsu.epamlab.beans.interfaces;

import java.util.Map;

public interface EntryChecker<K,V> {
	boolean check(Map.Entry<K, V> entry);
}
