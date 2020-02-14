import java.util.ArrayList;
import java.util.HashMap;

class Telefonbok {
    public static void main(String[] args) {
        Hund borge = new Hund("Borge", "46793400", "Rolf E Stenersens Alle 9");
        Hund trygve = new Hund("Trygve", "867988", "Skiveien 42");
        Hund bjarte = new Hund("Bjarte", "224488", "Skismorbrod 8");

        Hund[] persArray = new Hund[10];
        persArray[0] = borge;
        persArray[1] = trygve;
        persArray[2] = bjarte;

        // for lokke som kjorer lengden til persArray antall ganger, og bruker .skrivInfo()-metoden om indeksen ikke gir verdien null
        for (int i=0; i<persArray.length; i++) {
            if (persArray[i] != null) {
                persArray[i].skrivInfo();
            }
        }

        // samme som over bare ikke i vanlig array, men i ArrayList
        ArrayList<Hund> arrayList = new ArrayList<Hund>();

        // legger til de 3 Hund-objektene i ArrayList
        arrayList.add(borge);
        arrayList.add(trygve);
        arrayList.add(bjarte);

        // bruker for-each lokke for a kalle paa metoden
        for (Hund pers : arrayList) {
            pers.skrivInfo();
        }

        // samme som over bare i HashMap
        HashMap<String, Hund> ordbok = new HashMap<String, Hund>();

        // legger til de 3-Hund objektene som innholdsverdi med navnene deres som nokkelverdi
        ordbok.put((borge.hentNavn()), borge);
        ordbok.put((trygve.hentNavn()), trygve);
        ordbok.put((bjarte.hentNavn()), bjarte);

        // for each lokke hvor "key" er hver nokkel i ordbok.keySeth() som er en rekke med nokkeler.
        for (String key : ordbok.keySet()) {
            ordbok.get(key).skrivInfo();
        }
    }

}