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
        chessBoard.printBoard();

        Knight knight = new Knight(Piece.PieceColorOptions.WHITE, "d4");
        knight.printPossibleMoves("b1");
    }
}
