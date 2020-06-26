
public class HvitRute extends Rute {

    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    // returner tegnet for "hvit" rute
    public char tilTegn() {
        return '.';
    }
}