import java.util.Iterator;


public class Lenkeliste<T> implements Liste<T>  {

    // node-klasse som skal lenkes sammen i listen
    protected class Node {

        // variabel som holder styr paa neste node og dataen den inneholder
        protected Node neste;
        protected T innhold;

        // konstruktor som gir data fra parameter til instansvariabel
        Node(T data) {
            innhold = data;
        }
    }

    // instansvariabel: Forste node i listen
    protected Node forste;


    // indre klasse "LenkelisteIterator" brukt for aa iterere gjennom Lenkeliste
    public class LenkelisteIterator implements Iterator<T> {

        Node current = forste;

        // sjekker om lenkelisten har en til node
        public boolean hasNext() {

            // returner om neste finnes eller ikke
            return current != null;
        }

        // sjekker og returner om det finnes en neste node (returner data fra noden)
        public T next(){

            // om neste node finnes, return data
            if (hasNext()){
                T data = current.innhold;
                current = current.neste;
                return data;
            }
            // om neste er tom
            else {
                return null;
            }
        }

        // metode som kaster unntak
        public void remove(){
            throw new UnsupportedOperationException("Remove har ikke blitt implementert");
        }
    }

    // Metode som returner nytt LenkelisteIterator-objekt
    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }


    @Override // legger inn ny nod paa slutten
    public void leggTil(T x) {

        // om listen er tom, legges inn ny node med innhold fra parameter
        if (forste==null) { forste = new Node(x); }
        else {

            // ellers itereres til slutten av lenken
            Node naaNode = forste;
            while (naaNode.neste != null) {
                naaNode = naaNode.neste;
            }
            // siste node peker til ny node med innhold fra parameter
            naaNode.neste = new Node(x);
        }
    }

    @Override // legger til nytt element til posisjon fra parameter
    public void leggTil(int pos, T x) {

        if (pos < 0 || pos > stoerrelse()) { throw new UgyldigListeIndeks(pos); }
        else {
            // Node-variabler for iterering og for ny node blir lagd
            Node naaNode = forste;
            Node nyNode = new Node(x);

            // om posisjonen er indeks 0
            if (pos==0) {
                nyNode.neste = forste;  // forste node peker saa til ny Node
                forste = nyNode;         // forste node i lenken blir deretter ny Node
            }
            else { // ellers itereres til noden og ny node blir satt inn
                for (int i = 0; i < pos-1; i++) {
                    naaNode = naaNode.neste;
                }
                nyNode.neste = naaNode.neste;  // Ny node peker paa naaNoden sin neste, og
                naaNode.neste = nyNode;       // naaNoden sin neste peker saa til ny Node
            }
        }
    }

    @Override // fjerner og returner innhold paa starten
    public T fjern() {

        // er listen tom kommer feilmelding med -1
        if (stoerrelse()==0) { throw new UgyldigListeIndeks(-1); }
        else {
            T verdi = forste.innhold;  // innhold til forste blir lagret
            forste = forste.neste;  // forste sin neste blir nye forste
            return verdi;
        }
    }

    @Override  // fjerner en node paa en gitt posisjon fra parameter og returner innholdet
    public T fjern(int pos) {
        if (pos<0 || pos >= stoerrelse() || stoerrelse()==0) { throw new UgyldigListeIndeks(pos); }

        Node naaNode = forste;
        for (int i=0; i < pos-1; i++) {
            naaNode = naaNode.neste;
        }
        if (pos==0) {
            T verdi = forste.innhold;
            forste = forste.neste;
            return verdi;
        }
        // lagrer noden vi vil fjerne
        Node nodeVekk = naaNode.neste;

        // Vi linker sammen noden for og etter den vi vil fjerne med .neste
        naaNode.neste = nodeVekk.neste;
        return nodeVekk.innhold;
    }

    @Override // fjerner objekt fra parameter om den eksisterer (04.05.20)
    public void fjernInnhold(T x) {
        int counter = 0;
        for (T object : this) {
            if (object.equals(x)) {
                fjern(counter);
            } counter++;
        }
    }

    // lokal counter brukt for storrelse til lenkelisten for mer ryddig kode og mindre linjer
    @Override // returnerer instansvariabel som tar vare paa antall noder
    public int stoerrelse() {
        int counter = 0;
        Node naaNode = forste;
        while (naaNode != null) {
            counter++;
            naaNode = naaNode.neste;
        } return counter;
    }

    @Override // bytter ut element fra parameter med node i gitt posisjon
    public void sett(int pos, T x) {

        // feilmelding om indeks er ugyldig eller listen er tom
        if (pos < 0 || pos >= stoerrelse() || stoerrelse()==0) { throw new UgyldigListeIndeks(pos); }

        Node naaNode = forste;
        for (int i=0; i < pos; i++) {
            naaNode = naaNode.neste;
        }
        naaNode.innhold = x;
    }

    @Override // blar gjennom listen og returner innhold paa posisjon fra parameter
    public T hent(int pos) {

        if (pos < 0 || pos >= stoerrelse() || stoerrelse()==0) { throw new UgyldigListeIndeks(pos); }

        // itererer til gitt posisjon og returner innholdet
        Node naaNode = forste;
        for (int i=0; i < pos; i++) {
            naaNode = naaNode.neste;
        }
        return naaNode.innhold;
    }


    @Override // toemmer lenkelisten (13.04.20)
    public void toem() {
        forste = null;
    }

    @Override // sjekker om parameter finnes i listen (04.05.20) - lik "contains" metoden
    public boolean inneholder(T x) {
        for (T object : this) {
            if (object.equals(x))
                return true;
        } return false;
    }

    @Override // test metode som printer ut innhold i hver node
    public void hentAll() {
        Node naa = forste;

        while(naa != null) {
            System.out.print(naa.innhold + "\n");
            naa = naa.neste;
        }
    }
}