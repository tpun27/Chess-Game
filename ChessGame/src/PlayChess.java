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

        Knight knight = new Knight(Piece.PieceColorOptions.WHITE, "d4");
        knight.printPossibleMoves("b1");
        System.out.println(chessBoard.overlapOwnPiece(Piece.PieceColorOptions.BLACK, "a7"));
        System.out.println(chessBoard.isDiagonalPath("a1", "h8"));
        System.out.println(chessBoard.isHorizontalOrVertPath("a1","h1"));
    }
}
