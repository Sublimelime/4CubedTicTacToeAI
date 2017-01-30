package pack1;

import java.util.ArrayList;
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
        Random rand = new Random();
        ArrayList<Location> zeros = new ArrayList<>();
        ArrayList<Location> singles = new ArrayList<>();
        ArrayList<Location> doubles = new ArrayList<>();
        ArrayList<Location> triples = new ArrayList<>();
        ArrayList<Location> quadruples = new ArrayList<>();

        for (int x = 0; x < board.numRows(); x++) {
            for (int y = 0; y < board.numRows(); y++) {
                for (int z = 0; z < board.numRows(); z++) {
                    if (board.isEmpty(new Location(z, y, x))) {
                        LocationScore ls = new LocationScore(board, new Location(z, y, x), letter);
                        if (ls.getSelfQuadruples() > 0) {
                            quadruples.add(new Location(z, y, x));
                        } else if (ls.getSelfTriples() > 0) {
                            triples.add(new Location(z, y, x));
                        } else if (ls.getSelfDoubles() > 0) {
                            doubles.add(new Location(z, y, x));
                        } else if (ls.getSelfSingles() > 0) {
                            singles.add(new Location(z, y, x));
                        } else {
                            zeros.add(new Location(z, y, x));
                        }
                    }
                }
            }
        }
        if (quadruples.size() > 0) {
            return quadruples.get(rand.nextInt(quadruples.size()));
        } else if (triples.size() > 0) {
            return triples.get(rand.nextInt(triples.size()));
        } else if (doubles.size() > 0) {
            return doubles.get(rand.nextInt(doubles.size()));
        } else if (singles.size() > 0) {
            return singles.get(rand.nextInt(singles.size()));
        } else if (zeros.size() > 0) {
            return zeros.get(rand.nextInt(zeros.size()));
        } else {
            return new Location(0, 0, 0);
        }
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
