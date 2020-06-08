import java.util.HashMap;
import java.util.Map;

public class VeivalgTerreng extends Terreng {

    public VeivalgTerreng(String filnavn) {
        super(filnavn);
    }


    // gaar gjennom hashmap og ser etter steder som er koblet med seg selv > endrer disse
    private void naboShuffle(HashMap<String, Sted> utganger, Sted s) {

        for (Map.Entry<String,Sted> entry : utganger.entrySet()) {

            // henter "key" og "value" i HashMap
            String retning = entry.getKey();
            Sted til = entry.getValue();

            // henter beskrivelsen til stedet vi er paa og stedet i HashMapen
            String hashSted = til.hentBeskrivelse();
            String herSted = s.hentBeskrivelse();

            // mens de to stedene er like, bytt ut med en ny tilfeldig en (helt til de ikke er like)
            while (herSted.equals(hashSted)) {
                Sted nytt = steder.get(rand.nextInt(steder.size()));
                utganger.replace(retning, nytt);
                herSted = nytt.hentBeskrivelse();
            }
        }
    }

    @Override // settNaboer setter 3 naboer til hvert sted i sub-klasse vha polymorfi
    public void settNaboer() {
        for (Sted s : steder) {

            // henter VeivalgStedet i listen over alle Steder og henter HashMapen dere
            VeivalgSted veivalgSted = (VeivalgSted) s;
            HashMap<String, Sted> utganger = veivalgSted.hentListe();

            // oppretter nye steder for hver utgang  til hvert sted
            Sted rettFram = steder.get(rand.nextInt(steder.size()));
            Sted hoeyre = steder.get(rand.nextInt(steder.size()));
            Sted venstre = steder.get(rand.nextInt(steder.size()));

            // setter utgangene i en HashMapen til hvert sted
            utganger.put("Rett fram", rettFram);
            utganger.put("Hoeyre", hoeyre);
            utganger.put("Venstre", venstre);

            // kaller paa metode som sikrer ingen steder kommer til seg selv
            naboShuffle(utganger, s);
        }
    }
}