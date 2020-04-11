
class HvitRute extends Rute {

    public HvitRute(int rad, int kolonne) {
        super(rad, kolonne);
    }
    // returner tegnet for "hvit" rute
    public char tilTegn() {
        return '.';
    }

    // returner fasle - fordi en hvit rute ikke er aapning med mindre den er av typen "Aapning"
    public boolean erAapning() {
        return false;
    }
}