import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.*;

public class Oppgave3 extends Application {

    public void start(Stage teater){

        GridPane rootPane = new GridPane();
        MinKnappBehandler behandler = new MinKnappBehandler();

        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 10; x++){

                MinKnapp b = new MinKnapp();
                b.setOnAction(behandler);
                b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                b.setPrefWidth(40);
                b.setPrefHeight(40);
                rootPane.add(b, x, y);

            }
        }

        Scene scene = new Scene(rootPane);
        teater.setScene(scene);
        teater.show();
    }

    class MinKnapp extends Button {

        private String farge = "hvit";

        public String hentFarge(){
            return farge;
        }

        public void settFarge(String f){
            farge = f;
        }
    }

    class MinKnappBehandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){

            MinKnapp m = (MinKnapp) e.getSource();
            String farge = m.hentFarge();

            switch(farge){
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
}