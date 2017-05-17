/**
 * Class for Pawn
 */
public class Pawn extends Piece {
    public static final char WHITE_PAWN_SYMBOL = '\u2659';
    public static final char BLACK_PAWN_SYMBOL = '\u265F';

    public Pawn() {

    }

    public Pawn(PieceColorOptions pieceColor, String pieceStringPos) {
        super(pieceColor, pieceStringPos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == PieceColorOptions.WHITE)
            pieceSymbol = WHITE_PAWN_SYMBOL;
        else if (pieceColor == PieceColorOptions.BLACK)
            pieceSymbol = BLACK_PAWN_SYMBOL;
    }
}
