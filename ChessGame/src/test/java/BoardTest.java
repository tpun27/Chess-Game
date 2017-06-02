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

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Pawn is moved diagonally without capturing a piece
     */
    public void testMakeMove4() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("e2","d3");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Pawn is moved 2 spaces after having already moved
     */
    public void testMakeMove5() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("e2","e3");
        gameGrid.makeMove("e7","e6");
        gameGrid.makeMove("e3","e5");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Knight is moved illegally (not a L-shaped move)
     */
    public void testMakeMove6() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("g1","f3");
        gameGrid.makeMove("b8","c6");
        gameGrid.makeMove("f3","e4");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Bishop is moved on a non-diagonal path
     */
    public void testMakeMove7() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("f2","h4");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Bishop is moved on a diagonal path obstructed by another piece
     */
    public void testMakeMove8() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("f2","a6");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Rook is moved on a path that is neither vertical or horizontal
     */
    public void testMakeMove9() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("h1","c6");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Rook is moved on a straight path obstructed by another piece
     */
    public void testMakeMove10() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("h1","h6");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a Queen is moved illegally (neither a straight nor diagonal path)
     */
    public void testMakeMove11() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("d1","e3");
    }

    @Test(expected = InvalidMoveException.class)
    /*
     * Testing method makeMove() (and indirectly isValidPath()) to see if it suitably returns InvalidMoveException
     * when a King is moved more than one space
     */
    public void testMakeMove12() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("e1","e3");
    }


    @After
    public void tearDown() {
        gameGrid = null;
    }
}