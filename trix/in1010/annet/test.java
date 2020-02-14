
class Rektangel {

    int lengde;
    int bredde;

    public Rektangel(int l, int b){
        lengde = l;
        bredde = b;
    }
    public int areal() {
        return lengde * bredde; }
}

class Hovedprogram {
    public static void main(String[] arg){
        Rektangel objekt = new Rektangel(5, 2);
        int areal = objekt.areal();
        System.out.println(areal);
    }
}