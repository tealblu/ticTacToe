// Charlie Hartsell
package cpsc2150.extendedTicTacToe;
public abstract class AbsGameBoard implements IGameBoard {
    /**
     * overridden form of the toString() method from the Object class.
     *  returns one string showing the entire game board.
     *
     * @return one string showing the entire game board
     *
     * @pre none
     *
     * @post none
     */
    @Override
    public String toString() {
        // Create string with pre-formatting
        String formatted = "   "; // three spaces

        // column by column add header
        for(int j = 0; j < getNumColumns(); j++) {
            if(j < 10) {
                formatted += " " + j + "|";
            } else {
                formatted += j + "|";
            }
        }
        // formatted is now "   0| 1| 2| 3| ..."

        // loop row by row
        for(int i = 0; i < getNumRows(); i++) {
            // add column [header?]
            if(i < 10) {
                formatted += "\n" + " " + i + "|";
            } else {
                formatted += "\n" + i + "|";
            }

            // loop column by column
            for(int j = 0; j < getNumColumns(); j++) {
                // create new BoardPosition
                BoardPosition temp = new BoardPosition(i,j);

                // append the string with "[position]|"
                formatted += whatsAtPos(temp) + " |";
            }
        }

        formatted += "\n";

        // return the created string
        return formatted;
    }
}
