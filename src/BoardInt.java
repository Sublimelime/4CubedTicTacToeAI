import java.awt.Graphics;
import java.util.*;

public interface BoardInt
{
	public boolean setLocation(LocationInt l, char c);
	
	public void setLocationNoCheck(LocationInt l, char c);

	public char getLocation(LocationInt l);

	public boolean isWinner(char c);

	public void display();

	public void draw(Graphics g);

	public char[][][] getData();

	public boolean isEmpty(LocationInt l);

	public boolean isCat();

	public int numSheets();

	public int numRows();

	public int numCols();

	public void reset();
	
	public ArrayList<GamePolygon> getPolys();
}
