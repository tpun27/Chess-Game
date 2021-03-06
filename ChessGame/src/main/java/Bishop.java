/**
 * Class for Bishop
 */
public class Bishop extends Piece{
    public static final char WHITE_BISHOP_SYMBOL = '\u2657';
    public static final char BLACK_BISHOP_SYMBOL = '\u265D';

    public Bishop() {

    }

    public Bishop(Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        super(pieceColor, pieceStringPos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == Piece.PieceColorOptions.WHITE)
            pieceSymbol = WHITE_BISHOP_SYMBOL;
        else if (pieceColor == Piece.PieceColorOptions.BLACK)
            pieceSymbol = BLACK_BISHOP_SYMBOL;
    }
}
