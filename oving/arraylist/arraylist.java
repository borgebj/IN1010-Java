// importerer ArrayList
import java.util.ArrayList;

class arraylist {
    public static void main(String[] args) {

        // deklarerer en ArrayList med navn array som inneholder typen String
        ArrayList<String> array = new ArrayList<String>();

        // bruker metoden .add(#) for aa legge til String
        array.add("borge");
        array.add("arne");

        // printer ut hele listen
        System.out.println(array);

        // bruker metoden .get(#) for aa hente ut element i indeksen 0
        String indx = array.get(0);
        System.out.println("Index nr 0: "+indx);

        // bruker metoden .remove(# for aa fjerner index nummer 1, og saa printer ut ny liste
        array.remove(1);
        System.out.println(array);

        // bruker metoden .size() for aa finne lengden/storrelse paa arrayList
        int storrelse = array.size();
        System.out.println("Storrelse "+storrelse);

        // legger til nye og finner ny storrelse
        array.add("lisa");
        array.add("bjorn");
        int nyStorrelse = array.size();
        System.out.println("Ny storrelse: "+nyStorrelse);

        // itererer gjennom arraylisten med en for-lokke
        for (int i=0; i<array.size(); i++) {
            System.out.println("- "+array.get(i));
        }
        // mens i er under array.size() > aka lengden til array:
        // skriv ut hvert element med array.get(i) < henter array med index i (som okes for hver gang)

        System.out.println("------------------------------------------------------------");

        for (String navn : array) {
            System.out.println(navn);
        }
    }
}