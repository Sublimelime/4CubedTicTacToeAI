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

    private final char letter;
    private final String name;

    //location arraylists
    ArrayList<Location> selfZeros;
    ArrayList<Location> selfSingles;
    ArrayList<Location> selfDoubles;
    ArrayList<Location> selfTriples;
    ArrayList<Location> selfQuadruples;

    ArrayList<Location> otherZeros;
    ArrayList<Location> otherSingles;
    ArrayList<Location> otherDoubles;
    ArrayList<Location> otherTriples;
    ArrayList<Location> otherQuadruples;

    /**
     * Creates a new instance of the RecursivePython AI.
     *
     * @param letter The letter the AI will be playing as.
     */
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
        Location locFinal = null; //holds the location to be returned at the end.

        Random rand = new Random();
        //clear and recreate all the arraylists
        selfZeros = new ArrayList<>();
        selfSingles = new ArrayList<>();
        selfDoubles = new ArrayList<>();
        selfTriples = new ArrayList<>();
        selfQuadruples = new ArrayList<>();

        otherZeros = new ArrayList<>();
        otherSingles = new ArrayList<>();
        otherDoubles = new ArrayList<>();
        otherTriples = new ArrayList<>();
        otherQuadruples = new ArrayList<>();

        for (int sheet = 0; sheet < board.numSheets(); sheet++) { //Go through all the spots on the board
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) {
                    if (board.isEmpty(new Location(sheet, row, col))) { //If this spot is valid, ie not taken
                        Location locCurrent = new Location(sheet, row, col);

                        LocationScore ls = new LocationScore(board, locCurrent, letter); //gets the location score of the current spot

                        if (ls.getOtherQuadruples() > 0) { //add the location to one of the arraylists for the enemy if the spot is good for them
                            otherQuadruples.add(locCurrent);
                        } else if (ls.getOtherTriples() > 0) {
                            otherTriples.add(locCurrent);
                        } else if (ls.getOtherDoubles() > 0) {
                            otherDoubles.add(locCurrent);
                        } else if (ls.getOtherSingles() > 0) {
                            otherSingles.add(locCurrent);
                        } else {
                            otherZeros.add(locCurrent);
                        }

                        if (ls.getSelfQuadruples() > 0) { //add the location to one of the self arrayLists if spot is good for us
                            selfQuadruples.add(locCurrent);
                        } else if (ls.getSelfTriples() > 0) {
                            selfTriples.add(locCurrent);
                        } else if (ls.getSelfDoubles() > 0) {
                            selfDoubles.add(locCurrent);
                        } else if (ls.getSelfSingles() > 0) {
                            selfSingles.add(locCurrent);
                        } else {
                            selfZeros.add(locCurrent);
                        }

                        score = (ls.getSelfQuadruples() * 1000) + (ls.getSelfTriples() * 100) + (ls.getSelfDoubles() * 10) + ls.getSelfSingles();

                        //System.out.println("(" + sheet + "," + row + "," + col + ")  My score here is..." + score);
                        if (score > scoreTemp) { //If this location is the best so far
                            scoreTemp = score;
                            locFinal = locCurrent;
                        }
                    }
                }
            }
        }

        //Logic for how to move ----------------------------------------------------
        //todo after this is done, old method with score should be unnecessary
        //if()
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
