import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.*;

import java.util.Random;

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

public class TTT extends Application {
    Text statusinfo;
    Rute brett[];
    boolean ferdig = false;

    class Rute extends Button {
        char merke = ' ';

        Rute() {
            super(" ");
            setFont(new Font(50));
            setPrefSize(120, 120);
        }

        void settMerke(char c) {
            setText(""+c);  merke = c;
        }
    }

    class Klikkbehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (! ferdig)
                spillO((Rute)e.getSource());
        }
    }

    class Stoppbehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage teater) {
        statusinfo = new Text("Velg en rute");
        statusinfo.setFont(new Font(20));
        statusinfo.setX(10);  statusinfo.setY(410);

        Button stoppknapp = new Button("Stopp");
        stoppknapp.setLayoutX(10);  stoppknapp.setLayoutY(450);
        Stoppbehandler stopp = new Stoppbehandler();
        stoppknapp.setOnAction(stopp);

        brett = new Rute[9+1];
        Klikkbehandler klikk = new Klikkbehandler();
        for (int i = 1;  i <= 9;  i++) {
            brett[i] = new Rute();
            brett[i].setOnAction(klikk);
        }

        GridPane rutenett = new GridPane();
        rutenett.setGridLinesVisible(true);
        rutenett.add(brett[1], 0, 0);
        rutenett.add(brett[2], 1, 0);
        rutenett.add(brett[3], 2, 0);
        rutenett.add(brett[4], 0, 1);
        rutenett.add(brett[5], 1, 1);
        rutenett.add(brett[6], 2, 1);
        rutenett.add(brett[7], 0, 2);
        rutenett.add(brett[8], 1, 2);
        rutenett.add(brett[9], 2, 2);
        rutenett.setLayoutX(10);  rutenett.setLayoutY(10);

        Pane kulisser = new Pane();
        kulisser.setPrefSize(400, 500);
        kulisser.getChildren().add(rutenett);
        kulisser.getChildren().add(statusinfo);
        kulisser.getChildren().add(stoppknapp);

        Scene scene = new Scene(kulisser);

        teater.setTitle("Tripp-trapp-tresko");
        teater.setScene(scene);
        teater.show();

        spillX();  // La X starte:
    }

    void spillO(Rute r) {
        if (r.merke != ' ') {
            statusinfo.setText("Ruten er opptatt; velg en annen");
            return;
        } else {
            statusinfo.setText("Velg en rute");
        }

        r.settMerke('O');
        if (harVunnet('O')) utropVinner('O');

        if (! ferdig) spillX();
    }

    Random tilfeldig = new Random();
    void spillX() {
        int p;
        do {
            p = tilfeldig.nextInt(9)+1;
        } while (brett[p].merke != ' ');
        brett[p].settMerke('X');

        if (harVunnet('X')) utropVinner('X');
        else if (erUavgjort()) utropUavgjort();
    }

    boolean harVunnet(char c) {
        return
                trePaaRad(1, 2, 3, c) ||  // Vannrett
                        trePaaRad(4, 5, 6, c) ||
                        trePaaRad(7, 8, 9, c) ||
                        trePaaRad(1, 4, 7, c) ||  // Loddrett
                        trePaaRad(2, 5, 8, c) ||
                        trePaaRad(3, 6, 9, c) ||
                        trePaaRad(1, 5, 9, c) ||  // Diagonal
                        trePaaRad(3, 5, 7, c);
    }

    boolean trePaaRad(int r1, int r2, int r3, char c) {
        if (brett[r1].merke != c) return false;
        if (brett[r2].merke != c) return false;
        if (brett[r3].merke != c) return false;
        return true;
    }

    boolean erUavgjort() {
        for (int i = 1;  i <= 9;  i++)
            if (brett[i].merke == ' ') return false;
        return true;
    }

    void utropVinner(char c) {
        statusinfo.setText(c + " har vunnet!");
        ferdig = true;
    }

    void utropUavgjort() {
        statusinfo.setText("Det ble uavgjort!");
        ferdig = true;
    }
}