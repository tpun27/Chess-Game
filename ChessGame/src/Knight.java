import java.util.ArrayList;

/**
 * Class for Knight
 */
public class Knight extends Piece {
    public static final char WHITE_KNIGHT_SYMBOL = '\u2658';
    public static final char BLACK_KNIGHT_SYMBOL = '\u265E';
    public final int MAX_POSSIBLE_KNIGHT_MOVES = 8;
    final int[] KNIGHT_MOVE_CONSTANTS = {
            -2, -1,
            -2, 1,
            -1, -2,
            -1, 2,
            1, -2,
            1, 2,
            2, -1,
            2, 1
    };

    public Knight() {

    }

    public Knight(Piece.PieceColorOptions pieceColor) {
        super(pieceColor);
        setPieceSymbol();
    }

    public Knight(String initialPiecePos) {
        super(initialPiecePos);
    }

    public Knight(Piece.PieceColorOptions pieceColor, String initialPiecePos) {
        super(pieceColor, initialPiecePos);
        setPieceSymbol();
    }

    public void setPieceSymbol() {
        if (pieceColor == PieceColorOptions.WHITE)
            pieceSymbol = WHITE_KNIGHT_SYMBOL;
        else if (pieceColor == PieceColorOptions.BLACK)
            pieceSymbol = BLACK_KNIGHT_SYMBOL;
    }

    public boolean isValidMove() {
        return true;
    }

    public void printPossibleMoves(String initialPiecePos) {
        int initPosX, initPosY, possiblePosX, possiblePosY, moveCounter;
        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        moveCounter = 0;

        int[] possibleMovesList = new int[MAX_POSSIBLE_KNIGHT_MOVES * 2];
        for (int i = 0; i < KNIGHT_MOVE_CONSTANTS.length; i += 2) {
            possiblePosX = initPosX + KNIGHT_MOVE_CONSTANTS[i];
            possiblePosY = initPosY + KNIGHT_MOVE_CONSTANTS[i+1];
            if (possiblePosX >= 0 && possiblePosX < 8 && possiblePosY >= 0 && possiblePosY < 8) {
                possibleMovesList[moveCounter] = possiblePosX;
                moveCounter++;
                possibleMovesList[moveCounter] = possiblePosY;
                moveCounter++;
            }
        }
        for (int i = 0; i < moveCounter; i += 2) {
            System.out.println(possibleMovesList[i] + " " + possibleMovesList[i+1]);
        }
    }
}
