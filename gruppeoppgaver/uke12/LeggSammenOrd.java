import java.util.ArrayList;
import java.util.List;

class LeggSammenOrd {
    private static int riktig = 0;
    private static int feil = 0;
    private static List<String> ord;
    private static List<String> ord2;

    public static void main(String[] args) {
        testHentStringRiktigVei();
        testHentStringFeilVei();
        System.out.println("------------------------------------");
        System.out.println(riktig + " riktig -- " + feil + " feil");

        ord = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            ord.add(Integer.toString(i));
        }

        String [] randomOrd = "Test setning for aa sjekke om er riktig".split(" ");
        ord2 = new ArrayList<>();
        for (String i : randomOrd) {
            ord2.add(i);
        }

        testHentStringRiktigVei();
        testHentStringFeilVei();
        System.out.println("------------------------------------");
        System.out.println(riktig + " riktig -- " + feil + " feil");

    }

    public static void testHentStringRiktigVei() {

    }

    public static void testHentStringFeilVei() {

    }
}