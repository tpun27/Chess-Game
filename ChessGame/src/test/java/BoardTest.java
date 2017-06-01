import static org.junit.Assert.*;
import org.junit.*;

public class BoardTest {
    Grid gameGrid;

    @Before
    public void setUp() throws Exception {
        gameGrid = new Grid();
        gameGrid.clearGrid();
    }

    @Test
    /*
     * Testing method isInCheckMate() using a board position similar to:
     * http://www.chessgames.com/perl/chessgame?gid=1259987
     * Black is in Checkmate
     */
    public void testCheckmate1() {
        gameGrid.initializePiece(new King(), Piece.PieceColorOptions.WHITE, "e1");
        gameGrid.initializePiece(new Queen(), Piece.PieceColorOptions.WHITE, "d8");
        gameGrid.initializePiece(new King(), Piece.PieceColorOptions.BLACK, "f8");
        gameGrid.initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "f7");
        gameGrid.initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "g7");
        gameGrid.initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "h7");
        gameGrid.nextMoveColor = Piece.PieceColorOptions.BLACK;
        //gameGrid.printBoard();
        assertEquals(gameGrid.isInCheckMate(gameGrid.nextMoveColor), true);
    }

    @Test
    /*
     * Testing method isInStaleMate() using a board position from:
     * https://en.wikipedia.org/wiki/Stalemate#CITEREFPolgarTruong2005
     * Black has the next move
     */
    public void testStalemate1() {
        gameGrid.initializePiece(new King(), Piece.PieceColorOptions.WHITE, "f7");
        gameGrid.initializePiece(new Queen(), Piece.PieceColorOptions.WHITE, "g6");
        gameGrid.initializePiece(new King(), Piece.PieceColorOptions.BLACK, "h8");
        gameGrid.nextMoveColor = Piece.PieceColorOptions.BLACK;
        //gameGrid.printBoard();
        assertEquals(gameGrid.isInStaleMate(gameGrid.nextMoveColor), true);
    }

    @Test(expected = InvalidBoardPositionException.class)
    /*
     * Testing method makeMove() to see if it suitably returns InvalidBoardPositionException
     * when an invalid Coordinate string is used
      * A valid Coordinate string has a letter (a to h) followed by a digit (1 to 8)
     */
    public void testMakeMove1() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("1a","j9");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidEndpoints()) to see if it suitably returns InvalidMoveException
     * when the initial and final Coordinates contain pieces of the same color (White)
     */
    public void testMakeMove2() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("a1","a2");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidEndpoints()) to see if it suitably returns InvalidMoveException
     * when the initial Coordinate does not contain a piece
     */
    public void testMakeMove3() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("a3","a5");
    }
    
    @After
    public void tearDown() {
        gameGrid = null;
    }
}