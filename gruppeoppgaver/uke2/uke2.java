import java.util.Arrays;

class uke2 {
    public static void main(String[] args){
        int[] tall = new int[10];

        // teller opp fra 0 til lengden
        for (int i = 0; i<tall.length; i++){
            tall[i] = i;
            System.out.println(tall[i]);
        }

        // bruke Arrays-metoden for aa skrive ut arrayen
        System.out.println("\n");
        System.out.println(Arrays.toString(tall));
        System.out.println("\n");

        // teller ned fra lengden til 0
        for (int i = tall.length-1; 0 < i; i--){
            tall[i] = i;
            System.out.println(tall[i]); }

        System.out.println("\n");

        // teller opp fra 0 - 9 og printer samtidig summen
        int b = 0;
        for (int i=0; i < tall.length; i++){
            tall[i] = i;
            b += tall[i];
            System.out.print(tall[i]);
            System.out.println(" "+b);
        }

        System.out.println("\n");

        String[] tall2 = new String[10];
        for (int i=0; i < tall2.length; i++) {
            tall2[i] = "";
            tall2[i] = tall2[0] += "*";
            System.out.println(tall2[i]);
        }
    }
}