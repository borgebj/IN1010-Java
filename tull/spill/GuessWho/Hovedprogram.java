import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;


public class Hovedprogram extends Application {

    VBox rootPane = new VBox();
    ArrayList<Person> personer = new ArrayList<Person>();

    private void lagBrett() {
        GridPane grid = new GridPane();

        int i =0;

        for (int y=0; y < 4; y++) {
            for (int x=0; x < 4; x++) {

                // lag navn og Person
                String imgNavn = "pers"+i+".jpg";
                lagPersoner(imgNavn);

                // opprett + tilpass bildet
                ImageView img = new ImageView(imgNavn);i++;
                img.setFitHeight(180); img.setFitWidth(180);

                // legg til border
                BorderPane imgBorder = new BorderPane(img);
                imgBorder.setStyle("-fx-border-color: black; -fx-border-width: 3px");

                // legg til i gridpane
                grid.add(imgBorder, x, y);
            }
        }
        rootPane.getChildren().add(grid);
        grid.setHgap(10);
        grid.setVgap(10);
    }

    private void lagUnderBar() {
        HBox linje2 = new HBox(30); linje2.setAlignment(Pos.BASELINE_CENTER); linje2.setStyle("-fx-border-color: black; -fx-border-width: 3px;");

            Font font = new Font(12);

            Button b1 = new Button("Avslutt");
            b1.setOnAction(new AvsluttBehandler());
            b1.setPrefWidth(100); b1.setPrefHeight(45); b1.setFont(font);

            TextField t1 = new TextField(" 'HER GJETTER DU' ");
            t1.setPrefWidth(120); t1.setPrefHeight(45); t1.setFont(font);

            Button b2 = new Button("Gjett");
            b2.setPrefWidth(100); b2.setPrefHeight(45); b2.setFont(font);


        linje2.getChildren().addAll(b1, t1, b2);
        rootPane.getChildren().add(linje2);
    }

    private void lagPersoner(String navn) {
        Person person = new Person(navn);
        personer.add(person);
    }


    @Override
    public void start(Stage teater) {

        lagBrett();
        lagUnderBar();

        Scene scene = new Scene(rootPane);

        System.out.println( personer );

        teater.setTitle("Guess Who");
        teater.setScene(scene);
        teater.show();
    }

    // avslutter
    static class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }
}