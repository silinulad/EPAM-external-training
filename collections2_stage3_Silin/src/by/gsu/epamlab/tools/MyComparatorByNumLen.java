package by.gsu.epamlab.tools;

import java.util.Comparator;

import by.gsu.epamlab.beans.Segment;

public class MyComparatorByNumLen implements Comparator<Segment> {
    @Override
    public int compare(Segment o1, Segment o2) {
        return o2.getNumLen() - o1.getNumLen();
    }
}
