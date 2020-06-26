
class Aapning extends HvitRute {
    public Aapning(int rad, int kolonne) {
        super(rad, kolonne);
    }

    @Override // basistilfelle: vha polymorfi stopper rekursjonen i en aapning-rute
    public void gaa(Rute denne, String koordinater) {
        koordinater += mineKoordinater;
        koordinater += " (Thread som fant: " + Thread.currentThread() + ")";
        minLabyrint.utveier.leggTil(koordinater);
    }
}