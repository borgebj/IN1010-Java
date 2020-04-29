import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes
import javafx.scene.layout.GridPane;

// kulisser og annet
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.Node;


// non-javafx
import java.io.File;
import java.io.FileNotFoundException;




public class GUI extends Application {

    private static Labyrint minLab;
    private int rader;
    private int kolonner;
    private final GridPane root = new GridPane();
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage teater) {

        try {
            File fil = fileChooser.showOpenDialog(teater);
            minLab = Labyrint.lesFraFil(fil);
            rader = minLab.antRader;
            kolonner = minLab.antKol;

            opprettBrett( minLab.hentbrett() );

            Scene scene = new Scene(root);
            teater.setScene(scene);
            teater.setTitle(fil.getName());
            teater.show();
        } catch (FileNotFoundException e) { System.out.println("feil ved innlesing av fil"); }

    }

    private void opprettBrett(Rute[][] brett) {
        double knappStoerrelse = 1900.0/(rader+kolonner);
        for (int rad = 0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {

                LabKnapp b = new LabKnapp(kol, rad);

                //hvit
                if (brett[rad][kol] instanceof HvitRute) {
                    settBakgrunn(b, 'H');
                    b.setOnAction(new RuteKlikk());
                }
                //sort
                else if (brett[rad][kol] instanceof SortRute) {
                    settBakgrunn(b, 'S');
                }

                // dyanmisk skalering av knappene i forhold til brettet
                b.setMinSize(16, 16);
                b.setPrefSize(knappStoerrelse, knappStoerrelse);

                // legger til ruten i GridPane
                root.add(b, kol, rad);
            }
        }
    }

    // hjelpemetode som setter bakgrunn - med character som identifikator
    private void settBakgrunn(LabKnapp knapp, char c) {
        if (c == 'H')
            knapp.setStyle("-fx-border-color: darkgrey; -fx-border-width: 1px; -fx-background-color: white;");
            knapp.settFarge(c);
        if (c == 'S')
            knapp.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: black;");
            knapp.settFarge(c);
        if (c == 'G')
            knapp.setStyle("-fx-border-color: darkgrey; -fx-border-width: 1px; -fx-background-color: #16C3EF;");
            knapp.settFarge(c);
    }

    // gjor graa ruter til hvite
    private void resetHvite() {
        for (int rad=0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {
                LabKnapp knapp = (LabKnapp) hentKnappFraGrid(kol, rad);
                if (knapp.hentFarge().equals("graa"))
                    settBakgrunn(knapp, 'H');
            }
        }
    }

    // metode som henter noden=knappen paa plassen til
    private Node hentKnappFraGrid(int col, int row) {
        for (Node node : root.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        } return null;
    }

    // egen knapp-klasse for Labyrinten
    static class LabKnapp extends Button {

        int kolonne;
        int rad;
        String farge = "null";

        // konstruktor som tar inn knappens koordinater (kol og rad)
        LabKnapp(int kol, int rad) {
            this.kolonne = kol;
            this.rad = rad;
        }

        // get og sett-metoder
        void settFarge(char f) {
            if (f == 'S')
                farge = "svart";
            if (f == 'H')
                farge = "hvit";
            if (f == 'G')
                farge = "graa";
        }
        String hentFarge() {
            return farge;
        }
        int getKol() {
            return kolonne;
        }
        int getRad() {
            return rad;
        }
    }

    // Klasse som kjoerer naar en rute blir klikket - finner og viser den korteste veien i GUI-labyrinten
    class RuteKlikk implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            // hver gang vi trykker en rute, fjerner vi alle graae ruter
            resetHvite();

            // oppretter variabler for knapp, rad, kolonne og Alert-boks
            LabKnapp m = (LabKnapp) e.getSource();
            int kol = m.getKol();
            int rad = m.getRad();
            Alert tomt = new Alert(Alert.AlertType.INFORMATION);
            tomt.setHeaderText("Utveier");

            // finner alle utveier fra ruten hvor knappen ble trykket - og den korteste samtidig
            Liste<String> liste = minLab.finnUtveiFra(kol, rad);
            String kortest = minLab.finnKortestUtvei();

            // hvis ingen utveier: la alert-boks fortelle bruker
            if (kortest.equals("Ingen utveier")) {
                tomt.setContentText("Det finnes ingen utveier fra ["+kol+"."+rad+"]");
                tomt.show();
            }
            else {

                // lager en liste av koordinater ved aa splitte ved pil
                String[] ruter = kortest.split("-->");

                // fjerner alle klammer
                for (String s : ruter) {
                    s = s.replace("[", "");
                    s = s.replace("]", "");

                    String[] ruten = s.split("\\."); // (5.5) > (5), (5)
                    int ruteKol = Integer.parseInt(ruten[0]);
                    int ruteRad = Integer.parseInt(ruten[1]);

                    LabKnapp knapp = (LabKnapp) hentKnappFraGrid(ruteKol, ruteRad);
                    settBakgrunn(knapp, 'G');

                    // endrer teksten i alert-boksen og viser den
                    tomt.setContentText("Totalt antall utveier: " + liste.stoerrelse() + "   -   Viser kortest utvei");
                    tomt.show();
                }
            }
        }
    }
}
