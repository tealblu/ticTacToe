// Charlie Hartsell
package cpsc2150.extendedTicTacToe;

public class BoardPosition {
    // Data--------------------------------------------------------
    private int row, col;

    // Methods----------------------------------------------------
    /**
     * Constructor for the BoardPosition class
     * @param r variable representing the row position
     * @param c variable representing the column position
     *
     * @pre none
     *
     * @post row = r
     * @post col = c
     */
    public BoardPosition(int r, int c) {
        row = r;
        col = c;
    }

    /**
     * Returns the row corresponding to the position stored
     *
     * @return the row position
     *
     * @pre none
     *
     * @post none
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column corresponding to the position stored
     *
     * @return the column atm
     *
     * @pre none
     *
     * @post none
     */
    public int getColumn() {
        return col;
    }

    /**
     * Returns true if the boardposition is equal to pos2, otherwise false
     *
     * @param pos2 the second position to compare
     * @return true if the positions are the same
     *
     * @pre pos2 must exist
     * @post pos2 = #pos2
     */
    public boolean equals(Object pos2) {
        if(pos2 instanceof BoardPosition && ((BoardPosition) pos2).getRow() == this.row && ((BoardPosition)pos2).getColumn() == this.col) {
            return true;
        }

        return false;
    }

    /**
     * Returns a string containing the formatted data
     *
     * @return string containing data in format "r,c"
     *
     * @pre row and column must be initialized
     *
     * @post row = #row && col = #col
     */
    public String toString() {
        String temp = row + "," + col;
        return temp;
    }
}
