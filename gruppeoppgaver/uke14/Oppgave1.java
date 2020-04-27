import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Oppgave1 extends Application {

    public void start(Stage teater) {

        GridPane grid = new GridPane();

        int teller = 1;
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {

                StackPane pane = new StackPane();

                // oppretter, fyller og legger til rektangel foerst
                Rectangle r = new Rectangle(150, 150);
                r.setFill(Color.SKYBLUE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);

                // oppretter, setter font og legger til tekst over rektangel
                Text t = new Text(""+(teller++));
                t.setFont(new Font(50));
                pane.getChildren().add(t);

                grid.add(pane, j, i);
            }
        }

        Scene scene = new Scene(grid);

        teater.setScene(scene);
        teater.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}