/**
 * Test Games
 */
public class TestGames {
    // John Odin Howard Taylor vs Unknown Player
    // http://www.chessgames.com/perl/chessgame?gid=1336733
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
}
