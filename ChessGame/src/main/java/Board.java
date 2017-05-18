/**
 * Class that contains board/piece layout on a 8-by-8 grid
 */

import java.lang.Math;

public class Board {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';

    /*
     * Classic chess coordinates start from the bottom left of the grid
     * and increase towards the top right
     *
     * 2-Dimensional arrays start from the top left and increase towards the bottom right
     */

    /*

    Piece[][] boardArray;

    public Board() {
        boardArray = new Piece[VERTICAL_BOARD_LENGTH][HORIZONTAL_BOARD_LENGTH];
        initializeBoardPieces();
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

    public void initializePiece(Piece piece, Piece.PieceColorOptions pieceColor, String pieceStringPos) {
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


    public void movePiece(String initialPiecePos, String newPiecePos) {
        Piece piece = getPieceFromPosition(initialPiecePos);
        if (canMovePiece(initialPiecePos, newPiecePos)) {
            piece.setPiecePosition(newPiecePos);
            boardArray[piece.getPosY()][piece.getPosX()] = piece;
            removePieceFromBoard(initialPiecePos);
        }
    }

    public boolean canMovePiece(String initialPiecePos, String newPiecePos) {
        Piece piece = getPieceFromPosition(initialPiecePos);

        if (isContainsOtherPiece(piece.pieceColor, newPiecePos)) {
            System.out.println("Invalid Move: You already have a piece at " + newPiecePos + "!");
            return false;
        }

        if (piece instanceof Pawn) {
            if (!isValidPawnMove(piece.getPieceColor(), initialPiecePos, newPiecePos)) {
                return false;
            }
        }

        if (piece instanceof Knight) {
            if (!isValidKnightMove(initialPiecePos, newPiecePos)) {
                return false;
            }
        }

        if (piece instanceof Bishop) {
            if (!isValidBishopMove(initialPiecePos, newPiecePos)) {
                return false;
            }
        }

        if (piece instanceof Rook) {
            if (!isValidRookMove(initialPiecePos, newPiecePos)) {
                return false;
            }
        }

        if (piece instanceof Queen) {
            if (!isValidQueenMove(initialPiecePos, newPiecePos)) {
                return false;
            }
        }

        if (piece instanceof King) {
            if (!isValidKingMove(initialPiecePos, newPiecePos)) {
                return false;
            }
        }
        return true;
    }

    public Piece getPieceFromPosition(String piecePos) {
        return boardArray[parsePosY(piecePos)][parsePosX(piecePos)];
    }

    public void removePieceFromBoard(String piecePos) {
        boardArray[parsePosY(piecePos)][parsePosX(piecePos)] = null;
    }

    int parsePosX(String piecePos) {
        char xChar = piecePos.charAt(0);
        return X_POSITIONS.indexOf(xChar);
    }

    int parsePosY(String piecePos) {
        char yChar = piecePos.charAt(1);
        return Character.getNumericValue(yChar) - 1;
    }

    String convertToChessNotation(int posY, int posX) {
        String chessString = "";
        chessString += X_POSITIONS.charAt(posX);
        chessString += Integer.toString(posY+1);
        return chessString;
    }

    public boolean isWithinBoard(int boardPosVal) {
        if (boardPosVal >= 0 && boardPosVal < VERTICAL_BOARD_LENGTH) {
            return true;
        }
        return false;
    }

    public boolean isContainsOtherPiece(Piece.PieceColorOptions pieceColor, String newPiecePos) {
        Piece overlapPiece = getPieceFromPosition(newPiecePos);
        if (overlapPiece != null) {
            if (overlapPiece.pieceColor == pieceColor) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDiagonalPath(String initialPiecePos, String newPiecePos, boolean oneSpaceMovement) {
        int initPosX, initPosY, newPosX, newPosY, btwnPosX, btwnPosY;
        int spacesToVerify, xIncrement, yIncrement;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);

        // check if path is a diagonal
        if (Math.abs(newPosX - initPosX) != Math.abs(newPosY - initPosY)) {
            return false;
        }

        // check if movement is one space (for King)
        spacesToVerify = Math.abs(newPosX-initPosX) - 1;
        if (oneSpaceMovement && spacesToVerify != 0) {
            return false;
        }

        // check if path is unobstructed
        xIncrement = (newPosX-initPosX) / Math.abs(newPosX-initPosX);
        yIncrement = (newPosY-initPosY) / Math.abs(newPosY-initPosY);

        btwnPosX = initPosX;
        btwnPosY = initPosY;
        for (int i = 0; i < spacesToVerify; i++) {
            btwnPosX += xIncrement;
            btwnPosY += yIncrement;
            Piece piece = boardArray[btwnPosY][btwnPosX];
            if (piece != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidHorizOrVertPath(String initialPiecePos, String newPiecePos, boolean oneSpaceMovement) {
        int initPosX, initPosY, newPosX, newPosY, btwnPosX, btwnPosY;
        int spacesToVerify, xIncrement, yIncrement;
        boolean isHorizOrVertPath;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);
        spacesToVerify = xIncrement = yIncrement = 0;
        isHorizOrVertPath = false;

        // check if path is vertical
        if ((newPosX == initPosX) && (newPosY != initPosY)) {
            spacesToVerify = Math.abs(newPosY - initPosY) - 1;
            yIncrement = (newPosY - initPosY) / Math.abs(newPosY - initPosY);
            isHorizOrVertPath = true;
        }

        // check if path is horizontal
        if ((newPosY == initPosY) && (newPosX != initPosX)) {
            spacesToVerify = Math.abs(newPosX - initPosX) - 1;
            xIncrement = (newPosX - initPosX) / Math.abs(newPosX - initPosX);
            isHorizOrVertPath = true;
        }

        // check if path is horizontal or vertical
        if (!isHorizOrVertPath) {
            return false;
        }

        // check if movement is one space (for King)
        if (oneSpaceMovement && spacesToVerify != 0) {
            return false;
        }

        // check if path is unobstructed
        btwnPosX = initPosX;
        btwnPosY = initPosY;
        for (int i = 0; i <= spacesToVerify; i++) {
            btwnPosX += xIncrement;
            btwnPosY += yIncrement;
            Piece piece = boardArray[btwnPosY][btwnPosX];
            if (piece != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isInCheck(String kingPos) {
        Piece kingPiece, oppPiece;
        Piece.PieceColorOptions kingColor;
        String initPos;

        kingPiece = getPieceFromPosition(kingPos);
        kingColor = kingPiece.getPieceColor();

        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                System.out.println(i + "," + j + " " + convertToChessNotation(i,j));
                oppPiece = boardArray[i][j];
                if (oppPiece != null && oppPiece.getPieceColor() != kingColor) {
                    initPos = convertToChessNotation(i,j);
                    // the King is in check if an opposing piece can move to it's position
                    if (canMovePiece(initPos, kingPos))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean isKingMoveable(String kingPos) {
        Piece kingPiece, tempPieceRef;
        Piece.PieceColorOptions kingColor;
        String newPos;
        int initX, initY, newX, newY, possibleVals;

        kingPiece = getPieceFromPosition(kingPos);
        kingColor = kingPiece.getPieceColor();
        // to calculate possible King moves, add -1, 0, or 1 to its vertical and/or horizontal position
        possibleVals = 3;
        initX = parsePosX(kingPos);
        initY = parsePosY(kingPos);

        for (int i = 0; i < possibleVals; i++) {
            newX = initX + i - 1;
            if (isWithinBoard(newX)) {
                for (int j = 0; j < possibleVals; j++) {
                    newY = initY + j - 1;
                    if (isWithinBoard(newY)) {
                        newPos = convertToChessNotation(newY, newX);
                        // determine if the King can move to the new position and if so, return true
                        if (!isContainsOtherPiece(kingColor, newPos)) {
                            tempPieceRef = getPieceFromPosition(newPos);
                            movePiece(kingPos, newPos);
                            if (!isInCheck(newPos)) {
                                movePiece(newPos, kingPos);
                                boardArray[newY][newX] = tempPieceRef;
                                return true;
                            }
                            else {
                                movePiece(newPos, kingPos);
                                boardArray[newY][newX] = tempPieceRef;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canBlockCheck(String oppPos, String kingPos) {
        Piece oppPiece, ownPiece, kingPiece;
        Piece.PieceColorOptions kingColor;
        String[] blockSpaces;
        int kingPosX, kingPosY, oppPosX, oppPosY, diffX, diffY;
        int incrementX, incrementY, blockSpaceCounter;

        oppPiece = getPieceFromPosition(oppPos);
        kingPiece = getPieceFromPosition(kingPos);
        kingColor = kingPiece.getPieceColor();
        blockSpaces = new String[VERTICAL_BOARD_LENGTH];
        kingPosX = parsePosX(kingPos);
        kingPosY = parsePosY(kingPos);
        oppPosX = parsePosX(oppPos);
        oppPosY = parsePosY(oppPos);
        diffX = oppPosX - kingPosX;
        diffY = oppPosY - kingPosY;
        blockSpaceCounter = 0;

        // calculate spaces that will successfully block the threatening piece
        if (oppPiece instanceof Pawn || oppPiece instanceof Knight) {
            blockSpaces[blockSpaceCounter] = oppPos;
            blockSpaceCounter++;
        }
        else {
            // diagonal threat (Bishop or Queen)
            if (Math.abs(diffX) == Math.abs(diffY)) {
                incrementX = diffX / Math.abs(diffX);
                incrementY = diffY / Math.abs(diffY);
                for (int i = 1; i <= Math.abs(diffX); i++) {
                    int tempPosY = kingPosY + incrementY * i;
                    int tempPosX = kingPosX + incrementX * i;
                    String tempPos = convertToChessNotation(tempPosY, tempPosX);
                    blockSpaces[blockSpaceCounter] = tempPos;
                    blockSpaceCounter++;
                }
            }
            // vertical/horizontal threat (Rook or Queen)
            else {
                if (diffX == 0) {
                    incrementY = diffY / Math.abs(diffY);
                    for (int i = 1; i <= Math.abs(diffY); i++) {
                        int tempPosY = kingPosY + incrementY * i;
                        int tempPosX = kingPosX;
                        String tempPos = convertToChessNotation(tempPosY, tempPosX);
                        blockSpaces[blockSpaceCounter] = tempPos;
                        blockSpaceCounter++;
                    }
                }
                else if (diffY == 0) {
                    incrementX = diffX / Math.abs(diffX);
                    for (int i = 1; i <= Math.abs(diffX); i++) {
                        int tempPosY = kingPosY;
                        int tempPosX = kingPosX + incrementX * i;
                        String tempPos = convertToChessNotation(tempPosY, tempPosX);
                        blockSpaces[blockSpaceCounter] = tempPos;
                        blockSpaceCounter++;
                    }
                }
            }
        }

        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                ownPiece = boardArray[i][j];
                if (ownPiece != null && ownPiece.getPieceColor() == kingColor) {
                    String ownPiecePos = convertToChessNotation(j, i);
                    for (int k = 0; k < blockSpaceCounter; k++) {
                        if (canMovePiece(ownPiecePos, blockSpaces[k])) {
                            // ensure that moving the piece to block does not still put King in check
                            Piece tempPieceRef = getPieceFromPosition(blockSpaces[k]);
                            movePiece(ownPiecePos, blockSpaces[k]);
                            if (!isInCheck(kingPos)) {
                                movePiece(blockSpaces[k], ownPiecePos);
                                boardArray[parsePosY(blockSpaces[k])][parsePosY(blockSpaces[k])] = tempPieceRef;
                                return true;
                            }
                            else {
                                movePiece(blockSpaces[k], ownPiecePos);
                                boardArray[parsePosY(blockSpaces[k])][parsePosY(blockSpaces[k])] = tempPieceRef;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isInCheckMate(String kingPos) {
        if (isKingMoveable(kingPos)) {
            return false;
        }

        // check if King is in check and another Piece can block the check
        Piece kingPiece, oppPiece;
        Piece.PieceColorOptions kingColor;
        String initPos;

        kingPiece = getPieceFromPosition(kingPos);
        kingColor = kingPiece.getPieceColor();

        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                //System.out.println(i + "," + j + " " + convertToChessNotation(i,j));
                oppPiece = boardArray[i][j];
                if (oppPiece != null && oppPiece.getPieceColor() != kingColor) {
                    initPos = convertToChessNotation(j,i);
                    // the King is in check if an opposing piece can move to it's position
                    if (canMovePiece(initPos, kingPos)) {
                        if (canBlockCheck(initPos, kingPos)) {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidPawnMove(Piece.PieceColorOptions pieceColor, String initialPiecePos, String newPiecePos) {
        int initPosX, initPosY, newPosX, newPosY, posXDiff, posYDiff, forwardMultiplier;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);
        posXDiff = newPosX - initPosX;
        posYDiff = newPosY - initPosY;

        forwardMultiplier = 1;
        if (pieceColor == Piece.PieceColorOptions.BLACK) {
            forwardMultiplier *= -1;
        }

        // check if Pawn moves forward vertically one space (including diagonals)
        if (posYDiff == forwardMultiplier) {
            Piece piece = getPieceFromPosition(newPiecePos);
            // diagonal move valid if capturing opponent's piece
            if (posXDiff == 1 || posXDiff == -1) {
                if (piece != null && piece.pieceColor != pieceColor) {
                    return true;
                }
            }
            // if not a diagonal move, make sure space is unoccupied
            if (posXDiff == 0) {
                if (piece == null) {
                    return true;
                }
            }
        }

        // if Pawn moves two spaces forward (no diagonals)
        if (posXDiff == 0 && posYDiff == 2 * forwardMultiplier) {
            int forwardPosY1 = initPosY + forwardMultiplier;
            int forwardPosY2 = initPosY + 2 * forwardMultiplier;
            // Pawns can only move two spaces from their starting row
            if ((pieceColor == Piece.PieceColorOptions.WHITE && initPosY == 1)
                || (pieceColor == Piece.PieceColorOptions.BLACK && initPosY == 6)) {
                // check if forward spaces are unoccupied
                if (boardArray[forwardPosY1][initPosX] == null && boardArray[forwardPosY2][initPosX] == null) {
                    return true;
                }
            }
        }

        System.out.println("Invalid move: Pawn cannot move there!");
        return false;
    }

    public boolean isValidKnightMove(String initialPiecePos, String newPiecePos) {
        int initPosX, initPosY, newPosX, newPosY, absPosXDiff, absPosYDiff;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);

        absPosXDiff = Math.abs(newPosX-initPosX);
        absPosYDiff = Math.abs(newPosY-initPosY);

        if (absPosXDiff + absPosYDiff == 3) {
            if ((absPosXDiff == 1 || absPosYDiff == 1) && (absPosXDiff == 2 || absPosYDiff == 2)) {
                return true;
            }
        }
        System.out.println("Invalid Move: Not an L-shaped path!");
        return false;
    }

    public boolean isValidBishopMove(String initialPiecePos, String newPiecePos) {
        if (!isValidDiagonalPath(initialPiecePos, newPiecePos, false)) {
            System.out.println("Invalid Move: Not a valid diagonal path!");
            return false;
        }
        return true;
    }

    public boolean isValidRookMove(String initialPiecePos, String newPiecePos) {
        if (!isValidHorizOrVertPath(initialPiecePos, newPiecePos, false)) {
            System.out.println("Invalid Move: Not a valid vertical/horizontal path!");
            return false;
        }
        return true;
    }

    public boolean isValidQueenMove(String initialPiecePos, String newPiecePos) {
        if (isValidDiagonalPath(initialPiecePos, newPiecePos, false)) {
            return true;
        }
        if (isValidHorizOrVertPath(initialPiecePos, newPiecePos, false)) {
            return true;
        }
        System.out.println("Invalid Move: Not a valid path!");
        return false;
    }

    public boolean isValidKingMove(String initialPiecePos, String newPiecePos) {
        if (isValidDiagonalPath(initialPiecePos, newPiecePos, true)) {
            return true;
        }
        if (isValidHorizOrVertPath(initialPiecePos, newPiecePos, true)) {
            return true;
        }
        System.out.println("Invalid Move: Not a valid path!");
        return false;
    }
    */
}
