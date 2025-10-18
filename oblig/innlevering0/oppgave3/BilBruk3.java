package innlevering0.oppgave3;

class BilBruk3 {
    public static void main(String[] args){

        // bil3-objekt med bilnummer
        Bil3 bilObj = new Bil3("A586ETO");

        // Hund-objekt med navn og objekt
        Person person = new Person("Arne", bilObj);

        // kaller paa metoden "skrivBilNummer" paa Hund-objektet
        person.skrivBilNummer();

    }
}