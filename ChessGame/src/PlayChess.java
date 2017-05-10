/**
 * Main class for playing chess
 */
public class PlayChess {
    public static void main(String args[]) {
        Board chessBoard = new Board();
        chessBoard.printBoard();
        chessBoard.movePiece("d2", "d4");
        chessBoard.movePiece("d7", "d5");
        chessBoard.movePiece("g1", "f3");
        chessBoard.movePiece("b8", "c6");
        chessBoard.movePiece("c1", "f4");
        chessBoard.movePiece("c8", "f5");
        chessBoard.movePiece("e2", "e3");
        chessBoard.movePiece("e7", "e6");
        chessBoard.movePiece("c2", "c4");
        chessBoard.movePiece("g8", "f6");
        chessBoard.printBoard();
        chessBoard.movePiece("f3", "h2");
        chessBoard.movePiece("f4", "h4");
        chessBoard.movePiece("f1", "b5");
        chessBoard.movePiece("h1", "h2");
        chessBoard.movePiece("h1", "h3");
        chessBoard.movePiece("d1", "g1");
        chessBoard.movePiece("d8", "a5");
        chessBoard.movePiece("e1", "c3");
        chessBoard.movePiece("e1", "e4");
        chessBoard.movePiece("e8", "b8");
        chessBoard.movePiece("b1", "e2");
        chessBoard.movePiece("c6", "e4");
    }
}
