import java.util.ArrayList;

class TrixKatt implements Comparable<TrixKatt> {

    String navn;
    int alder;

    public TrixKatt(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    @Override
    public String toString() {
        return navn + " - " +alder+ " aar gammel";
    }

    @Override
    public int compareTo(TrixKatt k) {
        if (k.alder > alder) { return -1; }
        else  if (k.alder < alder) { return 1; }
        return 0;
    }
}

public class TrixTestProgram701 {
    public static void main(String[] args) {

        ArrayList<TrixKatt> katter = new ArrayList<TrixKatt>();

        TrixKatt k1 = new TrixKatt("pus", 12);
        TrixKatt k2 = new TrixKatt("arilkatt", 4);
        TrixKatt k3 = new TrixKatt("Myso", 2);
        TrixKatt k4 = new TrixKatt("Bjeff", 13);
        katter.add(k1); katter.add(k2); katter.add(k3); katter.add(k4);


        TrixKatt eldst = katter.get(0);
        for (TrixKatt k : katter) {
            if (k.compareTo(eldst) > 0)
                eldst = k;
        }
        System.out.println(eldst);
    }
}