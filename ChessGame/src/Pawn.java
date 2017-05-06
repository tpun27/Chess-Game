/**
 * Class for Pawn
 */
public class Pawn extends Piece {
    char pieceColor;

    public Pawn(char pieceColor) {
        setPieceColor(pieceColor);
        setPieceSymbol();
    }

    public Pawn(char pieceColor, String initialPiecePos) {
        setPieceColor(pieceColor);
        setPieceSymbol();
        this.posX = parsePosX(initialPiecePos);
        this.posY = parsePosY(initialPiecePos);
    }

    public void setPieceColor(char pieceColor) {
        this.pieceColor = pieceColor;
    }

    public void setPieceSymbol() {
        if (pieceColor == 'W')
            pieceSymbol = WHITE_PAWN_SYMBOL;
        else if (pieceColor == 'B')
            pieceSymbol = BLACK_PAWN_SYMBOL;
    }
}
