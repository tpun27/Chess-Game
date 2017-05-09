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
        if (overlapOwnPiece(piece.pieceColor, newPiecePos)) {
            System.out.println("Invalid Move: You already have a piece at " + newPiecePos + "!");
            return;
        }
        if (piece instanceof Bishop) {
            if (!isDiagonalPath(initialPiecePos, newPiecePos)) {
                System.out.println("Invalid Move: That is not a diagonal path!");
                return;
            }
            if (!isUnobstructedDiagonalPath(initialPiecePos, newPiecePos)) {
                System.out.println("Invalid Move: There is another piece along that path!");
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

    public boolean overlapOwnPiece(Piece.PieceColorOptions pieceColor, String newPiecePos) {
        Piece overlapPiece = boardArray[parsePosY(newPiecePos)][parsePosX(newPiecePos)];
        if (overlapPiece != null) {
            if (overlapPiece.pieceColor == pieceColor) {
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalPath(String initialPiecePos, String newPiecePos) {
        int initPosX, initPosY, newPosX, newPosY;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);

        if (Math.abs(newPosX - initPosX) == Math.abs(newPosY - initPosY)) {
            return true;
        }
        return false;
    }

    public boolean isUnobstructedDiagonalPath(String initialPiecePos, String newPiecePos) {
        int initPosX, initPosY, newPosX, newPosY, btwnPosX, btwnPosY;
        int spacesToVerify, xIncrement, yIncrement;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);

        spacesToVerify = Math.abs(newPosX-initPosX) - 1;
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

    public boolean isHorizontalOrVertPath(String initialPiecePos, String newPiecePos) {
        int initPosX, initPosY, newPosX, newPosY;

        initPosX = parsePosX(initialPiecePos);
        initPosY = parsePosY(initialPiecePos);
        newPosX = parsePosX(newPiecePos);
        newPosY = parsePosY(newPiecePos);

        if ((newPosX == initPosX) && (newPosY != initPosY)) {
            return true;
        }

        if ((newPosY == initPosY) && (newPosX != initPosX)) {
            return true;
        }

        return false;
    }
}
