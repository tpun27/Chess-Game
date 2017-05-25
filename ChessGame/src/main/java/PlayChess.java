/**
 * Main class for playing chess
 */
public class PlayChess {
    public static void main(String args[]) {
        // Jens Hohmeister vs Tena Frank
        // http://www.chessgames.com/perl/chessgame?gid=1281617
        // includes stalemate
        Game chessGame = new Game();
        chessGame.makeMove("d2", "d4");
        chessGame.makeMove("e7", "e5");

        chessGame.makeMove("d1", "d2");
        chessGame.makeMove("e5", "e4");

        chessGame.makeMove("d2", "f4");
        chessGame.makeMove("f7", "f5");

        chessGame.makeMove("h2", "h3");
        chessGame.makeMove("f8", "b4"); // White King in check

        chessGame.makeMove("b1", "d2");
        chessGame.makeMove("d7", "d6");

        chessGame.makeMove("f4", "h2");
        chessGame.makeMove("c8", "e6");

        chessGame.makeMove("a2", "a4");
        chessGame.makeMove("d8", "h4");

        chessGame.makeMove("a1", "a3");
        chessGame.makeMove("c7", "c5");

        chessGame.makeMove("a3", "g3");
        chessGame.makeMove("f5", "f4");

        chessGame.makeMove("f2", "f3");
        chessGame.makeMove("e6", "b3");

        chessGame.makeMove("d4", "d5");
        chessGame.makeMove("b4", "a5"); // false stalemate to debug

        /*
        chessGame.makeMove("c2", "c4");
        chessGame.makeMove("e4", "e3"); // stalemate
        */

        chessGame.printBoard();
    }
}
