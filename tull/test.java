import java.util.HashMap;
import java.util.Map;

class test {

    static HashMap<String, Integer> liste = new HashMap<String, Integer>();

    private static void skrivUt() {
        for (Map.Entry<String,Integer> entry : liste.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "\nValue: " + entry.getValue());
        } System.out.println("---------------");
    }

    public static void main(String[] args)
    {
        liste.put("En", 1);
        liste.put("To", 2);
        liste.put("Tre", 3);
        liste.put("Fire", 4);
        liste.put("Fem", 5);
        liste.put("Seks", 6);

        skrivUt();

        liste.replace("Fem", 80);

        skrivUt();
    }
}
