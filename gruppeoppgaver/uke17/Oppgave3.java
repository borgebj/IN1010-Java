import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.geometry.Pos;


/** Oppgave 3 fra gruppeovelser 23.04.20 - lagt til reset-knapp selv */


public class Oppgave3 extends Application {

    GridPane rootPane;

    @Override
    public void start(Stage teater) {

        // oppretter linje 1 /////////////////////////////////////////////
        HBox linje1 = new HBox();
        rootPane = new GridPane();
        MinKnappBehandler behandler = new MinKnappBehandler();

        for (int y=0; y < 9; y++) {
            for (int x=0; x < 10; x++) {

                MinKnapp b = new MinKnapp();
                b.setOnAction(behandler);
                b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                b.setPrefWidth(40);  b.setPrefHeight(40);

                rootPane.add(b, x, y);
            }
        }
        linje1.getChildren().add(rootPane);
        // avslutter linje 1 /////////////////////////////////////////////


        // oppretter linje 2 /////////////////////////////////////////////
        HBox linje2 = new HBox(20); linje2.setAlignment(Pos.BASELINE_CENTER);
        Font font = new Font(20);

        // oppretter knapp, endrer font og gir den funksjon
        Button reset = new Button("Reset");
        reset.setFont(font);
        reset.setOnAction(new ResetBehandler());

        Button avslutt = new Button("Avslutt");
        avslutt.setFont(font);
        avslutt.setOnAction(new AvsluttBehandler());

        linje2.getChildren().addAll(reset, avslutt);
        // avslutter linje 2 /////////////////////////////////////////////

        // legger sammen alle linjene i en hoved-pane
        VBox root = new VBox();
        root.getChildren().addAll(linje1, linje2);

        // oppretter scene og starter "teatret"
        Scene scene = new Scene(root);
        teater.setScene(scene);
        teater.show();
    }

    // utvider knapp for aa ta vare paa farger
    class MinKnapp extends Button {

        private String farge = "hvit";

        public String hentFarge() {
            return farge;
        }

        public void settFarge(String f) {
            farge = f;
        }
    }

    class MinKnappBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            MinKnapp m = (MinKnapp) e.getSource();
            String farge = m.hentFarge();

            switch(farge) {
                case "hvit":
                    m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: red;");
                    m.settFarge("roed");
                    break;
                case "roed":
                    m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: blue;");
                    m.settFarge("blaa");
                    break;
                case "blaa":
                    m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                    m.settFarge("hvit");
                    break;
            }

        }
    }

    // reset = lager bretter paa nytt igjen med hvite ruter
    class ResetBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            MinKnappBehandler behandler = new MinKnappBehandler();
            for (int y=0; y < 9; y++) {
                for (int x=0; x < 10; x++) {

                    MinKnapp b = new MinKnapp();
                    b.setOnAction(behandler);
                    b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                    b.setPrefWidth(40); b.setPrefHeight(40);

                    rootPane.add(b, x, y);
                }
            }

        }
    }

    // avslutter
    class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

}