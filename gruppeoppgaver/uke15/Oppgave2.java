import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Oppgave2 extends Application {

    TextField f1, f2;
    Text t;

    @Override
    public void start(Stage teater) {

        Font font = new Font(30);

        f1 = new TextField("Tall 1");
        f1.setFont(font);
        f2 = new TextField("Tall 2");
        f2.setFont(font);
        f1.setPrefWidth(200);
        f2.setPrefWidth(200);
        HBox linje1 = new HBox();
        linje1.getChildren().addAll(f1, f2);

        AritmetikkBehandler a = new AritmetikkBehandler();
        Button b1 = new Button("+");
        b1.setFont(font);
        b1.setOnAction(a);
        Button b2 = new Button("-");
        b2.setFont(font);
        b2.setOnAction(a);
        Button b3 = new Button("/");
        b3.setFont(font);
        b3.setOnAction(a);
        Button b4 = new Button("*");
        b4.setFont(font);
        b4.setOnAction(a);
        HBox linje2 = new HBox(60);
        linje2.getChildren().addAll(b1, b2, b3, b4);

        t = new Text("Resultat");
        t.setFont(font);
        t.setX(200);
        HBox linje3 = new HBox(20);
        linje3.setAlignment(Pos.BASELINE_CENTER);
        linje3.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        linje3.getChildren().add(t);

        Button avslutt = new Button("Avslutt");
        avslutt.setOnAction(new AvsluttBehandler());
        avslutt.setPrefWidth(400);
        HBox linje4 = new HBox();
        linje4.getChildren().add(avslutt);


        VBox rootPane = new VBox(10);
        rootPane.getChildren().addAll(linje1, linje2, linje3, linje4);

        Scene scene = new Scene(rootPane);
        teater.setScene(scene);
        teater.show();
    }

    class AritmetikkBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            double tall1;
            double tall2;

            try {
                tall1 = Integer.parseInt(f1.getText());
                tall2 = Integer.parseInt(f2.getText());
            } catch (NumberFormatException c) {
                t.setText("Ugyldig input");
                return;
            }

            Button knapp = (Button) e.getSource();
            String tegn = knapp.getText();

            double svar = 0;

            switch (tegn) {
                case "+":
                    svar = tall1 + tall2;
                    break;
                case "-":
                    svar = tall1 - tall2;
                    break;
                case "/":
                    svar = tall1 / tall2;
                    break;
                case "*":
                    svar = tall1 * tall2;
                    break;
            }

            t.setText(Double.toString(svar));
        }
    }

    // avslutter
    static class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }
}