/**
 * Class that contains board/piece layout on a 8-by-8 grid
 */
public class Board {
    public static final int VERTICAL_BOARD_LENGTH = 8;
    public static final int HORIZONTAL_BOARD_LENGTH = 8;
    public static final char DEFAULT_PIECE_SYMBOL = '\u2026';

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
        Pawn wPawn1 = new Pawn(Piece.PieceColorOptions.WHITE, "a2");
        initializePiece(wPawn1);
        Pawn wPawn2 = new Pawn(Piece.PieceColorOptions.WHITE, "b2");
        initializePiece(wPawn2);
        Pawn wPawn3 = new Pawn(Piece.PieceColorOptions.WHITE, "c2");
        initializePiece(wPawn3);
        Pawn wPawn4 = new Pawn(Piece.PieceColorOptions.WHITE, "d2");
        initializePiece(wPawn4);
        Pawn wPawn5 = new Pawn(Piece.PieceColorOptions.WHITE, "e2");
        initializePiece(wPawn5);
        Pawn wPawn6 = new Pawn(Piece.PieceColorOptions.WHITE, "f2");
        initializePiece(wPawn6);
        Pawn wPawn7 = new Pawn(Piece.PieceColorOptions.WHITE, "g2");
        initializePiece(wPawn7);
        Pawn wPawn8 = new Pawn(Piece.PieceColorOptions.WHITE, "h2");
        initializePiece(wPawn8);

        // black Pawns
        Pawn bPawn1 = new Pawn(Piece.PieceColorOptions.BLACK, "a7");
        initializePiece(bPawn1);
        Pawn bPawn2 = new Pawn(Piece.PieceColorOptions.BLACK, "b7");
        initializePiece(bPawn2);
        Pawn bPawn3 = new Pawn(Piece.PieceColorOptions.BLACK, "c7");
        initializePiece(bPawn3);
        Pawn bPawn4 = new Pawn(Piece.PieceColorOptions.BLACK, "d7");
        initializePiece(bPawn4);
        Pawn bPawn5 = new Pawn(Piece.PieceColorOptions.BLACK, "e7");
        initializePiece(bPawn5);
        Pawn bPawn6 = new Pawn(Piece.PieceColorOptions.BLACK, "f7");
        initializePiece(bPawn6);
        Pawn bPawn7 = new Pawn(Piece.PieceColorOptions.BLACK, "g7");
        initializePiece(bPawn7);
        Pawn bPawn8 = new Pawn(Piece.PieceColorOptions.BLACK, "h7");
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
