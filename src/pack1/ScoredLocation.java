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
public class ScoredLocation extends Location implements LocationInt {

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

}
