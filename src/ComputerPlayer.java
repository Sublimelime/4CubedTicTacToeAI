import java.util.Scanner;

public class ComputerPlayer implements PlayerInt
{
	private char letter;
	private String name;

	public ComputerPlayer(char letter)
	{
		this.letter = letter;
		name = "Random Computer";
	}

	public char getLetter()
	{
		return letter;
	}

	public String getName()
	{
		return name;
	}

	public LocationInt getMove(BoardInt board)
	{
		try
		{
			System.out.print("Thinking .");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".\n");
			Thread.sleep(500);
			
		}
		catch(Exception e)
		{
			System.out.println("Error in Computer Player - getMove: "+e.getMessage());
			e.printStackTrace();
		}
		while(true)
		{
			int sheet = (int) (Math.random()*4);
			int row = (int) (Math.random()*4);
			int col = (int) (Math.random()*4);
			Location l = new Location(sheet,row,col);
			if(board.isEmpty(l))
			{
				return l;
			}
		}
	}
	
	public String toString()
	{
		return name+" - "+letter;
	}
}