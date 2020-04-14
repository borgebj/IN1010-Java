
class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    // metode som returner true - fordi rute er aapning
    public boolean erAapning() {
        return true;
    }
}