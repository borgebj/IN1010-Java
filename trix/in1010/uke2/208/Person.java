
class Hund {

    private String navn;
    private String telefonnummer;
    private String adresse;

    public Hund(String navn, String telefonnummer, String adresse) {
        this.navn = navn;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
    }

    public void skrivInfo() {
        System.out.println("Navn: " + navn);
        System.out.println("Telefonnummer: " + telefonnummer);
        System.out.println("Adresse: " + adresse + "\n");
    }

    public String hentNavn() {
        return navn;
    }
}