# Chess-Game

8 ║♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜
7 ║♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟
6 ║… … … … … … … …
5 ║… … … … … … … …
4 ║… … … … … … … …
3 ║… … ♘ … … … … …
2 ║♙ ♙ ♙ ♙ ♙ ♙ ♙ ♙
1 ║♖ … ♗ ♕ ♔ ♗ ♘ ♖
—╚═══════════════
——a   b   c   d   e   f   g   h

Agenda:
	1. Write Java, Objected Oriented Programming, Exception Handling, Singleton, Interfaces/Inheritance
	2. Write Test cases
	

Test cases:

//1. Handling piece movements:
//Initialize players
Player one = new Player("one");
Player two = new Player("two");

//Initialize Game Manager
//TODO: ChessGameManager may want to be a singleton class - For the future
GameManager cgm =  new ChessGameManager();

//Initialize Game & Chess Pieces
Game chessGame = cgm.createGame();
chessGame.initializeAllPieces();
chessGame.start();
chessGame.move(one, "d2", "d5"); // Pawn movement - Should show invalid move and try again
chessGame.move(one, "d2", "d4"); // Pawn movement - No error
chessGame.move(two, "d7", "d5"); // Pawn movement - No error
chessGame.move(one, "b1", "c3"); // Knight movement - No error
chessGame.move(two, "b8", "b6"); // Knight movement - Should show invalid move and try again
// ... create your own test cases and make sure that all pieces move correctly

//2. Handling Pawn to the other side of the board:

//3. Handling Check:

//4. Handling Checkmate:
Player one = new Player("one");
Player two = new Player("two");
GameManager cgm =  new ChessGameManager();
Game chessGame = cgm.createGame();
chessGame.initializePiece(new Queen(), one, "c6");
chessGame.initializePiece(new King(), one, "b7");
chessGame.initializePiece(new King(), two, "a8");
chessGame.start();
// Should notify user that it is checkmate