/**
 * Class that contains board/piece layout on a 8-by-8 grid
 */
public class Board {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';

    // Board positions are mirrored over the middle (horizontal) half of the board
    // For example, boardArray[0][0] maps to a1 and boardArray[7][7] maps to h8
    Piece[][] boardArray;

    public Board() {
        boardArray = new Piece[VERTICAL_BOARD_LENGTH][HORIZONTAL_BOARD_LENGTH];
        initializeBoardPieces();
    }

    public void initializeBoardPieces() {
        // white Pawns
        Pawn wPawn1 = new Pawn('W', "a2");
        initializePiece(wPawn1);
        Pawn wPawn2 = new Pawn('W', "b2");
        initializePiece(wPawn2);
        Pawn wPawn3 = new Pawn('W', "c2");
        initializePiece(wPawn3);
        Pawn wPawn4 = new Pawn('W', "d2");
        initializePiece(wPawn4);
        Pawn wPawn5 = new Pawn('W', "e2");
        initializePiece(wPawn5);
        Pawn wPawn6 = new Pawn('W', "f2");
        initializePiece(wPawn6);
        Pawn wPawn7 = new Pawn('W', "g2");
        initializePiece(wPawn7);
        Pawn wPawn8 = new Pawn('W', "h2");
        initializePiece(wPawn8);

        // black Pawns
        Pawn bPawn1 = new Pawn('B', "a7");
        initializePiece(bPawn1);
        Pawn bPawn2 = new Pawn('B', "b7");
        initializePiece(bPawn2);
        Pawn bPawn3 = new Pawn('B', "c7");
        initializePiece(bPawn3);
        Pawn bPawn4 = new Pawn('B', "d7");
        initializePiece(bPawn4);
        Pawn bPawn5 = new Pawn('B', "e7");
        initializePiece(bPawn5);
        Pawn bPawn6 = new Pawn('B', "f7");
        initializePiece(bPawn6);
        Pawn bPawn7 = new Pawn('B', "g7");
        initializePiece(bPawn7);
        Pawn bPawn8 = new Pawn('B', "h7");
        initializePiece(bPawn8);
    }

    public void initializePiece(Piece piece) {
        boardArray[piece.getPosY()][piece.getPosX()] = piece;
        //System.out.println(piece.getPosX() + " " + piece.getPosY());
    }

    public void printBoard() {
        for (int i = 0; i < VERTICAL_BOARD_LENGTH; i++) {
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
    }
}
