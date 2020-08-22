import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// kulisser og annet
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

// non-javafx
import java.io.File;
import java.io.FileNotFoundException;

/* Representasjon av GUI
    <VBox>
        <children<
            <root>
                <children>
                    <LabKnapp>
                     ...
                    <LabKnapp>
                </children>
            </root>
            <linje1>
                <children>
                    <LabKnapp>
                </children>
            </linje1>
        </children>
    </VBox>

    - Hovedpane er VBox
    - Inne i hovedpane er GridPane og HBox
    - Inne i GridPane er rutenett med knapper
    - Inne i HBox er èn lang knapp
 */

public class GUI extends Application {

    private final FileChooser fileChooser = new FileChooser(); // Filechooser for aa starte programmet
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION); // alert-boks

    private static Labyrint minLab; // referense til labyrinten den tilhoerer
    private final GridPane root = new GridPane(); // og rutenettet labyrinten lages til
    private int rader;
    private int kolonner;
    private double knappStoerrelse;
    private LabKnapp current; // knappen vi er på nå


    @Override
    public void start(Stage teater) {
        try {
            File fil = fileChooser.showOpenDialog(teater);
            minLab = Labyrint.lesFraFil(fil);
            rader = minLab.antRader;
            kolonner = minLab.antKol;

            opprettBrett( minLab.hentbrett() );

            // lager hoved-panen i programmet og legger til rutenettet og HBox for "neste vei"-knapp
            VBox main = new VBox(); root.setAlignment(Pos.BASELINE_CENTER);
                HBox linje1 = new HBox(); linje1.setAlignment(Pos.BASELINE_CENTER); //linje1.setStyle("-fx-background-color: black");

                    Button neste = new Button("Neste vei");
                    neste.setPrefWidth(knappStoerrelse*kolonner); neste.setPrefHeight(35);  // knappen er ca like lang som brettet
                    neste.setOnAction(new veiBehandler());

                linje1.getChildren().add(neste);
            main.getChildren().addAll(root, linje1);

            Scene scene = new Scene(main);
            teater.setScene(scene);
            teater.setTitle(fil.getName()); // tittel = navn paa fil
            teater.show();
        } catch (FileNotFoundException e) { System.out.println("Feil ved innlesing av fil"); }
    }


    private void opprettBrett(Rute[][] brett) {
        knappStoerrelse = 2000.0/(rader+kolonner); // lager knappstoerrelsen

        for (int rad = 0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {
                LabKnapp b = new LabKnapp(kol, rad);

                //hvit
                if (brett[rad][kol] instanceof HvitRute) {
                    b.setOnAction(new RuteKlikk());
                    settStyle(b, 'H');

                    // aapning
                    if (brett[rad][kol] instanceof Aapning) // om den er aapning legg i tillegg til bokstaven "A" for Aapning
                        b.setText("A");
                }
                //sort
                else if (brett[rad][kol] instanceof SortRute) {
                    b.setFont(new Font(150));
                    settStyle(b, 'S');
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
    private void settStyle(LabKnapp knapp, char c) {
        if (c == 'H') {
            knapp.setStyle("-fx-font: "+(knappStoerrelse/4)+" arial;"); // dynamisk font-stoerrelse
            knapp.settFarge(c);
        }
        else if (c == 'S') {
            knapp.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: black;");
            knapp.settFarge(c);
        }
        else if (c == 'B') {
            knapp.setStyle("-fx-font: "+(knappStoerrelse/3)+" arial; -fx-border-color: darkgrey; -fx-border-width: 1px; -fx-background-color: #16C3EF;");
            knapp.settFarge(c);
        }
    }

    // gjor graa ruter til hvite
    private void resetHvite()  {
        for (int rad=0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {
                LabKnapp knapp = (LabKnapp) hentKnappFraGrid(kol, rad); // henter knappen fra hver rute
                if (knapp.hentFarge().equals("blaa")) {
                    settStyle(knapp, 'H');
                }
            }
        }
    }

    // metode som henter noden=knappen paa plassen til col og row
    private Node hentKnappFraGrid(int col, int row) {

        //itererer gjennom gridpane sine nodes og sjekker om ruten stemmer
        for (Node node : root.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        } return null;
    }

    // egen knapp-klasse for Labyrinten
    private static class LabKnapp extends Button {

        private final int kolonne;
        private final int rad;
        String farge = "";

        // konstruktor som tar inn knappens koordinater (kol og rad)
        public LabKnapp(int kol, int rad) {
            this.kolonne = kol;
            this.rad = rad;
        }

        // get og set-metoder
        public void settFarge(char f) {
            if (f == 'S')
                farge = "svart";
            if (f == 'H')
                farge = "hvit";
            if (f == 'B')
                farge = "blaa";
        }
        public String hentFarge() {
            return farge;
        }
        public int getKol() {
            return kolonne;
        }
        public int getRad() {
            return rad;
        }
    }

    // lyser opp veien til en string med koordianter
    public void lysVei(String vei) {
        System.out.println(vei);
        String[] ruter = vei.split("-->");

        // fjerner alle paranteser
        for (String s : ruter) {
            s = s.replace("[", "");
            s = s.replace("]", "");

            // splitter hver rute for aa faa tilgang til hvert tall
            String[] ruten = s.split("\\."); // (5.5) > (5), (5)
            int ruteKol = Integer.parseInt(ruten[0]);
            int ruteRad = Integer.parseInt(ruten[1]);

            // henter knappen vha casting og setter style
            LabKnapp knapp = (LabKnapp) hentKnappFraGrid(ruteKol, ruteRad);
            settStyle(knapp, 'B');
        }
    }

    // Klasse som kjoerer naar en rute blir klikket - finner og viser den korteste veien i GUI-labyrinten
    class RuteKlikk implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            // hver gang vi trykker en rute, fjerner vi alle graae ruter
            resetHvite();

            // oppretter variabler for knapp, rad, kolonne og Alert-boks
            LabKnapp m = (LabKnapp) e.getSource(); current = m;
            int kol = m.getKol();
            int rad = m.getRad();
            alert.setHeaderText("Utveier");

            // finner alle utveier fra ruten hvor knappen ble trykket - og den korteste samtidig
            Liste<String> liste = minLab.finnUtveiFra(kol, rad);
            String kortest = minLab.finnKortestUtvei();

            // hvis ingen utveier: la alert-boks fortelle bruker
            if (kortest.equals("Ingen utveier"))
                alert.setContentText("Det finnes ingen utveier fra ["+kol+"."+rad+"]");
            else {
                // ellers lyses den korteste veien, og tekst til alertboks settes
                lysVei(kortest);
                alert.setContentText("Totalt antall utveier: " + liste.stoerrelse() + "   -   Viser kortest utvei");
            }
            alert.show(); // alert vises uansett
        }
    }

    // eventhandler som viser hver vei i labyrinten, en etter en
    class veiBehandler implements EventHandler<ActionEvent> {

        // counter oekes kun 1 gang hver gang knappen trykkes
        int counter = 0;

        @Override
        public void handle(ActionEvent e) {
            resetHvite();
            try {
                int kol = current.getKol();
                int rad = current.getRad();

                // henter alle utveier fra ruten til current-knapp
                Liste<String> utveier = minLab.finnUtveiFra(kol, rad);

                // henter en og en vei, og lyser de opp
                String utvei = utveier.hent(counter);
                lysVei(utvei);
            }
            // om current er null -> gi feilmelding til bruker via Alert
            catch (NullPointerException npe) {
                alert.setHeaderText("Ingen rute registrert!");
                alert.setContentText(null);
                alert.show();
            }
            // oppstaar ugyldigindeks reseter vi counteren
            catch (UgyldigListeIndeks uli) {
                counter = -1;
            }
            counter++;
        }
    }
}
