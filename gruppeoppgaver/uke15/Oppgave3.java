import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Platform;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.io.File;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javafx.stage.FileChooser;

import java.awt.Toolkit;
import javafx.scene.transform.Scale;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;

/** IDE fra Oppgave 3 fra gruppeovelser 23.04.20 - endret for det meste ALT selv */


public class Oppgave3 extends Application {

    FileChooser fileChooser = new FileChooser();
    GridPane rootPane;
    TextField tf;

    // default-options
    int ruterX  = 15;
    int ruterY = 15;
    int resX = 1920;
    int resY = 1200;
    double borderWidth = 0.1;
    boolean grid = true;
    boolean svartBorder = true;
    String currentColor = "black";


    // fikser noen av fargene som er feil
    private String fiksFarger() {
        switch (currentColor) {
            case "burlywood":
            case "brown": return "burlywood";
            case "bisque":
            case "beige": return "bisque";
        } return currentColor;
    }

    // hjelpemetode for klikk og drag av knapper i lagBrett()
    private void farger(Region b) {
        currentColor = fiksFarger();
        if (!grid) b.setStyle("-fx-background-color:"+currentColor+";");
        else b.setStyle("-fx-border-color: black; -fx-border-width:"+borderWidth+"px; -fx-background-color:"+currentColor+";");
        if (svartBorder && currentColor.equals("black")) b.setStyle("-fx-border-color: white; -fx-border-width:"+borderWidth+"px; -fx-background-color:"+currentColor+";");
    }

    // lager brettet og linjene under
    private void lagBrett() {
        rootPane.getChildren().clear();
        for (int y=0; y < ruterY; y++) {
            for (int x=0; x < ruterX; x++) {

                Region b = new Region();
                if (grid) b.setStyle("-fx-border-color: black; -fx-border-width:"+borderWidth+"px;");
                b.setPrefWidth(40);  b.setPrefHeight(40);

                // registrere muse-drag
                b.setOnMouseDragEntered(
                        event -> {
                            event.consume();
                            farger(b);
                        });

                // registrere museklikk
                b.setOnMouseClicked(
                        event -> {
                            event.consume();
                            farger(b);
                        });
                rootPane.add(b, x, y);
            }
        }
    }

    private void nyttBrett() {
        try {
            String[] tall = tf.getText().split("x| x ");
            ruterY = Integer.parseInt(tall[0]);
            ruterX = Integer.parseInt(tall[1]);
            lagBrett();
        } catch (NumberFormatException c) {
            tf.setText("Ugyldig input");
        }}

    private Menu opprettFilMenu() {
        Menu fileMenu = new Menu("_File");
        MenuItem newBoard = new MenuItem("Nytt brett...");  newBoard.setOnAction(a->{a.consume(); lagBrett();});
        MenuItem lagre = new MenuItem("Lagre...");  lagre.setOnAction(new BildeBehandler());
        MenuItem settings = new MenuItem("Innstillinger...");  settings.setOnAction(new settingsBehandler());
        MenuItem avslutt = new MenuItem("Avslutt...");  avslutt.setOnAction(b->{b.consume(); Platform.exit();});
        fileMenu.getItems().addAll(lagre, newBoard, new SeparatorMenuItem(), settings, new SeparatorMenuItem(), avslutt);
        return fileMenu;
    }

    private HBox opprettLinje1() {
        HBox linje1 = new HBox(); linje1.setAlignment(Pos.BASELINE_CENTER);

        rootPane = new GridPane(); rootPane.setStyle("-fx-border-color: black; -fx-border-width:2px;");
        lagBrett();

        linje1.getChildren().add(rootPane);
        return linje1;
    }

    private HBox opprettLinje2() {
        HBox linje2 = new HBox(20); linje2.setAlignment(Pos.BASELINE_CENTER);
        Font font = new Font(15);

        // tekstfelt
        tf = new TextField(); tf.setPrefWidth(100);
        tf.setPromptText("Bredde x Hoyde");

        // endre knapp
        Button b = new Button("Endre");
        b.setPrefWidth(80);
        b.setOnAction(ae->{ae.consume(); nyttBrett();});
        b.setFont(font);

        // skille streker
        Font font1 = new Font(30);
        Text t1 = new Text("|  "); t1.setFont(font1);
        Text t2 = new Text("  |  "); t2.setFont(font1);
        Text t3 = new Text("  |"); t3.setFont(font1);

        // reset knapp
        Button reset = new Button("Reset");
        reset.setPrefWidth(80);
        reset.setFont(font);
        reset.setOnAction(be->{be.consume(); lagBrett();});

        // avslutt knapp
        Button avslutt = new Button("Avslutt");
        avslutt.setPrefWidth(90);
        avslutt.setFont(font);
        avslutt.setOnAction(ce->{ce.consume(); Platform.exit();});

        linje2.getChildren().addAll(t1, tf, b, t2, reset, avslutt, t3);
        return linje2;
    }

