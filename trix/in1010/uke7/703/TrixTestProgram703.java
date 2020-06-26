
class TrixDobbeltlenketListe<T> {

    TrixNode start;
    int storrelse = 0;

    public void settInn(T element) {
        TrixNode ny = new TrixNode(element);
        if (start == null) { start = ny; }
        else {

            TrixNode naa = start;
            while (naa.neste != null) {
                naa = naa.neste;
            }
            naa.neste = ny;
            ny.forrige = naa;
        }
        storrelse++;
    }

    // fjerner forste
    public T fjern() {
        if (start == null) return null;
        else {
            T fjern = start.innhold;
            start = start.neste;
            start.forrige = null;
            return fjern;
        }
    }

    public int hentStorrelse() {
        return storrelse;
    }

    public void skrivUt() {
        TrixNode naa = start;
        while (naa != null) {
            System.out.println(naa.innhold);
            naa = naa.neste;
        }
    }

    private class TrixNode {
        T innhold;
        TrixNode neste;
        TrixNode forrige;

        public TrixNode(T innhold) {
            this.innhold = innhold;
        }
    }
}

class TrixTestProgram703 {
    public static void main(String[] args) {
        TrixDobbeltlenketListe<String> dobbel = new TrixDobbeltlenketListe<String>();
        dobbel.settInn("en");
        dobbel.settInn("to");
        dobbel.settInn("tre");
        dobbel.settInn("fire");
        dobbel.settInn("fem");

        dobbel.skrivUt();
        System.out.println("\n");

        System.out.println("Fjernet: " + dobbel.fjern() );
        System.out.println("Fjernet: " + dobbel.fjern() +"\n");

        dobbel.skrivUt();
        System.out.println("\n");

        dobbel.settInn("atte");

        dobbel.skrivUt();

        System.out.println("\nListestorrelse: " + dobbel.hentStorrelse() );
    }
}