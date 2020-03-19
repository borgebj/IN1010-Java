


public class B extends A{

    String navn;

    public B() {
        navn = "B";
    }

    @Override
    public String hentNavn() {
        return navn;
    }
}