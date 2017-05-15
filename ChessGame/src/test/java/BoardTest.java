import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by kewin on 5/14/2017.
 */
public class BoardTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testCheckMate() throws Exception {
        board.initializePiece(new King(), Piece.PieceColorOptions.WHITE, "a1");
        board.initializePiece(new Queen(), Piece.PieceColorOptions.BLACK, "b2");
        board.initializePiece(new King(), Piece.PieceColorOptions.BLACK, "b3");
        assertEquals(board.isInCheckMate("a1"), true);
    }


}