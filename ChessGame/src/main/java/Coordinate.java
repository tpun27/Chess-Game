/**
 * Class for chess Coordinate
 */
public class Coordinate {
    public static final String X_POSITIONS = "abcdefgh";

    int posX;
    int posY;
    String chessString;

    public Coordinate() {

    }

    public Coordinate(int posX, int posY) {
        setPosXY(posX, posY);
    }

    public Coordinate(String chessString) {
        setChessString(chessString);
    }

    public int parsePosX(String chessString) {
        char xChar = chessString.charAt(0);
        return X_POSITIONS.indexOf(xChar);
    }

    public int parsePosY(String chessString) {
        char yChar = chessString.charAt(1);
        return Character.getNumericValue(yChar) - 1;
    }

    public String convertToChessNotation(int posX, int posY) {
        String tempString = "";
        tempString += X_POSITIONS.charAt(posX);
        tempString += Integer.toString(posY+1);
        return tempString;
    }

    public void addVals(int xIncr, int yIncr) {
        posX += xIncr;
        posY += yIncr;
    }

    public void reCalcChessStr() {
        chessString = convertToChessNotation(posX, posY);
    }

    public void reCalcChessPos() {
        posX = parsePosX(chessString);
        posY = parsePosY(chessString);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getChessString() {
        return chessString;
    }

    public void setChessString(String chessString) {
        this.chessString = chessString;
    }

    public void setPosXY(int posX, int posY) {
        setPosX(posX);
        setPosY(posY);
    }
}
