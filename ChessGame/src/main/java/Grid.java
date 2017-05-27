/**
 * Class for retrofitting Board
 */

import java.util.Scanner;

public class Grid {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';
    public static final String KING_SIDE_CASTLE_STRING = "O-O";
    public static final String QUEEN_SIDE_CASTLE_STRING = "O-O-O";

    private Piece[][] boardArray;
    private int turnNumber;
    private Piece.PieceColorOptions nextMoveColor;
    private boolean doEnPassant, doPromotion, setTwoSpaceFlag;
    Coordinate gridEnPassantCoordinate;

    public Grid() {
        boardArray = new Piece[VERTICAL_BOARD_LENGTH][HORIZONTAL_BOARD_LENGTH];
        initializeBoardPieces();
        turnNumber = 1;
        nextMoveColor = Piece.PieceColorOptions.WHITE;
        resetPawnFlags();
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

        // verify that user inputs valid chess notation
        initialCoordinate = new Coordinate(initialPos);
        newCoordinate = new Coordinate(newPos);
        if (initialCoordinate.getChessStringPos() == null || newCoordinate.getChessStringPos() == null) {
            System.out.println("Invalid Coordinate"); // debugging
            throw new InvalidBoardPositionException();
        }

        if (!isValidEndpoints(initialCoordinate, newCoordinate, nextMoveColor)) {
            System.out.println("!isValidEndpoints"); // debugging
            throw new InvalidMoveException();
        }

        if (!isValidPath(initialCoordinate, newCoordinate, nextMoveColor, true)) {
            System.out.println("!isValidPath"); // debugging
            throw new InvalidMoveException();
        }

        // make the move if it does not result in the current player's King being checked
        if (isMovePossibleWithoutCheck(initialCoordinate, newCoordinate, nextMoveColor, doEnPassant)) {
            Piece movingPiece = getPieceFromCoordinate(initialCoordinate);
            boardArray[newCoordinate.getPosY()][newCoordinate.getPosX()] = movingPiece;
            boardArray[initialCoordinate.getPosY()][initialCoordinate.getPosX()] = null;
            movingPiece.setPieceCoordinate(newCoordinate);
            movingPiece.setHasMoved(true);
            if (doEnPassant) {
                boardArray[gridEnPassantCoordinate.getPosY()][gridEnPassantCoordinate.getPosX()] = null;
            }
            if (setTwoSpaceFlag) {
                ((Pawn) movingPiece).setTwoSpaceMoveTurnNum(turnNumber);
            }
            if (doPromotion) {
                promotePiece(nextMoveColor, newCoordinate.getChessStringPos());
            }
        } else {
            System.out.println("!isMovePossibleWithoutCheck"); // debugging
            throw new InvalidMoveException();
        }

        // output a message if the player has won or if the next player is in check
        nextMoveColor = oppositeColor(nextMoveColor);
        if (isInCheck(nextMoveColor)) {
            if (isInCheckMate(nextMoveColor)) {
                System.out.println("Congratulations! " + oppositeColor(nextMoveColor) + " Wins!");
            }
            else {
                System.out.println(nextMoveColor  + " is in check!");
            }
        }
        else if (isInStaleMate(nextMoveColor)) {
            System.out.println("It's a draw!");
        }

        turnNumber++;
        resetPawnFlags();
    }

