import java.util.ArrayList;
import java.util.Random;

public class Skattkiste {

    ArrayList<Gjenstand> innhold = new ArrayList<Gjenstand>();
    final int maksAntSkatter;

    // brukt for aa faa tilfeldig tall
    Random rand = new Random();


    // konstruktoer sikrer at en kiste aldri kan holde 0 skatter
    public Skattkiste(int maksAntSkatter) {
        if (maksAntSkatter <= 0) this.maksAntSkatter = 1;
        else this.maksAntSkatter = maksAntSkatter;
    }

    // returner en tilfeldig gjenstand om kisten ikke er tom
    public Gjenstand taUt() {
        int index = rand.nextInt(innhold.size());
        Gjenstand fjern = innhold.get(index);
        innhold.remove(fjern);

        return fjern;
    }

    // legger inn inn gjenstand fra parameter om den ikke er full og returner verdien
    public int leggNed(Gjenstand g) {
        innhold.add(g);

        // tilfeldiggjoer verdien
        int verdi = g.hentVerdi();
        verdi = rand.nextInt(verdi) + (verdi + 5) / 2;

        return verdi;
    }

    public void fjern(Gjenstand g) {
        if (innhold.contains(g)) innhold.remove(g);
        else System.out.println("Den gjenstanden finnes ikke her");
    }

    // hjelpemetode
    public boolean erFull() {
        return innhold.size() >= maksAntSkatter;
    }

    // hjelpemetode
    public boolean erTom() {
        return innhold.size() <= 0;
    }

    public int stoerrelse() {
        return innhold.size();
    }

    public Gjenstand hent(int index) {
        return innhold.get(index);
    }

    // returner liste med alterantiver for "Terminal-objektet"
    public String[] hentGjenstander() {
        String[] gjenstander = new String[stoerrelse()];

        for (int i = 0; i < gjenstander.length; i++) {
            Gjenstand g = innhold.get(i);
            gjenstander[i] = g.toString();
        }
        return gjenstander;
    }

    @Override
    public String toString() {
        StringBuilder tekst = new StringBuilder("|");
        for (Gjenstand s : innhold) {
            tekst.append(" ").append(s).append(" ");
        }
        tekst.append("|");
        return tekst.toString();
    }
}