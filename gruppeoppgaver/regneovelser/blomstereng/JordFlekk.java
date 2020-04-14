import java.util.ArrayList;

public class JordFlekk
{
    // nabo-JordFlekker
    private JordFlekk nord, ost, syd, vest;
    private boolean blomstrer = false;
    private Eng eng;

    public void polliner()
    {
        // stopper om den blomstrer allerede
        if (blomstrer)
            return;
        blomstrer = true;
        skrivUtBrett();
        pollinerNaboer();
    }

    public void skrivUtBrett()
    {
        sleep(300);
        System.out.println(eng);
    }

    public void pollinerNaboer()
    {
        ArrayList<JordFlekk> kanPollineres = new ArrayList<>();
        fyllListeMedNaboer(kanPollineres);
        for (JordFlekk nabo : kanPollineres)
            nabo.polliner();
    }

    public void fyllListeMedNaboer(ArrayList<JordFlekk> naboer)
    {
        JordFlekk[] naboene = { nord, ost, syd, vest };
        for (JordFlekk nabo : naboene) {
            if (nabo != null)
                naboer.add(nabo);
        }
    }

    public void sleep(int millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // setter naboer
    public void settNord(JordFlekk nord)
    {
        this.nord = nord;
    }
    public void settSyd(JordFlekk syd)
    {
        this.syd = syd;
    }
    public void settVest(JordFlekk vest)
    {
        this.vest = vest;
    }
    public void settOst(JordFlekk ost)
    {
        this.ost = ost;
    }

    public void settEngReferanse(Eng eng)
    {
        this.eng = eng;
    }

    @Override
    public String toString()
    {
        if (!blomstrer)
            return ".";
        return "\u2698";
    }
}