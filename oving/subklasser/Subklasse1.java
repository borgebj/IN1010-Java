// arv og subklasser - 29.01.20

// for sub-klasser
// hver klasse har hver sine ting
class Student {
    // felles variabler
    String navn;
    int tlf;

    // egne variabler
    String program;

    // lignende metode
    void skrivData() {
        System.out.println("Navn: " + navn);
        System.out.println("Telefon: " + tlfnr);
        System.out.println("Studieprogram: " + program);
    }

    // felles metode
    boolean gyldigTlf() {
        return tlf >= 10000000 && tlfnr <= 99999999;
    }

    // egen metode
    void byttProgram(String nytt) {
        program = nytt;
    }
}

class Ansatt {
    // felles variabler
    String navn;
    int tlf;

    // egne variabler
    int lonnsTrinn;
    int antTimer;

    // lignende metode
    void skrivData() {
        System.out.println("Navn: " + navn);
        System.out.println("Telefon: " + tlfnr);
        System.out.println("Lønnstrinn: " + lønnstrinn);
        System.out.println("Timer: " + antallTimer);
    }

    // felles metode
    boolean gyldigTlf() {
        return tlf >= 10000000 && tlfnr <= 99999999;
    }

    // egen metode
    void lonnsTillegg(int tillegg) {
        lonnsTrinn += tillegg;
    }
}


// etter sub-klasser
// person-klasse har alt som er felles i student og ansatt
class Person {
    // protected - gjor at alle subklasser kan se disse
    protected String navn;
    protected int tlfnr;

    boolean gyldigTlfnr() {
        return tlfnr >= 10000000 && tlfnr <= 99999999;
    }
}

// Disse har kun variabler og metoder som er ulike hverandre
class Student extends Person {
    protected String program;

    void byttProgram(String nytt) {
        program = nytt;
    }
}

class Ansatt extends Person {
    protected int lønnstrinn;
    protected int antallTimer;

    void lønnstillegg(int tillegg) {
        lønnstrinn += tillegg;
    }
}