package pack1;

import tully.*;

import java.util.ArrayList;
import java.util.Random;

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
        int score, scoreTemp = 0, otherScore;
        Location locFinal = null;

        Random rand = new Random();
        ArrayList<Location> zeros = new ArrayList<Location>();
        ArrayList<Location> singles = new ArrayList<Location>();
        ArrayList<Location> doubles = new ArrayList<Location>();
        ArrayList<Location> triples = new ArrayList<Location>();
        ArrayList<Location> quadruples = new ArrayList<Location>();

        for (int sheet = 0; sheet < board.numSheets(); sheet++) { //Go through all the spots on the board
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) {
                    if (board.isEmpty(new Location(sheet, row, col))) { //If this spot is valid, ie not taken
                        Location locCurrent = new Location(sheet, row, col);

                        LocationScore ls = new LocationScore(board, locCurrent, letter);

                        if (ls.getSelfQuadruples() > 0) {
                            quadruples.add(new Location(sheet, row, col));
                        } else if (ls.getSelfTriples() > 0) {
                            triples.add(new Location(sheet, row, col));
                        } else if (ls.getSelfDoubles() > 0) {
                            doubles.add(new Location(sheet, row, col));
                        } else if (ls.getSelfSingles() > 0) {
                            singles.add(new Location(sheet, row, col));
                        } else {
                            zeros.add(new Location(sheet, row, col));
                        }

                        score = (ls.getSelfQuadruples() * 1000) + (ls.getSelfTriples() * 100) + (ls.getSelfDoubles() * 10) + ls.getSelfSingles();
                        otherScore = (ls.getOtherQuadruples() * 1000) + (ls.getOtherTriples() * 100) + (ls.getOtherDoubles() * 10) + ls.getOtherSingles();

                        //System.out.println("(" + sheet + "," + row + "," + col + ")  My score here is..." + score);
                        if (score > scoreTemp) { //If this location is the best so far
                            scoreTemp = score;
                            locFinal = new Location(sheet, row, col);
                        }
                    }
                }
            }
        }

        if (quadruples.size() > 0) {
            locFinal = quadruples.get(rand.nextInt(quadruples.size()));
        } else if (triples.size() > 0) {
            locFinal = triples.get(rand.nextInt(triples.size()));
        } else if (doubles.size() > 0) {
            locFinal = doubles.get(rand.nextInt(doubles.size()));
        } else if (singles.size() > 0) {
            locFinal = singles.get(rand.nextInt(singles.size()));
        } else if (zeros.size() > 0) {
            locFinal = zeros.get(rand.nextInt(zeros.size()));
        } else {
            locFinal = new Location(0, 0, 0);
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
