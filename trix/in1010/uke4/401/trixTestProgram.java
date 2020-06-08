
class trixTestProgram {
    public static void main(String[] args) {

        trixB b = new trixB();

        trixA en = b;
        trixB to = b;

        en.skrivKlasse();
        to.skrivKlasse();

    }
}

// begge skriver ut "Klasse B"
// dette er fordi begge peker til en klasse av typen "trixB", selv om den ene er satt som "trixA", fordi selv
// om vi har trixA, så skriver den Klasse B fordi trixB er subklasse av trixA, så den godkjenner det som en type av A, men skriver ut B pga pekeren.