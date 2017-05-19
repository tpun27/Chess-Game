/**
 * Class for Chess Game
 */
public class Game {

    private Grid chessBoard;

    public Game() {
        chessBoard = new Grid();
        chessBoard.initializeBoardPieces();
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
}
