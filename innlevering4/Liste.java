
interface Liste<T> extends Iterable<T> {
    int stoerrelse();
    void leggTil(int pos, T x);
    void leggTil(T x);
    void sett(int pos, T x);
    T hent(int pos);
    T fjern(int pos);
    T fjern();
    void hentAll(); // (13.04.20)
    void toem(); // (13.04.20)
}
