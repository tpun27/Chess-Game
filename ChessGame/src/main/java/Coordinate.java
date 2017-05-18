/**
 * Class for chess Coordinate
 */
public class Coordinate {
    public static final String X_POSITIONS = "abcdefgh";

    private int posX;
    private int posY;
    private String chessStringPos;

    public Coordinate() {

    }

    public Coordinate(String chessStringPos) throws InvalidBoardPositionException {
        setChessStringPos(chessStringPos);
    }

    public Coordinate(int posX, int posY) throws InvalidBoardPositionException {
        setPosXY(posX, posY);
    }

    public void setChessStringPos(String chessStringPos) throws InvalidBoardPositionException {
        if (isWithinBoard(chessStringPos)) {
            this.chessStringPos = chessStringPos;
            reCalcChessIntPos();
        }
        else {
            throw new InvalidBoardPositionException();
        }
    }

    public void setPosXY(int posX, int posY) throws InvalidBoardPositionException {
        if (isWithinBoard(posX) && isWithinBoard(posY)) {
            this.posX = posX;
            this.posY = posY;
            reCalcChessStrPos();
        }
        else {
            throw new InvalidBoardPositionException();
        }
    }

    private void reCalcChessStrPos() {
        chessStringPos = convertToChessNotation(posX, posY);
    }

    private void reCalcChessIntPos() {
        posX = parsePosX(chessStringPos);
        posY = parsePosY(chessStringPos);
    }

    private String convertToChessNotation(int posX, int posY) {
        String tempStringPos = "";
        tempStringPos += X_POSITIONS.charAt(posX);
        tempStringPos += Integer.toString(posY+1);
        return tempStringPos;
    }

    private int parsePosX(String chessStringPos) {
        char xChar = chessStringPos.charAt(0);
        return X_POSITIONS.indexOf(xChar);
    }

    private int parsePosY(String chessStringPos) {
        char yChar = chessStringPos.charAt(1);
        return Character.getNumericValue(yChar) - 1;
    }

    private boolean isWithinBoard(int posVal) {
        if (posVal >= 0 && posVal <= 7) {
            return true;
        }
        return false;
    }

    private  boolean isWithinBoard(String chessStringPos) {
        if (chessStringPos.length() != 2) {
            return false;
        }

        char tempX, tempY;
        tempX = chessStringPos.charAt(0);
        tempY = chessStringPos.charAt(1);

        if (X_POSITIONS.indexOf(tempX) == -1) {
            return false;
        }

        if (tempY < '1' || tempY > '8') {
            return false;
        }
        return true;
    }

    // xIncr moves left and right, yIncr moves down and up
    public int addVals(int xIncr, int yIncr) throws InvalidBoardPositionException {
        int tempX, tempY;

        tempX = posX + xIncr;
        tempY = posY + yIncr;
        setPosXY(tempX, tempY);

        // return 1 if posX and posY were incremented and return 0 otherwise
        if (tempX == posX && tempY == posY) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getChessStringPos() {
        return chessStringPos;
    }
}