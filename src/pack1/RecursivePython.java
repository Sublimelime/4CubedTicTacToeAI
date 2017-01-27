package pack1;

import java.util.Random;
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
        int score;
        Location loc = null;
        int scoreTemp = -1;

        if (!movedRandomly) {
            Location l;
            Random rand = new Random();
            do {
                l = new Location(rand.nextInt(4), rand.nextInt(4), rand.nextInt(4));
            } while (!board.isEmpty(l));

            movedRandomly = true;
            System.out.println("Moved randomly.");
            return l;
        }

        for (int sheet = 0; sheet < board.numSheets(); sheet++) {
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) { //goes through the whole grid
                    Location l = new Location(sheet, row, col);
                    if (board.isEmpty(l)) {

                        LocationScore ls = new LocationScore(board, l, letter); //determines the score for the current place

                        score = (ls.getSelfQuadruples() * 1000) + (ls.getSelfTriples() * 100) + (ls.getSelfDoubles() * 10) + ls.getSelfSingles(); //Sets the score
                        System.out.println(l + " Has a score of..." + score);
                        if (score >= scoreTemp) { // If this place is better than all so far

                            scoreTemp = score;
                            loc = l;
                        }
                    }
                }
            }
        }
        System.out.println("I moved to" + loc);
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

    /**
     * Returns a copy of the AI for the client testing program.
     *
     * @return A new instance of this file.
     */
    public PlayerInt freshCopy() {
        return new RecursivePython(letter);
    }

}
