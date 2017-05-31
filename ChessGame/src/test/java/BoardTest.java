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
     * Testing method isInCheckMate using a board position similar to:
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
        gameGrid.printBoard();
        assertEquals(gameGrid.isInCheckMate(gameGrid.nextMoveColor), true);
    }

    @After
    public void tearDown() {
        gameGrid = null;
    }
}