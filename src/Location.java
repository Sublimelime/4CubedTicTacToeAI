public class Location implements LocationInt
{
	int sheet;
	int row;
	int col;
	
	// Pre: 	Receives Location values x, y ,z
	// Post:	Sets x, y, z to the recieved values
	public Location(int sheet,int row, int col)
	{
		this.col	= col;
		this.row	= row;
		this.sheet 	= sheet;
	}
	
	// Pre: 	Receives Location l
	// Post:	Sets x, y, z to the values of the received location
	public Location(LocationInt l)
	{
		this.col	= l.getCol();
		this.row	= l.getRow();
		this.sheet 	= l.getSheet();
	}
	// pre:		method is called
	// post:	returns the x value of the Location
	public int getCol()
	{
		return col;
	}
	
	// pre:		method is called
	// post:	returns the y value of the Location
	public int getRow()
	{
		return row;	
	}
	
	// pre:		method is called
	// post:	returns the z value of the Location
	public int getSheet()
	{
		return sheet;	
	}
	
	// pre:		method is called
	// post:	returns the z value of the Location
	public String toString()
	{
		String s = "(" + sheet+","+row+ ","+col+ ")";
		return s;
	}
	
	public Object clone()
	{
		return new Location(sheet,row,col);
	}

}