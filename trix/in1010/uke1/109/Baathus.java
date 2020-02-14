package baatpakke;
import baatpakke.Baat;

public class Baathus {

    private Baat[] objListe;
    private int i = 0;

    public Baathus(int antallPlasser) {
        objListe = new Baat[antallPlasser];
    }

    public void settInn(Baat baatObj) {
        if (i < objListe.length){
            objListe[i] = baatObj;
            i++;
            if (i >= objListe.length){
                System.out.println(" -  Det er ikke flere plasser  - ");
            }
        }
    }

    public void skrivBaater() {
        int ant = 0;
        for (int i = 0; i < objListe.length; i++){

            if (objListe[i] != null){
                System.out.println(objListe[i].hentInfo());
            }
            else { ant++; }
        }
        if (ant != 0){
            String ch = "", ah = "";
            if (ant > 1) {ch = "e"; ah = "er"; }
            System.out.println("  -  Det er " +ant+ " ledig" +ch+ " plass"+ah+"  -  ");
        }

    }
}