    // version of makeMove to handle castling
    public void makeMove(String castleString) throws InvalidMoveException {
        Piece kingPiece, rookPiece, betweenPiece;
        Coordinate kingCoordinate, rookCoordinate, betweenCoordinate, oppCoordinate;
        int xIncrement, spacesToRook;

        // verify valid castle String O-O or O-O-O
        if (!castleString.equals(KING_SIDE_CASTLE_STRING) && !castleString.equals(QUEEN_SIDE_CASTLE_STRING)) {
            throw new InvalidMoveException();
        }

        kingCoordinate = getKingCoordinate(nextMoveColor);
        kingPiece = getPieceFromCoordinate(kingCoordinate);

        // King cannot be in check or have moved already
        if (isInCheck(nextMoveColor) || kingPiece.getHasMoved()) {
            throw new InvalidMoveException();
        }

        if (castleString == KING_SIDE_CASTLE_STRING) {
            xIncrement = 1;
            spacesToRook = 3;
        }
        else {
            xIncrement = -1;
            spacesToRook = 4;
        }

        rookCoordinate = new Coordinate(kingCoordinate.getPosX() + spacesToRook * xIncrement,
                kingCoordinate.getPosY());
        rookPiece = getPieceFromCoordinate(rookCoordinate);
        // Rook must not have moved already
        if (!(rookPiece instanceof Rook) || rookPiece.getPieceColor() != nextMoveColor || rookPiece.getHasMoved()) {
            throw new InvalidMoveException();
        }

        betweenCoordinate = new Coordinate(kingCoordinate);
        for (int i = 0; i < 2; i++) {
            betweenCoordinate.addVals(xIncrement, 0);
            betweenPiece = getPieceFromCoordinate(betweenCoordinate);
            // the two spaces to the left or right of the King must be empty
            if (betweenPiece != null) {
                throw new InvalidMoveException();
            }
            for (int j = 0; j < VERTICAL_BOARD_LENGTH; j++) {
                for (int k = 0; k < HORIZONTAL_BOARD_LENGTH; k++) {
                    oppCoordinate = new Coordinate(k, j);
                    // an opposing piece cannot be attacking the two empty spaces
                    if (isValidEndpoints(oppCoordinate, betweenCoordinate, oppositeColor(nextMoveColor))) {
                        if (isValidPath(oppCoordinate, betweenCoordinate, oppositeColor(nextMoveColor), false)) {
                            throw new InvalidMoveException();
                        }
                    }
                }
            }
        }

        // move the King and Rook for castling
        boardArray[betweenCoordinate.getPosY()][betweenCoordinate.getPosX()] = kingPiece;
        boardArray[kingCoordinate.getPosY()][kingCoordinate.getPosX()] = null;
        kingPiece.setPieceCoordinate(betweenCoordinate);

        betweenCoordinate.addVals(xIncrement * -1, 0);
        boardArray[betweenCoordinate.getPosY()][betweenCoordinate.getPosX()] = rookPiece;
        boardArray[rookCoordinate.getPosY()][rookCoordinate.getPosX()] = null;
        rookPiece.setPieceCoordinate(betweenCoordinate);


        kingPiece.setHasMoved(true);
        rookPiece.setHasMoved(true);

        nextMoveColor = oppositeColor(nextMoveColor);
        turnNumber++;
    }

    private boolean isMovePossibleWithoutCheck(Coordinate initialCoordinate, Coordinate newCoordinate,
                                   Piece.PieceColorOptions playerColor, boolean isEnPassant) {
        Piece movingPiece, capturedPiece, enPassantPiece = null;
        boolean isPossible;
        int incrementY;
        Coordinate enPassantCoordinate = null;

        movingPiece = getPieceFromCoordinate(initialCoordinate);
        capturedPiece = getPieceFromCoordinate(newCoordinate);
        // move Pieces temporarily
        boardArray[newCoordinate.getPosY()][newCoordinate.getPosX()] = movingPiece;
        boardArray[initialCoordinate.getPosY()][initialCoordinate.getPosX()] = null;

        // if En Passant occurs, the opposing Pawn must also be removed from the board
        if (isEnPassant) {
            enPassantPiece = getPieceFromCoordinate(gridEnPassantCoordinate);
            boardArray[gridEnPassantCoordinate.getPosY()][gridEnPassantCoordinate.getPosX()] = null;
        }


        if (isInCheck(playerColor)) {
            isPossible = false;
        }
        else {
            isPossible = true;
        }

        // undo Board changes
        boardArray[newCoordinate.getPosY()][newCoordinate.getPosX()] = capturedPiece;
        boardArray[initialCoordinate.getPosY()][initialCoordinate.getPosX()] = movingPiece;

        if (isEnPassant) {
            boardArray[gridEnPassantCoordinate.getPosY()][gridEnPassantCoordinate.getPosX()] = enPassantPiece;
        }

        return isPossible;
    }



    private boolean isInCheckMate(Piece.PieceColorOptions playerColor) {
        if (isKingMovable(playerColor)) {
            return false;
        }

        if (isCheckBlockable(playerColor)) {
            return false;
        }

        return true;
    }

