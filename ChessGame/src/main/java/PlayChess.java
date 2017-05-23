/**
 * Main class for playing chess
 * http://www.chessgames.com/perl/chesscollection?cid=1031427
 */
public class PlayChess {
    public static void main(String args[]) {
        // Future Simulation:
        // https://chess.stackexchange.com/questions/12279/famous-pawn-promotion-matches

        // Daniel Fidlow vs Albert Maier
        // http://www.chessgames.com/perl/chessgame?gid=1250783
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
        //chessGame.makeMove("h8", "g8");
        //chessGame.makeMove("c1", "g5"); // Black King in check, Black resigns
        chessGame.printBoard();
    }
}
