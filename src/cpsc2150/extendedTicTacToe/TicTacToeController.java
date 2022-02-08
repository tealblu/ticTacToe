package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    //Boolean to store if game is over
    private boolean gameOver;

    //Integer to store number of players
    private int playerNum;

    //Player tokens
    private char[] playerTokens = {'X','O','A','M','W','Y','S','L','F'};

    //Current Player
    private int currentPlayer;

    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;

        // Some code is needed here.
        this.gameOver = false;
        this.playerNum = np;
        this.currentPlayer = 1;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        // check if game is over before continuing
        if(gameOver) {
            // game is over, start a new game and do nothing else
            newGame();
            currentPlayer = 1;
        } else {
            // game is not over, so we continue
            // check space availability
            BoardPosition pos = new BoardPosition(row, col);
            if(curGame.checkSpace(pos)) {
                // place marker
                curGame.placeMarker(pos, playerTokens[currentPlayer-1]);
                screen.setMarker(row, col, playerTokens[currentPlayer-1]);

                // check for winner
                if(curGame.checkForWinner(pos)) {
                    // display win message
                    screen.setMessage("Player " + playerTokens[currentPlayer-1] + " wins! Click any button for a new game.");

                    // end game
                    gameOver = true;
                } else if(curGame.checkForDraw()){
                    // draw reached
                    screen.setMessage("A draw has been reached. Click any button for a new game.");

                    // end game
                    gameOver = true;
                } else {
                    // game proceeds, next player
                    currentPlayer++;
                    if(currentPlayer > playerNum) {currentPlayer = 1;}
                    screen.setMessage("It is " + playerTokens[currentPlayer-1] + "'s turn.");
                }
            } else {
                // space not valid
                screen.setMessage("This space is unavailable. Pick another.");
            }
        }
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}