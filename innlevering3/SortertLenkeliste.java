
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override // legger inn ny node og sorterer den inn i listen
    public void leggTil(T x) {

        for (int i=0; i < stoerrelse(); i++) {

            // hvis noden er mindre enn x
            if (hent(i).compareTo(x) > 0) {
                super.leggTil(i, x);
                return;
            }
        } super.leggTil(x);
    }

    @Override // fjerner siste element i listen med bruk av fjern-metoden fra Lenkeliste
    public T fjern() {
        return fjern(stoerrelse()-1);
    }


    @Override // overrider fra Lenkeliste og kaster unntak
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException("Denne operasjonen stottes ikke");
    }

    @Override // overrider fra Lenkeliste og kaster unntak
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException("Denne operasjonen stottes ikke");
    }
}


// 0 om likt
// - om argument er storre ( < 0) // om i er mindre enn x
// + om argument er mindre ( > 0) // om i er storre enn x

// x.compareTo(y)
// a < 0 - x er mindre enn y
// a = 0 - x er lik y
// a > 0 - x er større enn y

