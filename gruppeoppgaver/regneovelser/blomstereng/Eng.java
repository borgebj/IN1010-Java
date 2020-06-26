
public class Eng
{
    private JordFlekk[][] eng;
    private int rader;
    private int kolonner;

    public Eng(JordFlekk[][] engen, int antRader, int antKolonner)
    {
        eng = engen;
        rader = antRader;
        kolonner = antKolonner;
    }

    public void polliner(int rad, int kolonne)
    {
        eng[rad][kolonne].polliner();
    }

    @Override
    public String toString()
    {
        String utskrift = new String(new char[50]).replace("\0", "\n");
        for (int rad = 0; rad < rader; rad++) {
            utskrift += "\n";
            for (int kol = 0; kol < kolonner; kol++) {
                utskrift += eng[rad][kol].toString() + " ";
            }
        }
        return utskrift;
    }
}