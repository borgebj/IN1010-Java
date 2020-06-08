import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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

// non-javafx
import java.util.ArrayList;
import java.util.Random;


public class SpillBrett extends Application {

    VBox rootPane = new VBox();
    GridPane grid = new GridPane();
    ArrayList<SpillPerson> personer = new ArrayList<SpillPerson>();
    SpillPerson person; // personen spilleren maa lete etter
    TextField t1;

    int rader = 5;
    int kolonner = 5;

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
        linje2.setAlignment(Pos.BASELINE_CENTER); linje2.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        grid.setAlignment(Pos.BASELINE_CENTER); linje2.setStyle("-fx-border-color: black; -fx-border-width: 3px;");

        Font font = new Font(12);

        Button b1 = new Button("Exit");
        b1.setOnAction(new AvsluttBehandler());
        b1.setPrefWidth(100); b1.setPrefHeight(45); b1.setFont(font);

        t1 = new TextField(" 'YOU GUESS HERE' ");
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

    private String[] finnAttributter(String name) {
        if (name.equals ("Kenneth"))
            return new String [] {"white", "white skin", "glasses", "has glasses", "happy", "hair", "has hair", "brown hair", "smiles", "smile"} ;
        if (name.equals ("Borris"))
            return new String [] {"white", "white skin", "poor", "beard", "black beard", "blonde", "has hair", "hair", "blonde hair", "happy", "smiles", "smile", "weird", "sweater", "has sweater","blue sweater"};
        if (name.equals ("Morpheus"))
            return new String [] {"black", "black skin", "glasses", "has glasses", "black glasses", "bald", "jacket", "has jacket", "black jacket" };
        if (name.equals ("Bill Clinton"))
            return new String [] {"white", "white skin", "rich", "hair", "white hair", "happy", "smiles", "smile", "dying", "is dying", "red nose", "black dress", "dress", "has dress"};
        if (name.equals ("VSauce"))
            return new String [] {"white", "white skin", "glasses", "has glasses", "beard", "black beard", "bald", "red sweater", "brown dress" , "dress", "has dress"};
        if (name.equals ("Jan Kaare"))
            return new String [] {"white", "white skin", "poor", "sad", "glasses", "has glasses", "hair", "has hair", "white hair", "mustache", "has mustache", "angry"};
        if (name.equals ("Oskar"))
            return new String [] {"white", "white skin", "smiles", "smile", "happy", "hair", "has hair", "blonde", "blonde hair", "red sweater", "Watermark"};
        if (name.equals ("Casey Neistat"))
            return new String [] {"white", "white skin", "glasses", "has glasses", "black glasses", "hair", "has hair", "black hair", "white sweater", "striped sweater"};
        if (name.equals ("Dr Phil"))
            return new String [] {"white", "white skin", "rich", "smiles", "smile", "happy", "bald", "dress", "has dress", "black dress ","mustache", "has mustache", "black mustache", "side hair", "sidehair"};
        if (name.equals ("Steve Harvey"))
            return new String [] {"black", "black skin", "rich", "bleached teeth", "happy", "smiles", "smile", "mustache", "has mustache", "black mustache", "dress", "has dress", "black dress", "bald", "teeth", "weird"};
        if (name.equals ("Erna Solberg"))
            return new String [] {"white", "white skin", "smile", "smile", "hungry", "hair", "have hair", "gray hair", "gray hair", "dress", "has dress", "pink dress", "fat", "rar"};
        if (name.equals ("The Rock"))
            return new String [] {"white", "white skin", "smile", "rich", "happy", "smile", "glasses", "have glasses", "black glasses", "bald", "dress", "has dress", "brown dress"};
        if (name.equals ("Danny Devito"))
            return new String [] {"white", "white skin", "smile", "rich", "happy", "smiles", "side hair", "glasses", "has glasses", "bald "," weird ","sidehair","dress","has dress","black dress","beard","has beard","black beard","mustache","has mustache","black mustache","fat"};
        if (name.equals ("Larry King"))
            return new String [] {"white", "white skin", "retiree", "creepy", "glasses", "has glasses", "hair", "has hair", "white hair", "weird", "blue sweater", "angry"};
        if (name.equals ("Barack Obama"))
            return new String [] {"black", "black skin", "rich", "sad", "dress", "hair", "has hair", "has dress", "black dress", "president"} ;
        if (name.equals ("Harrison Ford"))
            return new String [] {"white", "white skin", "rich", "hair", "white hair", "happy", "smiles", "smile", "beard", "white beard", "has beard","black suit","suit","has suit"};
        if (name.equals ("Donald Trump"))
            return new String [] {"white", "white skin", "rich", "orange", "happy", "orange skin", "orange", "orange skin", "smiles", "smile", "hair","has hair","blonde","blonde hair","bleached hair","dress","has dress","black dress","president"};
        if (name.equals ("Snoop Dogg"))
            return new String [] {"black", "black skin", "rich", "white sweater", "has sweater", "sweater", "glasses", "has glasses", "angry", "red sweater" , "beard", "has beard", "black beard", "sidehair", "side hair"};
        if (name.equals ("Tove Bjorgaas"))
            return new String [] {"white", "white skin", "weird", "jerk", "sweater", "has sweater", "blue sweater", "hair", "has hair", "black hair" , "racist", "scared", "earrings", "teeth", "open mouth"};
        if (name.equals ("Oprah"))
            return new String [] {"black", "black skin", "rich", "angry", "glasses", "jerk", "white glasses", "has glasses", "sweater", "has sweater", "white sweater", "curler", "hair curler", "hair", "has hair", "brown hair", "fat"};
        if (name.equals ("Jackie Chan"))
            return new String [] {"yellow", "yellow skin", "asian", "glasses", "has glasses", "hair", "has hair", "black hair", "sweater", "has sweater" , "black sweater", "white sweater", "short hair", "angry"};
        if (name.equals ("Kpop dust"))
            return new String [] {"yellow", "yellow skin", "asian", "jerk", "hair", "has hair", "white hair", "bleached hair", "earrings", "sweater", "has sweater", "red sweater", "black sweater", "stupid", "weird", "kpop"};
        if (name.equals ("Sheik"))
            return new String [] {"white", "white skin", "rich", "hood", "has hood", "happy", "beard", "has beard", "black beard", "smile", "smiles", "hood", "has hood", "sweater", "has sweater", "white sweater"};
        if (name.equals ("Vegard"))
            return new String [] {"white", "white skin", "poor", "hair", "has hair", "happy", "brown hair", "smiles", "smile", "weird", "jacket","has jacket","hood","has hood","black jacket"};
        if (name.equals ("Beyonce"))
            return new String [] {"black", "black skin", "rich", "hair", "has hair", "happy", "blonde hair", "blonde", "smile", "smiles", "sweater","has sweater","white sweater","lipstick"};
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
        private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

        @Override
        public void handle(ActionEvent e) {
            String input = t1.getText();
            String navn = input.substring(0, 1).toUpperCase() + input.substring(1);

            if (input.equals(navn) && antForsok>3) {
                alert.setHeaderText(input + " er riktig!");
                alert.setContentText("Du vant!");
                alert.show();
                antForsok = 0;
            }
            else {
                antForsok++;

                for (SpillPerson pers : personer) {
                    if (pers.contains(input, person)) {
                        int rad = pers.hentRad();
                        int kolonne = pers.hentKol();

                        BorderPane bilde = (BorderPane) hentImgFraGrid(kolonne, rad);
                        ImageView img = (ImageView) bilde.getCenter();

                        ColorAdjust blackout = new ColorAdjust();
                        blackout.setBrightness(-1.0);

                        img.setEffect(blackout);
                    }
                }
            }
        }
    }
}