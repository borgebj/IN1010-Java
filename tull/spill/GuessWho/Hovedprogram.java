import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Tooltip;


public class Hovedprogram extends Application {

    VBox rootPane = new VBox();
    ArrayList<SpillPerson> personer = new ArrayList<SpillPerson>();
    SpillPerson person; // personen spilleren maa lete etter
    TextField t1;

    private void lagBrett() {
        GridPane grid = new GridPane();

        int i =0;

        for (int y=0; y < 4; y++) {
            for (int x=0; x < 4; x++) {

                // lag navn og Person
                String imgNavn = "pers"+i+".jpg";
                String navn = finnNavn(imgNavn);
                String[] attributter = finnAttributter(imgNavn);
                lagPersoner(navn, attributter);

                // opprett + tilpass bildet
                GuessImage img = new GuessImage(imgNavn, x, y); i++;
                img.setFitHeight(180); img.setFitWidth(180);

                // legg til border
                BorderPane imgBorder = new BorderPane(img);
                imgBorder.setStyle("-fx-border-color: black; -fx-border-width: 3px");

                Tooltip.install(img, new Tooltip(navn));

                // legg til i gridpane
                grid.add(imgBorder, x, y);
            }
        }
        rootPane.getChildren().add(grid);
        grid.setHgap(10);
        grid.setVgap(10);
    }

    private String finnNavn(String navn) {
        if (navn.equals("pers0.jpg"))
            return "Kenneth";
        if (navn.equals("pers1.jpg"))
            return "Borris";
        if (navn.equals("pers2.jpg"))
            return "Harrison Ford";
        if (navn.equals("pers3.jpg"))
            return "Bill Clinton";
        if (navn.equals("pers4.jpg"))
            return "VSauce";
        if (navn.equals("pers5.jpg"))
            return "Jan Kaare";
        if (navn.equals("pers6.jpg"))
            return "Oskar";
        if (navn.equals("pers7.jpg"))
            return "Casey Neistat";
        if (navn.equals("pers8.jpg"))
            return "Dr Phil";
        if (navn.equals("pers9.jpg"))
            return "Erna Solberg";
        if (navn.equals("pers10.jpg"))
            return "The Rock";
        if (navn.equals("pers11.jpg"))
            return "Danny Devito";
        if (navn.equals("pers12.jpg"))
            return "Larry King";
        if (navn.equals("pers13.jpg"))
            return "Barack Obama";
        if (navn.equals("pers14.jpg"))
            return "Morpheus";
        if (navn.equals("pers15.jpg"))
            return "Donald Trump";
        return null;
    }

    private void lagUnderBar() {
        HBox linje2 = new HBox(30); linje2.setAlignment(Pos.BASELINE_CENTER); linje2.setStyle("-fx-border-color: black; -fx-border-width: 3px;");

            Font font = new Font(12);

            Button b1 = new Button("Avslutt");
            b1.setOnAction(new AvsluttBehandler());
            b1.setPrefWidth(100); b1.setPrefHeight(45); b1.setFont(font);

            t1 = new TextField(" 'HER GJETTER DU' ");
            t1.setPrefWidth(120); t1.setPrefHeight(45); t1.setFont(font);

            Button b2 = new Button("Gjett");
            b2.setOnAction(new InputBehandler());
            b2.setPrefWidth(100); b2.setPrefHeight(45); b2.setFont(font);


        linje2.getChildren().addAll(b1, t1, b2);
        rootPane.getChildren().add(linje2);
    }

