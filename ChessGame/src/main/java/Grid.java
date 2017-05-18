/**
 * Class for retrofitting Board
 */
public class Grid {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';

    private Piece[][] boardArray;
    private Coordinate whiteKingPos;
    private Coordinate blackKingPos;
    private int turnNumber;
    private Piece.PieceColorOptions nextMove;

    public Grid() {
        boardArray = new Piece[VERTICAL_BOARD_LENGTH][HORIZONTAL_BOARD_LENGTH];
        initializeBoardPieces();
        turnNumber = 1;
        nextMove = Piece.PieceColorOptions.WHITE;
        whiteKingPos = new Coordinate("e1");
        blackKingPos = new Coordinate("e8");
    }

    public void initializeBoardPieces() {
        // white Pawns
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "a2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "b2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "c2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "d2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "e2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "f2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "g2");
        initializePiece(new Pawn(), Piece.PieceColorOptions.WHITE, "h2");

        // black Pawns
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "a7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "b7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "c7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "d7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "e7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "f7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "g7");
        initializePiece(new Pawn(), Piece.PieceColorOptions.BLACK, "h7");

        // Knights
        initializePiece(new Knight(), Piece.PieceColorOptions.WHITE, "b1");
        initializePiece(new Knight(), Piece.PieceColorOptions.WHITE, "g1");
        initializePiece(new Knight(), Piece.PieceColorOptions.BLACK, "b8");
        initializePiece(new Knight(), Piece.PieceColorOptions.BLACK, "g8");

        // Bishops
        initializePiece(new Bishop(), Piece.PieceColorOptions.WHITE, "c1");
        initializePiece(new Bishop(), Piece.PieceColorOptions.WHITE, "f1");
        initializePiece(new Bishop(), Piece.PieceColorOptions.BLACK, "c8");
        initializePiece(new Bishop(), Piece.PieceColorOptions.BLACK, "f8");

        // Rooks
        initializePiece(new Rook(), Piece.PieceColorOptions.WHITE, "a1");
        initializePiece(new Rook(), Piece.PieceColorOptions.WHITE, "h1");
        initializePiece(new Rook(), Piece.PieceColorOptions.BLACK, "a8");
        initializePiece(new Rook(), Piece.PieceColorOptions.BLACK, "h8");

        // Queens
        initializePiece(new Queen(), Piece.PieceColorOptions.WHITE, "d1");
        initializePiece(new Queen(), Piece.PieceColorOptions.BLACK, "d8");

        // Kings
        initializePiece(new King(), Piece.PieceColorOptions.WHITE, "e1");
        initializePiece(new King(), Piece.PieceColorOptions.BLACK, "e8");
    }

    private void initializePiece(Piece piece, Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        Coordinate pieceCoordinate;

        piece.setPieceColor(pieceColor);
        piece.setPieceCoordinate(pieceStringPos);
        piece.setPieceSymbol();

        pieceCoordinate = piece.getPieceCoordinate();
        boardArray[pieceCoordinate.getPosY()][pieceCoordinate.getPosX()] = piece;
    }

    public void printBoard() {
        Piece piece;

        for (int i = VERTICAL_BOARD_LENGTH - 1; i >= 0; i--) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                piece = boardArray[i][j];
                if (piece == null) {
                    System.out.print(DEFAULT_PIECE_SYMBOL + " ");
                }
                else {
                    System.out.print(piece.getPieceSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void makeMove(String initialPos, String newPos) {
        Coordinate initialCoordinate, newCoordinate;

        if (isInCheckMate()) {
            System.out.println("Congratulations! " + nextMove + " Loses!");
        }

        try {
            initialCoordinate = new Coordinate(initialPos);
            newCoordinate = new Coordinate(newPos);
        } catch (InvalidBoardPositionException e) {
            e.printErrorMsg();
            return;
        }
    }

    private boolean isInCheckMate() {
        return true;
    }

    private boolean isValidDiagonalMove(Coordinate initialCoordinate, Coordinate newCoordinate) {

    }

    private int diffX(String initialPos, String newPos) {
        Coordinate initialCoord, newCoord;
        initialCoord = new Coordinate(initialPos);
        newCoord = new Coordinate(newPos);

        return newCoord.getPosX() - initialCoord.getPosX();
    }

    private int diffY(String initialPos, String newPos) {
        Coordinate initialCoord, newCoord;
        initialCoord = new Coordinate(initialPos);
        newCoord = new Coordinate(newPos);

        return newCoord.getPosY() - initialCoord.getPosY();
    }

    private int calculateDirection(int posDiff) {
        if (posDiff > 0) {
            return 1;
        }
        else if (posDiff < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
