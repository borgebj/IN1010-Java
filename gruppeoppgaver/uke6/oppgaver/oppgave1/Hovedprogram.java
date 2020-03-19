
class Hovedprogram {

    public static void main(String[] args) {
        Node forste = new Node(1);
        Node<String> andre = new Node<String>("paa");
        Node<Integer> tredje = new Node<Integer>(3);

        // riktig
        forste.settNeste(andre);
        forste.hentNeste().settNeste(tredje);


        Node node = forste;

        while (node != null) {
            System.out.println(node.hentData());
            node = node.hentNeste();
        }
    }
}