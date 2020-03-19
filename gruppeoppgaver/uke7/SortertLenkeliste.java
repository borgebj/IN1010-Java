
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {
    private class Node {}

    Node start;

    public void leggTil(T data) {
        Node node = start;
        if (node.data.compareTo(data) < 0) {
            // gaa videre
        }
        // ny peker paa neste
        // for peker paa ny
    }

    public T fjern(int pos) {
        // passe paa at forrige peker paa neste neste
    }
}