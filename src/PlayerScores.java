public class PlayerScores
{
	private int wins 	= 0;
	private int losses	= 0;
	private int cats	= 0;
	private final int POINTS_FOR_CAT 	= 1;
	private final int POINTS_FOR_WIN 	= 2;
	private final int POINTS_FOR_LOSS	= 0;
	
	// Pre:		constuctor is called
	// Post: 	sets wins, lossses, cats to zero
	public PlayerScores()
	{}
	
	// Pre:		method is called
	// Post: 	adds one to the win variable
	public void addWin()
	{
		wins++;	
	}
	
	// Pre:		method is called
	// Post: 	adds one to the losses variable
	public void addLoss()
	{
		losses++;
	}
	
	// Pre:		method is called
	// Post: 	adds one to the cats variable
	public void addCat()
	{
		cats++;	
	}
	
	// Pre:		method is called
	// Post: 	returns the value of wins
	public int getWins()
	{
		return wins;	
	}
	
	// Pre:		method is called
	// Post: 	returns the value of losses
	public int getLosses()
	{
		return losses;	
	}
	
	// Pre:		method is called
	// Post: 	returns the value of cats
	public int getCats()
	{
		return cats;	
	}
	
	// Pre:		method is called
	// Post: 	returns the score caluclated with wins, cats, losses
	public int totalScore()
	{
		return POINTS_FOR_WIN*wins + POINTS_FOR_CAT*cats + POINTS_FOR_LOSS*losses;	
	}
}