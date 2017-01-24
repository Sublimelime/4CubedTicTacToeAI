import java.util.Scanner;

public class HumanPlayer implements PlayerInt
{
	private char letter;
	private String name;

	public HumanPlayer(char letter, String name)
	{
		this.letter = letter;
		this.name = name;
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
		return null;
	}
	
	public String toString()
	{
		return name+" - "+letter;
	}
}