
abstract class TrixFigur {
    public abstract double areal();
}

class TrixKvadrat extends TrixFigur {
    double side;

    public TrixKvadrat(double side) {
        this.side = side;
    }
    public double areal() {
        return side*side;
    }
    public double omkrets() { return 4*side; }
}

class TrixKvadratStabel {
    private TrixNode head;

    public void leggPaa(TrixKvadrat e) {
        if (erTom()) { head = new TrixNode(e); }
        else {
            TrixNode ny = new TrixNode(e);
            ny.neste = head;
            head = ny;
        }
    }

    public TrixKvadrat taAv() {
        if (erTom()) { return null; }

        TrixKvadrat verdi = head.innhold;
        head = head.neste;
        return verdi;
    }

    public boolean erTom() {
        return head == null;
    }


    private static class TrixNode {
        private final TrixKvadrat innhold;
        private TrixNode neste;

        private TrixNode(TrixKvadrat innhold) {
            this.innhold = innhold;
        }
    }
}

public class TrixTestProgram {
    public static void main(String[] args) {

        TrixKvadratStabel stabel = new TrixKvadratStabel();

        stabel.leggPaa(new TrixKvadrat(1));
        stabel.leggPaa(new TrixKvadrat(2));
        stabel.leggPaa(new TrixKvadrat(3));
        stabel.leggPaa(new TrixKvadrat(4));
        stabel.leggPaa(new TrixKvadrat(5));
        stabel.leggPaa(new TrixKvadrat(6));

        double sum = 0, siste = 0;

        while (!stabel.erTom()){
            TrixKvadrat k = stabel.taAv();
            sum += k.areal();
            siste = k.areal();
        }

        System.out.println("Summen av kvadratenes areal er " + sum);
        System.out.println("Det siste kvadratet i stabelen har areal: " + siste);
    }
}