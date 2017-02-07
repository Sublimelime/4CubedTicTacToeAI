package tully;

/* Location - Stores the x,y,z values of a 3D location */
import java.io.Serializable;

public class Location implements LocationInt, Serializable {

    int sheet;
    int row;
    int col;

    /* Pre: 	Receives Location values x, y ,z
	 * Post:	Sets x, y, z to the recieved values */
    public Location(int sheet, int row, int col) {
        this.col = col;
        this.row = row;
        this.sheet = sheet;
    }

    /* Pre: 	Receives Location l
	 * Post:	Sets x, y, z to the values of the received location */
    public Location(LocationInt l) {
        this.col = l.getCol();
        this.row = l.getRow();
        this.sheet = l.getSheet();
    }

    /* Pre:		None
	 * Post: 	returns the column (x) of the location */
    public int getCol() {
        return col;
    }

    /* Pre:		None
	 * Post: 	returns the row (y) of the location */
    public int getRow() {
        return row;
    }

    /* Pre:		None
	 * Post: 	returns the sheet (z) of the location */
    public int getSheet() {
        return sheet;
    }

    /* Pre:		None
	 * Post: 	returns a text representation of the location
	 * in the form of (x,y,x)*/
    public String toString() {
        String s = "(z=" + sheet + ",y=" + row + ",x=" + col + ")";
        return s;
    }

    /* Pre:		None
	 * Post: 	returns a copy of the Location*/
    public Object clone() {
        return new Location(sheet, row, col);
    }

}
