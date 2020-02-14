
public class KoLapp{

    int koNummer;

    //Konstruktor
    public KoLapp(int nummer) {
        koNummer = nummer;
    }

    //Returnerer et tildelt nummer paa kolappen.
    public int hentNummer(){
        return koNummer;
    }
}