import static org.junit.Assert.*;
import org.junit.*;

public class HelperFunctionTest {
    Grid gameGrid;

    @Before
    public void setUp() throws Exception {
        gameGrid = new Grid();
        gameGrid.clearGrid();
    }

    @Test
    /*
     * Testing method getKingCoordinate() to see if it suitably returns the correct Coordinate
     * of the King
     */
    public void testGetKingCoordinate1() throws InvalidBoardPositionException, InvalidMoveException {
        gameGrid.initializeBoardPieces();
        gameGrid.makeMove("e2","e4");
        gameGrid.makeMove("e7","e5");
        gameGrid.makeMove("e1","e2");
        assertEquals("e2", gameGrid.getKingCoordinate(Piece.PieceColorOptions.WHITE).getChessStringPos());
    }


    @After
    public void tearDown() {
        gameGrid = null;
    }
}