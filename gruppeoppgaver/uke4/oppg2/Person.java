
class Person {
    String navn = "ikke registrert.";
    String adresse = "ikke registrert.";

    public void registrerPerson(String n) {
        navn = n;
    }

    public void registrerPerson(String n, String a) {
        navn = n;
        adresse = a;
    }

    public void skrivPerson() {
        System.out.println("Navn: " + navn + ", adresse: " + adresse);
    }
}