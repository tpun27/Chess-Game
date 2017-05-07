/**
 * Class for Pawn
 */
public class Pawn extends Piece {

    public Pawn() {

    }

    public Pawn(PieceColorOptions pieceColor) {
        setPieceColor(pieceColor);
        setPieceSymbol();
    }

    public Pawn(PieceColorOptions pieceColor, String initialPiecePos) {
        setPieceColor(pieceColor);
        setPieceSymbol();
        this.posX = parsePosX(initialPiecePos);
        this.posY = parsePosY(initialPiecePos);
    }

    public void setPieceSymbol() {
        if (pieceColor == PieceColorOptions.WHITE)
            pieceSymbol = WHITE_PAWN_SYMBOL;
        else if (pieceColor == PieceColorOptions.BLACK)
            pieceSymbol = BLACK_PAWN_SYMBOL;
    }
}
