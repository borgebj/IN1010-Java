import java.util.ArrayList;

interface TrixPlanteetere {
    boolean kunneBeskytteSegSelv();
}

interface TrixRovdyr {
    boolean kunneJakte();
}

class Bjorn implements TrixRovdyr {
    @Override
    public boolean kunneJakte() {
        return true;
    }
}

class Ulv implements TrixRovdyr {
    @Override
    public boolean kunneJakte() {
        return true;
    }
}

class Elg implements TrixPlanteetere {
    @Override
    public boolean kunneBeskytteSegSelv() {
        return true;
    }
}

class Sau implements TrixPlanteetere {
    @Override
    public boolean kunneBeskytteSegSelv() {
        return true;
    }
}

class TrixTestProgram {
    public static void main(String[] args) {

        ArrayList<TrixRovdyr> rovdyr = new ArrayList<TrixRovdyr>();
        ArrayList<TrixPlanteetere> planteetere = new ArrayList<TrixPlanteetere>();

        Bjorn b = new Bjorn(); rovdyr.add(b);
        Ulv u = new Ulv(); rovdyr.add(u);

        Elg e = new Elg(); planteetere.add(e);
        Sau s = new Sau();  planteetere.add(s);
    }
}