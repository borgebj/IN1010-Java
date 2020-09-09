import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

// kulisser og annet
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

// non-javafx
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;


public class SpillBrett extends Application {

    VBox rootPane = new VBox();
    GridPane grid = new GridPane();
    ArrayList<SpillPerson> personer = new ArrayList<SpillPerson>();
    SpillPerson person; // personen spilleren maa lete etter
    TextField t1;

    int rader = 5;
    int kolonner = 5;

    private void lagOverBar() {
        HBox linje1 = new HBox(30);
        linje1.setAlignment(Pos.BASELINE_CENTER); linje1.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        linje1.setPadding(new Insets(5, 0, 5, 0));
        rootPane.setStyle("-fx-background-color: lightyellow");

        Font font = new Font(19);

        Text intro = new Text(" 1 random person is chosen - find the person by typing in attributes in the field down below. After 3 tries, you can guess their name.");
        intro.setFont(font);

        linje1.getChildren().add(intro);
        rootPane.getChildren().add(linje1);
    }

    private void lagBrett() {
        int i =0;

        for (int y=0; y < rader; y++) {
            for (int x=0; x < kolonner; x++) {

                // lag navn og Person
                String imgNavn = "pers"+i+".jpg";
                String navn = finnNavn(imgNavn);
                String[] attributter = finnAttributter(navn);

                lagPersoner(navn, attributter, x, y);

                // opprett + tilpass bildet
                ImageView img = new ImageView(imgNavn); i++;
                img.setFitHeight(180); img.setFitWidth(180);

                // legg til border
                BorderPane imgBorder = new BorderPane(img);
                imgBorder.setStyle("-fx-border-color: black; -fx-border-width: 3px");

                // legger til tekst over bildet
                Tooltip.install(img, new Tooltip(navn));

                // legg til i gridpane
                grid.add(imgBorder, x, y);
            }
        }
        rootPane.getChildren().add(grid);
        grid.setHgap(10);
        grid.setVgap(10);
    }

    private void fjernSvart() {
        for (int y=0; y < rader; y++) {
            for (int x=0; x < kolonner; x++) {
                hentImgFraGrid(x, y);

                BorderPane bilde = (BorderPane) hentImgFraGrid(x, y);
                ImageView img = (ImageView) bilde.getCenter();

                ColorAdjust blackout = new ColorAdjust();
                blackout.setBrightness(0.0);

                img.setEffect(blackout);
            }
        }
    }

    private String finnNavn(String navn) {
        if (navn.equals("pers0.jpg"))
            return "Kenneth";
        if (navn.equals("pers1.jpg"))
            return "Borris";
        if (navn.equals("pers14.jpg"))
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
            return "Steve Harvey";
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
        if (navn.equals("pers2.jpg"))
            return "Morpheus";
        if (navn.equals("pers15.jpg"))
            return "Donald Trump";
        if (navn.equals("pers16.jpg"))
            return "Snoop Dogg";
        if (navn.equals("pers17.jpg"))
            return "Tove Bjorgaas";
        if (navn.equals("pers18.jpg"))
            return "Oprah";
        if (navn.equals("pers19.jpg"))
            return "Jackie Chan";
        if (navn.equals("pers20.jpg"))
            return "Kpop dust";
        if (navn.equals("pers21.jpg"))
            return "Dr Phil";
        if (navn.equals("pers22.jpg"))
            return "Sheik";
        if (navn.equals("pers23.jpg"))
            return "Vegard";
        if (navn.equals("pers24.jpg"))
            return "Beyonce";
        return null;
    }

    private void lagUnderBar() {
        HBox linje2 = new HBox(30);
        linje2.setAlignment(Pos.BASELINE_CENTER); linje2.setStyle("-fx-border-color: black; -fx-border-width:2px;");
        grid.setAlignment(Pos.BASELINE_CENTER);

            Font font = new Font(12);

            Button b1 = new Button("Exit");
            b1.setOnAction(new AvsluttBehandler());
            b1.setPrefWidth(100); b1.setPrefHeight(45); b1.setFont(font);

            t1 = new TextField();
            t1.setPromptText(" 'GUESS HERE ' ");
            t1.setPrefWidth(140); t1.setPrefHeight(45); t1.setFont(font);

            Button b2 = new Button("Guess");
            b2.setOnAction(new InputBehandler());
            b2.setPrefWidth(100); b2.setPrefHeight(45); b2.setFont(font);

            Button b3 = new Button("Reset");
            b3.setOnAction(new LagNyBehandler(this));
            b3.setPrefWidth(100); b3.setPrefHeight(45); b3.setFont(font);


        linje2.getChildren().addAll(b1, t1, b2, b3);
        rootPane.getChildren().add(linje2);
    }

