
public class SpillPerson {

    String navn;
    String[] attributter;

    int kolonne;
    int rad;

    public SpillPerson(String navn, String[] attributter, int kol, int rad) {
        this.navn = navn;
        this.attributter = attributter;
        this.kolonne = kol;
        this.rad = rad;
    }

    public boolean contains(String attributt, SpillPerson person) {
        for (String denne : attributter) {
            if (denne.equals(attributt.toLowerCase()) && this != person)
                return true;
        } return false;
    }

    public int hentKol() {
        return kolonne;
    }
    public int hentRad() {
        return rad;
    }
    public String hentNavn() { return navn; }

    @Override
    public String toString(){
        return navn;
    }
}