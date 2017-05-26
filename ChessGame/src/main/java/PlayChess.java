/**
 * Main class for playing chess
 */
public class PlayChess {
    public static void main(String args[]) {
        // Supposed to simulate http://www.chessgames.com/perl/chessgame?gid=1106430

        // Robert James Fischer vs Tigran Vartanovich Petrosian
        // http://www.chessgames.com/perl/chessgame?gid=1044100
        // includes pawn promotion
        Game chessGame = new Game();
        chessGame.makeMove("e2", "e4");
        chessGame.makeMove("c7", "c6");

        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("d7", "d5");

        chessGame.makeMove("g1", "f3");
        chessGame.makeMove("c8", "g4");

        chessGame.makeMove("h2", "h3");
        chessGame.makeMove("g4", "f3");

        chessGame.makeMove("d1", "f3");
        chessGame.makeMove("g8", "f6");

        chessGame.makeMove("d2", "d3");
        chessGame.makeMove("e7", "e6");

        chessGame.makeMove("g2", "g3");
        chessGame.makeMove("f8", "b4");

        chessGame.makeMove("c1", "d2");
        chessGame.makeMove("d5", "d4");

        chessGame.makeMove("c3", "b1");
        chessGame.makeMove("b4", "d2");

        chessGame.makeMove("b1", "d2");
        chessGame.makeMove("e6", "e5");

        chessGame.makeMove("f1", "g2");
        chessGame.makeMove("c6", "c5");

        chessGame.makeMove("O-O"); // White castles
        chessGame.makeMove("b8", "c6");

        chessGame.makeMove("f3", "e2");
        chessGame.makeMove("g7", "g5");

        chessGame.makeMove("d2", "f3");
        chessGame.makeMove("h7", "h6");

        chessGame.makeMove("h3", "h4");
        chessGame.makeMove("h8", "g8");

        chessGame.makeMove("a2", "a3");
        chessGame.makeMove("d8", "e7");

        chessGame.makeMove("h4", "g5");
        chessGame.makeMove("h6", "g5");

        chessGame.makeMove("e2", "d2");
        chessGame.makeMove("f6", "d7");

        chessGame.makeMove("c2", "c3");
        chessGame.makeMove("O-O-O"); // Black castles

        chessGame.makeMove("c3", "d4");
        chessGame.makeMove("e5", "d4");

        chessGame.makeMove("b2", "b4");
        chessGame.makeMove("c8", "b8");

        chessGame.makeMove("f1", "c1");
        chessGame.makeMove("c6", "e5");

        chessGame.makeMove("f3", "e5");
        chessGame.makeMove("e7", "e5");

        chessGame.makeMove("c1", "c4");
        chessGame.makeMove("d8", "c8");

        chessGame.makeMove("a1", "c1");
        chessGame.makeMove("g5", "g4");

        chessGame.makeMove("d2", "b2");
        chessGame.makeMove("g8", "d8");

        chessGame.makeMove("a3", "a4");
        chessGame.makeMove("e5", "e7");

        chessGame.makeMove("c1", "b1");
        chessGame.makeMove("d7", "e5");

        chessGame.makeMove("c4", "c5");
        chessGame.makeMove("c8", "c5");

        chessGame.makeMove("b4", "c5");
        chessGame.makeMove("e5", "d3");

        chessGame.makeMove("b2", "d2");
        chessGame.makeMove("d3", "c5");

        chessGame.makeMove("d2", "f4");
        chessGame.makeMove("e7", "c7");

        chessGame.makeMove("f4", "g4");
        chessGame.makeMove("c5", "a4");

        chessGame.makeMove("e4", "e5");
        chessGame.makeMove("a4", "c5");

        chessGame.makeMove("g4", "f3");
        chessGame.makeMove("d4", "d3");

        chessGame.makeMove("f3", "e3");
        chessGame.makeMove("d3", "d2");

        chessGame.makeMove("g2", "f3");
        chessGame.makeMove("c5", "a4");

        chessGame.makeMove("e3", "e4");
        chessGame.makeMove("a4", "c5");

        chessGame.makeMove("e4", "e2");
        chessGame.makeMove("a7", "a6");

        chessGame.makeMove("g1", "g2");
        chessGame.makeMove("b8", "a7");

        chessGame.makeMove("e2", "e3");
        chessGame.makeMove("d8", "d3");

        chessGame.makeMove("e3", "f4");
        chessGame.makeMove("c7", "d7");

        chessGame.makeMove("f4", "c4");
        chessGame.makeMove("b7", "b6");

        chessGame.makeMove("b1", "d1");
        chessGame.makeMove("a6", "a5");

        chessGame.makeMove("c4", "f4");
        chessGame.makeMove("d3", "d4");

        chessGame.makeMove("f4", "h6");
        chessGame.makeMove("b6", "b5");

        chessGame.makeMove("h6", "e3");
        chessGame.makeMove("a7", "b6");

        chessGame.makeMove("e3", "h6");
        chessGame.makeMove("c5", "e6");

        chessGame.makeMove("h6", "e3");
        chessGame.makeMove("b6", "a6");

        chessGame.makeMove("f3", "e2");
        chessGame.makeMove("a5", "a4");

        chessGame.makeMove("e3", "c3");
        chessGame.makeMove("a6", "b6");

        chessGame.makeMove("c3", "e3");
        chessGame.makeMove("e6", "c5");

        chessGame.makeMove("e2", "f3");
        chessGame.makeMove("b5", "b4");

        chessGame.makeMove("e3", "h6");
        chessGame.makeMove("c5", "e6");

        chessGame.makeMove("h6", "h8");
        chessGame.makeMove("d7", "d8");

        chessGame.makeMove("h8", "h7");
        chessGame.makeMove("d8", "d7");

        chessGame.makeMove("h7", "h8");
        chessGame.makeMove("b4", "b3");

        chessGame.makeMove("h8", "b8");
        chessGame.makeMove("b6", "a5");

        chessGame.makeMove("b8", "a8");
        chessGame.makeMove("a5", "b5");

        chessGame.makeMove("a8", "b8");
        chessGame.makeMove("b5", "c4");

        chessGame.makeMove("b8", "g8");
        chessGame.makeMove("c4", "c3");

        chessGame.makeMove("f3", "h5");
        chessGame.makeMove("e6", "d8");

        chessGame.makeMove("h5", "f3");
        chessGame.makeMove("a4", "a3");

        chessGame.makeMove("g8", "f8");
        chessGame.makeMove("c3", "b2");

        chessGame.makeMove("f8", "h8");
        chessGame.makeMove("d8", "e6");

        chessGame.makeMove("h8", "a8");
        chessGame.makeMove("a3", "a2");

        chessGame.makeMove("a8", "a5");
        chessGame.makeMove("d7", "a4");

        chessGame.makeMove("d1", "d2");
        chessGame.makeMove("b2", "a3");

        chessGame.printBoard();
    }
}
