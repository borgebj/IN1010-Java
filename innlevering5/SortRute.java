
public class SortRute extends Rute {

    public SortRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    // returner tegnet for "sort" rute
    public char tilTegn() {
        return '#';
    }

    @Override // basistilfelle: stopper rekursjon paa SortRute
    public void gaa(Rute forrige, String koordinater)  {
    }
}
