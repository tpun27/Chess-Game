import java.util.Scanner;
/**
 * Class for Chess Game
 */
public class Game {

    private Grid chessBoard;

    public Game() {
        chessBoard = new Grid();
        chessBoard.initializeBoardPieces();
    }

    public void startGameLoop() {
        char inputChar, castleChar;
        String initialCoordinate, finalCoordinate;


        inputChar = '?';
        while (inputChar != 'Q') {
            inputChar = getMove();

            switch (inputChar) {
                case 'M':
                    System.out.println("Enter The Current Coordinate Of The Piece You Wish To Move");
                    initialCoordinate = getInputCoordinate();
                    System.out.println("Enter The Destination Coordinate Of The Piece You Wish To Move");
                    finalCoordinate = getInputCoordinate();
                    makeMove(initialCoordinate, finalCoordinate);
                    break;
                case 'C':
                    castleChar = getCastleChar();
                    if (castleChar == 'K') {
                        makeMove("O-O");
                    }
                    else {
                        makeMove("O-O-O");
                    }
                    break;
                case 'P':
                    chessBoard.printBoard();
                    break;
                case 'I':
                    printInstructions();
                    break;
                case 'Q':
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }

    public void makeMove(String initialPos, String newPos) {
        try {
            chessBoard.makeMove(initialPos, newPos);
        } catch (InvalidBoardPositionException e) {
            e.printErrorMsg();
        } catch (InvalidMoveException e) {
            e.printErrorMsg();
        }
    }

    public void makeMove(String castleString) {
        try {
            chessBoard.makeMove(castleString);
        } catch (InvalidMoveException e) {
            e.printErrorMsg();
        }
    }

    public void printBoard() {
        chessBoard.printBoard();
    }

    protected char getMove() {
        char optionChar;
        Scanner moveScanner = new Scanner(System.in);

        while (true) {
            System.out.println("What Would You Like To Do?");
            printInstructions();
            optionChar = moveScanner.next().charAt(0);
            if (optionChar == 'M' || optionChar == 'C' || optionChar == 'P' || optionChar == 'I' || optionChar == 'Q') {
                break;
            }
        }
        return optionChar;
    }

    protected String getInputCoordinate() {
        String moveString;
        char tempChar;
        Scanner inputScanner = new Scanner(System.in);
        boolean inputValid;

        moveString = "";
        inputValid = false;

        while (!inputValid) {
            System.out.println("Enter a 2-character Chess Coordinate");
            moveString = inputScanner.nextLine();
            if (moveString.length() != 2) {
                System.out.println("Invalid Coordinate Length");
                continue;
            }

            tempChar = moveString.charAt(0);
            if (tempChar < 'a' || tempChar > 'h') {
                System.out.println("Invalid First Character");
                continue;
            }

            tempChar = moveString.charAt(1);
            if (tempChar < '1' || tempChar > '8') {
                System.out.println("Invalid Second Character");
                continue;
            }
            inputValid = true;
        }
        return moveString;
    }

    protected char getCastleChar() {
        char optionChar;
        Scanner moveScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 'K' to castle King-side or 'Q' to castle Queen-side");
            optionChar = moveScanner.next().charAt(0);
            if (optionChar == 'K' || optionChar == 'Q') {
                break;
            }
        }
        return optionChar;
    }

    protected void printInstructions() {
        System.out.println("Your options are: 'M', 'C', 'P', 'I', and 'Q'");
        System.out.println("For Move, Castle, Print Board, Display Instructions and Quit\n");
        System.out.println("All moves must use 2-character Chess Coordinates");
        System.out.println("The first character is a letter from a to h");
        System.out.println("The second character is an integer from 1 to 8:");
    }
}
