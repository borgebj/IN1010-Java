
class TrixGeneriskStabel<T> {
    private TrixNode head;

    public void leggPaa(T e) {
        if (erTom()) { head = new TrixNode(e); }
        else {
            TrixNode ny = new TrixNode(e);
            ny.neste = head;
            head = ny;
        }
    }

    public T taAv() {
        if (erTom()) { return null; }

        T verdi = head.innhold;
        head = head.neste;
        return verdi;
    }

    public boolean erTom() {
        return head == null;
    }


    private class TrixNode {
        private T innhold;
        private TrixNode neste;

        private TrixNode(T innhold) {
            this.innhold = innhold;
        }
    }
}

public class TrixTestProgram {
    public static void main(String[] args) {
        TrixGeneriskStabel<String> stabel = new TrixGeneriskStabel<String>();
        stabel.leggPaa("foobar");
        stabel.leggPaa("bazar");
        stabel.leggPaa("baz");
        stabel.leggPaa("bar");
        stabel.leggPaa("Foo");
        String resultat = "";
        while (!stabel.erTom())
            resultat += stabel.taAv() + " ";
        System.out.printf("Resultatet er: '%s'\n", resultat);
    }
}