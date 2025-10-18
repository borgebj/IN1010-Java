package uke6;

class Main {
    public static void main(String[] args) {

        // noder som refererer til andre noder
        Node start = new Node();
        Node nyNode = new Node();

        nyNode.settNeste(nyNode);

        // generisk klasse som kan ta inn alt
        Boks<String> nyBoks = new Boks<String>("Hei");
        Boks<Integer> intBoks = new Boks<Integer>(1);

        System.out.println(nyBoks.hentData());
    }
}