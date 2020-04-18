import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Hovedprogram extends Application {

    @Override
    public void start(Stage teater) {

        StackPane pane = new StackPane();

        // oppretter, setter posisjon og legger til tekst
        Text t = new Text("Hello world!");
        t.setFont(new Font(50));
        t.setY(50);
        t.setX(50);
        pane.getChildren().add(t);


        // oppretter, setter posisjon og legger til sirkel
        Circle c = new Circle(50, Color.RED);
        c.setCenterY(50);
        c.setCenterX(50);
        pane.getChildren().add(c);

        Scene scene = new Scene(pane);
        teater.setScene(scene);
        teater.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}