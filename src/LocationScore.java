public class LocationScore
{
	private char self;
	private char other;

	private	int selfSingles 	= 0;
	private int selfDoubles 	= 0;
	private int selfTriples 	= 0;
	private int selfQuadruples	= 0;

	private	int otherSingles 	= 0;
	private int otherDoubles 	= 0;
	private int otherTriples 	= 0;
	private int otherQuadruples	= 0;

	private BoardInt board;
	private LocationInt loc = null;
	private int c;
	private int r;
	private int s;

	public LocationScore(BoardInt board,LocationInt loc, char letter)
	{
		this.board	= board;
		this.self	= letter;
		this.loc    = loc;
		if(letter == 'X')
		{
			other = 'O';
		}
		else
		{
			other = 'X';
		}
		this.c = loc.getCol();
		this.r = loc.getRow();
		this.s = loc.getSheet();
		this.loc = loc;
		calculateScore();
	}

	public LocationInt getLocation()
	{
		return loc;
	}

	public void calculateScore()
	{
		int count;


		// row
		// done
		count = 1;
		
		for(int tx = 0; tx < board.numCols(); tx++)
		{
			if(board.getLocation(new Location(s,r,tx)) == self)
			{
				count++;
			}
			else if(board.getLocation(new Location(s,r,tx)) == other)
			{
				count=0;
				break;
			}
		}
		selfAdd(count);
			//other
		count = 1;
		for(int tx = 0; tx < board.numCols(); tx++)
		{
			if(board.getLocation(new Location(s,r,tx)) == other)
			{
				count++;
			}
			else if(board.getLocation(new Location(s,r,tx)) == self)
			{
				count=0;
				break;
			}
		}
		otherAdd(count);

		// column
		// done
		count = 1;
		for(int ty = 0; ty < board.numRows(); ty++)
		{
			if(board.getLocation(new Location(s,ty,c)) == self)
			{
				count++;
			}
			else if(board.getLocation(new Location(s,ty,c)) == other)
			{
				count=0;
				break;
			}
		}
		selfAdd(count);
			//other
		count = 1;
		for(int ty = 0; ty < board.numRows(); ty++)
		{
			if(board.getLocation(new Location(s,ty,c)) == other)
			{
				count++;
			}
			else if(board.getLocation(new Location(s,ty,c)) == self)
			{
				count=0;
				break;
			}
		}
		otherAdd(count);

		// depth
		// done
		count = 1;
		for(int tz = 0; tz < board.numSheets(); tz++)
		{
			if(board.getLocation(new Location(tz,r,c)) == self)
			{
				count++;
			}
			else if(board.getLocation(new Location(tz,r,c)) == other)
			{
				count=0;
				break;
			}
		}
		selfAdd(count);

		//other
		count = 1;
		for(int tz = 0; tz < board.numSheets(); tz++)
		{
			if(board.getLocation(new Location(tz,r,c)) == other)
			{
				count++;
			}
			else if(board.getLocation(new Location(tz,r,c)) == self)
			{
				count=0;
				break;
			}
		}
		otherAdd(count);

		// diangonal one in Z plane
		// done
		count =0;
		if(c ==r)
		{
			count = 1;
			if(board.getLocation(new Location(s,0,0)) == self)
				count++;
			if(board.getLocation(new Location(s,1,1)) == self)
				count++;
			if(board.getLocation(new Location(s,2,2)) == self)
				count++;
			if(board.getLocation(new Location(s,3,3)) == self)
				count++;
			if(board.getLocation(new Location(s,0,0)) == other ||
				board.getLocation(new Location(s,1,1)) == other ||
				board.getLocation(new Location(s,2,2)) == other ||
				board.getLocation(new Location(s,3,3)) == other)
				count = 0;
		}
		selfAdd(count);

			// other
		count =0;
		if(c ==r)
		{
			count = 1;
			if(board.getLocation(new Location(s,0,0)) == other)
				count++;
			if(board.getLocation(new Location(s,1,1)) == other)
				count++;
			if(board.getLocation(new Location(s,2,2)) == other)
				count++;
			if(board.getLocation(new Location(s,3,3)) == other)
				count++;
			if(board.getLocation(new Location(s,0,0)) == self ||
				board.getLocation(new Location(s,1,1)) == self ||
				board.getLocation(new Location(s,2,2)) == self ||
				board.getLocation(new Location(s,3,3)) == self)
				count = 0;
		}
		otherAdd(count);

		// diangonal two in Z plane
		// done
		count =0;
		if(c+r == 3)
		{
			count = 1;
			if(board.getLocation(new Location(s,0,3)) == self)
				count++;
			if(board.getLocation(new Location(s,1,2)) == self)
				count++;
			if(board.getLocation(new Location(s,2,1)) == self)
				count++;
			if(board.getLocation(new Location(s,3,0)) == self)
				count++;
			if(board.getLocation(new Location(s,0,3)) == other ||
				board.getLocation(new Location(s,1,2)) == other ||
				board.getLocation(new Location(s,2,1)) == other ||
				board.getLocation(new Location(s,3,0)) == other)
				count = 0;
		}
		selfAdd(count);

			//other
		count =0;
		if(c+r == 3)
		{
			count = 1;
			if(board.getLocation(new Location(s,0,3)) == other)
				count++;
			if(board.getLocation(new Location(s,1,2)) == other)
				count++;
			if(board.getLocation(new Location(s,2,1)) == other)
				count++;
			if(board.getLocation(new Location(s,3,0)) == other)
				count++;
			if(board.getLocation(new Location(s,0,3)) == self ||
				board.getLocation(new Location(s,1,2)) == self ||
				board.getLocation(new Location(s,2,1)) == self ||
				board.getLocation(new Location(s,3,0)) == self)
				count = 0;
		}
		otherAdd(count);
// I am here
		// diangonal one in Y plane
		// done
		count =0;
		if(c ==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,r,0)) == self)
				count++;
			if(board.getLocation(new Location(1,r,1)) == self)
				count++;
			if(board.getLocation(new Location(2,r,2)) == self)
				count++;
			if(board.getLocation(new Location(3,r,3)) == self)
				count++;
			if(board.getLocation(new Location(0,r,0)) == other ||
				board.getLocation(new Location(1,r,1)) == other ||
				board.getLocation(new Location(2,r,2)) == other ||
				board.getLocation(new Location(3,r,3)) == other)
				count = 0;
		}
		selfAdd(count);

			//other
		count =0;
		if(c ==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,r,0)) == other)
				count++;
			if(board.getLocation(new Location(1,r,1)) == other)
				count++;
			if(board.getLocation(new Location(2,r,2)) == other)
				count++;
			if(board.getLocation(new Location(3,r,3)) == other)
				count++;
			if(board.getLocation(new Location(0,r,0)) == self ||
				board.getLocation(new Location(1,r,1)) == self ||
				board.getLocation(new Location(2,r,2)) == self ||
				board.getLocation(new Location(3,r,3)) == self)
				count = 0;
		}
		otherAdd(count);

		// diangonal two in Y plane
		// done
		count =0;
		if(c+s == 3)
		{
			count = 1;
			if(board.getLocation(new Location(0,r,3)) == self)
				count++;
			if(board.getLocation(new Location(1,r,2)) == self)
				count++;
			if(board.getLocation(new Location(2,r,1)) == self)
				count++;
			if(board.getLocation(new Location(3,r,0)) == self)
				count++;
			if(board.getLocation(new Location(0,r,3)) == other ||
				board.getLocation(new Location(1,r,2)) == other ||
				board.getLocation(new Location(2,r,1)) == other ||
				board.getLocation(new Location(3,r,0)) == other)
				count = 0;
		}
		selfAdd(count);

		//other
		count =0;
		if(c+s == 3)
		{
			count = 1;
			if(board.getLocation(new Location(0,r,3)) == other)
				count++;
			if(board.getLocation(new Location(1,r,2)) == other)
				count++;
			if(board.getLocation(new Location(2,r,1)) == other)
				count++;
			if(board.getLocation(new Location(3,r,0)) == other)
				count++;
			if(board.getLocation(new Location(0,r,3)) == self ||
				board.getLocation(new Location(1,r,2)) == self ||
				board.getLocation(new Location(2,r,1)) == self ||
				board.getLocation(new Location(3,r,0)) == self)
				count = 0;
		}
		otherAdd(count);

		// diangonal one in X plane
		// done
		count =0;
		if(r ==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,0,c)) == self)
				count++;
			if(board.getLocation(new Location(2,1,c)) == self)
				count++;
			if(board.getLocation(new Location(2,2,c)) == self)
				count++;
			if(board.getLocation(new Location(3,3,c)) == self)
				count++;
			if(board.getLocation(new Location(0,0,c)) == other ||
				board.getLocation(new Location(1,1,c)) == other ||
				board.getLocation(new Location(2,2,c)) == other ||
				board.getLocation(new Location(3,3,c)) == other)
				count = 0;
		}
		selfAdd(count);

		//other
		count =0;
		if(r ==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,0,c)) == other)
				count++;
			if(board.getLocation(new Location(1,1,c)) == other)
				count++;
			if(board.getLocation(new Location(2,2,c)) == other)
				count++;
			if(board.getLocation(new Location(3,3,c)) == other)
				count++;
			if(board.getLocation(new Location(0,0,c)) == self ||
				board.getLocation(new Location(1,1,c)) == self ||
				board.getLocation(new Location(2,2,c)) == self ||
				board.getLocation(new Location(3,3,c)) == self)
				count = 0;
		}
		otherAdd(count);


		// diangonal two in X plane
		// done
		count =0;
		if(s+r == 3)
		{
			count = 1;
			if(board.getLocation(new Location(0,3,c)) == self)
				count++;
			if(board.getLocation(new Location(1,2,c)) == self)
				count++;
			if(board.getLocation(new Location(2,1,c)) == self)
				count++;
			if(board.getLocation(new Location(3,0,c)) == self)
				count++;
			if(board.getLocation(new Location(0,3,c)) == other ||
				board.getLocation(new Location(1,2,c)) == other ||
				board.getLocation(new Location(2,1,c)) == other ||
				board.getLocation(new Location(3,0,c)) == other)
				count = 0;
		}
		selfAdd(count);

		//other
		count =0;
		if(s+r == 3)
		{
			count = 1;
			if(board.getLocation(new Location(0,3,c)) == other)
				count++;
			if(board.getLocation(new Location(1,2,c)) == other)
				count++;
			if(board.getLocation(new Location(2,1,c)) == other)
				count++;
			if(board.getLocation(new Location(3,0,c)) == other)
				count++;
			if(board.getLocation(new Location(0,3,c)) == self ||
				board.getLocation(new Location(1,2,c)) == self ||
				board.getLocation(new Location(2,1,c)) == self ||
				board.getLocation(new Location(3,0,c)) == self)
				count = 0;
		}
		otherAdd(count);

		// special one
		// done
		count =0;
		if(c==r && r==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,0,0)) == self)
				count++;
			if(board.getLocation(new Location(1,1,1)) == self)
				count++;
			if(board.getLocation(new Location(2,2,2)) == self)
				count++;
			if(board.getLocation(new Location(3,3,3)) == self)
				count++;
			if(board.getLocation(new Location(0,0,0)) == other ||
				board.getLocation(new Location(1,1,1)) == other ||
				board.getLocation(new Location(2,2,2)) == other ||
				board.getLocation(new Location(3,3,3)) == other)
				count = 0;
		}
		selfAdd(count);

		//other
		count =0;
		if(c==r && r==s)
		{
			count = 1;
			if(board.getLocation(new Location(0,0,0)) == other)
				count++;
			if(board.getLocation(new Location(1,1,1)) == other)
				count++;
			if(board.getLocation(new Location(2,2,2)) == other)
				count++;
			if(board.getLocation(new Location(3,3,3)) == other)
				count++;
			if(board.getLocation(new Location(0,0,0)) == self ||
				board.getLocation(new Location(1,1,1)) == self ||
				board.getLocation(new Location(2,2,2)) == self ||
				board.getLocation(new Location(3,3,3)) == self)
				count = 0;
		}
		otherAdd(count);

		//special two
		//done
		count =0;
		if( (c ==3 && r == 0 && s == 0) ||
			(c ==2 && r == 1 && s == 1) ||
			(c ==1 && r == 2 && s == 2) ||
			(c ==0 && r == 3 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,0,3)) == self)
				count++;
			if(board.getLocation(new Location(1,1,2)) == self)
				count++;
			if(board.getLocation(new Location(2,2,1)) == self)
				count++;
			if(board.getLocation(new Location(3,3,0)) == self)
				count++;
			if(board.getLocation(new Location(0,0,3)) == other ||
				board.getLocation(new Location(1,1,2)) == other ||
				board.getLocation(new Location(2,2,1)) == other ||
				board.getLocation(new Location(3,3,0)) == other)
				count = 0;
		}

		selfAdd(count);
		//other
		count =0;
		if( (c ==3 && r == 0 && s == 0) ||
			(c ==2 && r == 1 && s == 1) ||
			(c ==1 && r == 2 && s == 2) ||
			(c ==0 && r == 3 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,0,3)) == other)
				count++;
			if(board.getLocation(new Location(1,1,2)) == other)
				count++;
			if(board.getLocation(new Location(2,2,1)) == other)
				count++;
			if(board.getLocation(new Location(3,3,0)) == other)
				count++;
			if(board.getLocation(new Location(0,0,3)) == self ||
				board.getLocation(new Location(1,1,2)) == self ||
				board.getLocation(new Location(2,2,1)) == self ||
				board.getLocation(new Location(3,3,0)) == self)
				count = 0;
		}

		otherAdd(count);

		// special three
		// done
		count =0;
		if( (c ==0 && r == 3 && s == 0) ||
			(c ==1 && r == 2 && s == 1) ||
			(c ==2 && r == 1 && s == 2) ||
			(c ==3 && r == 0 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,3,0)) == self)
				count++;
			if(board.getLocation(new Location(1,2,1)) == self)
				count++;
			if(board.getLocation(new Location(2,1,2)) == self)
				count++;
			if(board.getLocation(new Location(3,0,3)) == self)
				count++;
			if(board.getLocation(new Location(0,3,0)) == other ||
				board.getLocation(new Location(1,2,1)) == other ||
				board.getLocation(new Location(2,1,2)) == other ||
				board.getLocation(new Location(3,0,3)) == other)
				count = 0;
		}
		selfAdd(count);
		//other
		count =0;
		if( (c ==0 && r == 3 && s == 0) ||
			(c ==1 && r == 2 && s == 1) ||
			(c ==2 && r == 1 && s == 2) ||
			(c ==3 && r == 0 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,3,0)) == other)
				count++;
			if(board.getLocation(new Location(1,2,1)) == other)
				count++;
			if(board.getLocation(new Location(2,1,2)) == other)
				count++;
			if(board.getLocation(new Location(3,0,3)) == other)
				count++;
			if(board.getLocation(new Location(0,3,0)) == self ||
				board.getLocation(new Location(1,2,1)) == self ||
				board.getLocation(new Location(2,1,2)) == self ||
				board.getLocation(new Location(3,0,3)) == self)
				count = 0;
		}
		otherAdd(count);

		//special four
		count =0;
		if( (c ==3 && r == 3 && s == 0) ||
			(c ==2 && r == 2 && s == 1) ||
			(c ==1 && r == 1 && s == 2) ||
			(c ==0 && r == 0 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,3,3)) == self)
				count++;
			if(board.getLocation(new Location(1,2,2)) == self)
				count++;
			if(board.getLocation(new Location(2,1,1)) == self)
				count++;
			if(board.getLocation(new Location(3,0,0)) == self)
				count++;
			if(board.getLocation(new Location(0,3,3)) == other ||
				board.getLocation(new Location(1,2,2)) == other ||
				board.getLocation(new Location(2,1,1)) == other ||
				board.getLocation(new Location(3,0,0)) == other)
				count = 0;
		}

		selfAdd(count);
		//other
		count =0;
		if( (c ==3 && r == 3 && s == 0) ||
			(c ==2 && r == 2 && s == 1) ||
			(c ==1 && r == 1 && s == 2) ||
			(c ==0 && r == 0 && s == 3))
		{
			count = 1;
			if(board.getLocation(new Location(0,3,3)) == other)
				count++;
			if(board.getLocation(new Location(1,2,2)) == other)
				count++;
			if(board.getLocation(new Location(2,1,1)) == other)
				count++;
			if(board.getLocation(new Location(3,0,0)) == other)
				count++;
			if(board.getLocation(new Location(0,3,3)) == self ||
				board.getLocation(new Location(1,2,2)) == self ||
				board.getLocation(new Location(2,1,1)) == self ||
				board.getLocation(new Location(3,0,0)) == self)
				count = 0;
		}

		otherAdd(count);
	}

	public int getSelfSingles()
	{
		return selfSingles;
	}

	public int getSelfDoubles()
	{
		return selfDoubles;
	}

	public int getSelfTriples()
	{
		return selfTriples;
	}

	public int getSelfQuadruples()
	{
		return selfQuadruples;
	}

	private void selfAdd(int t)
	{
		if(t==1)
		{
			selfSingles++;
		}
		if(t==2)
		{
			selfDoubles++;
		}
		if(t==3)
		{
			selfTriples++;
		}
		if(t==4)
		{
			selfQuadruples++;
		}
	}

	public int getOtherSingles()
	{
		return otherSingles;
	}

	public int getOtherDoubles()
	{
		return otherDoubles;
	}

	public int getOtherTriples()
	{
		return otherTriples;
	}

	public int getOtherQuadruples()
	{
		return otherQuadruples;
	}

	private void otherAdd(int t)
	{
		if(t==1)
		{
			otherSingles++;
		}
		if(t==2)
		{
			otherDoubles++;
		}
		if(t==3)
		{
			otherTriples++;
		}
		if(t==4)
		{
			otherQuadruples++;
		}
	}

	public int getCol()
	{
		return c;
	}

	public int getRow()
	{
		return r;
	}

	public int getSheet()
	{
		return s;
	}
}
