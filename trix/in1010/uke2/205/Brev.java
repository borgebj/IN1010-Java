import java.util.ArrayList;

class Brev {

    private String avsender;
    private String mottaker;
    private ArrayList<String> linjer = new ArrayList<String>();

    public Brev(String as, String mt) {
        avsender = as;
        mottaker = mt;
    }

    public void skrivLinje(String linje) {
        linjer.add(linje);
    }

    public void lesBrev() {
        System.out.println("Hei "+mottaker+"\n");

        for (int i=0; i<linjer.size(); i++){
            System.out.println(linjer.get(i));
        }
        System.out.println();
        System.out.println("Hilsen fra,");
        System.out.println(avsender);
    }

}