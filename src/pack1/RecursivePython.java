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
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

}
