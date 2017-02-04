package pack1;

import tully.Location;
import tully.LocationInt;

/**
 * Description
 *
 * @author Noah Morton
 *
 * Date created: Feb 3, 2017
 *
 * Part of project: TicTacToeAI
 */
public class ScoredLocation extends Location implements LocationInt, Comparable<ScoredLocation> {

    private int scoreOfLocation;

    /**
     * Creates a new scored location.
     *
     * @param l The location
     * @param score The score of the mentioned location.
     */
    public ScoredLocation(LocationInt l, int score) {
        super(l);
        this.scoreOfLocation = score;
    }

    public int getScoreOfLocation() {
        return scoreOfLocation;
    }

    public void setScoreOfLocation(int scoreOfLocation) {
        this.scoreOfLocation = scoreOfLocation;
    }

    /**
     * Changes the class back into a regular location, so it interfaces with the
     * AI dispatcher.
     *
     * @return A new Location with the sheet,row, and col of the current
     * location.
     * @see tully.Location
     */
    public Location makeLocationOnly() {
        return new Location(getSheet(), getRow(), getCol());
    }

    /**
     * Compare two scored locations, works similarly to a standard compareTo.
     *
     * @param o A ScoredLocation to compare to 'this'
     * @return -1, 0, or 1 depending on if the provided object's score is
     * greater or smaller.
     */
    @Override
    public int compareTo(ScoredLocation o) {
        return (scoreOfLocation < o.getScoreOfLocation()) ? -1 : (scoreOfLocation > o.getScoreOfLocation()) ? 1 : 0;
    }

    /**
     * Compares 'this' and the provided Object for their scores. They are
     * considered equal if they have the same memory address, or the same score.
     *
     * @param o Object to check for equivalency.
     * @return True if they are both ScoredLocations and the scores match. _Does
     * not compare location._
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { //they are the same memory address
            return true;
        } else if (o instanceof ScoredLocation) {
            ScoredLocation temp = (ScoredLocation) o;
            if (temp.getScoreOfLocation() == this.scoreOfLocation) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.scoreOfLocation;
        return hash;
    }
}
