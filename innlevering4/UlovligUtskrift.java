
public class UlovligUtskrift extends Exception{
    public UlovligUtskrift(Lege lege, Legemiddel legemiddel, int pasientID){
        super(lege + " har ikke lov til å skrive ut " + legemiddel.hentNavn());
    }
}


/*
                    // LEGE-delen
                    delay(500);
                    System.out.print("Hva er lege navn? \n > ");
                    String legeNavn = scanner.nextLine().trim().toLowerCase();
                    for (Lege b : leger) {
                        if (legeNavn.equals(b.hentNavn().trim().toLowerCase())) {

                            // sjekker om lege er vanlig eller spesialist
                            if (b instanceof Spesialist) {
                                System.out.print("Hva er Kontroll-ID? \n > ");
                                int kontrollID = scanner.nextInt();
                                scanner.nextLine();

                                // vi vet at lege = spesialist, saa vi caster lege til spesialist for aa bruke "hentKontrollID()"
                                Spesialist spesialist = (Spesialist) b;

                                if (kontrollID == spesialist.hentKontrollID()) {
                                    legeMatch = true;
                                    lege = b;
                                    break;
                                }
                            }
                            else if (b instanceof Lege) {
                                if ( !(legemiddel instanceof Narkotisk)) {
                                    legeMatch = true;
                                    lege = b;
                                    break;
                                } else { narkoMatch = true; }
                            }
                        }
                    }
 */