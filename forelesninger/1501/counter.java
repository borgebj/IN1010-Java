import java.util.Scanner;

class Counter {
    //Deklarering av variabel teller av type int
    private int teller;

    //"Konstruktør" (samme navn som klassen)
    public Counter(){
        teller = 0;}

    //Public metode count() - void = returnerer ikke noe
    public void count() {
        teller++;}

    //Public metode getValue() - int = ??
    public int getValue() {
        return teller;}
}

// Klasse som kaller på objekt fra Counter-klassen ^
class Objekt {
    public static void main(String[] args){
        Counter objekt = new Counter();

        Scanner input = new Scanner(System.in);
        System.out.print("\n>");
        String x = input.nextLine();

        while (x.isEmpty()){
            objekt.count();
            System.out.print(">");
            x = input.nextLine();
        }

        // Teller oppbevares i variabel "svar"
        int svar = objekt.getValue();
        System.out.println("\nTeller: " + svar + "\n");
    }
}
