import java.util.ArrayList;
import java.util.Random;

public class Test_AI_2 implements PlayerInt
{
	private char letter;
	private String name;

	// Pre:		receives a char letter
	// Post: 	sets the name to "Random AI" and the letter to the letter received
	Test_AI_2(char letter)
	{
		name 		= "Straight Line AI";
		this.letter	= letter;
	}


	public char getLetter()
	{
		return letter;
	}

	// Pre:		method is called
	// Post: 	returns the Location where the player wants to move
	public LocationInt getMove(BoardInt board)
	{
		Random rand = new Random();
		ArrayList<Location> zeros = new ArrayList<Location>();
		ArrayList<Location> singles = new ArrayList<Location>();
		ArrayList<Location> doubles = new ArrayList<Location>();
		ArrayList<Location> triples = new ArrayList<Location>();
		ArrayList<Location> quadruples = new ArrayList<Location>();


		for(int x = 0; x < board.numRows(); x++)
			for(int y = 0; y < board.numRows(); y++)
				for(int z = 0; z < board.numRows(); z++)
				{
					if(board.isEmpty(new Location(z,y,x)))
					{
						LocationScore ls = new LocationScore(board,new Location(z,y,x),letter);
						if(ls.getSelfQuadruples() >0)
						{
							quadruples.add(new Location(z,y,x));
						}
						else if(ls.getSelfTriples() >0)
						{
							triples.add(new Location(z,y,x));
						}
						else if(ls.getSelfDoubles() >0)
						{
							doubles.add(new Location(z,y,x));
						}
						else if(ls.getSelfSingles() >0)
						{
							singles.add(new Location(z,y,x));
						}
						else
						{
							zeros.add(new Location(z,y,x));
						}
					}
				}
		if(quadruples.size() > 0)
		{
			return quadruples.get(rand.nextInt(quadruples.size()));
		}
		else if(triples.size() > 0)
		{
			return triples.get(rand.nextInt(triples.size()));
		}
		else if(doubles.size() > 0)
		{
			return doubles.get(rand.nextInt(doubles.size()));
		}
		else if(singles.size() > 0)
		{
			return singles.get(rand.nextInt(singles.size()));
		}
		else if(zeros.size() > 0)
		{
			return zeros.get(rand.nextInt(zeros.size()));
		}
		else
		{
			return new Location(0,0,0);
		}
	}

	// Pre:		method is called
	// Post: 	returns the name of the player
	public String getName()
	{
		return name;
	}
}