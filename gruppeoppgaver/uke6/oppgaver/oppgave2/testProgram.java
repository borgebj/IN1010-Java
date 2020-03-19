
class testProgram {
    public static void main(String[]args) {

        OrdnetPar<String, Integer> par = new OrdnetPar<String, Integer>("Fem", 5);

        System.out.println("Nokkel: " + par.hentNokkel());
        System.out.println("Verdi: " + par.hentVerdi());
    }
}