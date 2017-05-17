/**
 * Class for Knight
 */
public class Knight extends Piece {
    public static final char WHITE_KNIGHT_SYMBOL = '\u2658';
    public static final char BLACK_KNIGHT_SYMBOL = '\u265E';

    public Knight() {

    }

    public Knight(Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        super(pieceColor, pieceStringPos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == PieceColorOptions.WHITE)
            pieceSymbol = WHITE_KNIGHT_SYMBOL;
        else if (pieceColor == PieceColorOptions.BLACK)
            pieceSymbol = BLACK_KNIGHT_SYMBOL;
    }
}
