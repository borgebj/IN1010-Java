
public class SpillPerson {

    String navn;
    String[] attributter;

    public SpillPerson(String navn, String[] attributter) {
        this.navn = navn;
        this.attributter = attributter;
    }

    public boolean contains(String attributt, SpillPerson person) {
        for (String denne : attributter) {
            if (denne.equals(attributt) && this != person)
                return true;
        } return false;
    }

    @Override
    public String toString(){
        return navn;
    }
}