
class OrdnetPar<K, V> implements Par {

    K nokkel;
    V verdi;

    public OrdnetPar(K nokkel, V verdi) {
        this.nokkel = nokkel;
        this.verdi = verdi;
    }

    public K hentNokkel() {
        return nokkel;
    }

    public V hentVerdi() {
        return verdi;
    }
}