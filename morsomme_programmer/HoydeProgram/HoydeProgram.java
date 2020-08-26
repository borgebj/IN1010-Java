import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.event.*;

/** Laget av borgebj for morro - basert paa en meme :P */



public class HoydeProgram extends Application {

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private TextField prompt;

    @Override
    public void start(Stage teater) {

        Font textFont = new Font(16);

        Pane main = new Pane(); main.setMinSize(275, 120); main.setStyle("-fx-background-color: lightgrey");
            VBox linje1 = new VBox(30); linje1.setLayoutY(30);

                HBox hbox1 = new HBox(10);

                    Text t1 = new Text("Skriv inn din hoyde:");
                        t1.setFont(textFont);
                        t1.setTranslateX(5);

                    prompt = new TextField();
                        prompt.setPrefWidth(80);

                    Text t2 = new Text("cm");
                        t2.setFont(textFont);

                hbox1.getChildren().addAll(t1, prompt, t2); hbox1.setLayoutY(25);

                HBox hbox2 = new HBox(20); hbox2.setTranslateX(155);
                    Font knappFont = new Font(12);
                    Button ok = new Button("OK");
                    ok.setFont(knappFont);
                    ok.setOnAction(new BeregnBehandler());

                    Button avslutt = new Button("Avslutt");
                    avslutt.setFont(knappFont);
                    avslutt.setOnAction(new AvsluttBehandler());
                hbox2.getChildren().addAll(avslutt, ok);

        linje1.getChildren().addAll(hbox1, hbox2);
        main.getChildren().addAll(linje1);


        Scene scene = new Scene(main);
        teater.setTitle("Hoyde-kalkulator");
        teater.setResizable(false);
        teater.setScene(scene);
        teater.show();
    }

    class BeregnBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Image image = new Image("stonks.jpg", 125, 125, true, true);
            ImageView imageView = new ImageView(image);
            alert.setGraphic(imageView);

            String hoyde = prompt.getText();
            alert.setTitle("Resultat");
            alert.setHeaderText("Hoyden din er " + hoyde + "cm!");
            alert.show();
        }
    }
    class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

}