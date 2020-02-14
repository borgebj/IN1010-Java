import java.util.ArrayList;

public class KoSystem{

    // arraylist som inneholder lapper (antall kunder)
    private ArrayList<KoLapp> koLapper = new ArrayList<>();

    int teller = 0;

    //Lager et nytt objekt av KoLapp, printer ut informasjon om den og legger den til i listen over koLapper.
    public void trekkKoLapp(){
        teller++;
        KoLapp nyLapp = new KoLapp(teller);
        koLapper.add(nyLapp);

        System.out.print("Du har fatt tildelt billett nr ");
        System.out.println(nyLapp.hentNummer());
        System.out.println("Det staar "+(koLapper.size()-1)+" Hunder foran deg\n");
    }

    //Henter ut, printer ut informasjon og fjerner den forste kolappen i lista. Gir feilmelding dersom ingen kunder staar i ko.
    public void betjenKunde(){
        if (koLapper.isEmpty()) {
            System.out.println("Koen er tom");
        } else {
            KoLapp betjenes = koLapper.remove(0);
            System.out.println("Kunde med billett nr "+betjenes.hentNummer()+" er betjent");}
    }

    //Returnerer antall kunder som er i ko.
    public int antKunder(){
        return koLapper.size();
    }

    //Printer alle kunder i ko
    public void printKunderIKo(){
        if (koLapper.isEmpty()){
            System.out.println("Koen er tom");
        } else {
            System.out.println("Disse er i ko: ");
            for (KoLapp var : koLapper) {
                System.out.println("nr "+var.hentNummer()); }
        }
    }
}