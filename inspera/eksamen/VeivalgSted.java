import java.util.HashMap;

public class VeivalgSted extends Sted {

    // holder paa vare paa naboer/utganger og deres retning (String)
    HashMap<String, Sted> utganger = new HashMap<String, Sted>();


    public VeivalgSted(String beskrivelse) {
        super(beskrivelse);
    }


    // henter listen for aa kunne da modifisere den i VeivalgTerreng
    public HashMap<String, Sted> hentListe() {
        return utganger;
    }

    public String[] hentVeiNavn() {
        String[] veier = new String[3];

        int index = 0;
        for (String retning : utganger.keySet())
            veier[index++] = retning;

        return veier;
    }

    // returner en spesifikk nabo vha HashMap key fra parameter
    public Sted hentSted(String retningsNavn) {
        return utganger.get(retningsNavn);
    }
}