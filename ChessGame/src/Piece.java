/**
 * Basic Piece class to be extended to Pawn, Bishop, Knight, Rook, Queen, and King
 */
public abstract class Piece {

    public static final String X_POSITIONS = "abcdefgh";

    public enum PieceColorOptions {
        WHITE, BLACK
    }

    int posX;
    int posY;
    char pieceSymbol;
    PieceColorOptions pieceColor;

    public Piece() {

    }

    public Piece(PieceColorOptions pieceColor) {
        setPieceColor(pieceColor);
    }

    public Piece(String initialPiecePos) {
        setPiecePosition(initialPiecePos);
    }

    public Piece(PieceColorOptions pieceColor, String initialPiecePos) {
        setPieceColor(pieceColor);
        setPiecePosition(initialPiecePos);
    }

    int parsePosX(String piecePos) {
        char xChar = piecePos.charAt(0);
        return X_POSITIONS.indexOf(xChar);
    }

    int parsePosY(String piecePos) {
        char yChar = piecePos.charAt(1);
        return Character.getNumericValue(yChar) - 1;
    }

    int getPosX() {
        return posX;
    }

    int getPosY() {
        return posY;
    }

    PieceColorOptions getPieceColor() {
        return pieceColor;
    }

    char getPieceSymbol() {
        return pieceSymbol;
    }

    public void setPiecePosition(String newPiecePos) {
        this.posX = parsePosX(newPiecePos);
        this.posY = parsePosY(newPiecePos);
    }

    public void setPieceColor(PieceColorOptions pieceColor) {
        this.pieceColor = pieceColor;
    }

    public abstract void setPieceSymbol();

}
