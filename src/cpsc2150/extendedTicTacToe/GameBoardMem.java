// Charlie Hartsell
// I apologize if my code is badly organized. My boyfriend of 5 years dumped me yesterday and I'm a bit pre-occupied with that.
package cpsc2150.extendedTicTacToe;
import java.util.*;
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
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
    private Map<Character, List<BoardPosition>> board;
    private int numToWin, numColumns, numRows;

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
    public GameBoardMem(int r, int c, int toWin) {
        board = new HashMap<>();
        numToWin = toWin;
        numColumns = c;
        numRows = r;
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
    public void placeMarker(BoardPosition marker, char player) {
        if(!board.containsKey(player)) {
            List<BoardPosition> temp = new ArrayList<BoardPosition>();
            temp.add(marker);
            board.put(player, temp);
        } else {
            List<BoardPosition> temp = (ArrayList<BoardPosition>) board.get(player);
            temp.add(marker);
            board.put(player, temp);
        }
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
    public char whatsAtPos(BoardPosition pos) {
        // loop thru entire map??? this is the only way i can think of
        for(Map.Entry<Character, List<BoardPosition>> m: board.entrySet()) {
            // m holds our current player - list pair
            for(BoardPosition n: (List<BoardPosition>) m.getValue()) {
                // n holds our current boardPosition
                if(n.equals(pos)) {
                    return (char) m.getKey(); // return m's key, which is the character corresponding to n
                }
            }
        }

        return ' '; // value not found, return space
    }

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
        return numRows;
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
        return numColumns;
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

    /**
     * returns true if the player is at pos;
     *  otherwise, it returns false.
     *
     * @param pos the board position to check
     * @param player the player to check
     *
     * @return true if the player is at position pos, false otherwise
     *
     * @pre none
     *
     * @post none
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }
}