    private String[] finnAttributter(String navn) {
        if (navn.equals("pers0.jpg"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "haar", "brunt haar", "smil", "smiler"};
        if (navn.equals("pers1.jpg"))
            return new String[] {"hvit", "hvit hud", "skjegg", "svart skjegg", "blond", "haar", "blondt haar", "smil", "smiler", "rar"};
        if (navn.equals("pers2.jpg"))
            return new String[] {"hvit", "hvit hud", "haar", "hvitt haar", "smil", "smiler", "skjegg", "hvitt skjegg", "mye skjegg", "svart dress", "dress", "har dress"};
        if (navn.equals("pers3.jpg"))
            return new String[] {"hvit", "hvit hud", "haar", "hvitt haar", "smil", "smiler", "dodende", "doedende", "rod nese", "roed nese", "svart dress", "dress", "har dress"};
        if (navn.equals("pers4.jpg"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "skjegg", "svart skjegg", "skallet", "bald", "rod genser", "brun dress", "dress", "har dress"};
        if (navn.equals("pers5.jpg"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "haar", "har haar", "hvitt haar", "sur"};
        if (navn.equals("pers6.jpg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "haar", "har haar", "blond", "blondt haar", "rod genser", "watermark"};
        if (navn.equals("pers7.jpg"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "svarte briller", "haar", "har haar", "svart haar", "hvit genser", "stripete genser"};
        if (navn.equals("pers8.jpg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "skallet", "bald", "dress", "har dress", "svart dress", "bart", "har bart", "svart bart", "sidehaar"};
        if (navn.equals("pers9.jpg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "haar", "har haar", "gratt haar", "graa haar", "dress", "har dress", "rosa dress", "feit", "rar"};
        if (navn.equals("pers10.jpg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "briller", "har briller", "svarte briller", "skallet", "bald", "dress", "har dress", "brun dress"};
        if (navn.equals("pers11.jpg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "briller", "har briller", "skallet", "bald", "sidehaar", "dress", "har dress", "svart dress", "skjegg", "har skjegg", "svart skjegg", "bart", "har bart", "svart bart", "feit"};
        if (navn.equals("pers12.jpg"))
            return new String[] {"hvit", "hvit hud", "skummel", "briller", "har briller", "haar", "har haar", "hvitt haar", "blaa genser", "sur"};
        if (navn.equals("pers13.jpg"))
            return new String[] {"svart", "svart hud", "trist", "dress", "har dress", "svart dress", "president"};
        if (navn.equals("pers14.jpg"))
            return new String[] {"svart", "svart hud", "briller", "svarte briller", "skallet", "bald"};
        if (navn.equals("pers15.jpg"))
            return new String[] {"hvit", "hvit hud", "oransj", "oransj hud", "oransje", "oransje hud", "smil", "smiler", "haar", "har haar", "blond", "blondt haar", "dress", "har dress", "svart dress", "president"};
        return null;
    }

    private void lagPersoner(String navn, String[] attributter) {
        SpillPerson person = new SpillPerson(navn, attributter);
        personer.add(person);
    }

    private void velgPerson() {
        Random rand = new Random();
        this.person = personer.get(rand.nextInt(personer.size()));
    }

    // metode som henter noden=image paa plassen til col og row
    private Node hentImgFraGrid(int col, int row) {

        //itererer gjennom gridpane sine nodes og sjekker om ruten stemmer
        for (Node node : rootPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        } return null;
    }


    @Override //hoved-shiten
    public void start(Stage teater) {

        lagBrett();
        lagUnderBar();
        velgPerson();

        Scene scene = new Scene(rootPane);

        teater.setTitle("Guess Who");
        teater.setScene(scene);
        teater.show();
    }


    private static class GuessImage extends ImageView {

        private final int kolonne;
        private final int rad;

        public GuessImage(String url, int kol, int rad) {
            super(url);
            this.kolonne = kol;
            this.rad = rad;
        }

        public int getKol() {
            return kolonne;
        }
        public int getRad() {
            return rad;
        }
    }

    // avslutter
    private static class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    private class InputBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            String input = t1.getText();

            for (SpillPerson pers : personer) {
                if (pers.contains(input, person)) {
                    System.out.println("ja");
                }
                else {
                    System.out.println("nei");
                }
            }
        }
    }
}