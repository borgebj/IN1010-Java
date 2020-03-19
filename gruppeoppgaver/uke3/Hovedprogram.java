
class Hovedprogram {
    public static void main(String[] args) {
        // mobel objekt
        Mobel m = new Mobel(10, 20, 30);

        // sofa objekt
        Sofa s = new Sofa(20, 50, 70, 4);

        // kommode objekt
        Kommode k = new Kommode(20, 20, 30, 80, 5);

        // tester metoder
        System.out.println(m.hentStorrelse());
        s.skrivPlasser();
        System.out.println(k.hentStorrelse());
        k.hentAntSkuffer();
        System.out.println(m.dybde);
        m.dybde = 50;
        System.out.println(m.hentStorrelse());
    }
}