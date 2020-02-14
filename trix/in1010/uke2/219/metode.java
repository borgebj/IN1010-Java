import java.util.HashMap;

// HashMap > studenter
// nokke: navn
// innhold: ???

class metode {

    HashMap<String, Integer> studenter = new HashMap<String, Integer>();

    public void add(String pers, int alder) {
        studenter.put(pers, alder);
    }

    public boolean finnHund(String pers) {
        for (String navn : studenter.keySet()) {
            if (navn == pers) {
                return true; }
        }
        return false;
    }
}