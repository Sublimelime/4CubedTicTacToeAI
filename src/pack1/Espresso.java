package pack1;

import tully.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * An AI coded by Hunter Wright and Noah Morton
 *
 * @author Noah Morton/Hunter Wright Date created: Jan 25, 2017 Part of project:
 *         TicTacToeAI
 */
public class Espresso implements PlayerInt {

    private final char letter;
    private final String name;

    ArrayList<ScoredLocation> bestLocs;

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
    public Espresso(char letter) {
        this.letter = letter;
        this.name = "Espresso - The Coffee AI";
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
        int score = 0;
        LocationScore ls = null;

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

        bestLocs = new ArrayList<>();

        for (int sheet = 0; sheet < board.numSheets(); sheet++) { //Go through all the spots on the board
            for (int row = 0; row < board.numRows(); row++) {
                for (int col = 0; col < board.numCols(); col++) {
                    if (board.isEmpty(new Location(sheet, row, col))) { //If this spot is valid, ie not taken
                        Location locCurrent = new Location(sheet, row, col);

                        ls = new LocationScore(board, locCurrent, letter); //gets the location score of the current spot

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

                        if (selfQuadruples.size() > 0) { //todo finish laying out scoring algorithm
                            score += 10000;
                        }
                        if (selfDoubles.size() > 0) {
                            score += (5000)*selfDoubles.size();
                        }

                        bestLocs.add(new ScoredLocation(locCurrent, score));

                        score = 0;
                    }
                }
            }
        }
        bestLocs.sort(null); //sort the list of locations.

        //todo re-evaluate the remaining moves after the previous operation
        //Logic for how to move ----------------------------------------------------
        //instant wins, stop instant win
        if (selfQuadruples.size() > 0) { //win immediately
            return selfQuadruples.get(0);
        } else if (otherQuadruples.size() > 0) { //block them from winning immediately
            return otherQuadruples.get(0);
        } else {
            return bestLocs.get(0);
        }
    }

    /**
     * Checks if two provided locations are adjacent to each other.
     *
     * @param one First location
     * @param two Second location
     * @return A boolean, true if 'two' is adjacent to 'one'.
     */
    private boolean isAdjacent(LocationInt one, LocationInt two) {
        //Logic is only two may be true, not one or three may be true. Tests for adjacency
        //if (2 == ((one.getCol() == two.getCol()) ? 1 : 0) + ((one.getRow() == two.getRow()) ? 1 : 0) + ((one.getSheet() == two.getSheet()) ? 1 : 0)) {
        int oneCol = one.getCol(), twoCol = two.getCol(), oneRow = one.getRow(), twoRow = two.getRow(),
                oneSheet = one.getSheet(), twoSheet = two.getSheet();

        return 2 == ((Math.abs((double) oneCol - (double) twoCol) == 1) ? 1 : 0)
                + ((Math.abs((double) oneRow - (double) twoRow) == 1) ? 1 : 0)
                + ((Math.abs((double) oneSheet - (double) twoSheet) == 1) ? 1 : 0);
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
        return new Espresso(letter);
    }

}
