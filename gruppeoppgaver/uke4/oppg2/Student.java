package uke4.oppg2;

class Student extends Person {
    String idnr = "ikke registrert.";

    @Override // overload
    public void registrerPerson(String n, String i) {
        navn = n;
        idnr = i;
    }

    // overload
    public void registrerPerson(String n, String i, String a) {
        navn = n;
        idnr = i;
        adresse = a;
    }

    public void skrivPerson() {
        System.out.println("Navn: " + navn + ", studentnr: " + idnr + ", adresse: " + adresse);
    }
}