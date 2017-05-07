/**
 * Basic Piece class to be extended to Pawn, Bishop, Knight, Rook, Queen, and King
 */
public abstract class Piece {

    public static final String X_POSITIONS = "abcdefgh";
    public static final char WHITE_PAWN_SYMBOL = '\u2659';
    public static final char WHITE_KNIGHT_SYMBOL = '\u2658';
    public static final char WHITE_BISHOP_SYMBOL = '\u2657';
    public static final char WHITE_ROOK_SYMBOL = '\u2656';
    public static final char WHITE_QUEEN_SYMBOL = '\u2655';
    public static final char WHITE_KING_SYMBOL = '\u2654';
    public static final char BLACK_PAWN_SYMBOL = '\u265F';
    public static final char BLACK_KNIGHT_SYMBOL = '\u265E';
    public static final char BLACK_BISHOP_SYMBOL = '\u265D';
    public static final char BLACK_ROOK_SYMBOL = '\u265C';
    public static final char BLACK_QUEEN_SYMBOL = '\u265B';
    public static final char BLACK_KING_SYMBOL = '\u265A';

    public enum PieceColorOptions {
        WHITE, BLACK
    }

    int posX;
    int posY;
    char pieceSymbol;
    PieceColorOptions pieceColor;

    public Piece() {

    }

    public Piece(String initialPiecePos) {
        this.posX = parsePosX(initialPiecePos);
        this.posY = parsePosY(initialPiecePos);
    }

    public void movePiece(Player playerName, String initialPiecePos, String newPiecePos) {
        this.posX = parsePosX(newPiecePos);
        this.posY = parsePosY(newPiecePos);
    }

    public void capturePiece(char posX, char posY) {

    }

    public boolean isValidMove() {
        return true;
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

    char getPieceSymbol() {
        return pieceSymbol;
    }

    public void setPieceColor(PieceColorOptions pieceColor) {
        this.pieceColor = pieceColor;
    }

    public abstract void setPieceSymbol();

}
