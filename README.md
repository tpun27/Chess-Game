# Chess-Game

###### Agenda:
1. Write Java, Objected Oriented Programming, Exception Handling, Singleton, Interfaces/Inheritance 1
2. Write Test cases 2

###### Gradle:
1. Follow the steps listed in https://gradle.org/install#unpack to install gradle 1
2. Close Intellij 2
3. Manually remove Chess-Game/ChessGame/ChessGame.iml 3
4. Open Intellij -> New -> Modules from existing sources -> Select ChessGame 4
5. Import module from external modules -> Select Gradle -> Select next, next until import window closes 5
6. Under the Build tab from the top menu -> Select Build Project 6

```java
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
```