import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.event.*;

/** DETTE PROGRAMMET ER EKSEMPEL PÅ DÅRLIG KODE!! */

public class TellMedVenting extends Application {
    int teller = 0;
    Text tellerSomText = new Text("0");

    public static void main(String[] arg) {
        launch();
    }

    // PGA denne metoden
    static void ventAktivt(int n) {
        // Kjør kode som tar omtrent n sekunder.
        for (int i = 1;  i <= n;  i++) {
            long x = 1;
            for (long j = 1;  j <= 250*1000*1000;  j++) {
                x = (3+j)*x % (1000*1000);
            }
        }
    }

    class NulleBehandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) {
            teller = 0;  tellerSomText.setText("0");
            ventAktivt(3);
        }
    }

    class TelleBehandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) {
            teller++;  tellerSomText.setText(""+teller);
            ventAktivt(3);
        }
    }

    class StoppBehandler implements EventHandler<ActionEvent> {
        @Override public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    @Override
    public void start(Stage teater) {
        tellerSomText.setFont(new Font(25));
        tellerSomText.setX(30);  tellerSomText.setY(25);

        Button telleknapp = new Button("Øk teller");
        telleknapp.setLayoutX(10);  telleknapp.setLayoutY(50);
        TelleBehandler tell = new TelleBehandler();
        telleknapp.setOnAction(tell);

        Button nulleknapp = new Button("Nullstill");
        nulleknapp.setLayoutX(14);  nulleknapp.setLayoutY(80);
        NulleBehandler nulle = new NulleBehandler();
        nulleknapp.setOnAction(nulle);

        Button stoppknapp = new Button("Stopp");
        stoppknapp.setLayoutX(20);  stoppknapp.setLayoutY(110);
        StoppBehandler stopp = new StoppBehandler();
        stoppknapp.setOnAction(stopp);

        Pane kulisser = new Pane();
        kulisser.setPrefSize(90,150);
        kulisser.getChildren().add(tellerSomText);
        kulisser.getChildren().add(telleknapp);
        kulisser.getChildren().add(nulleknapp);
        kulisser.getChildren().add(stoppknapp);

        Scene scene = new Scene(kulisser);
        teater.setTitle("Teller");
        teater.setScene(scene);
        teater.show();
    }
}