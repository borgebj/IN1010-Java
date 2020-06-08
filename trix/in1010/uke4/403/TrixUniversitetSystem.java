import java.util.ArrayList;

abstract class Person {

    String navn;
    int alder;

    Person(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }
}

abstract class Ansatt extends Person {

    int timelonn;

    Ansatt(String navn, int alder, int timelonn) {
        super(navn, alder);
        this.timelonn = timelonn;
    }
}

abstract class Student extends Person {

    String kurs;
    String laerested;

    Student(String navn, int alder, String kurs, String laerested) {
        super(navn, alder);
        this.kurs = kurs;
        this.laerested = laerested;
    }

    abstract public boolean harTilgang();

    @Override
    public String toString() {
        return "[ " + navn + ", " + alder + "aar gammel | Tar kurset " + kurs + " paa " + laerested + " ]";
    }
}

class BachelorStudent extends Student {

    BachelorStudent(String navn, int alder, String kurs, String laerested) {
        super(navn, alder, kurs, laerested);
    }

    @Override
    public boolean harTilgang() {
        return false;
    }

}

class MasterStudent extends Student {

    MasterStudent(String navn, int alder, String kurs, String laerested) {
        super(navn, alder, kurs, laerested);
    }

    @Override
    public boolean harTilgang() { return true; }

}

class Forsker extends Ansatt {

    String fagomraade;

    Forsker(String navn, int alder, int timelonn, String fagomraade) {
        super(navn, alder, timelonn);
        this.fagomraade = fagomraade;
    }

    @Override
    public String toString() {
        return "[ " + navn + ", " + alder + "aar gammel med " + timelonn + "kr timelonn paa fagomraadet " + fagomraade + " ]";
    }
}

class Professor extends Ansatt {

    String fagomraade;
    String kurs;

    Professor(String navn, int alder, int timelonn, String fagomraade, String kurs) {
        super(navn, alder, timelonn);
        this.fagomraade = fagomraade;
        this.kurs = kurs;
    }

    @Override
    public String toString() {
        return "[ " + navn + ", " + alder + "aar gammel med " + timelonn + "kr timelonn paa fagomraadet " + fagomraade + " og har kurset " + kurs + " ]";
    }
}


class TrixUniversitetSystem {
    public static void main(String[] args) {

        ArrayList<Person> personer = new ArrayList<Person>();

        Forsker brett = new Forsker("Brett", 88, 400, "Biologi?"); personer.add(brett);
        Professor geir = new Professor("Geir", 22, 120, "Informatikk", "IN1010"); personer.add(geir);
        BachelorStudent bruh = new BachelorStudent("Bruh", 15, "IN1030", "UIO"); personer.add(bruh);
        MasterStudent bruh2 = new MasterStudent("Bruh2", 16, "IN1060", "UIO"); personer.add(bruh2);


        System.out.println();
        for (Object p : personer) {
            System.out.println( p );
        }

    }
}