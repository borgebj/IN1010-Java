import java.util.HashMap;
import java.util.Scanner;

class Telefonbok {
    public static void main(String[] args) {

        //a) oppretter en ny HashMap med navn telefonbok og typene String og String
        HashMap<String, String> telefonbok = new HashMap<String, String>();

        // oppretter scanner-objekt med navn scanner
        Scanner scanner = new Scanner(System.in);

        //b) legger til nokkel og innhold
        telefonbok.put("Arne", "22334455");
        telefonbok.put("Lisa", "95959595");
        telefonbok.put("Jonas", "97959795");
        telefonbok.put("Peder", "12345678");

        //c) while lokke som kjores automatisk
        String inp = "ja";
        while (inp.equals("ja") == true) {

            System.out.print("\nHvem onsker du nummer til: ");
            String brukerInp = scanner.next();
            if (telefonbok.get(brukerInp) == null) {
                System.out.println("Hunden er ikke i telefonboken");
                System.out.print("\nVil du fortsette: ");
                inp = scanner.next();
            } else {
                String navn = telefonbok.get(brukerInp);
                System.out.println("Telefon til " + brukerInp + " er: " + navn);

                // inp som  enten fortsetter eller stopper while-lokken
                System.out.print("\nVil du fortsette: ");
                inp = scanner.next();
            }

            // for-lokke som printer ut hver nokkel og innhold
            for (String navnekey : telefonbok.keySet()) {
                System.out.println(navnekey+" - "+telefonbok.get(navnekey));}
        }
    }
}