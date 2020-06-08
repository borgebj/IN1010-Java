import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// panes og annet
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;


public class Spill extends Application {

    private static final Random rand = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static Terreng terreng;
    private static ArrayList<Spiller> spillere = new ArrayList<Spiller>();


    // oppretter terreng avhengig av typen i parameter
    private static void opprettTerreng(String steder, String gjenstander, String spillType) {
        if (spillType.equals("veivalg")) terreng = new VeivalgTerreng(steder);
        else terreng = new Terreng(steder);
        terreng.fyllKister(gjenstander);
    }

    // oppretter en spiller med eller uten VeivalgSpiller
    private static void opprettSpillere(String[] navnListe, int antall, int antallTrekk, String spillType) {
        try {
            for (int i = 0; i < antall; i++) {
                Terminal brukerTerminal = new Terminal(scanner);
                Sted startSted = terreng.hentStart();
                String navn = navnListe[rand.nextInt(navnListe.length)] + "-" + (i + 1);

                Spiller spiller;
                if (spillType.equals("veivalg")) spiller = new VeivalgSpiller(navn, antallTrekk, brukerTerminal, startSted);
                else spiller = new Spiller(navn, antallTrekk, brukerTerminal, startSted);

                brukerTerminal.settSpiller(spiller);
                spillere.add(spiller);
            }
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }

    // oppretter en robot med eller uten VeivalgSpiller
    private static void opprettRoboter(String[] navnListe, int antall, int antallTrekk, String spillType, String robotType) {
        try {
            for (int i = 0; i < antall; i++) {
                Sted startSted = terreng.hentStart();
                String navn = "Robot" + "-" + (i + 1);

                Robot robot;
                if (robotType.equals("traad")) robot = new TraadSpiller();
                else robot = new Robot();

                Spiller spiller;
                if (spillType.equals("veivalg")) spiller = new VeivalgSpiller(navn, antallTrekk, robot, startSted);
                else spiller = new Spiller(navn, antallTrekk, robot, startSted);

                robot.settSpiller(spiller);
                spillere.add(spiller);
            }
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }


    // oppretter ulike spill avhengig av hva parameteren er
    private static void opprettSpill(String steder, String gjenstander, String[] navn, int antallSpillere, int antallRoboter, int maksAntallTrekk, String spillerType, String spillType) {
        opprettTerreng(steder, gjenstander, spillType);

        switch (spillerType) {
            case "robot":
                opprettRoboter(navn, antallRoboter, maksAntallTrekk, spillType, "robot");
                break;

            case "traad":
                opprettRoboter(navn, antallRoboter, maksAntallTrekk, spillType, "traad");

            case "spiller":
                opprettSpillere(navn, antallSpillere, maksAntallTrekk, spillType);
                break;

            case ("begge"):
                opprettSpillere(navn, antallSpillere, maksAntallTrekk, spillType);
                opprettRoboter(navn, antallRoboter, maksAntallTrekk, spillType, "robot");
                break;
        }
    }


    // spoer bruker om terreng-type og oppretter
    private static void spoerOgOpprettTerreng(String steder, String gjenstander, String[] navn, int antallSpillere, int antallRoboter, int maksAntallTrekk, String spillerType) {

        System.out.print("\nHva onsker du aa spille med? \n 1: Enkelt terreng \n 2: Terreng med veivalg \n a: Avslutt\n > ");

        switch (scanner.next()) {
            case "1":
                System.out.println("\n\nDu har valgt 'Enkelt terreng' - oppretter ...");
                opprettSpill(steder, gjenstander, navn, antallSpillere, antallRoboter, maksAntallTrekk, spillerType, "enkelt");
                break;
            case "2":
                System.out.println("\n\nDu har valgt 'Terreng med veivalg' - oppretter ...");
                opprettSpill(steder, gjenstander, navn, antallSpillere, antallRoboter, maksAntallTrekk, spillerType, "veivalg");
                break;

            case "a":
            default:
                System.out.println("\n\nAvslutter ...");
                System.exit(0);
        }
    }

    // spoer bruker om antall brukere
    private static int[] spoerOmAntall(String spillerType) {
        int[] antall = new int[2];
        try {
            switch (spillerType) {
                case "spiller":
                    System.out.print("\nHvor mange spillere onsker du?\n > ");
                    antall[0] = scanner.nextInt();
                    break;
                case "traad":
                case "robot":
                    System.out.print("\nHvor mange roboter onsker du?\n > ");
                    antall[1] = scanner.nextInt();
                    break;
                case "begge":
                    System.out.print("\nHvor mange spillere og roboter onsker du (spiller_robot)?\n > ");
                    antall[0] = scanner.nextInt();
                    antall[1] = scanner.nextInt();
                    break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("\nUgyldig input - Avslutter ...");
            System.exit(0);
        }
        return antall;
    }

    //spoer bruker om hva slags spiller de vil ha med
    private static String spoerOmSpillertype() {
        System.out.print("\nVelg din spillertype: \n 1: Spiller \n 2: Robot \n 3: Spiller og Robot \n > ");

        switch (scanner.next().toLowerCase()) {
            case "1":
                System.out.println("\nDu har valgt 'Spiller'");
                return "spiller";
            case "2":
                System.out.print("\nVelg din robotype: \n 1: Robot \n 2: TraadRobot \n > ");
                String valg = scanner.next().toLowerCase();
                if (valg.equals("2")) {
                    System.out.println("\nDu har valgt 'TraadRobot'");
                    return "traad";
                }
                else {
                    System.out.println("\nDu har valgt 'Robot'");
                    return "robot";
                }

            case "3":
                System.out.println("\nDu har valgt 'Begge'");
                return "begge";
            default:
                System.out.println("\nForstaar ikke - velger 'Spiller'");
                return "spiller";
        }
    }


    // oppgave 3 - viser resultater i et GUI-vindu
    @Override
    public void start(Stage teater) {

        // sorterer spilerListe
        spillere.sort(Collections.reverseOrder());


        // dynamis hoyde brukt for GUI-brett
        int hoyde = (spillere.size() * 50) + 180;

        // oppretter og endrer Hoved-panen
        VBox root = new VBox();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2");

        // oppretter lyse-blaa bakgrunn og legger inn i stackpane
        StackPane skjerm = new StackPane();
        Rectangle r = new Rectangle(420, hoyde);
        r.setFill(Color.SKYBLUE);
        skjerm.getChildren().add(r);

        int index = 1;
        // oppretter et tekst-felt for beskrivelse av resultatlisten
        VBox linje1 = new VBox(10);
        linje1.setAlignment(Pos.BASELINE_CENTER);
        Text beskrivelse = new Text(" - Resultatliste over skattjakten - ");
        beskrivelse.setFont(new Font(25));
        linje1.getChildren().add(beskrivelse);

        // skriver ut spillere som tekst med rektangler rundt
        for (Spiller s : spillere) {
            StackPane box = new StackPane();

            // oppretter rektangel
            Region rect = new Region();
            rect.setStyle("-fx-background-color: lightblue; -fx-border-style: solid; -fx-border-width: 3; -fx-border-color: black; -fx-max-width:380; -fx-max-height: 50;");

            // oppretter rekst for hver spiller
            String resultat = (index++) + ": " + s.hentNavn() + " - " + s.hentFormue() + " poeng";
            Text t = new Text(resultat);
            t.setFont(new Font(30));

            // setter rektangelet inn i stackpane med teksten over, og legger saa dette inn i linje1-VBox'en
            box.getChildren().addAll(rect, t);
            linje1.getChildren().add(box);
        }

        /// oppretter den knapp og setter den nederst i Stackpanen
        Button b = new Button("Avslutt");
        b.setPrefWidth(420);
        b.setPrefHeight(30);
        b.setOnAction(new AvsluttBehandler());
        StackPane.setAlignment(b, Pos.BOTTOM_CENTER);

        // legger til linje1 (box>(rektangel og tekst) og knappen til skjerm, deretter alt dette til "root-panen" som vises
        skjerm.getChildren().addAll(linje1, b);
        root.getChildren().addAll(skjerm);

        Scene scene = new Scene(root);
        teater.setScene(scene);
        teater.setTitle("Resultater");
        teater.show();
    }


    public static void main(String[] args) {
        try {

            /** Foer jar **/ /*
            // konstante verdier fra parameter naar en kjoerer filen
            final String steder = args[0];
            final String gjenstander = args[1];
            final int maksAntallTrekk = Integer.parseInt(args[2]);
            */

            final String steder = "steder.txt";
            final String gjenstander = "gjenstander.txt";
            final int maksAntallTrekk = 10;


            // bruker faar et tilfeldig navn fra listen med spillerNr etter
            String[] navn = {"Arne", "Jan", "Oskar", "Oda", "Bent", "Ola", "Ali", "Kari", "Mats", "Karl", "Trygve"};

            // foer spillet starter, "rens" skjerm og la bruker velge spillertype og terreng
            System.out.println(new String(new char[50]).replace('\0', '\n'));


            // metoder som henter spillertype, antallspillere og en som oppretter terrenget med dette
            String spillerType = spoerOmSpillertype();
            int[] antallListe = spoerOmAntall(spillerType);

            int antallSpillere = antallListe[0];
            int antallRoboter = antallListe[1];

            spoerOgOpprettTerreng(steder, gjenstander, navn, antallSpillere, antallRoboter, maksAntallTrekk, spillerType);

            // for hver spiller : gjor trekk til de ikke har mer trekk
            for (Spiller spiller : spillere) {

                // lager en traad og kjoerer spiller for robotene
                if (spiller.hentGrensesnitt() instanceof TraadSpiller) {
                    try {
                        Thread robotTraad = new Thread(new RobotTraad(spiller));
                        robotTraad.start();
                        robotTraad.join();
                    } catch (InterruptedException ignored) {
                    }
                }
                // fortsetter som vanlig
                else {
                    while (spiller.hentTrekk() > 0) {
                        spiller.nyttTrekk();
                    }
                    System.out.println("\n\nFerdig med runden til " + spiller.hentNavn());
                }
            }
            scanner.close();

            // starter avslutnings-nedtelling og starter GUI-vinduet
            Thread avslutter = new Thread(new AvsluttTraad());
            avslutter.start();
            launch(args);
        } catch (ArrayIndexOutOfBoundsException aiob) {
            System.out.println("\n\n'Spill' maa ha argumentene: steder.txt gjenstander.txt antallMaksTrekk\n");
            System.exit(0);
        }
    }

    private static class RobotTraad implements Runnable {

        Spiller spiller;

        RobotTraad(Spiller spiller) {
            this.spiller = spiller;
        }

        @Override
        public void run() {
            while (spiller.hentTrekk() > 0) {
                spiller.nyttTrekk();
            }
        }
    }

    // avslutter ved knappetrykk
    private static class AvsluttBehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    // avslutter etter 5 sekunder
    private static class AvsluttTraad implements Runnable {

        @Override // kode som kjoeres av traad
        public void run() {
            try {
                Thread.sleep(5000);
                Platform.exit();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
