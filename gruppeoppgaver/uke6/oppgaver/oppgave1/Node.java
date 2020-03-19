
class Node<T> {
    Node neste; // = null hvis ingen neste
    T verdi;

    public Node(T verdi) {
        this.verdi = verdi;
    }

    public void settNeste(Node n) {
        neste = n;
    }

    public Node hentNeste() {
        return neste;
    }

    public T hentData() {
        return verdi;
    }

}