package cpsc2150.extendedTicTacToe;
public class GameBoard extends AbsGameBoard implements IGameBoard {
    // correspondences and invariants
    /**
     * @correspondence placeMarker() = placeMarker()
     * @correspondence whatsAtPos() = whatsAtPos()
     * @correspondence getNumRows() = getNumRows()
     * @correspondence getNumColumns() = getNumColumns()
     *
     * no invariants...? the slides are super unclear on this.
     */

    // Data--------------------------------------------------------------------
    // Character array containing the game board. Coords in format row,column
    private char board[][];
    private int numToWin;

    // Methods-----------------------------------------------------------------
    /**
     * constructor for the GameBoard
     *
     * @pre none
     *
     * @post none
     *
     * @param r the number of rows on the board
     * @param c the number of columns on the board
     * @param toWin the number of markers needed in a row to win
     */
    public GameBoard(int r, int c, int toWin) {
        board = new char[r][c];
        numToWin = toWin;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // checkSpace implemented in interface as it is secondary

    /**
     * places the character in marker on the position specified by marker,
     *  and should not be called if the space is unavailable
     *
     * @param marker the position to place a marker in
     * @param player the player who is placing the marker
     *
     * @pre 0 <= marker.row < board max rows
     * @pre 0 <= marker.col < board max cols
     *
     * @post marker = #marker
     * */
    public void placeMarker(BoardPosition marker, char player) {
        // set corresponding position to player token
        board[marker.getRow()][marker.getColumn()] = player;
    }

    // checkForWinner implemented in interface as it is secondary

    // checkForDraw implemented in interface as it is secondary

    // checkHorizontalWin implemented in interface as it is secondary

    // checkVerticalWin implemented in interface as it is secondary

    // checkDiagonalWin impelmented in interface as it is secondary
    /**
     * returns what is in the GameBoard at position pos.
     *  if no marker is there, it returns a space character.
     *
     * @param pos the board position to check
     *
     * @return the character stored in the board position corresponding to pos
     *
     * @pre none
     *
     * @post none
     */
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()]; // returns character in corresponding cell of board
    }

    // isPlayerAtPos implemented in interface as it is secondary

    /**
     * returns the number of rows in the GameBoard
     *
     * @return an int containing the number of rows in the GameBoard
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumRows() {
        return board.length;
    }

    /**
     * returns the number of columns in the GameBoard
     *
     * @return an int containing the number of columns in the GameBoard
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumColumns() {
        return board[0].length;
    }

    /**
     * returns the number of tokens needed in a row to win the game
     *
     * @return number of tokens in a row to win the game
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumToWin() { // returns the number of tokens needed in a row to win the game
        return numToWin;
    }
}
