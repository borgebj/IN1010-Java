
// fra 701 brukt for testing
class TrixKatt implements Comparable<TrixKatt> {

    String navn;
    int alder;

    public TrixKatt(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    @Override
    public String toString() {
        return navn + " - " +alder+ " aar gammel";
    }

    @Override
    public int compareTo(TrixKatt k) {
        if (k.alder > alder) { return -1; }
        else  if (k.alder < alder) { return 1; }
        return 0;
    }
}

class GraadigBeholder<T extends Comparable<T>> {

    T innhold;

    public T settInn(T ny) {
        if (innhold == null) {
            innhold = ny;
            return null;
        }
        else if (ny.compareTo(innhold) >= 0) {
            T prev = innhold;
            innhold = ny;
            return prev;
        }
        else return null;
    }

    public T hentInnhold() {
        return innhold;
    }
}


class TrixTestProgram702 {
    public static void main(String[] args) {

        GraadigBeholder<Integer> beholder = new GraadigBeholder<Integer>();

        beholder.settInn(2);
        System.out.println( beholder.hentInnhold() );

        beholder.settInn(2);
        System.out.println( beholder.hentInnhold() );

        beholder.settInn(6);
        System.out.println( beholder.hentInnhold() );

        beholder.settInn(4);
        System.out.println( beholder.hentInnhold() );

        System.out.println("--------------------------------------------");

        GraadigBeholder<TrixKatt> katter = new GraadigBeholder<TrixKatt>();

        katter.settInn(new TrixKatt("Alexander", 5));
        System.out.println( katter.hentInnhold() );

        katter.settInn(new TrixKatt("Brunost", 5));
        System.out.println( katter.hentInnhold() );

        katter.settInn(new TrixKatt("Orne", 9));
        System.out.println( katter.hentInnhold() );

        katter.settInn(new TrixKatt("Baaa", 2));
        System.out.println( katter.hentInnhold() );

    }
}