/**
 * Basic Piece class to be extended to Pawn, Bishop, Knight, Rook, Queen, and King
 */
public abstract class Piece {

    public enum PieceColorOptions {
        WHITE, BLACK
    }

    protected Coordinate pieceCoordinate;
    protected PieceColorOptions pieceColor;
    protected char pieceSymbol;
    protected boolean hasMoved;

    public Piece() {

    }

    public Piece(PieceColorOptions pieceColor, String pieceStringPos) {
        setPieceColor(pieceColor);
        setPieceCoordinate(pieceStringPos);
        hasMoved = false;
    }

    public PieceColorOptions getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(PieceColorOptions pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Coordinate getPieceCoordinate() {
        return pieceCoordinate;
    }

    public void setPieceCoordinate(Coordinate pieceCoordinate) {
        this.pieceCoordinate = pieceCoordinate;
    }

    public void setPieceCoordinate(String pieceStringPos) {
        pieceCoordinate = new Coordinate(pieceStringPos);
    }

    char getPieceSymbol() {
        return pieceSymbol;
    }

    public abstract void setPieceSymbol();

    public boolean getHasMoved() {
        return hasMoved;
    }

}
