// Charlie Hartsell
package cpsc2150.extendedTicTacToe;
public interface IGameBoard {
    // Methods from GameBoard (Project 1)
    /**
     * returns true if the position specified in pos is available; false otherwise.
     *  if a space is not in bounds, then it is not available.
     *
     * @param pos the position to check
     *
     * @return true if position available, false otherwise
     *
     * @pre none
     *
     * @post pos = #pos
     */
    default public boolean checkSpace(BoardPosition pos) {
        // is the space in bounds?
        if(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0) {
            // space is out of bounds
            return false;
        }

        // is the space taken?
        if(whatsAtPos(pos) != ' ') {
            // space is taken
            return false;
        }

        return true;
    }

    /**
     * places the character in marker on the position specified by marker,
     *  and should not be called if the space is unavailable
     *
     * @param marker the position to place a marker in
     * @param player the player who is placing the marker
     *
     * @pre 0 <= marker.row < 5
     * @pre 0 <= marker.col < 8
     *
     * @post marker = #marker
     * */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * this function will check to see if the lastPos placed resulted in a winner.
     *  if so it will return true, otherwise false.
     *
     * @param lastPos the position in which a marker was last placed
     *
     * @return true if the game has been won, false otherwise
     *
     * @pre none
     *
     * @post none
     */
    default public boolean checkForWinner(BoardPosition lastPos) {
        // Detect current player
        char player = whatsAtPos(lastPos);

        // Check for win conditions
        if(checkHorizontalWin(lastPos, player)) {
            return true;
        }
        if(checkVerticalWin(lastPos, player)) {
            return true;
        }
        if(checkDiagonalWin(lastPos, player)) {
            return true;
        }

        // Win not reached, return false
        return false;
    }

    /**
     * this function will check to see if the game has resulted in a tie.
     *  a tie has been reached if there are no free board positions remaining.
     *
     * @return true if a draw has been reached, false otherwise
     *
     * @pre win conditions are being checked as the game is played
     *
     * @post none
     */
    default public boolean checkForDraw() { // secondary
        // row by row
        for(int i = 0; i < getNumRows(); i++) {
            // column by column
            for(int j = 0; j < getNumColumns(); j++) {
                // create new pos
                BoardPosition pos = new BoardPosition(i, j);

                // check pos
                if(whatsAtPos(pos) == ' ') {
                    // empty space detected, there cannot be a draw
                    return false;
                }
            }
        }

        // no empty spaces detected, a draw has been reached
        return true;
    }

    /**
     * checks to see if the last marker placed resulted in 5 in a row horizontally.
     *  returns true if it does, otherwise returns false.
     *
     * @param lastPos the position in which a marker was last placed
     * @param player the player who placed a marker at lastPos
     *
     * @return true if the game has been won, false otherwise
     *
     * @pre none
     *
     * @post none
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int inRow = 1;

        // check to the right
        for(int i = 1; i < 5; i++) {
            // create pos i to the right of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRow++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // check to the left
        for(int i = -1; i > -5; i--) {
            // create pos one to the left of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRow++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // is inRow at least 5 in a row?
        return inRow >= getNumToWin();
    }

    /**
     * checks to see if the last marker placed resulted in 5 in a row vertically.
     *  returns true if it does, otherwise returns false.
     *
     * @param lastPos the position in which a marker was last placed
     * @param player the player who placed a marker at lastPos
     *
     * @return true if the game has been won, false otherwise
     *
     * @pre none
     *
     * @post none
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int inRow = 1;

        // check above
        for(int i = 1; i < 5; i++) {
            // create pos i above of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn());

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRow++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // check below
        for(int i = -1; i > -5; i--) {
            // create pos i below lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn());

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRow++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // is inRow at least 5 in a row
        return inRow >= getNumToWin();
    }

    /**
     * checks to see if the last marker placed resulted in 5 in a row diagonally.
     *  returns true if it does, otherwise returns false.
     *
     * @param lastPos the position in which a marker was last placed
     * @param player the player who placed a marker at lastPos
     *
     * @return true if the game has been won, false otherwise
     *
     * @pre none
     *
     * @post none
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int inRowPosSlope = 1; // num in a row going from bottom left to top right
        int inRowNegSlope = 1; // num in a row going from top left to bottom right

        // check up right
        for(int i = 1; i < 5; i++) {
            // create pos one above of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() + i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRowPosSlope++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // check down left
        for(int i = -1; i > -5; i--) {
            // create pos one above of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() + i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRowPosSlope++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // check up left
        for(int i = 1; i < 5; i++) {
            // create pos one above of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() - i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRowNegSlope++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        // check down right
        for(int i = -1; i > -5; i--) {
            // create pos one above of lastPos
            BoardPosition pos = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() - i);

            // check pos
            if(!(pos.getRow() >= getNumRows() || pos.getColumn() >= getNumColumns() || pos.getRow() < 0 || pos.getColumn() < 0)) {
                if(isPlayerAtPos(pos, player)) {
                    inRowNegSlope++;
                } else {
                    // if pos is not player, exit for loop
                    break;
                }
            }
        }

        return inRowPosSlope >= getNumToWin() || inRowNegSlope >= getNumToWin();
    }

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
    public char whatsAtPos(BoardPosition pos);

    /**
     * returns true if the player is at pos;
     *  otherwise, it returns false.
     *
     * @param pos the board position to check
     * @param player the player to check
     *
     * @return true if the player is at position pos, false otherwise
     *
     * @pre pos is a valid position on the board
     *
     * @post none
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        // Check to see if player is at pos
        return whatsAtPos(pos) == player;
    }

    // New methods (Project 2 Instructions)

    /**
     * returns the number of rows in the GameBoard
     *
     * @return an int containing the number of rows in the GameBoard
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumRows();

    /**
     * returns the number of columns in the GameBoard
     *
     * @return an int containing the number of columns in the GameBoard
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumColumns();

    /**
     * returns the number of tokens needed in a row to win the game
     *
     * @return number of tokens in a row to win the game
     *
     * @pre the GameBoard must exist
     *
     * @post board = #board
     */
    public int getNumToWin();
}