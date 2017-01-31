package pack1;

import tully.*;

/**
 * An AI coded by Hunter Wright and Noah Morton
 *
 * @author Noah Morton/Hunter Wright Date created: Jan 25, 2017 Part of project:
 * TicTacToeAI
 */
public class RecursivePython implements PlayerInt {

    private char letter;
    private String name;
    private boolean movedRandomly = false;

    public RecursivePython(char letter) {
        this.letter = letter;
        this.name = "Recursive Python";
    }

    /**
     * Returns the letter that the AI is playing.
     *
     * @return A char, either X or O.
     */
    @Override
    public char getLetter() {
        return letter;
    }

    /**
     * Gets the move from the AI, sends it to the parent.
     *
     * @param board The board provided by the calling class.
     * @return returns the move that the AI selected.
     */
    @Override
    public LocationInt getMove(BoardInt board) {
        int score, scoreTemp = 0;
        Location locFinal = null;
        for (int sheet = 0; sheet < board.numSheets(); sheet++) { //Go through all the spots on the board
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) {
                    if (board.isEmpty(new Location(sheet, row, col))) { //If this spot is valid, ie not taken
                        Location locCurrent = new Location(sheet, row, col);

                        LocationScore ls = new LocationScore(board, locCurrent, letter);

                        score = (ls.getSelfQuadruples() * 1000) + (ls.getSelfTriples() * 100) + (ls.getSelfDoubles() * 10) + ls.getSelfSingles();

                        //System.out.println("(" + sheet + "," + row + "," + col + ")  My score here is..." + score);
                        if (score > scoreTemp) { //If this location is the best so far
                            scoreTemp = score;
                            locFinal = new Location(sheet, row, col);
                        }
                    }
                }
            }
        }
        //System.out.println("I moved to..." + loc);
        return locFinal;
    }

    /**
     * Returns the name of the AI
     *
     * @return The name of the AI
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the AI for the client testing program.
     *
     * @return A new instance of this file.
     */
    public PlayerInt freshCopy() {
        return new RecursivePython(letter);
    }

}
