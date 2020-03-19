import pakker.*;

class TestListe {
    public static void main(String[] args) {
        Liste<String> lx = new Arrayliste<>();

// ....Sett inn 13 elementer, andre tester....
        for (int i = 0; i <= 12; i++)
            lx.add("A"+i);

        // Sjekk størrelsen:
        System.out.println("Listen har " + lx.size() + " elementer");

// Marker element nr 10:
        lx.set(10, lx.get(10)+"*");

// Fjern det første elementet:
        String s = lx.remove(0);
        System.out.println("Fjernet " + s);
// Skriv ut innholdet:
        for (int i = 0; i < lx.size(); i++)
            System.out.println("Element " + i + ": " + lx.get(i));
// Lag en feil:
        lx.remove(999);
    }
}