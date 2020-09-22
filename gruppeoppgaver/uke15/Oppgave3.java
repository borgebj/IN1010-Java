import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;



/** IDE fra Oppgave 3 fra gruppeovelser 23.04.20 - endret for det meste ALT selv */


public class Oppgave3 extends Application {

    GridPane rootPane;
    int ruterX  = 14;
    int ruterY = 14;
    TextField tf;
    String currentColor = "black";


    // lager brettet og linjene under
    private void lagBrett() {
        rootPane.getChildren().clear();
        for (int y=0; y < ruterY; y++) {
            for (int x=0; x < ruterX; x++) {

                Region b = new Region();
                b.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
                b.setPrefWidth(40);  b.setPrefHeight(40);

                // registrere muse-drag
                b.setOnMouseDragEntered(
                        event -> {
                            event.consume();
                            b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color:"+currentColor+";");
                        });

                // registrere museklikk
                b.setOnMouseClicked(
                        event -> {
                            event.consume();
                            b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color:"+currentColor+";");
                        });

                rootPane.add(b, x, y);
            }
        }
    }

    private HBox opprettLinje2() {
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
        Text t1 = new Text("|  "); t1.setFont(font1);
        Text t2 = new Text("  |  "); t2.setFont(font1);
        Text t3 = new Text("  |"); t3.setFont(font1);

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
        return linje2;
    }

    private HBox opprettLinje3() {
        // oppretter linje 3 /////////////////////////////////////////////
        HBox linje3 = new HBox(20);linje3.setAlignment(Pos.BASELINE_CENTER); linje3.setPadding(new Insets(10, 0, 0, 0));

        StackPane linje3Stack = new StackPane();

        Rectangle rrr = new Rectangle(508, 27); rrr.setFill(Color.GRAY);

        HBox box = new HBox(15); box.setAlignment(Pos.BASELINE_CENTER);
        Button b1 = new Button("red"); b1.setOnAction(new SettFargeBehandler()); b1.setStyle("-fx-background-color: red; -fx-border-color: black;");
        Button b2 = new Button("orange"); b2.setOnAction(new SettFargeBehandler()); b2.setStyle("-fx-background-color: orange; -fx-border-color: black;");
        Button b3 = new Button("yellow"); b3.setOnAction(new SettFargeBehandler()); b3.setStyle("-fx-background-color: yellow; -fx-border-color: black;");
        Button b4 = new Button("green"); b4.setOnAction(new SettFargeBehandler()); b4.setStyle("-fx-background-color: green; -fx-border-color: black;");
        Button b5 = new Button("blue"); b5.setOnAction(new SettFargeBehandler()); b5.setStyle("-fx-background-color: blue; -fx-border-color: black;");
        Button b6 = new Button("brown"); b6.setOnAction(new SettFargeBehandler()); b6.setStyle("-fx-background-color: brown; -fx-border-color: black;");
        Button b7 = new Button("grey"); b7.setOnAction(new SettFargeBehandler()); b7.setStyle("-fx-background-color: grey; -fx-border-color: black;");
        Button b8 = new Button("white"); b8.setOnAction(new SettFargeBehandler()); b8.setStyle("-fx-background-color: white; -fx-border-color: black;");
        Button b9 = new Button("black"); b9.setOnAction(new SettFargeBehandler()); b9.setStyle("-fx-background-color: black; -fx-border-color: black;");
        box.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);

        linje3Stack.getChildren().addAll(rrr, box);

        linje3.getChildren().add(linje3Stack);
        // avslutter linje 3 /////////////////////////////////////////////
        return linje3;
    }



    @Override
    public void start(Stage teater) {

        // oppretter linje 1 /////////////////////////////////////////////
        HBox linje1 = new HBox(); linje1.setAlignment(Pos.BASELINE_CENTER);

        rootPane = new GridPane();
        lagBrett();

        linje1.getChildren().add(rootPane);
        // avslutter linje 1 /////////////////////////////////////////////


        HBox linje2 = opprettLinje2();


        HBox linje3 = opprettLinje3();


        // legger sammen alle linjene i en hoved-pane
        VBox root = new VBox();
        root.getChildren().addAll(linje1, linje2, linje3);

        // start full press-drag-release gesture
        root.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        root.startFullDrag();
                    }
                });

        // oppretter scene og starter "teatret"
        Scene scene = new Scene(root);
        teater.setScene(scene);
        teater.show();
    }




    // ulike behandlere - "EventHandlers"

    // reset = lager bretter paa nytt igjen med hvite ruter
    class ResetBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            lagBrett();
        }
    }

    // avslutter
    static class AvsluttBehandler implements EventHandler<ActionEvent> {
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
                String[] tall = tf.getText().split("x| x ");
                ruterY = Integer.parseInt(tall[0]);
                ruterX = Integer.parseInt(tall[1]);
                lagBrett();
            } catch (NumberFormatException c) {
                tf.setText("Ugyldig input"); } }
    }

    class SettFargeBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Button m = (Button) e.getSource();
            currentColor = m.getText();
        }
    }
}
