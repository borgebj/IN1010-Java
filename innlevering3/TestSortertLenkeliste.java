public class TestSortertLenkeliste {
    private static int antallTester = 0;
    private static int antallPasserte = 0;
    private static int antallFeil = 0;

    public static void main(String[] args) {
        testTom();
        testEttElement();
        testLeggTilOgFjerningAvFlereElementer();
        testLeggTilMedIntegers();
        testFjernMedIndeks();
        testUnntak();
        System.out.println("\n"+ antallTester + " tester ferdig");
        System.out.println(antallPasserte + " passerte, " + antallFeil + " feil");
    }

    static void testTom() {
        System.out.println("Test tom liste");
        Liste<String> liste = new SortertLenkeliste<String>();
        sjekk(0, liste.stoerrelse(), "stoerrelse() i tom liste");
    }

    static void testEttElement() {
        System.out.println("\nTest ett element");
        Liste<String> liste = new SortertLenkeliste<String>();
        liste.leggTil("A");
        sjekk(1, liste.stoerrelse(), "stoerrelse() i liste med ett element");
        String hentetElement = liste.hent(0);
        sjekk("A", hentetElement, "hent(0) i liste med ett element");
        // hent(0) skal ikke fjerne elementet, la oss sjekke at fjerning fungerer
        String fjernetElement = liste.fjern();
        sjekk("A", fjernetElement, "fjern() i liste med ett element");
        sjekk(0, liste.stoerrelse(), "stoerrelse() etter at eneste element er fjernet");
    }

    static void testLeggTilOgFjerningAvFlereElementer() {
        System.out.println("\nTest leggTil() og fjern() med flere elementer");
        Liste<String> liste = new SortertLenkeliste<String>();
        liste.leggTil("C");
        liste.leggTil("A");
        liste.leggTil("D");
        liste.leggTil("B");
        sjekk(4, liste.stoerrelse(), "stoerrelse() i liste med 4 elementer");
        sjekk("A", liste.hent(0), "hent(0) i liste med 4 elementer");
        sjekk("B", liste.hent(1), "hent(1) i liste med 4 elementer");
        sjekk("D", liste.hent(3), "hent(3) i liste med 4 elementer");
        sjekk("D", liste.fjern(), "fjern() i liste med 4 elementer");
        sjekk("C", liste.fjern(), "fjern() (for andre gang) i liste med 3 elementer");
        sjekk(2, liste.stoerrelse(), "stoerrelse() etter fjerning av 2 av 4 elementer");
    }

    static void testLeggTilMedIntegers() {
        System.out.println("\nTest leggTil() med Integer i stedet for String");
        Liste<Integer> liste = new SortertLenkeliste<Integer>();
        liste.leggTil(4);
        liste.leggTil(1337);
        liste.leggTil(30);
        liste.leggTil(15);
        String rekkefolge = "" + liste.hent(0) + " - " + liste.hent(1) + " - " + liste.hent(2) + " - " + liste.hent(3);
        sjekk("4 - 15 - 30 - 1337", rekkefolge, "sjekk at sortering blir riktig med integers");
    }

    static void testFjernMedIndeks() {
        System.out.println("\nTest fjern(pos) med indekser");
        Liste<String> liste = new SortertLenkeliste<String>();
        liste.leggTil("elementC");
        liste.leggTil("elementAA");
        liste.leggTil("elementSist");
        liste.leggTil("elementAA"); // Duplikater skal vaere tillatt
        liste.leggTil("elementBBB");
        liste.leggTil("elementD");

        String rekkefolge = liste.hent(0) + " - " + liste.hent(1) + " - " + liste.hent(2) +
                " - " + liste.hent(3) + " - " + liste.hent(4) + " - " + liste.hent(5);
        String forventet = "elementAA - elementAA - elementBBB - elementC - elementD - elementSist";

        sjekk(forventet, rekkefolge, "sjekk at sortering blir riktig med strings");
        sjekk("elementBBB", liste.fjern(2), "fjern(2) paa listen ["+rekkefolge+"]");
        sjekk("elementC", liste.fjern(2), "fjern(2) andre gang paa listen ["+rekkefolge+"]");
        sjekk("elementSist", liste.fjern(), "fjern() der elementSist skal ligge sist");
        sjekk(3, liste.stoerrelse(), "stoerrelse() etter flere kall paa fjern() og leggTil()");
    }

    static void testUnntak() {
        System.out.println("\nTest unntak");
        Liste<String> liste = new SortertLenkeliste<String>();
        try {
            liste.fjern(); // skal ikke fungere, men skal kaste et unntak
            // hit kommer vi ikke om det ble kastet et unntak
            sjekkFeilet("fjern() paa tom liste skulle kastet unntak");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.fjern(0);
            sjekkFeilet("fjern(0) paa tom liste skulle kastet unntak");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.hent(0);
            sjekkFeilet("hent(0) paa tom liste skulle kastet unntak");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.sett(0, "forventet UnsupportedOperationException her");
            sjekkFeilet("sett(0, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }

        try {
            liste.leggTil(1, "forventet UnsupportedOperationException her");
            sjekkFeilet("leggTil(1, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }

        liste.leggTil("Forste element");
        liste.leggTil("Siste element");
        try {
            liste.fjern(2);
            sjekkFeilet("fjern(2) paa liste med 2 elementer skulle kastet unntak");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.hent(2);
            sjekkFeilet("hent(2) paa liste med 2 elementer skulle kastet unntak");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.sett(2, "forventet UnsupportedOperationException her");
            sjekkFeilet("sett(2, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }

        try {
            liste.leggTil(3, "forventet UnsupportedOperationException her");
            sjekkFeilet("leggTil(3, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }

        try {
            liste.fjern(-1);
            sjekkFeilet("fjern(-1) skal kaste unntaket UgyldigListeIndeks");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.hent(-1);
            sjekkFeilet("hent(-1) skal kaste unntaket UgyldigListeIndeks");
        } catch(UgyldigListeIndeks e) {
            sjekkPasserte();
        }

        try {
            liste.sett(-1, "forventet UnsupportedOperationException her");
            sjekkFeilet("sett(-1, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }

        try {
            liste.leggTil(-1, "forventet UnsupportedOperationException her");
            sjekkFeilet("leggTil(-1, ...) skulle kastet UnsupportedOperationException");
        } catch(UnsupportedOperationException e) {
            sjekkPasserte();
        }
    }

    static void sjekk(Object forventet, Object faktisk, String testmelding) {
        if (forventet.equals(faktisk)) {
            sjekkPasserte();
        } else {
            sjekkFeilet(testmelding);
            System.out.println("  > Forventet verdi: " + forventet);
            System.out.println("  > Faktisk verdi: " + faktisk);
        }
    }

    static void sjekkPasserte() {
        antallTester++;
        antallPasserte++;
        System.out.println("- Test " + antallTester + ": OK");
    }

    static void sjekkFeilet(String testmelding) {
        antallTester++;
        antallFeil++;
        System.out.println("- Test " + antallTester + " feilet: " + testmelding);
    }
}
