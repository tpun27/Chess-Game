/**
 * Class for King
 */
public class King extends Piece {
    public static final char WHITE_KING_SYMBOL = '\u2654';
    public static final char BLACK_KING_SYMBOL = '\u265A';

    public King() {

    }

    public King(Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        super(pieceColor, pieceStringPos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == Piece.PieceColorOptions.WHITE)
            pieceSymbol = WHITE_KING_SYMBOL;
        else if (pieceColor == Piece.PieceColorOptions.BLACK)
            pieceSymbol = BLACK_KING_SYMBOL;
    }
}