    private HBox opprettLinje3() {
        HBox linje3 = new HBox(20);linje3.setAlignment(Pos.BASELINE_CENTER); linje3.setPadding(new Insets(10, 0, 0, 0));

        StackPane linje3Stack = new StackPane();

        Rectangle rrr = new Rectangle(565, 27); rrr.setFill(Color.GRAY);

        HBox box = new HBox(15); box.setAlignment(Pos.BASELINE_CENTER);
        Button b1 = new Button("red"); b1.setOnAction(ae->{ae.consume(); currentColor=b1.getText();}); b1.setStyle("-fx-background-color: red; -fx-border-color: black;");
        Button b2 = new Button("orange"); b2.setOnAction(be->{be.consume(); currentColor=b2.getText();}); b2.setStyle("-fx-background-color: orange; -fx-border-color: black;");
        Button b3 = new Button("yellow"); b3.setOnAction(ce->{ce.consume(); currentColor=b3.getText();}); b3.setStyle("-fx-background-color: yellow; -fx-border-color: black;");
        Button b4 = new Button("green"); b4.setOnAction(de->{de.consume(); currentColor=b4.getText();}); b4.setStyle("-fx-background-color: green; -fx-border-color: black;");
        Button b5 = new Button("blue"); b5.setOnAction(ee->{ee.consume(); currentColor=b5.getText();}); b5.setStyle("-fx-background-color: blue; -fx-border-color: black;");
        Button b6 = new Button("brown"); b6.setOnAction(fe->{fe.consume(); currentColor=b6.getText();}); b6.setStyle("-fx-background-color: burlywood; -fx-border-color: black;");
        Button b7 = new Button("beige"); b7.setOnAction(ge->{ge.consume(); currentColor=b7.getText();}); b7.setStyle("-fx-background-color: bisque; -fx-border-color: black;");
        Button b8 = new Button("grey"); b8.setOnAction(he->{he.consume(); currentColor=b8.getText();}); b8.setStyle("-fx-background-color: grey; -fx-border-color: black;");
        Button b9 = new Button("white"); b9.setOnAction(ie->{ie.consume(); currentColor=b9.getText();}); b9.setStyle("-fx-background-color: white; -fx-border-color: black;");
        Button b10 = new Button("black"); b10.setOnAction(je->{je.consume(); currentColor=b10.getText();}); b10.setStyle("-fx-background-color: black; -fx-border-color: black;");
        box.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);

        linje3Stack.getChildren().addAll(rrr, box);

        linje3.getChildren().add(linje3Stack);
        return linje3;
    }

    private void resolutionOption() {
        List<String> choices = new ArrayList<>();
        choices.add("1920x1200");
        choices.add("2560x1440");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("....", choices);
        dialog.setTitle("Oppstart");
        dialog.setHeaderText("Velg din resolution");
        dialog.setContentText("Valg:");

        Optional<String> result = dialog.showAndWait();
        if (result.get().equals("1920x1200")) {
            resX = 1920;
            resY = 1200;
        } else if (result.get().equals("2560x1440")) {
            resX = 2560;
            resY = 1440;
        }
    }


    @Override
    public void start(Stage teater) {

        Menu fileMenu = opprettFilMenu();

        // lager en BorderPane for aa legge til MenuBar
        BorderPane layout = new BorderPane();
            MenuBar menuBar = new MenuBar();
            menuBar.getMenus().addAll(fileMenu);
        layout.setTop(menuBar);

        // henter linje1, 2 og 3 fra hver sine funksjoner som oppretter dem
        HBox linje1 = opprettLinje1();
        HBox linje2 = opprettLinje2();
        HBox linje3 = opprettLinje3();
        resolutionOption();

        // legger sammen alle linjene i en hoved-pane
        VBox root = new VBox();
            Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
            double width = resolution.getWidth();
            double height = resolution.getHeight();
            double w = width/resX;
            double h = height/resY;
            Scale scale = new Scale(w, h, 0, 0);
            root.getTransforms().add(scale);
        root.getChildren().addAll(layout, linje1, linje2, linje3);

        // start full press-drag-release gesture
        root.setOnDragDetected(
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        event.consume();
                        root.startFullDrag();
                    }
                });

        // gjoer at man kan enkelt lagre bildet sitt
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("jpeg", "*.jpeg"));

        // oppretter scene og starter "teatret"
        Scene scene = new Scene(root);
        teater.setScene(scene);
        teater.show();
    }





    // ulike behandlere - "EventHandlers"

    class BildeBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            WritableImage image = rootPane.snapshot(new SnapshotParameters(), null);
            File f1 = fileChooser.showSaveDialog(null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", f1);
            } catch (IOException ignore) {}
        }
    }

    class settingsBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent ignore) {
            Alert settingsBox = new Alert(Alert.AlertType.CONFIRMATION);
            settingsBox.setTitle("Settings...");
            settingsBox.setHeaderText("Velg hva du vil endre. (NB! endringer vil resette brett!");

            ButtonType border = new ButtonType("Sorte-ruter");
            ButtonType grid = new ButtonType("Grid");

            settingsBox.getButtonTypes().addAll(border, grid);
            Optional<ButtonType> resultat = settingsBox.showAndWait();

            if (resultat.get() == border) borderOption();
            else if (resultat.get() == grid) gridOption();
        }
        private void gridOption() {
            Alert gridBox = new Alert(Alert.AlertType.CONFIRMATION);
            gridBox.setTitle("Settings...");
            gridBox.setHeaderText("Velg grid-type.");

            ButtonType med = new ButtonType("Med grid");
            ButtonType uten = new ButtonType("Uten grid");

            gridBox.getButtonTypes().addAll(med, uten);
            Optional<ButtonType> resultat = gridBox.showAndWait();

            if (resultat.get() == uten) grid = false;
            else grid = true;
            lagBrett();
        }
        private void borderOption() {
            Alert borderBox = new Alert(Alert.AlertType.CONFIRMATION);
            borderBox.setTitle("Settings...");
            borderBox.setHeaderText("Skal sorte ruter ha hvite borders?");

            ButtonType ja = new ButtonType("Ja");
            ButtonType nei = new ButtonType("Nei");

            borderBox.getButtonTypes().addAll(ja, nei);
            Optional<ButtonType> resultat = borderBox.showAndWait();

            if (resultat.get() == ja) svartBorder = true;
            else svartBorder = false;
            lagBrett();
        }
    }
}