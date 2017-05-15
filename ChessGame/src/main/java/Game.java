/**
 * Class for Chess Game
 */
public class Game {
    Board chessBoard;

    public Game() {
        chessBoard = new Board();
        chessBoard.initializeBoardPieces();
    }
}