    private boolean isInCheck(Piece.PieceColorOptions playerColor) {
        Coordinate kingCoordinate, oppCoordinate;
        kingCoordinate = getKingCoordinate(playerColor);

        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                oppCoordinate = new Coordinate(j, i);

                if (isValidEndpoints(oppCoordinate, kingCoordinate, oppositeColor(playerColor))) {
                    if (isValidPath(oppCoordinate, kingCoordinate, oppositeColor(playerColor), false)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isKingMovable(Piece.PieceColorOptions playerColor) {
        Coordinate kingCoordinate, oppCoordinate;
        int kingPosX, kingPosY;

        kingCoordinate = getKingCoordinate(playerColor);
        kingPosX = kingCoordinate.getPosX();
        kingPosY = kingCoordinate.getPosY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                oppCoordinate = new Coordinate(kingPosX + i, kingPosY + j);
                // if the position data in oppCoordinate is not empty, the Coordinate is a valid board space
                if (oppCoordinate.getChessStringPos() != null) {
                    if (isValidEndpoints(kingCoordinate, oppCoordinate, playerColor)) {
                        if (isMovePossibleWithoutCheck(kingCoordinate, oppCoordinate, playerColor, false)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isCheckBlockable(Piece.PieceColorOptions playerColor) {
        // this function groups both capturing and blocking together even though
        // capturing the opposing piece is not technically considered blocking a check
        Coordinate[] coordinatesToBlock;
        Coordinate kingCoordinate, oppCoordinate, allyCoordinate;
        int blockCounter, diffX, diffY, spacesToVerify, xIncrement, yIncrement;

        coordinatesToBlock = new Coordinate[VERTICAL_BOARD_LENGTH];
        kingCoordinate = getKingCoordinate(playerColor);
        oppCoordinate = null;
        blockCounter = 0;

        outerloop:
        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                oppCoordinate = new Coordinate(j, i);
                if (isValidEndpoints(oppCoordinate, kingCoordinate, oppositeColor(playerColor))) {
                    if (isValidPath(oppCoordinate, kingCoordinate, oppositeColor(playerColor), false)) {
                        coordinatesToBlock[blockCounter] = oppCoordinate;
                        blockCounter++;
                        // if there is more than one piece checking the King
                        // the check is not blockable
                        break outerloop;
                    }
                }
            }
        }

        diffX = subtractXCoordinates(oppCoordinate, kingCoordinate);
        diffY = subtractYCoordinates(oppCoordinate, kingCoordinate);
        xIncrement = calculateIncrement(diffX);
        yIncrement = calculateIncrement(diffY);
        spacesToVerify = Math.max(Math.abs(diffX), Math.abs(diffY)) - 1;

        // moving a piece to oppCoordinate capture's the opposing piece
        // only opposing Bishops, Rooks, and Queens can be blocked
        if (isValidDiagonalPath(oppCoordinate, kingCoordinate) || isValidStraightPath(oppCoordinate, kingCoordinate)) {
            for (int i = 0; i < spacesToVerify; i++) {
                Coordinate betweenCoordinate = new Coordinate(oppCoordinate);
                betweenCoordinate.addVals(xIncrement, yIncrement);
                coordinatesToBlock[blockCounter] = betweenCoordinate;
                blockCounter++;
            }
        }

        // loop through all Coordinates in coordinatesToBlock and see if any can block the check
        for (int i = 0; i < blockCounter; i++) {
            oppCoordinate = coordinatesToBlock[i];
            for (int j = 0; j < VERTICAL_BOARD_LENGTH; j++) {
                for (int k = 0; k < HORIZONTAL_BOARD_LENGTH; k++) {
                    allyCoordinate = new Coordinate(k, j);
                    if (isValidEndpoints(allyCoordinate, oppCoordinate, playerColor)) {
                        if (isValidPath(allyCoordinate, oppCoordinate, playerColor, false)) {
                            if (isMovePossibleWithoutCheck(allyCoordinate, oppCoordinate, playerColor, false)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isInStaleMate(Piece.PieceColorOptions playerColor) {
        Piece allyPiece;
        Coordinate allyCoordinate;

        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                allyCoordinate = new Coordinate(j, i);
                allyPiece = getPieceFromCoordinate(allyCoordinate);
                if (allyPiece != null && allyPiece.getPieceColor() == playerColor) {
                    if (isMovable(allyPiece)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isMovable(Piece piece) {
        Coordinate[] coordinateList;
        Coordinate pieceCoordinate, tempCoordinate;
        Piece.PieceColorOptions pieceColor;

        coordinateList = calculateMoves(piece);
        pieceCoordinate = piece.getPieceCoordinate();
        pieceColor = piece.getPieceColor();

        for (int i = 0; i < 8; i++) {
            tempCoordinate = coordinateList[i];
            if (tempCoordinate != null) {
                if (isValidEndpoints(pieceCoordinate, tempCoordinate, pieceColor)) {
                    if (isValidPath(pieceCoordinate, tempCoordinate, pieceColor, false)) {
                        if (isMovePossibleWithoutCheck(pieceCoordinate, tempCoordinate, pieceColor, false)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Coordinate[] calculateMoves(Piece piece) {
        Coordinate[] coordinateList;
        Coordinate pieceCoordinate, tempCoordinate;
        Piece.PieceColorOptions pieceColor;
        int forwardMultiplier, coordinateCount;

        coordinateList = new Coordinate[8];
        pieceCoordinate = piece.getPieceCoordinate();
        pieceColor = piece.getPieceColor();
        coordinateCount = 0;

        // add two diagonal moves and two forward moves
        if (piece instanceof Pawn) {
            if (pieceColor == Piece.PieceColorOptions.WHITE) {
                forwardMultiplier = 1;
            }
            else {
                forwardMultiplier = -1;
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = 1; j <= 2; j++) {
                    if (j == 2 && i != 0) {
                        continue;
                    }
                    tempCoordinate = new Coordinate(pieceCoordinate.getPosX() + i,
                            pieceCoordinate.getPosY() + j * forwardMultiplier);
                    if (tempCoordinate != null) {
                        coordinateList[coordinateCount] = tempCoordinate;
                        coordinateCount++;
                    }
                }
            }
        }

        // add 8 L-shaped moves
        if (piece instanceof Knight) {
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (Math.abs(i) + Math.abs(j) == 3) {
                        tempCoordinate = new Coordinate(pieceCoordinate.getPosX() + i, pieceCoordinate.getPosY() + j);
                        if (tempCoordinate != null) {
                            coordinateList[coordinateCount] = tempCoordinate;
                            coordinateCount++;
                        }
                    }
                }
            }
        }

        // add 4 diagonal moves
        if (piece instanceof Bishop || piece instanceof  Queen || piece instanceof King) {
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    tempCoordinate = new Coordinate(pieceCoordinate.getPosX() + i, pieceCoordinate.getPosY() + j);
                    if (tempCoordinate != null) {
                        coordinateList[coordinateCount] = tempCoordinate;
                        coordinateCount++;
                    }
                }
            }
        }

        // add four staight moves
        if (piece instanceof Rook || piece instanceof  Queen || piece instanceof Rook) {
            for (int i = -1; i <= 1; i += 2) {
                tempCoordinate = new Coordinate(pieceCoordinate.getPosX() + i, pieceCoordinate.getPosY());
                if (tempCoordinate != null) {
                    coordinateList[coordinateCount] = tempCoordinate;
                    coordinateCount++;
                }
            }
            for (int j = -1; j <= 1; j += 2) {
                tempCoordinate = new Coordinate(pieceCoordinate.getPosX(), pieceCoordinate.getPosY() + j);
                if (tempCoordinate != null) {
                    coordinateList[coordinateCount] = tempCoordinate;
                    coordinateCount++;
                }
            }
        }
        return coordinateList;
    }


    private boolean isValidEndpoints(Coordinate initialCoordinate, Coordinate newCoordinate,
                                     Piece.PieceColorOptions playerColor) {
        Piece startPiece, endPiece;

        startPiece = getPieceFromCoordinate(initialCoordinate);
        if (startPiece == null || startPiece.getPieceColor() != playerColor) {
            return false;
        }

        endPiece = getPieceFromCoordinate(newCoordinate);
        if (endPiece != null && endPiece.getPieceColor() == playerColor) {
            return false;
        }
        return true;
    }

    private boolean isValidPath(Coordinate initialCoordinate, Coordinate newCoordinate,
                                Piece.PieceColorOptions playerColor, boolean setPawnFlags) {
        Piece piece;
        piece = getPieceFromCoordinate(initialCoordinate);

        if (piece instanceof Pawn) {
            if (isValidPawnMove(initialCoordinate, newCoordinate, playerColor, setPawnFlags)) {
                return true;
            }
        }

        if (piece instanceof Knight) {
            if (isValidKnightMove(initialCoordinate, newCoordinate)) {
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

        if (piece instanceof Queen) {
            if (isValidDiagonalPath(initialCoordinate, newCoordinate)) {
                return true;
            }
            if (isValidStraightPath(initialCoordinate, newCoordinate)) {
                return true;
            }
        }

        if (piece instanceof King) {
            if (isValidKingMove(initialCoordinate, newCoordinate)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidPawnMove(Coordinate initialCoordinate, Coordinate newCoordinate,
                                    Piece.PieceColorOptions playerColor, boolean setPawnFlags) {
        int diffX, diffY, forwardMultiplier;
        Piece pawnPiece, pieceToCapture;
        Coordinate betweenCoordinate, enPassantCapturedPawnCoordinate;

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

        // 2-space forward moves and diagonal moves have this property
        if (Math.abs(diffX) + Math.abs(diffY) == 2) {
            // 2-space forward move must be unobstructed by another piece
            if (diffY == 2*forwardMultiplier && !pawnPiece.getHasMoved()) {
                // check if the space in front of the Pawn is occupied by another piece
                betweenCoordinate = new Coordinate(initialCoordinate);
                betweenCoordinate.addVals(0, forwardMultiplier);
                if (getPieceFromCoordinate(betweenCoordinate) == null) {
                    // check if next space in front is occupied by another piece
                    betweenCoordinate.addVals(0, forwardMultiplier);
                    if (getPieceFromCoordinate(betweenCoordinate) == null && setPawnFlags) {
                        setTwoSpaceFlag = true;
                        return true;
                    }
                }
            }
            // if the Pawn moves forward one space, it must also move left or right one space
            // to have a total displacement of 2
            if (diffY == forwardMultiplier) {
                // normal capture
                pieceToCapture = getPieceFromCoordinate(newCoordinate);
                if (pieceToCapture != null && pieceToCapture.getPieceColor() != playerColor) {
                    // check if Pawn needs to be promoted
                    if (newCoordinate.getPosY() == 0 || newCoordinate.getPosY() == 7) {
                        if (setPawnFlags) {
                            doPromotion = true;
                        }
                    }
                    return true;
                }
                // En Passant
                if (pieceToCapture == null) {
                    // the Pawn to capture is one space behind the new position of the moving Pawn
                    enPassantCapturedPawnCoordinate = new Coordinate(newCoordinate);
                    enPassantCapturedPawnCoordinate.addVals(0, forwardMultiplier * -1);
                    pieceToCapture = getPieceFromCoordinate(enPassantCapturedPawnCoordinate);
                    if (pieceToCapture instanceof Pawn && pieceToCapture.getPieceColor() != playerColor) {
                        // En Passant is only valid of the opposing Pawn moved 2 spaces on the previous move
                        int twoSpaceMoveTurnNum = ((Pawn) pieceToCapture).getTwoSpaceMoveTurnNum();
                        if (turnNumber == twoSpaceMoveTurnNum + 1 && setPawnFlags) {
                            gridEnPassantCoordinate = enPassantCapturedPawnCoordinate;
                            doEnPassant = true;
                            return true;
                        }
                    }
                }
            }
        }

        // 1-space forward move and promotion
        if (diffX == 0 && diffY == forwardMultiplier) {
            // check if the space in front of the Pawn is occupied by another piece
            betweenCoordinate = new Coordinate(initialCoordinate);
            betweenCoordinate.addVals(0, forwardMultiplier);
            if (getPieceFromCoordinate(betweenCoordinate) == null) {
                // check if Pawn needs to be promoted
                if (newCoordinate.getPosY() == 0 || newCoordinate.getPosY() == 7) {
                    if (setPawnFlags) {
                        doPromotion = true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean isValidKnightMove(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int absDiffX, absDiffY;

        absDiffX = Math.abs(subtractXCoordinates(initialCoordinate, newCoordinate));
        absDiffY = Math.abs(subtractYCoordinates(initialCoordinate, newCoordinate));

        // all valid Knight moves move 1 space either horizontally or vertically
        // and 2 spaces in the other direction
        if (absDiffX + absDiffY == 3) {
            if ((absDiffX == 1 || absDiffY == 1) && (absDiffX == 2 || absDiffY == 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidKingMove(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int absDiffX, absDiffY;

        absDiffX = Math.abs(subtractXCoordinates(initialCoordinate, newCoordinate));
        absDiffY = Math.abs(subtractYCoordinates(initialCoordinate, newCoordinate));

        // besides castling, the King can move at most one space both horizontally and vertically
        if (absDiffX <= 1 && absDiffY <= 1) {
            return true;
        }

        return false;
    }

    private boolean isValidDiagonalPath(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int diffX, diffY;

        diffX = subtractXCoordinates(initialCoordinate, newCoordinate);
        diffY = subtractYCoordinates(initialCoordinate, newCoordinate);

        // verify that path is diagonal
        if (Math.abs(diffX) != Math.abs(diffY)) {
            return false;
        }

        if (isPathUnobstructed(initialCoordinate, newCoordinate)) {
            return true;
        }
        return false;
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

        if (isPathUnobstructed(initialCoordinate, newCoordinate)) {
            return true;
        }
        return false;
    }

    private boolean isPathUnobstructed(Coordinate initialCoordinate, Coordinate newCoordinate) {
        int diffX, diffY, spacesToVerify, xIncrement, yIncrement;
        Coordinate betweenCoordinate;
        Piece betweenPiece;

        diffX = subtractXCoordinates(initialCoordinate, newCoordinate);
        diffY = subtractYCoordinates(initialCoordinate, newCoordinate);
        xIncrement = calculateIncrement(diffX);
        yIncrement = calculateIncrement(diffY);
        spacesToVerify = Math.max(Math.abs(diffX), Math.abs(diffY)) - 1;

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

    private char getPromotionInput() {
        char pieceChar;
        Scanner pieceScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter piece to promote Pawn to.");
            System.out.println("Options are: 'Q', 'R', 'K', and 'B'");
            System.out.println("For Queen, Rook, Knight, and Bishop:");
            pieceChar = pieceScanner.next().charAt(0);
            if (pieceChar == 'Q' || pieceChar == 'R' || pieceChar == 'K' || pieceChar == 'B') {
                break;
            }
        }
        return pieceChar;
    }

    private void promotePiece(Piece.PieceColorOptions pieceColor, String pieceStringPos) {
        char pieceChar = getPromotionInput();
        switch (pieceChar) {
            case 'Q' :
                initializePiece(new Queen(), pieceColor, pieceStringPos);
                break;
            case 'R' :
                initializePiece(new Rook(), pieceColor, pieceStringPos);
                break;
            case 'K' :
                initializePiece(new Knight(), pieceColor, pieceStringPos);
                break;
            case 'B' :
                initializePiece(new Bishop(), pieceColor, pieceStringPos);
                break;
        }
    }

    private Coordinate getKingCoordinate(Piece.PieceColorOptions playerColor) {
        Piece kingPiece;
        Coordinate kingCoordinate;

        kingCoordinate = null;
        outerLoop:
        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
            for (int j = 0; j < HORIZONTAL_BOARD_LENGTH; j++) {
                kingCoordinate = new Coordinate(j, i);
                kingPiece = getPieceFromCoordinate(kingCoordinate);
                if ((kingPiece instanceof King) && kingPiece.getPieceColor() == playerColor) {
                    break outerLoop;
                }
            }
        }
        return kingCoordinate;
    }

    private void resetPawnFlags() {
        doEnPassant = false;
        doPromotion = false;
        setTwoSpaceFlag = false;
        gridEnPassantCoordinate = null;
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

    private Piece.PieceColorOptions oppositeColor(Piece.PieceColorOptions currentColor) {
        if (currentColor == Piece.PieceColorOptions.WHITE) {
            return Piece.PieceColorOptions.BLACK;
        }
        else {
            return Piece.PieceColorOptions.WHITE;
        }
    }
}
