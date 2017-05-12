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
        //System.out.println(chessBoard.isInCheck("e1"));
        System.out.println();
        chessBoard.movePiece("b1", "c3");
        chessBoard.movePiece("c3", "b5");
        chessBoard.movePiece("b5", "c7");
        //System.out.println(chessBoard.isInCheck("e8"));
        System.out.println();
        System.out.println(chessBoard.isKingMoveable("e1"));
    }
}
