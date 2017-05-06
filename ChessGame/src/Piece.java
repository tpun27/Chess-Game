/**
 * Basic Piece class to be extended to Pawn, Bishop, Knight, Rook, Queen, and King
 */
public abstract class Piece {
    char posX;
    char posY;

    public void movePiece(char newX, char newY) {
        this.posX = newX;
        this.posY = newY;
    }

    public void capturePiece(char posX, char posY) {

    }

    public boolean isValidMove() {
        return true;
    }

}
