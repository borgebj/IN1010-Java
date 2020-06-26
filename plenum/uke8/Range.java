// egen (range) klasse som i python

import java.lang.Iterable;
import java.util.Iterator;

public class Range implements Iterable<Integer> {

    int start;
    int slutt;
    int steg;

    public Iterator<Integer>iterator() {
        return new RangeIterator();
    }

    // indre klasse "RangeIterator"
    private class RangeIterator implements Iterator<Integer> {

        int i = start-steg;

        public boolean hasNext() {
            return i < slutt;
        }

        public Integer next() {
            i = i + steg;
            return i;
        }
    }

    // konstruktør hvis parameter har start, slutt og steg
    public Range(int start, int slutt, int steg) {
        this.start = start;
        this.slutt = slutt;
        this.steg = steg;
    }

    // konstruktør hvis parameter har bare start og slutt
    public Range(int start, int slutt) {
        this(start, slutt);
    }

    // konstruktr hvis parameter har bare slutt
    public Range(int slutt) {
        this(0, slutt);
    }
}
