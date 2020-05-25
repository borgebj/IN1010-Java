import java.util.ArrayList;

public class Person {

    String navn;
    ArrayList<String> utseende;

    public Person(String navn) {
        this.navn = navn;
        utseende = new ArrayList<String>();
    }

    @Override
    public String toString(){
        return navn;
    }

}