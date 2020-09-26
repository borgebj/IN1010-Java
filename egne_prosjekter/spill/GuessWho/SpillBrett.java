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
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import java.util.Optional;
import javafx.scene.control.ScrollPane;


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

    int bildeStoerrelse = 185;
    Font overBarfont = new Font(19);

    private void lagOverBar() {
        HBox linje1 = new HBox(30);
        linje1.setAlignment(Pos.BASELINE_CENTER); linje1.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        linje1.setPadding(new Insets(5, 0, 5, 0));
        rootPane.setStyle("-fx-background-color: lightyellow");

        Text intro = new Text(" 1 tilfeldig person er valgt - Finn personen ved aa skrive inn attributter i feltet nedenfor. Etter 3 forsok, kan du gjette navnet.");
        intro.setFont(overBarfont);

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
                img.setFitHeight(bildeStoerrelse); img.setFitWidth(bildeStoerrelse);
                img.setPreserveRatio(true);

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

            Button b1 = new Button("Avslutt");
            b1.setOnAction(new AvsluttBehandler());
            b1.setPrefWidth(100); b1.setPrefHeight(45); b1.setFont(font);

            t1 = new TextField();
            t1.setPromptText(" 'HER GJETTER DU' ");
            t1.setPrefWidth(140); t1.setPrefHeight(45); t1.setFont(font);

            Button b2 = new Button("Gjett");
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
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "glad", "haar", "har haar", "brunt haar", "smil", "smiler", "tenner", "viser tenner"};
        if (navn.equals("Borris"))
            return new String[] {"hvit", "hvit hud", "fattig", "skjegg", "svart skjegg", "blond", "haar", "blondt haar", "glad", "smil", "smiler", "rar", "genser", "har genser", "blaa genser", "tenner", "viser tenner"};
        if (navn.equals("Morpheus"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "briller", "har briller", "svarte briller", "skallet", "bald", "jakke", "har jakke", "svart jakke"};
        if (navn.equals("Bill Clinton"))
            return new String[] {"hvit", "hvit hud", "rik", "haar", "hvitt haar", "glad", "slips", "har slips", "svart slips", "smil", "smiler", "dodende", "doedende", "rod nese", "roed nese", "svart dress", "dress", "har dress", "tenner", "viser tenner"};
        if (navn.equals("VSauce"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "skjegg", "svart skjegg", "skallet", "bald", "rod genser", "brun dress", "dress", "har dress"};
        if (navn.equals("Jan Kaare"))
            return new String[] {"hvit", "hvit hud", "fattig", "trist", "briller", "har briller", "haar", "har haar", "hvitt haar", "bart", "har bart", "jakke","har jakke", "beige jakke", "sur", "tenner", "viser tenner"};
        if (navn.equals("Oskar"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "glad", "haar", "har haar", "blond", "blondt haar", "genser", "har genser", "rod genser", "watermark", "tenner", "viser tenner"};
        if (navn.equals("Casey Neistat"))
            return new String[] {"hvit", "hvit hud", "briller", "har briller", "svarte briller", "haar", "har haar", "svart haar", "genser", "har genser", "hvit genser", "stripete genser"};
        if (navn.equals("Steve Harvey"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "rik", "bleket tenner", "glad", "slips", "har slips", "svart slips", "sort slips", "smil", "smiler", "bart", "har bart", "svart bart", "dress", "har dress", "svart dress", "skallet", "bald", "tenner", "rar", "tenner", "viser tenner"};
        if (navn.equals("Erna Solberg"))
            return new String[] {"hvit", "hvit hud", "smil", "smiler", "sulten", "haar", "har haar", "gratt haar", "graa haar", "dress", "har dress", "rosa dress", "feit", "rar"};
        if (navn.equals("The Rock"))
            return new String[] {"hvit", "hvit hud", "smil", "rik", "glad", "smiler", "briller", "har briller", "svarte briller", "skallet", "bald", "dress", "har dress", "brun dress", "tenner", "viser tenner"};
        if (navn.equals("Danny Devito"))
            return new String[] {"hvit", "hvit hud", "smil", "rik", "glad", "smiler", "sidehaar", "briller", "har briller", "skallet", "bald", "rar", "sidehaar", "dress", "har dress", "svart dress", "skjegg", "har skjegg", "svart skjegg", "bart", "har bart", "svart bart", "feit", "tenner", "viser tenner"};
        if (navn.equals("Larry King"))
            return new String[] {"hvit", "hvit hud", "pensjonist", "skummel", "briller", "har briller", "slips", "har slips", "blaatt slips", "haar", "har haar", "hvitt haar", "rar", "genser", "har genser", "blaa genser", "sur"};
        if (navn.equals("Barack Obama"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "rik", "trist", "dress", "haar", "har haar", "har dress", "svart dress", "president"};
        if (navn.equals("Harrison Ford"))
            return new String[] {"hvit", "hvit hud", "rik", "haar", "hvitt haar", "sloyfe", "har sloyfe", "glad", "smil", "smiler", "skjegg", "hvitt skjegg", "mye skjegg", "svart dress", "dress", "har dress", "tenner", "viser tenner"};
        if (navn.equals("Donald Trump"))
            return new String[] {"hvit", "hvit hud", "rik", "oransj", "glad", "oransj hud", "oransje", "oransje hud", "smil", "smiler", "haar", "har haar", "blond", "blondt haar", "bleket haar", "dress", "har dress", "svart dress", "president", "tenner", "viser tenner"};
        if (navn.equals("Snoop Dogg"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "rik", "hvit genser", "har genser", "genser", "briller", "har briller", "sint", "rod genser", "skjegg", "har skjegg", "svart skjegg", "sidehaar"};
        if (navn.equals("Tove Bjorgaas"))
            return new String[] {"hvit", "hvit hud", "rar", "dust", "genser", "har genser", "blaa genser", "haar", "har haar", "svart haar", "rasist", "redd", "oredobber", "tenner", "aapen munn", "tenner", "viser tenner", "tenner", "viser tenner"};
        if (navn.equals("Oprah"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "rik", "sint", "briller", "dust", "hvite briller", "har briller", "genser", "har genser", "hvit genser", "kroller", "har kroller", "haar", "har haar", "brunt haar", "feit", "viser tenner", "tenner"};
        if (navn.equals("Jackie Chan"))
            return new String[] {"gul", "gul hud", "asian", "briller", "har briller", "haar", "har haar", "svart haar", "genser", "har genser", "svart genser", "hvit genser", "kort haar", "sur", "sint"};
        if (navn.equals("Kpop dust"))
            return new String[] {"gul", "gul hud", "asian", "dust", "haar", "har haar", "hvitt haar", "bleket haar", "oredobber", "genser", "har genser", "rod genser", "svart genser", "dum", "rar", "kpop", "tenner", "viser tenner"};
        if (navn.equals("Dr Phil"))
            return new String[] {"hvit", "hvit hud", "rik", "smil", "smiler", "slips", "har slip", "blaatt slips", "glad", "skallet", "bald", "dress", "har dress", "svart dress", "bart", "har bart", "svart bart", "sidehaar", "tenner", "viser tenner"};
        if (navn.equals("Sheik"))
            return new String[] {"hvit", "hvit hud", "rik", "hette", "har hette", "glad", "skjegg", "har skjegg", "svart skjegg", "smil", "smiler", "hodeplagg", "har hodeplagg", "genser", "har genser", "hvit genser"};
        if (navn.equals("Vegard"))
            return new String[] {"hvit", "hvit hud", "fattig", "haar", "har haar", "glad", "brunt haar", "smil", "smiler", "rar", "jakke", "har jakke", "hette", "har hette", "svart jakke", "tenner", "viser tenner"};
        if (navn.equals("Beyonce"))
            return new String[] {"svart", "svart hud", "sort", "sort hud", "rik", "haar", "har haar", "glad", "blondt haar", "blond", "smil", "smiler", "genser", "har genser", "hvit genser", "leppestift", "tenner", "viser tenner"};
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

    private void spoerBruk() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vindu-stoerrelse");
        alert.setHeaderText("Velg din PC-type.");

        ButtonType laptop = new ButtonType("Laptop");
        ButtonType stasjonaer = new ButtonType("Stasjonaer");

        alert.getButtonTypes().addAll(laptop, stasjonaer);

        Optional<ButtonType> resultat = alert.showAndWait();
        if (resultat.get() == laptop) {
                bildeStoerrelse = 105; // 90
                overBarfont = new Font(12); // 10
        }
    }


    @Override //hoved-shiten
    public void start(Stage teater) {

        spoerBruk();
        lagOverBar();
        lagBrett();
        lagUnderBar();
        velgPerson();

        ScrollPane sp = new ScrollPane();
        sp.setContent(rootPane);
        sp.setPannable(true);
        Scene scene = new Scene(sp);

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
                String input = t1.getText().toLowerCase();

                String[] NoNoWords = new String[]{"hvit", "hvit hud", "sort", "sort hud", "svart", "svart hud", "rik"};
                boolean erI = Arrays.stream(NoNoWords).anyMatch(input::equals);

                if (antForsok < 3 && erI) {
                    alert.setHeaderText("Proev igjen");
                    alert.setContentText(input + " er for op, vent et par runder.");
                    alert.show();
                }
                else {
                    if (input.equals(person.hentNavn()) && antForsok > 3 && antallGjemt > 20) {
                        alert.setHeaderText(input + " er riktig!");
                        alert.setContentText("Du vant!");
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