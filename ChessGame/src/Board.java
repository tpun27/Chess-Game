/**
 * Class that contains board/piece layout on a 8-by-8 grid
 */

import java.lang.Math;

public class Board {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';
    public static final String X_POSITIONS = "abcdefgh";

    /*
     * Classic chess coordinates start from the botttom left of the grid
     * and increase towards the top right
     *
     * 2-Dimensional arrays start from the top left and increase towards the bottom right
     */
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

    public void initializePiece(Piece piece, Piece.PieceColorOptions pieceColor, String initialPiecePos) {
        piece.setPieceColor(pieceColor);
        piece.setPiecePosition(initialPiecePos);
        piece.setPieceSymbol();
        boardArray[piece.getPosY()][piece.getPosX()] = piece;
    }

    public void printBoard() {
        for (int i = VERTICAL_BOARD_LENGTH - 1; i >= 0; i--) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                if (boardArray[i][j] == null) {
                    System.out.print(DEFAULT_PIECE_SYMBOL + " ");
                }
                else {
                    System.out.print(boardArray[i][j].getPieceSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void movePiece(String initialPiecePos, String newPiecePos) {
        Piece piece = getPieceFromPosition(initialPiecePos);

        if (isOverlapOwnPiece(piece.pieceColor, newPiecePos)) {
            System.out.println("Invalid Move: You already have a piece at " + newPiecePos + "!");
            return;
        }

        if (piece instanceof Knight) {
            if (!isValidKnightMove(initialPiecePos, newPiecePos)) {
                return;
            }
        }

        if (piece instanceof Bishop) {
            if (!isValidBishopMove(initialPiecePos, newPiecePos)) {
                return;
            }
        }

        if (piece instanceof Rook) {
            if (!isValidRookMove(initialPiecePos, newPiecePos)) {
                return;
            }
        }

        if (piece instanceof Queen) {
            if (!isValidQueenMove(initialPiecePos, newPiecePos)) {
                return;
            }
        }

        if (piece instanceof King) {
            if (!isValidKingMove(initialPiecePos, newPiecePos)) {
                return;
            }
        }

        piece.setPiecePosition(newPiecePos);
        boardArray[piece.getPosY()][piece.getPosX()] = piece;
        removePieceFromBoard(initialPiecePos);
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

    public boolean isOverlapOwnPiece(Piece.PieceColorOptions pieceColor, String newPiecePos) {
        Piece overlapPiece = boardArray[parsePosY(newPiecePos)][parsePosX(newPiecePos)];
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

    public boolean isValidLShapedMove(String initialPiecePos, String newPiecePos) {
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
        return false;
    }

    public boolean isValidKnightMove(String initialPiecePos, String newPiecePos) {
        if (!isValidLShapedMove(initialPiecePos, newPiecePos)) {
            System.out.println("Invalid Move: Not an L-shaped path!");
            return false;
        }
        return true;
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
}
