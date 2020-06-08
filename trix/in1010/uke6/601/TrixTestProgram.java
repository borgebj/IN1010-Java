import java.lang.Math;

// fra uke3
abstract class TrixFigur {
    public abstract double areal();
}

class TrixSirkel extends TrixFigur {
    private double radius;

    public TrixSirkel(double radius) {
        this.radius = radius;
    }
    public double areal() {
        return radius*radius*Math.PI;
    }
}

class TrixKvadrat extends TrixFigur {
    double side;

    public TrixKvadrat(double side) {
        this.side = side;
    }
    public double areal() {
        return side*side;
    }
}


// fra uke6
class TrixKvadratBeholder {
    private TrixKvadrat kvadrat;

    public void settInn(TrixKvadrat kvadrat) {
        this.kvadrat = kvadrat;
    }

    public TrixKvadrat taUt() {
        return this.kvadrat;
    }
}

class TrixBeholder<E> {
    private E element;

    public void settInn(E element) {
        this.element = element;
    }

    public E taUt() {
        return this.element;
    }
}

class TrixTestProgram {
    public static void main(String[] args) {

        TrixBeholder<TrixSirkel> figurBeholder = new TrixBeholder<TrixSirkel>();

        TrixKvadrat kvadrat = new TrixKvadrat(5);
        TrixSirkel sirkel = new TrixSirkel(20);

        figurBeholder.settInn(sirkel);
        figurBeholder.settInn(kvadrat); // er feil
    }
}