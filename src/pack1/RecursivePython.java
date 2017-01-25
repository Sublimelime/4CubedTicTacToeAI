package pack1;

import tully.*;

/**
 * An AI coded by Hunter Wright and Noah Morton
 *
 * @author Noah Morton/Hunter Wright Date created: Jan 25, 2017 Part of project:
 *         TicTacToeAI
 */
public class RecursivePython implements PlayerInt {

    private char letter;
    private String name;

    private RecursivePython(char letter) {
        this.letter = letter;
        this.name = "Recursive Python";
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public LocationInt getMove(BoardInt board) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
