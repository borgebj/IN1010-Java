import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes
import javafx.scene.layout.GridPane;

// kulisser og annet
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.event.*;


// non-javafx
import java.io.FileNotFoundException;



public class GUI extends Application {

    private Labyrint minLab;
    private int rader;
    private int kolonner;
    private final GridPane root = new GridPane();
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage teater) {

        try {
            minLab = Labyrint.lesFraFil(fileChooser.showOpenDialog(teater));
            rader = minLab.antRader;
            kolonner = minLab.antKol;
        } catch (FileNotFoundException e) { System.out.println("feil ved innlesing av fil"); }

        opprettBrett( minLab.hentbrett() );

        Scene scene = new Scene(root);
        teater.setScene(scene);

        teater.show();
    }

    private void opprettBrett(Rute[][] brett) {
        double knappStoerrelse = 1900.0/(rader+kolonner);
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

                root.add(b, x, y);
            }
        }
    }

    static class RuteKlikk implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            Button m = (Button) e.getSource();
            m.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: cyan;");

        }
    }
}





