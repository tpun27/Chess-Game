/**
 * Main class for playing chess
 */
public class PlayChess {
    public static void main(String args[]) {
        // Supposed to simulate http://www.chessgames.com/perl/chessgame?gid=1106430

        // Non-Professional Match
        // https://www.chess.com/forum/view/game-analysis/shortest-checkmated-game-with-pawn-promotion (Post #14)
        // includes pawn promotion
        Game chessGame = new Game();
        chessGame.makeMove("d2", "d3");
        chessGame.makeMove("e7", "e5");

        chessGame.makeMove("b2", "b3");
        chessGame.makeMove("e5", "e4");

        chessGame.makeMove("e1", "d2"); // Black King in check
        chessGame.makeMove("e4", "d3");

        chessGame.makeMove("d2", "c3");
        chessGame.makeMove("d3", "e2");

        chessGame.makeMove("c3", "b2"); // Black King in check
        chessGame.makeMove("e2", "d1"); // promote Pawn to Knight to checkmate Black King

        chessGame.printBoard();
    }
}
