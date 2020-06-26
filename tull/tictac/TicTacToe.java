
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TicTacToe {

    // arraylister som inneholder posisjonene som er spilt av baade CPU og spiller
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) throws InterruptedException {

        // lager brettet (gameBoard) - en nested-array med 5 arrayer inne
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '},
                              {'-', '+', '-', '+', '-'},
                              {' ', '|', ' ', '|', ' '}};

        // printer tomt brett
        printGameBoard(gameBoard);

        // evig while-lokke hvor spiller og CPU velger tall
        while(true) {

            // scanner-objekt som tar tall fra bruker og lagrer i variabel
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.print("Position taken! Enter a correct position: ");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            // sjekker resultat etter spiller har spilt
            String result = checkWinner();
            if (result.length() > 0 ){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            // bruker random-objekt for aa faa random tall fra 1-9 for CPU
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            // printer spillebrettet
            TimeUnit.MILLISECONDS.sleep(150);
            printGameBoard(gameBoard);

            // sjekker resultat etter CPU har spilt
            result = checkWinner();
            if (result.length() > 0 ){
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char [] [] gameBoard) {

        // printer ut brettet (gameBoard) med 2 for-loops
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char [] [] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        // switch-setning som bytter symbol på spillebrettet med enten O eller X (se ovenfor)
        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        // alle posisjoner som er "vinnerposisjoner"
        // rader
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        // kolonner
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        // kryssene
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        // legger til alle vinnerposisjonene til en liste som inneholder lister
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        // for-lokke som sjekker hver liste i listen om "playerPositions"-listen inneholder alle (containsAll) tallene i en av vinnerlistene
        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
            return "Gratulerer, du vant!";
        } else if (cpuPositions.containsAll(l)) {
                return "CPU vinner! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Likt! Ingen vinner";
            }
        }
        // returnes dette saa ble det likt
        return "";
    }
}