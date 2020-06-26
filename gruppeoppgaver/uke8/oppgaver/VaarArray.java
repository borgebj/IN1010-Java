import java.util.Iterator;

class VaarArray<T> implements Iterable<T> {
    T [] arr;

    public VaarArray(int strl) {
        arr = (T[]) new Object[strl];
    }

    //Finner ledig plass og setter inn. Hvis det er plass returneres true,
    //hvis det er fullt returneres false
    public boolean settInn(T elem) {

        // gaar gjennom hvert element
        for (int i=0; i < arr.length; i++) {

            // sjekker om indeks er element eller tom - legger inn om indeks er tom
            if (arr[i]==null) {
                arr[i] = elem;
                return true;
            } return false;
        }
    }

    //Returnerer true dersom det var mulig å fjerne, false ellers.
    public boolean fjernFraIndex(int n) {

        // om array er tomt
        if (arr.length <= n || n < 0) { return false; }

        // sjekker om posisjon er tom eller ikke - gjoer tom om ikke
        else if (arr[n] != null) {
            arr[n] = null;
            return true;
        } return false;
    }

    public Iterator<T> iterator();


    private class ArrayIterator implements Iterator<T>{

        int i = 0;

        public boolean hasNext() {

        }

        public T next() {
            T verdi = arr[i];
            i++;
            return verdi;
        }
    }
}