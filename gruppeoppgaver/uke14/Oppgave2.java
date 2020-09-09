import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalTime;


public class Oppgave2 extends Application {

    Text tid = new Text(naa());

    @Override
    public void start(Stage teater) {

        StackPane pane = new StackPane();
            Rectangle r = new Rectangle(300, 100, Color.WHITE);
            r.setStyle("-fx-stroke: black; -fx-stroke-width: 8;");
            r.setFill(Color.LIGHTGREY);
        pane.getChildren().add(r);

        // setter font paa tekst og legger til
        tid.setFont(new Font(80));
        pane.getChildren().add(tid);

        Scene scene = new Scene(pane);
        teater.setScene(scene);
        teater.show();

        Teller teller = new Teller();
        Thread thread = new Thread(teller);
        thread.start();
    }

    private class Teller implements Runnable {
        public void run() {
            while(true) {
                try { Thread.sleep(1000); }
                catch (InterruptedException e) { System.out.println(e); }
                tid.setText(naa());
            }
        }
    }

    private static String naa() {
        LocalTime t = LocalTime.now();
        return String.format("%02d:%02d:%02d", t.getHour(), t.getMinute(), t.getSecond());
    }

    public static void main(String[] args) {
        launch(args);
    }
}