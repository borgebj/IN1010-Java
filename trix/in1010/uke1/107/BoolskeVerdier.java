
class BoolskeVerdier {
    public static void main(String[] args) {
        boolean sann = true;
        boolean usann = false;

        System.out.println(sann);

        if (sann != usann) { System.out.println("Forste test slo til!"); }
        else { System.out.println("Noe gikk galt"); }

        if (sann == usann) { System.out.println("Noe gikk galt"); }
        else { System.out.println("Andre test slo ikke til!\n"); }
    }
}