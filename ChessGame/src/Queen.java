/**
 * Class for Queen
 */
public class Queen extends Piece {
    public static final char WHITE_QUEEN_SYMBOL = '\u2655';
    public static final char BLACK_QUEEN_SYMBOL = '\u265B';

    public Queen() {

    }

    public Queen(Piece.PieceColorOptions pieceColor) {
        super(pieceColor);
        setPieceSymbol();
    }

    public Queen(String initialPiecePos) {
        super(initialPiecePos);
    }

    public Queen(Piece.PieceColorOptions pieceColor, String initialPiecePos) {
        super(pieceColor, initialPiecePos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == Piece.PieceColorOptions.WHITE)
            pieceSymbol = WHITE_QUEEN_SYMBOL;
        else if (pieceColor == Piece.PieceColorOptions.BLACK)
            pieceSymbol = BLACK_QUEEN_SYMBOL;
    }
}