    private String[] finnAttributter(String navn) {
        if (navn.equals("Kenneth"))
            return new String[] {"white", "white skin", "glasses", "has glasses", "happy", "hair", "has hair", "brown hair", "smile", "smiles", "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("Borris"))
            return new String[] {"white", "white skin", "poor", "beard", "black beard", "blond", "hair", "blond hair", "happy", "smile", "smiles", "weird", "sweater", "has a sweater", "blue sweater",  "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("Morpheus"))
            return new String[] {"black", "black skin", "glasses", "has glasses", "black glasses", "bald", "bald", "jacket", "has a jacket", "black jacket"};
        if (navn.equals("Bill Clinton"))
            return new String[] {"white", "white skin", "rich", "hair", "white hair", "happy", "tie", "has a tie", "black tie", "smile", "smiles", "dying", "is dying", "red nose", "red nose", "black dress", "dress", "has a dress", "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("VSauce"))
            return new String[] {"white", "white skin", "glasses", "has glasses", "beard", "black beard", "bald", "bald", "red sweater", "brown dress", "dress", "has a dress"};
        if (navn.equals("Jan Kaare"))
            return new String[] {"white", "white skin", "poor", "sad", "glasses", "has glasses", "hair", "has hair", "white hair", "mustache", "has a mustache", "jacket","has a jacket", "beige jacket", "angry", "teeth", "shows teeth", "showing teeth"};
        if (navn.equals("Oskar"))
            return new String[] {"white", "hvit skin", "smile", "smiles", "happy", "hair", "has hair", "blonde", "blonde hair", "sweater", "has a sweater", "red sweater", "watermark", "teeth", "shows teeth", "showing teeth"};
        if (navn.equals("Casey Neistat"))
            return new String[] {"white", "hvit skin", "glasses", "has glasses", "black glasses", "hair", "has hair", "black haar", "sweater", "has a sweater", "white sweater", "striped sweater"};
        if (navn.equals("Steve Harvey"))
            return new String[] {"black", "black skin", "rich", "bleached teeth", "happy", "tie", "has a tie", "svart slips", "black tie", "smile", "smiles", "mustache", "has a mustache", "black mustache", "dress", "has a dress", "black dress", "bald", "bald", "teeth", "weird", "showing teeth", "shows tenner"};
        if (navn.equals("Erna Solberg"))
            return new String[] {"white", "white skin", "smile", "smiles", "hungry", "hair", "has hair", "gray hair", "grey hair", "dress", "has a dress", "pink dress", "fat", "weird"};
        if (navn.equals("The Rock"))
            return new String[] {"white", "white skin", "smile", "rich", "happy", "smiles", "glasses", "has glasses", "black glasses", "bald", "bald", "dress", "has a dress", "brown dress", "teeth", "shows teeth", "showing tenner"};
        if (navn.equals("Danny Devito"))
            return new String[] {"white", "white skin", "smile", "smiles", "rich", "happy", "smiles", "sidehair", "glasses", "has glasses", "bald", "bald", "weird", "sidehair", "dress", "has a dress", "black dress", "beard", "has a beard", "black beard", "mustache", "has a mustache", "black mustache", "fat", "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("Larry King"))
            return new String[] {"white", "white skin", "senior", "smiles", "scary", "glasses", "has glasses", "tie", "has a tie", "blue tie", "hair", "has hair", "white hair", "weird", "sweater", "has a sweater", "blue sweater", "angry"};
        if (navn.equals("Barack Obama"))
            return new String[] {"black", "black skin", "rich", "sad", "dress", "hair", "has hair", "has a dress", "black dress", "president"};
        if (navn.equals("Harrison Ford"))
            return new String[] {"white", "white skin", "rich", "hair", "white hair", "tie", "has a tie", "bowtie", "has a bowtie", "happy", "smile", "smiles", "beard", "white beard", "has a beard", "black dress", "dress", "has a dress", "teeth", "shows teeth", "showing teeth"};
        if (navn.equals("Donald Trump"))
            return new String[] {"white", "white skin", "rich", "orange", "happy", "orange skin", "orange", "orange skin", "smile", "smiles", "hair", "has hair", "blonde", "blonde hair", "bleached hair", "dress", "has a dress", "black dress", "president", "teeth", "shows teeth", "showing teeth"};
        if (navn.equals("Snoop Dogg"))
            return new String[] {"black", "black skin", "rich", "hair", "has hair", "black hair", "white sweater", "has a sweater", "sweater", "glasses", "has glasses", "angry", "red sweater", "beard", "has a beard", "black beard", "sidehair"};
        if (navn.equals("Tove Bjorgaas"))
            return new String[] {"white", "hvit skin", "weird", "jerk", "sweater", "has a sweater", "blue sweater", "hair", "has hair", "black hair", "racist", "scared", "eardubs", "teeth", "open mouth", "teeth", "shows teeth", "showing teeth", "tenner", "viser tenner"};
        if (navn.equals("Oprah"))
            return new String[] {"black", "black skin", "rich", "angry", "glasses", "jerk", "white glasses", "has glasses", "sweater", "has sweater", "white sweater", "curls", "curly hair", "hair", "hashair", "brown hair", "fat", "shows teeth", "teeth", "showing teeth"};
        if (navn.equals("Jackie Chan"))
            return new String[] {"yellow", "yellow skin", "asian", "glasses", "has glasses", "hair", "has hair", "black haar", "sweater", "has a sweater", "black sweater", "white sweater", "short hair", "angry", "sint"};
        if (navn.equals("Kpop dust"))
            return new String[] {"yellow", "yellow skin", "asian", "jerk", "hair", "has hair", "white hair", "bleached hair", "eardubs", "sweater", "has sweater", "red sweater", "black sweater", "stupid", "weird", "kpop", "teeth", "shows sweater", "showing teeth"};
        if (navn.equals("Dr Phil"))
            return new String[] {"white", "white skin", "rich", "smile", "smiles", "tie", "has a tie", "blue tie", "happy", "bald", "bald", "dress", "has a dress", "black dress", "mustache", "has a mustache", "black mustache", "sidehair", "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("Sheik"))
            return new String[] {"white", "white skin", "rich", "hood", "has a hood", "has a hoodie", "hoodie", "happy", "beard", "has a beard", "black beard", "smile", "smiles", "headgear", "has headgear", "sweater", "has a sweater", "white sweater"};
        if (navn.equals("Vegard"))
            return new String[] {"white", "white skin", "poor", "hair", "has hair", "happy", "brown hair", "smile", "smiles", "weird", "jacket", "has a jacket", "hood", "hoodie", "has a hood", "had a hoodie", "black jacket", "teeth", "showing teeth", "shows teeth"};
        if (navn.equals("Beyonce"))
            return new String[] {"black", "black skin", "black skin", "rich", "hair", "has hair", "happy", "blonde hair", "blonde", "smile", "smiles", "sweater", "has a sweater", "white sweater", "lipstick", "teeth", "shows teeth", "showing teeth"};
        return null;
    }

    private void lagPersoner(String navn, String[] attributter, int kol, int rad) {
        SpillPerson person = new SpillPerson(navn, attributter, kol, rad);
        personer.add(person);
    }

    private void velgPerson() {
        Random rand = new Random();
        this.person = personer.get(rand.nextInt(personer.size()));
    }

    // metode som henter noden=image paa plassen til col og row
    private Node hentImgFraGrid(int col, int row) {

        //itererer gjennom gridpane sine nodes og sjekker om ruten stemmer
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        } return null;
    }


    @Override //hoved-shiten
    public void start(Stage teater) {

        lagOverBar();
        lagBrett();
        lagUnderBar();
        velgPerson();

        Scene scene = new Scene(rootPane);

        teater.setTitle("Guess Who");
        teater.setScene(scene);
        teater.show();
    }


    // avslutter
    private static class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    private static class LagNyBehandler implements EventHandler<ActionEvent> {
        SpillBrett hoved;

        public LagNyBehandler(SpillBrett hoved) {
            this.hoved = hoved;
        }
        @Override
        public void handle(ActionEvent e) {
            hoved.fjernSvart();
            hoved.velgPerson();
        }
    }

    private class InputBehandler implements EventHandler<ActionEvent> {

        int antForsok = 0;
        int antallGjemt = 0;
        private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

        @Override
        public void handle(ActionEvent e) {
            try { // try-catch om input er tom
                String input = t1.getText();

                // oversatt
                String[] NoNoWords = new String[]{"white", "white skin", "black", "black skin", "rich"};
                boolean erI = Arrays.stream(NoNoWords).anyMatch(input::equals);

                if (antForsok < 3 && erI) {
                    alert.setHeaderText("Try again");
                    alert.setContentText(input + " is too op, wait a few rounds first.");
                    alert.show();
                }
                else {
                    if (input.equals(person.hentNavn()) && antForsok > 3 && antallGjemt > 20) {
                        alert.setHeaderText(input + " is correct!");
                        alert.setContentText("You won!");
                        alert.show();
                        antForsok = 0;
                    } else {
                        antForsok++;

                        for (SpillPerson pers : personer) {
                            if (pers.contains(input, person)) {
                                int rad = pers.hentRad();
                                int kolonne = pers.hentKol();

                                BorderPane bilde = (BorderPane) hentImgFraGrid(kolonne, rad);
                                assert bilde != null; // assert om bildet ikke er null
                                ImageView img = (ImageView) bilde.getCenter();

                                ColorAdjust blackout = new ColorAdjust();
                                blackout.setBrightness(-1.0);

                                img.setEffect(blackout);
                                antallGjemt++;
                            }
                        }
                    }
                }
            } catch(StringIndexOutOfBoundsException ignored) {}
        }
    }
}