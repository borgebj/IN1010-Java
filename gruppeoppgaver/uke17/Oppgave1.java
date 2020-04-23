import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.*;

public class Oppgave1 extends Application {

    // global slik at eventhandler kan bruke den
    TextField t;
    Text te;

    @Override
    public void start(Stage teater) {

        Font font = new Font(30);

        // kuliss nr 1 - tekstfelt
        t = new TextField("Skriv inn ditt navn");
        t.setFont(font);

        // kuliss nr 2 - knapp
        Button b = new Button("Vis hilsen");
        b.setFont(font);
        b.setOnAction(new VisHilsenBehandler());

        // kuliss nr 3 - tekst
        te = new Text("");
        te.setFont(font);


        VBox rootPane = new VBox(20);
        rootPane.getChildren().addAll(t, b, te);

        Scene scene = new Scene(rootPane);
        teater.setTitle("Personlig hilsen");
        teater.setScene(scene);
        teater.show();
    }

    class VisHilsenBehandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            String navn = t.getText();
            te.setText("Vaer hilset " + navn);
            t.setText("");
        }
    }
}