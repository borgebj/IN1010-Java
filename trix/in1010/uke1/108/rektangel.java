
class Rektangel {

    double lengde;
    double bredde;

    public Rektangel (double l, double b) {// Konstruktør
        lengde = l;
        bredde = b;
    }

    public void oekLengde (int okning) {    // Oek lengden som angitt
        lengde = lengde + okning;
    }

    public void oekBredde (int okning) {    // Oek bredden som angitt
        bredde = bredde + okning;
    }

    public double areal () {     // Beregn mitt areal
        double areal = (lengde * bredde);
        return areal;
    }

    public double omkrets () {   // Beregn min omkrets
        double omkrets = (lengde * 2) + (bredde * 2);
        return omkrets;
    }

    public void redLengde (int redusering) {
        lengde = lengde - redusering;
    }

    public void redBredde (int redusering) {
        bredde = bredde - redusering;
    }
}

class Hovedprogram {
    public static void main(String[] arg){
        Rektangel objekt1 = new Rektangel(5.00, 2.50);
        Rektangel objekt2 = new Rektangel(6.00, 3.00);

        System.out.println("Areal Rektangel 1: " + objekt1.areal());
        System.out.println("Areal Rektangel 2: " + objekt2.areal());

        objekt1.oekLengde(7);
        objekt2.oekBredde(4);

        System.out.println("Omkrets Rektangel 1: " + objekt1.omkrets());
        System.out.println("Omkrets Rektangel 2: " + objekt2.omkrets());

        objekt1.redLengde(4);
        objekt2.redBredde(2);

        System.out.println("Ny omkrets Rektangel 1: " + objekt1.omkrets());
        System.out.println("Ny omkrets Rektangel 2: " + objekt2.omkrets());
    }
}