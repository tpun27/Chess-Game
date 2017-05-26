/**
 * Test Games
 */
public class TestGames {
    // John Odin Howard Taylor vs Unknown Player
    // http://www.chessgames.com/perl/chessgame?gid=1336733
    // includes checkmate
    public void playGame1() {
        Game chessGame = new Game();
        chessGame.makeMove("e2", "e4");
        chessGame.makeMove("e7", "e5");
        chessGame.makeMove("g1", "f3");
        chessGame.makeMove("g8", "f6");
        chessGame.makeMove("f1", "c4");
        chessGame.makeMove("f6", "e4");
        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("e4", "c5");
        chessGame.makeMove("f3", "e5");
        chessGame.makeMove("f7", "f6");
        chessGame.makeMove("d1", "h5"); // Black King in check
        chessGame.makeMove("g7", "g6");
        chessGame.makeMove("c4", "f7"); // Black King in check
        chessGame.makeMove("e8", "e7");
        chessGame.makeMove("c3", "d5"); // Black King in check
        chessGame.makeMove("e7", "d6");
        chessGame.makeMove("e5", "c4"); // Black King in check
        chessGame.makeMove("d6", "c6");
        chessGame.makeMove("d5", "b4"); // Black King in check
        chessGame.makeMove("c6", "b5");
        chessGame.makeMove("a2", "a4"); // Black King in check
        chessGame.makeMove("b5", "b4");
        chessGame.makeMove("c2", "c3"); // Black King in check
        chessGame.makeMove("b4", "b3");
        chessGame.makeMove("h5", "d1"); // Black King in checkmate
        chessGame.printBoard();
    }

    // Joseph Henry Blake vs George Archer Hooke
    // http://www.chessgames.com/perl/chessgame?gid=1250635
    // includes checkmate
    public void playGame2() {
        Game chessGame = new Game();
        chessGame.makeMove("e2", "e4");
        chessGame.makeMove("e7", "e5");
        chessGame.makeMove("g1", "f3");
        chessGame.makeMove("d7", "d6");
        chessGame.makeMove("f1", "c4");
        chessGame.makeMove("f7", "f5");
        chessGame.makeMove("d2", "d4");
        chessGame.makeMove("g8", "f6");
        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("e5", "d4");
        chessGame.makeMove("d1", "d4");
        chessGame.makeMove("c8", "d7");
        chessGame.makeMove("f3", "g5");
        chessGame.makeMove("b8", "c6");
        chessGame.makeMove("c4", "f7"); // Black King in check
        chessGame.makeMove("e8", "e7");
        chessGame.makeMove("d4", "f6"); // Black King in check
        chessGame.makeMove("e7", "f6");
        chessGame.makeMove("c3", "d5"); // Black King in check
        chessGame.makeMove("f6", "e5");
        chessGame.makeMove("g5", "f3"); // Black King in check
        chessGame.makeMove("e5", "e4");
        chessGame.makeMove("d5", "c3"); // Black King in checkmate
        chessGame.printBoard();
    }

    // Daniel Fidlow vs Albert Maier
    // http://www.chessgames.com/perl/chessgame?gid=1250783
    // includes Pawn promotion
    public void playGame3() {
        Game chessGame = new Game();
        chessGame.makeMove("d2", "d4");
        chessGame.makeMove("d7", "d5");
        chessGame.makeMove("c2", "c4");
        chessGame.makeMove("e7", "e6");
        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("c7", "c5");
        chessGame.makeMove("c4", "d5");
        chessGame.makeMove("c5", "d4");
        chessGame.makeMove("d5", "e6");
        chessGame.makeMove("d4", "c3");
        chessGame.makeMove("e6", "f7"); // Black King in check
        chessGame.makeMove("e8", "e7");
        chessGame.makeMove("f7", "g8"); // Pawn promotion
        chessGame.printBoard();
    }

