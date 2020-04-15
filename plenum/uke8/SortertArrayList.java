import java.util.ArrayList;

class SortertArrayList<T extends Comparable<T>> extends ArrayList<T> implements Liste<T> {

    @Override
    public void leggTil(T x) {
        int stoerrelse = size();
        int index = 0;

        // for hvert element
        for (int i=0; i < stoerrelse; i++) {

            // om argument er storre enn "NaaNoden"
            if (get(i).compareTo(x) < 0) {
                index = i+1;
            }
        }
        add(index, x);
    }

    @Override
    public int stoerrelse() {
        return size();
    }

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException("");
    }
}