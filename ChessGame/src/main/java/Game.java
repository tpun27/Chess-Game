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
        chessBoard.makeMove(initialPos, newPos);
    }
}