    // Magnus Carlsen vs Loek van Wely
    // http://www.chessgames.com/perl/chessgame?gid=1410669
    // includes castling and en passant
    public void playGame4() {
        Game chessGame = new Game();
        chessGame.makeMove("e2", "e4");
        chessGame.makeMove("c7", "c5");
        chessGame.makeMove("g1", "f3");
        chessGame.makeMove("e7", "e6");
        chessGame.makeMove("d2", "d4");
        chessGame.makeMove("c5", "d4");
        chessGame.makeMove("f3", "d4");
        chessGame.makeMove("a7", "a6");
        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("d8", "c7");
        chessGame.makeMove("f1", "d3");
        chessGame.makeMove("g8", "f6");
        chessGame.makeMove("O-O"); // White castles
        chessGame.makeMove("f8", "c5");
        chessGame.makeMove("d4", "b3");
        chessGame.makeMove("c5", "e7");
        chessGame.makeMove("f2", "f4");
        chessGame.makeMove("d7", "d6");
        chessGame.makeMove("a2", "a4");
        chessGame.makeMove("b8", "c6");
        chessGame.makeMove("a4", "a5");
        chessGame.makeMove("b7", "b5");

        chessGame.printBoard();
        chessGame.makeMove("a5", "b6"); // White Pawn en passant
        chessGame.printBoard();
        chessGame.makeMove("c7", "b6"); // White King in check

        chessGame.makeMove("g1", "h1");
        chessGame.makeMove("O-O"); // Black castles
        chessGame.makeMove("d1", "e2");
        chessGame.makeMove("a6", "a5");
        chessGame.makeMove("c1", "e3");
        chessGame.makeMove("b6", "c7");
        chessGame.makeMove("c3", "b5");
        chessGame.makeMove("c7", "b8");
        chessGame.makeMove("c2", "c3");
        chessGame.makeMove("d6", "d5");
        chessGame.makeMove("e4", "e5");
        chessGame.makeMove("f6", "e4");
        chessGame.makeMove("d3", "e4");
        chessGame.makeMove("d5", "e4");
        chessGame.makeMove("b3", "c5");
        chessGame.makeMove("e7", "c5");
        chessGame.makeMove("e3", "c5");
        chessGame.makeMove("c8", "a6");
        chessGame.makeMove("c3", "c4");
        chessGame.makeMove("f8", "d8");
        chessGame.makeMove("b5", "d6");
        chessGame.makeMove("f7", "f5");
        chessGame.makeMove("e5", "f6"); // White Pawn en passant
        chessGame.makeMove("d8", "d6");
        chessGame.makeMove("e2", "e4");
        chessGame.makeMove("a6", "b7");
        chessGame.makeMove("c5", "d6");
        chessGame.makeMove("b8", "d6");
        chessGame.makeMove("a1", "d1");
        chessGame.makeMove("c6", "d8");
        chessGame.makeMove("f6", "f7"); // Black King in check
        chessGame.makeMove("g8", "f7");
        chessGame.makeMove("e4", "h7");
        chessGame.makeMove("d6", "c6");
        chessGame.makeMove("f1", "f2");
        chessGame.makeMove("c6", "e4");
        chessGame.makeMove("f4", "f5");
        chessGame.makeMove("e6", "e5");
        chessGame.makeMove("f2", "d2");
        chessGame.makeMove("b7", "c6");
        chessGame.makeMove("h7", "g6"); // Black King in check
        chessGame.makeMove("f7", "e7");
        chessGame.makeMove("d2", "d7"); // Black King in check
        chessGame.printBoard();
    }

    // Jens Hohmeister vs Tena Frank
    // http://www.chessgames.com/perl/chessgame?gid=1281617
    // includes stalemate
    public void playGame5() {
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
        chessGame.makeMove("b4", "a5");
        chessGame.makeMove("c2", "c4");
        chessGame.makeMove("e4", "e3"); // stalemate
    }

