/**
 * Class for Rook
 */
public class Rook extends Piece {
    public static final char WHITE_ROOK_SYMBOL = '\u2656';
    public static final char BLACK_ROOK_SYMBOL = '\u265C';

    public Rook() {

    }

    public Rook(Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        super(pieceColor, pieceStringPos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == Piece.PieceColorOptions.WHITE)
            pieceSymbol = WHITE_ROOK_SYMBOL;
        else if (pieceColor == Piece.PieceColorOptions.BLACK)
            pieceSymbol = BLACK_ROOK_SYMBOL;
    }
}
