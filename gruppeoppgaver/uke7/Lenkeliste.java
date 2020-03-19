import java.util.Iterator;

class Lenkeliste<T> implements Iterable<T> {

    // node-klasse vi bruker for listen vaar
    class Node {
        T data;
        Node neste;

        Node(T data) {
            this.data = data;
        }
    }

    // variabel som holder styr paa forste node
    private Node start;

    // setter inn paa starten, og dytter element til neste plass om listen ikke er tom
    public void settInnPaaStarten(T data) {

        if (start == null) { // hvis listen er tom, sett inn paa starten
            start = new Node(data);
        } else { // ellers dyttes noden bortover og ny settes paa starten
            Node node = new Node(data);
            node.neste = start;
            start = node;
        }
    }

    // fjerner node paa starten om listen ikke er tom, og returner data fra dne
    public T fjernPaaStarten() {
        if (start == null) {
            return null;
        } else {
            Node returNode = start;
            start = start.neste;
            return returNode.data;
        }
    }

    // wtf er dette?
    public Iterator<T> iterator() {
        return new ListeIterator();
    }

    // ny klasse vi bruker for aa iterere
    class ListeIterator implements Iterator<T> {

        Node current = start;

        public boolean hasNext() {
            if (current == null) {
                return false;
            } return true;
        }

        public T next() {
            if (hasNext()) {
                T data = current.data;
                current = current.neste;
                return data;
            } return null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not implemented"); // basically pass
        }
    }
}