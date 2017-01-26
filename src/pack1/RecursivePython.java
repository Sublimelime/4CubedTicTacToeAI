package pack1;

import tully.*;

/**
 * An AI coded by Hunter Wright and Noah Morton
 *
 * @author Noah Morton/Hunter Wright
 *
 * Date created: Jan 25, 2017 Part of project: TicTacToeAI
 */
public class RecursivePython implements PlayerInt {

    private char letter;
    private String name;

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
        int score;
        Location loc = null;
        for (int sheet = 0; sheet < board.numSheets(); sheet++) {
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) {
                    LocationScore ls = new LocationScore(board, new Location(sheet, row, col), letter);
                    int scoreTemp = 0;
                    score = (ls.getSelfQuadruples() * 1000) + (ls.getSelfTriples() * 100) + (ls.getSelfDoubles() * 10) + ls.getSelfSingles();
                    if (score > scoreTemp) {
                        scoreTemp = score;
                        loc = new Location(sheet, row, col);
                    }
                }
            }
        }
        return loc;
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

}
