
class Node {

    private Node neste;
    private String data;

    public void settNeste(Node node) {
        neste = node;
    }

    public Node hentNeste() {
        return neste;
    }

    public String hentData() {
        return data;
    }
}