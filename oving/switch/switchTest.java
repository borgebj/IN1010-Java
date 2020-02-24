import java.util.Scanner;

class switchTest {


    // main som tester switch-setning
    public static void main(String[] args1) {

        System.out.println("\n -- Med Switch-setning -- \n");

        // scanner objekt som tar inn input fra bruker og legger det inn i String variabel
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hva er ditt navn?: ");
        String navn = scanner.nextLine().toLowerCase();


        // switch-setning som sjekker Stringen "navn"
        switch(navn) {
            case "borge": // skjer om man skriver inn borge
                System.out.println("> Du kan komme inn\n");
                break;

            case "bob": // skjer om man skriver inn bob
                System.out.println("> Du kommer ikke inn\n");
                break;

            case "geir": // case uten noe gjelder for begge (gjelder til break; !)
            case "arne":
                System.out.println("> Du har ikke tilgang\n");
                break;

            default: // skjer om ingen tilfeller treffer
                System.out.println("> Prov igjen senere\n");
                break;
        }

        // kaller samme klasse med annen metode
        new switchTest().ifMain();
    }

    // metode som ligner main, men med if-setninger
    public void ifMain() {

        System.out.println("\n -- Med IF-setning -- \n");

        // scanner objet som tar inn input ...
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hva er ditt navn?: ");
        String navn = scanner.nextLine().toLowerCase();

        // bruker String.equals(###) for aa sjekke String, IKKE == (fordi string er en egen klasse)
        if (navn.equals("borge")) {
            System.out.println("> Du kan komme inn\n");
        } else if (navn.equals("bob")) {
            System.out.println("> Du kommer ikke inn\n");
        } else if (navn.equals("geir") || navn.equals("arne")) {
            System.out.println("> Du har ikke tilgang\n");
        } else {
            System.out.println("> Prov igjen senere\n");
        }
    }
}