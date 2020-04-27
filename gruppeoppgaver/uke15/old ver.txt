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
import javafx.scene.text.Text;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;



/** Oppgave 3 fra gruppeovelser 23.04.20 - lagt til knapper og svart selv */


public class Oppgave3 extends Application {

    GridPane rootPane;
    int ruterX  = 15;
    int ruterY = 15;
    TextField tf;
    MinKnappBehandler behandler = new MinKnappBehandler();

    private void lagBrett() {
        rootPane.getChildren().clear();
        for (int y=0; y < ruterY; y++) {
            for (int x=0; x < ruterX; x++) {

                MinKnapp b = new MinKnapp();
                b.setOnAction(behandler);
                b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                b.setPrefWidth(40);  b.setPrefHeight(40);

                rootPane.add(b, x, y);
            }
        }
    }

    @Override
    public void start(Stage teater) {

        // oppretter linje 1 /////////////////////////////////////////////
        HBox linje1 = new HBox(); linje1.setAlignment(Pos.BASELINE_CENTER);
        rootPane = new GridPane();

        lagBrett();

        linje1.getChildren().add(rootPane);
        // avslutter linje 1 /////////////////////////////////////////////


        // oppretter linje 2 /////////////////////////////////////////////
        HBox linje2 = new HBox(20); linje2.setAlignment(Pos.BASELINE_CENTER);
        Font font = new Font(15);

        // tekstfelt
        tf = new TextField(); tf.setPrefWidth(100);
        tf.setPromptText("Bredde x Hoyde");

        // endre knapp
        Button b = new Button("Endre");
        b.setPrefWidth(80);
        b.setOnAction(new NyttBrettBehandler());
        b.setFont(font);

        // skille streker
        Font font1 = new Font(30);
        Text t1 = new Text("  |  "); t1.setFont(font1);
        Text t2 = new Text("  |  "); t2.setFont(font1);
        Text t3 = new Text("  |  "); t3.setFont(font1);

        // reset knapp
        Button reset = new Button("Reset");
        reset.setPrefWidth(80);
        reset.setFont(font);
        reset.setOnAction(new ResetBehandler());

        // avslutt knapp
        Button avslutt = new Button("Avslutt");
        avslutt.setPrefWidth(90);
        avslutt.setFont(font);
        avslutt.setOnAction(new AvsluttBehandler());

        linje2.getChildren().addAll(t1, tf, b, t2, reset, avslutt, t3);
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
                    m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: black;");
                    m.settFarge("svart");
                    break;
                case "svart":
                    m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white");
                    m.settFarge("hvit");
                    break;
            }

        }
    }

    // reset = lager bretter paa nytt igjen med hvite ruter
    class ResetBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            lagBrett();
        }
    }

    // avslutter
    class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    // lager stoerre brett
    class NyttBrettBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            try {
                String[] tall = tf.getText().split("x|\\ x ");
                ruterY = Integer.parseInt(tall[0]);
                ruterX = Integer.parseInt(tall[1]);
                lagBrett();
            } catch (NumberFormatException c) {
                tf.setText("Ugyldig input");
            }
        }
    }

}