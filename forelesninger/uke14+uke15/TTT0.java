import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

/* Brettet:
      +---+---+---+
      | 1 | 2 | 3 |
      +---+---+---+
      | 4 | 5 | 6 |
      +---+---+---+
      | 7 | 8 | 9 |
      +---+---+---+

   Spillerne:
      X - maskinen
      O - brukeren
 */

public class TTT0 extends Application {
    @Override
    public void start(Stage teater) {
        GridPane rutenett = new GridPane();
        rutenett.setGridLinesVisible(true);
        rutenett.add(new Text(" "), 0, 0);
        rutenett.add(new Text("X"), 1, 0);
        rutenett.add(new Text("X"), 2, 0);
        rutenett.add(new Text(" "), 0, 1);
        rutenett.add(new Text("O"), 1, 1);
        rutenett.add(new Text("X"), 2, 1);
        rutenett.add(new Text("O"), 0, 2);
        rutenett.add(new Text("O"), 1, 2);
        rutenett.add(new Text(" "), 2, 2);

        Pane kulisser = new Pane();
        kulisser.getChildren().add(rutenett);

        Scene scene = new Scene(kulisser);

        teater.setTitle("Tripp-trapp-tresko");
        teater.setScene(scene);
        teater.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}