    // William Fuller vs Leonid Basin
    // http://www.chessgames.com/perl/chessgame?gid=1359374
    // includes stalemate, castling, and en passant
    public void playGame6() {
        Game chessGame = new Game();
        chessGame.makeMove("d2", "d4");
        chessGame.makeMove("f7", "f5");
        chessGame.makeMove("b1", "c3");
        chessGame.makeMove("d7", "d5");
        chessGame.makeMove("c1", "g5");
        chessGame.makeMove("c7", "c6");
        chessGame.makeMove("e2", "e3");
        chessGame.makeMove("d8", "b6");
        chessGame.makeMove("a1", "b1");
        chessGame.makeMove("b8", "d7");
        chessGame.makeMove("g1", "f3");
        chessGame.makeMove("g8", "f6");
        chessGame.makeMove("f1", "d3");
        chessGame.makeMove("e7", "e6");
        chessGame.makeMove("O-O"); // White castles
        chessGame.makeMove("f8", "d6");
        chessGame.makeMove("b2", "b4");
        chessGame.makeMove("O-O"); // Black castles
        chessGame.makeMove("c3", "a4");
        chessGame.makeMove("b6", "d8");
        chessGame.makeMove("a4", "c5");
        chessGame.makeMove("d8", "e8");
        chessGame.makeMove("g5", "h4");
        chessGame.makeMove("f6", "e4");
        chessGame.makeMove("d3", "e4");
        chessGame.makeMove("f5", "e4");
        chessGame.makeMove("c5", "d7");
        chessGame.makeMove("c8", "d7");
        chessGame.makeMove("f3", "e5");
        chessGame.makeMove("f8", "f5");
        chessGame.makeMove("h4", "g3");
        chessGame.makeMove("e8", "e7");
        chessGame.makeMove("c2", "c4");
        chessGame.makeMove("d7", "e8");
        chessGame.makeMove("c4", "c5");
        chessGame.makeMove("d6", "c7");
        chessGame.makeMove("a2", "a4");
        chessGame.makeMove("h7", "h5");
        chessGame.makeMove("h2", "h3");
        chessGame.makeMove("a7", "a6");
        chessGame.makeMove("a4", "a5");
        chessGame.makeMove("h5", "h4");
        chessGame.makeMove("g3", "h2");
        chessGame.makeMove("e8", "h5");
        chessGame.makeMove("d1", "c2");
        chessGame.makeMove("a8", "f8");
        chessGame.makeMove("b4", "b5");
        chessGame.makeMove("a6", "b5");
        chessGame.makeMove("a5", "a6");
        chessGame.makeMove("c7", "e5");
        chessGame.makeMove("h2", "e5");
        chessGame.makeMove("b7", "a6");
        chessGame.makeMove("e5", "d6");
        chessGame.makeMove("e7", "g5");
        chessGame.makeMove("f2", "f4");
        chessGame.makeMove("e4", "f3"); // en passant
        chessGame.makeMove("d6", "f8");
        chessGame.makeMove("f5", "f8");
        chessGame.makeMove("c2", "d2");
        chessGame.makeMove("a6", "a5");
        chessGame.makeMove("b1", "b2");
        chessGame.makeMove("a5", "a4");
        chessGame.makeMove("g1", "h1");
        chessGame.makeMove("f3", "g2"); // White King in check
        chessGame.makeMove("d2", "g2");
        chessGame.makeMove("f8", "f1"); // White King in check
        chessGame.makeMove("g2", "f1");
        chessGame.makeMove("g5", "e3");
        chessGame.makeMove("b2", "f2");
        chessGame.makeMove("h5", "g6");
        chessGame.makeMove("f2", "f8"); // Black King in check
        chessGame.makeMove("g8", "h7");
        chessGame.makeMove("f1", "f2");
        chessGame.makeMove("e3", "f2");
        chessGame.makeMove("f8", "h8"); // Black king in check
        chessGame.makeMove("h7", "h8"); // stalemate
    }
}
