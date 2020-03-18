package obligFire;

class Stabel<T> extends Lenkeliste<T> {

    // legger til nytt element paa slutten
    public void leggPaa(T x) {

        // kaller paa leggTill fra Lenkeliste pga samme funksjon
        leggTil(x);
    }

    // tar av og returner element paa slutten
    public T taAv() {

        // kaller paa fjern fra lenkeliste som fjerner og returnerer innhold fra parameter
        // storrelsen paa listen-1 som parameter for indeksen til siste node
        return fjern(stoerrelse()-1);
    }
}