
abstract class Emballasje {

    // globale variabler
    int volum;  // cm^2
    String prodIndikator;

    public Emballasje(int volum, String prodIndikator) {
        this.volum = volum;
        this.prodIndikator = prodIndikator;
        System.out.println("Konstruktoeren til klassen [Emballasje] utfoeres");
    }

}

abstract class Papp extends Emballasje {

    int vekt;  // gram

    public Papp(int volum, String prodIndikator, int vekt) {
        super(volum, prodIndikator);
        this.vekt = vekt;
        System.out.println("Konstruktoeren til klassen [Papp] utfoeres"); // fjern?
    }
}

abstract class Plast extends Emballasje {

    public Plast(int volum, String prodIndikator) {
        super(volum, prodIndikator);
        System.out.println("Konstruktoeren til klassen [Plast] utfoeres"); // fjern?
    }
}

interface LitenPant {
    int pant = 100;  // statisk final pant 100 oere
}

class LitenFlaskeMedPant extends Plast implements LitenPant {

    // har statisk final-variabel fra LitenPant "Pant = 100"

    String returordningen;

    public LitenFlaskeMedPant(int volum, String prodIndikator, String returordningen) {
        super(volum, prodIndikator);
        this.returordningen = returordningen;
        System.out.println("Konstruktoeren til klassen [LitenFlaskeMedPant] utfoeres"); // fjern?
    }
}

class LitenNedbrytbarFlaskeMedPant extends Plast implements LitenPant {

    // har statisk final-variabel fra LitenPant "Pant = 100"

    String returordningen;

    int varighet;  // aar

    public LitenNedbrytbarFlaskeMedPant(int volum, String prodIndikator, String returordningen, int varighet) {
        super(volum, prodIndikator);
        this.returordningen = returordningen;
        this.varighet = varighet;
        System.out.println("Konstruktoeren til klassen [LitenNedbrytbarFlaskeMedPant] utfoeres"); // fjern?
    }
}

class StorNedbrytbarFlaskeMedPant extends Papp {

    // har statisk final-variabel fra LitenPant "Pant = 100"

    String returordningen;
    int varighet;  // aar
    int pant;

    public StorNedbrytbarFlaskeMedPant(int volum, String prodIndikator, int vekt, String returordningen, int varighet, int pant) {
        super(volum, prodIndikator, vekt);
        this.returordningen = returordningen;
        this.varighet = varighet;
        this.pant = pant;
        System.out.println("Konstruktoeren til klassen [StorNedbrytbarFlaskeMedPant] utfoeres"); // fjern?
    }
}

// main-klassen
public class BrukPant {
    public static void main(String[] args) {

        LitenNedbrytbarFlaskeMedPant flaskeEn = new LitenNedbrytbarFlaskeMedPant(50, "Cola", "returodrning", 5);

        System.out.println("\n");

        LitenFlaskeMedPant flaskeTo = new LitenFlaskeMedPant(50, "Fanta", "returodrning");

        System.out.println("\n");

        StorNedbrytbarFlaskeMedPant flasketre = new StorNedbrytbarFlaskeMedPant(150, "Melk", 1600, "returodrning", 2, 300);

        // jeg har skrevet "returordrning" paa returodrning fordi jeg ikke skjoenner hva det er, og kan derfor ikke komme ned noen eksempler.

    }
}

