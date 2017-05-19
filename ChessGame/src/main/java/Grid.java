/**
 * Class for retrofitting Board
 */
public class Grid {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';
    public static final String KING_SIDE_CASTLE_STRING = "O-O";
    public static final String QUEEN_SIDE_CASTLE_STRING = "O-O-O";

    private Piece[][] boardArray;
    private Coordinate whiteKingPos, blackKingPos, promotePawnCoordinate, lastMovedPieceCoordinate, enPassantCoordinate;
    private int turnNumber;
    private Piece.PieceColorOptions nextMoveColor;
    private boolean setEnPassantCoordinate;

    public Grid() {
        boardArray = new Piece[VERTICAL_BOARD_LENGTH][HORIZONTAL_BOARD_LENGTH];
        initializeBoardPieces();
        turnNumber = 1;
        nextMoveColor = Piece.PieceColorOptions.WHITE;
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

    public void makeMove(String initialPos, String newPos) throws InvalidBoardPositionException, InvalidMoveException {
        Coordinate initialCoordinate, newCoordinate;

        if (isInCheckMate()) {
            System.out.println("Congratulations! " + nextMoveColor + " Loses!");
        }

        // verify that user inputs valid chess notation
        initialCoordinate = new Coordinate(initialPos);
        newCoordinate = new Coordinate(newPos);
        if (initialCoordinate.getChessStringPos() == null || newCoordinate.getChessStringPos() == null) {
            throw new InvalidBoardPositionException();
        }

        if (!isValidEndpoints(initialCoordinate, newCoordinate)) {
            throw new InvalidMoveException();
        }

        if (!isValidPath(initialCoordinate, newCoordinate)) {
            throw new InvalidMoveException();
        }

        if (promotePawnCoordinate != null) {
            promotePawn();
        }

        turnNumber++;
    }

    // version of makeMove to handle castling
    public void makeMove(String castleString) throws InvalidMoveException {
        if (castleString.equals(KING_SIDE_CASTLE_STRING) || castleString.equals(QUEEN_SIDE_CASTLE_STRING)) {
            System.out.println("Yay Castling!");
        }
        else {
            throw new InvalidMoveException();
        }
    }

    private boolean isInCheckMate() {
        return true;
    }

    private boolean isValidEndpoints(Coordinate initialCoordinate, Coordinate newCoordinate) {
        Piece startPiece, endPiece;

        startPiece = getPieceFromCoordinate(initialCoordinate);
        if (startPiece == null || startPiece.getPieceColor() != nextMoveColor) {
            return false;
        }

        endPiece = getPieceFromCoordinate(newCoordinate);
        if (endPiece != null && endPiece.getPieceColor() == nextMoveColor) {
            return false;
        }
        return true;
    }

    private boolean isValidPath(Coordinate initialCoordinate, Coordinate newCoordinate) {
        Piece piece;
        piece = getPieceFromCoordinate(initialCoordinate);

        if (piece instanceof Pawn) {
            if (isValidPawnMove(initialCoordinate, newCoordinate)) {
                return true;
            }
        }

        if (piece instanceof Bishop) {
            if (isValidDiagonalPath(initialCoordinate, newCoordinate)) {
                return true;
            }
        }

        if (piece instanceof Rook) {
            if (isValidStraightPath(initialCoordinate, newCoordinate)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidPawnMove(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int diffX, diffY, forwardMultiplier;
        Piece pawnPiece, pieceToCapture;
        Coordinate betweenCoordinate;

        diffX = subtractXCoordinates(initialCoordinate, newCoordinate);
        diffY = subtractYCoordinates(initialCoordinate, newCoordinate);

        // posY increases for White Pawns and decreases for Black Pawns
        pawnPiece = getPieceFromCoordinate(initialCoordinate);
        if (pawnPiece.getPieceColor() == Piece.PieceColorOptions.WHITE) {
            forwardMultiplier = 1;
        }
        else {
            forwardMultiplier = -1;
        }

        // diagonal and 2-space forward moves have this property
        if (Math.abs(diffX) + Math.abs(diffY) == 2) {
            // 2-space forward move must be unobstructed by another piece
            if (diffY == 2*forwardMultiplier && !pawnPiece.getHasMoved()) {
                betweenCoordinate = new Coordinate(initialCoordinate);
                betweenCoordinate.addVals(0, forwardMultiplier);
                if (getPieceFromCoordinate(betweenCoordinate) == null) {
                    return true;

                }
            }
            // diagonal move must capture a piece (normal capture or en passant)
            if (diffY == forwardMultiplier) {
                // normal capture
                pieceToCapture = getPieceFromCoordinate(newCoordinate);
                if (pieceToCapture != null && pieceToCapture.getPieceColor() != nextMoveColor) {
                    return true;
                }
                // En Passant
                pieceToCapture = getPieceFromCoordinate(enPassantCoordinate);
                if (pieceToCapture != null) {
                    return true;
                }
            }
        }

        // 1-space forward move and promotion
        if (diffX == 0 && diffY == forwardMultiplier) {
            if (newCoordinate.getPosY() == 1 || newCoordinate.getPosY() == 8) {
                promotePawnCoordinate = initialCoordinate;
            }
            return true;
        }
        return false;
    }

    private boolean isValidDiagonalPath(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int diffX, diffY, spacesToVerify, xIncrement, yIncrement;
        Coordinate betweenCoordinate;
        Piece betweenPiece;

        diffX = subtractXCoordinates(initialCoordinate, newCoordinate);
        diffY = subtractYCoordinates(initialCoordinate, newCoordinate);

        // verify that path is diagonal
        if (Math.abs(diffX) != Math.abs(diffY)) {
            return false;
        }

        // verify that path is unobstructed by other pieces
        spacesToVerify = Math.abs(diffX) - 1;
        xIncrement = calculateIncrement(diffX);
        yIncrement = calculateIncrement(diffY);
        betweenCoordinate = new Coordinate(initialCoordinate);

        for (int i = 0; i < spacesToVerify; i++) {
            betweenCoordinate.addVals(xIncrement, yIncrement);
            betweenPiece = getPieceFromCoordinate(betweenCoordinate);
            if (betweenPiece != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidStraightPath(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int diffX, diffY, spacesToVerify, xIncrement, yIncrement;
        Coordinate betweenCoordinate;
        Piece betweenPiece;

        diffX = subtractXCoordinates(initialCoordinate, newCoordinate);
        diffY = subtractYCoordinates(initialCoordinate, newCoordinate);

        // verify that path is vertical or horizontal
        if (diffX != 0 && diffY != 0) {
            return false;
        }

        if (diffX == 0) {
            xIncrement = 0;
            yIncrement = calculateIncrement(diffY);
            spacesToVerify = Math.abs(diffY) - 1;
        }
        else {
            xIncrement = calculateIncrement(diffX);
            yIncrement = 0;
            spacesToVerify = Math.abs(diffX) - 1;
        }

        // verify that path is unobstructed by other pieces
        betweenCoordinate = new Coordinate(initialCoordinate);
        for (int i = 0; i < spacesToVerify; i++) {
            betweenCoordinate.addVals(xIncrement, yIncrement);
            betweenPiece = getPieceFromCoordinate(betweenCoordinate);
            if (betweenPiece != null) {
                return false;
            }
        }
        return true;
    }

    private void promotePawn() {
        promotePawnCoordinate = null;
    }

    private Piece getPieceFromCoordinate(Coordinate pieceCoordinate) {
        return boardArray[pieceCoordinate.getPosY()][pieceCoordinate.getPosX()];
    }

    private int subtractXCoordinates(Coordinate initialCoordinate, Coordinate newCoordinate) {
        return newCoordinate.getPosX() - initialCoordinate.getPosX();
    }

    private int subtractYCoordinates(Coordinate initialCoordinate, Coordinate newCoordinate) {
        return newCoordinate.getPosY() - initialCoordinate.getPosY();
    }

    private int calculateIncrement(int posDiff) {
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
