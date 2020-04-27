import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

// kulisser og annet
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.event.*;
import javafx.geometry.Pos;


// non-javafx
import java.io.FileNotFoundException;



public class GUI extends Application {

    private Labyrint minLab;
    private int rader;
    private int kolonner;
    private GridPane root = new GridPane();
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage teater) {

        try {
            minLab = Labyrint.lesFraFil(fileChooser.showOpenDialog(teater));
            rader = minLab.antRader;
            kolonner = minLab.antKol;
        } catch (FileNotFoundException e) {
            System.out.println("feil ved innlesing av fil");
        }

        opprettBrett( minLab.hentbrett() );

        Scene scene = new Scene(root);
        teater.setScene(scene);

        teater.show();
    }

    private void opprettBrett(Rute[][] brett) {
        double knappStoerrelse = 1900/(rader+kolonner);
        for (int y=0; y < brett.length; y++) {
            for (int x=0; x < brett[y].length; x++) {
                Button b = new Button();

                //hvit
                if (brett[y][x] instanceof HvitRute) {
                    b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                    b.setOnAction(new RuteKlikk());
                }
                //sort
                else if (brett[y][x] instanceof SortRute) {
                    b.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: black;");
                }

                // dyanmisk skalering av knappene i forhold til brettet
                b.setMinSize(16, 16);
                b.setPrefSize(knappStoerrelse, knappStoerrelse);

                //b.setPrefWidth(40);  b.setPrefHeight(40);
                root.add(b, x, y);
            }
        }
    }

    class RuteKlikk implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            Button m = (Button) e.getSource();
            m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: lightblue;");

        }
    }
}





