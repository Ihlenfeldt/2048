package game2048;

import javax.swing.JFrame;

public class RussianGameBoard implements GameBoard {

	protected Block2048[][] gameArray;
	int length = 0;
	int centerColumn = 0;
	int height = 0;
	private int capacity = 0;
	private int numberOfBlocks = 0;
	public int gameScore = 0;
	public int combineTotal = 0;
	public boolean needToPopulate = false;
	public int iPosition = 0;;
	public int jPosition = 0;
	
	public RussianGameBoard(JFrame frame,int passedLength, int passedHeight) {
		gameArray = new Block2048[passedHeight][passedLength];
		length = passedLength;
		height = passedHeight;
		centerColumn = (passedLength/2);
		fillGameBoard(frame);

		
	}

	private void fillGameBoard(JFrame frame)
	{
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < length; col++)
			{
				gameArray[row][col] = new Block2048(frame, 0, true);
				gameArray[row][col].setX(col*67);
				gameArray[row][col].setY(row*67 + Controller2048.barHeight);
			}
		}
		populate();
	}
	
	@Override
	public void moveRight() {
		for(int row = height-1; row >= 0; row--)
		{
			for(int col = length-1; col >=0; col--)
			{
				//Identifies the falling block
				if(gameArray[row][col].getLockedIn() == false)
				{
					//prevents out of bounds exception or falling block moving into already occupied space. 
					if(col != length-1 &&(gameArray[row][col+1].getValue()==0))
					{
						gameArray[row][col+1].setBlockValue(gameArray[row][col].getValue());
						gameArray[row][col+1].setLockedIn(false);
						gameArray[row][col].setBlockValue(0);
					}
				}
				
			}
		}
		
	}

	@Override
	public void moveLeft() {
		for(int row = height-1; row >= 0; row--)
		{
			for(int col = 0; col < length; col++)
			{
				//Identifies the falling block
				if(gameArray[row][col].getLockedIn() == false)
				{
					//prevents out of bounds exception or falling block moving into already occupied space. 
					if(col != 0 &&(gameArray[row][col-1].getValue()==0))
					{
						gameArray[row][col-1].setBlockValue(gameArray[row][col].getValue());
						gameArray[row][col-1].setLockedIn(false);
						gameArray[row][col].setBlockValue(0);
					}
				}
				
			}
		}
	}

	@Override
	public void moveUp() {
		
	}
	
	public void moveToHolderCell() {
		
	}
	
	public void fall() {
		boolean wasThereACombination = false;
		int loopCount = 0;
		//printArray();
	 for(int row = height-1; row >= 0; row--)
		{
			for(int col = 0; col < length; col++)
			{
				//If falling block is not on the bottom row
				if(gameArray[row][col].getLockedIn() == false && row != height-1)
				{
					//if the space below the falling block is open, then block falls.
					if(lookDown(row,col).getValue()==0)
					{
						gameArray[row+1][col].setBlockValue(gameArray[row][col].getValue());
						gameArray[row+1][col].setLockedIn(false);
						gameArray[row][col].setBlockValue(0);
					}
					//if there is a block beneath falling block then lock the falling block in place and look for combinations around it. 
					else
					{
						gameArray[row][col].setLockedIn(true);
						//look for combinations around the block 
						wasThereACombination = combineAround(row,col);
						
						//while loop continues to make combinations until there are no more on the board.
						while(wasThereACombination)
						{
							loopCount ++;
							System.out.println("Second Pass At Combine Around *******************");
							for(int combineRow = height-1; combineRow >= 0; combineRow--)
							{
								for(int combineCol = 0; combineCol < length; combineCol++)
								{
									if(gameArray[combineRow][combineCol].isNewCombination()) {
										System.out.println("Block " + combineRow + ", " + combineCol + " is a new combination");
										wasThereACombination = combineAround(combineRow,combineCol);
										System.out.println("Was there a combine on pass " + loopCount  + wasThereACombination);
									}
								}
							}	
						}
						needToPopulate = true;
					}
				
				}
				if(gameArray[row][col].getLockedIn() == false && row == height-1)
				{
					gameArray[row][col].setLockedIn(true);
					wasThereACombination = combineAround(row,col);
					
					while(wasThereACombination)
					{
						System.out.println("Second Pass At Combine Around *******************");
						for(int combineRow = height-1; combineRow >= 0; combineRow--)
						{
							for(int combineCol = 0; combineCol < length; combineCol++)
							{
								if(gameArray[combineRow][combineCol].isNewCombination()) {
									System.out.println("Block " + combineRow + ", " + combineCol + " is a new combination");
									wasThereACombination = combineAround(combineRow,combineCol);
									System.out.println("Was there a combine on pass " + loopCount  + wasThereACombination);
								}
							}
						}	
					}
					
					
					loopCount = 0;
					needToPopulate = true;
				}
				
			}
		}
	  
	 //printArray();
	}
	
	
	@Override
	public void moveDown() {
	
		for(int col = 0; col < length; col++) 
		{
			int moveTo = -1;
			for(int row = height-1; row > 0; row--) 
			{
				if(gameArray[row][col].getValue() == 0) 
				{
					moveTo = row;
					break;
				}
			}
			if(moveTo != -1)
			{
				for(int moveFrom = moveTo - 1; moveFrom >= 0; moveFrom--) 
				{
					
					if(gameArray[moveFrom][col].getValue() != 0) 
					{
						if(gameArray[moveFrom][col].isNewCombination())
						{
							gameArray[moveTo][col].setNewCombination(true);
							gameArray[moveFrom][col].setNewCombination(false);
							
						}
						gameArray[moveTo][col].setBlockValue(gameArray[moveFrom][col].getValue());
						gameArray[moveFrom][col].setBlockValue(0);
						moveTo --;
					}
				}
			}
		}
		printArray();
	}

	public void dropBlock()
	{
		int column = lookForColumn();
		if(column != -1)
		{
			int bottom = lookForBottom(column);
		
	
		for(int row = 0; row < height; row++) 
		{
			for(int col = 0; col < length; col++)
			{
				if(gameArray[row][col].getLockedIn()==false)
				{
					gameArray[bottom][col].setBlockValue(gameArray[row][col].getValue());
					gameArray[bottom][col].setLockedIn(false);
					gameArray[row][col].setBlockValue(0);
					fall();
				}
			}
		}
			needToPopulate = true;
		}
	}
	public int lookForColumn()
	{
		int columnValue = -1;
		 for(int row = height-1; row >= 0; row--)
			{
				for(int col = 0; col < length; col++)
				{
					if(gameArray[row][col].getLockedIn()==false)
					{
						 columnValue =  gameArray[row][col].getArrayX();

					}
					
				}
				
			}
		 return columnValue;
	}
	
	public int lookForBottom(int column)
	{
		int bottom = 0;
		for(int row = height-1; row > 0; row--)
		{
			if(gameArray[row][column].getValue() == 0)
			{
				bottom = row;
				break;
			}
		}
		return bottom;
	}
	
	@Override
	public boolean isGameOver() {
		boolean gameOver = false;
		if(isFull()==true)
		{
			gameOver = true;
		}
		return gameOver;
	}

public Block2048 lookUp(int row, int col) {
	//System.out.println("looking UP at :" + (i-1) + ", " + col );
		return gameArray[row-1][col];
	}

	@Override
	public Block2048 lookRight(int row, int col) {
		
		//System.out.println("looking RIGHT at :" + i + ", " + (col+1));
		return gameArray[row][col+1];
	}

	@Override
	public Block2048 lookDown(int row, int col) {
		//System.out.println("looking DOWN at :" + (i+1) + ", " + col);
		return gameArray[row+1][col];
	}

	@Override
	public Block2048 lookLeft(int row, int col) {
		//System.out.println("looking LEFT at :" + i + ", " + (col-1));
		return gameArray[row][col-1];
	}

	//Populate generates the block that will fall from the top center. A random number between 0 and 1 is generated and depending on that number, a block with a certain value is populated.
	public void populate() {
		
		double whatNumber = Math.random();
		if(whatNumber < 0.167) 
		{
			gameArray[0][centerColumn].setBlockValue(2);
		}
		else if(whatNumber > 0.167 && whatNumber <0.333)
		{
			gameArray[0][centerColumn].setBlockValue(4);
		}
		else if(whatNumber >= 0.334 && whatNumber < 0.499)
		{
			gameArray[0][centerColumn].setBlockValue(8);
		}
		else if(whatNumber >= 0.499 && whatNumber < 0.664)
		{
			gameArray[0][centerColumn].setBlockValue(16);
		}
		else if(whatNumber >= 0.664 && whatNumber < 0.83)
		{
			gameArray[0][centerColumn].setBlockValue(32);
		}
		else 
		{
			gameArray[0][centerColumn].setBlockValue(64);
		}
		
		gameArray[0][centerColumn].setLockedIn(false);
	}

	@Override
	public boolean isFull() {
		
		boolean full = false;
		if(gameArray[0][centerColumn].getValue()!=0 && gameArray[0][centerColumn].getLockedIn()==true)
		{
			full = true;
		}
		return full;
	}
	
	public boolean getNeedToPopulate()
	{
		return needToPopulate;
	}
	
	public void setNeedToPopulate(boolean setValue)
	{
		needToPopulate = setValue;
	}

	@Override
	public void draw() 
	{
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < length; col++)
			{
				gameArray[row][col].drawBlock();
			}
		}
	}
	
	public boolean combineAround(int row, int col)
	{
		System.out.println(row + ", "+ col);
		boolean didWeCombine = false;
		gameArray[row][col].setNewCombination(false);
		combineTotal= gameArray[row][col].getValue();
		Block2048 myBlock = gameArray[row][col];
		System.out.println(myBlock.getValue());
		
		//If the block is in the top row
		if(row==0) 
		{
			//If block is in the top row, left column
			if(col==0) 
			{
				if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookRight(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookDown(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				gameArray[row][col].setBlockValue(combineTotal);
				

			}
			//If block is in the top row, right column.
			else if(col== length-1)
			{
				if(lookLeft(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookLeft(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookDown(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				gameArray[row][col].setBlockValue(combineTotal);

				
			}
			//If block is in the top row but not in the left or right corner
			else
			{
				if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookRight(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookLeft(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookLeft(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookDown(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				gameArray[row][col].setBlockValue(combineTotal);
				
			}
		}
		// If block is in the bottom row
		else if(row == height-1)
		{
			//If block is in the bottom left corner
			if(col == 0)
			{
				if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookRight(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}

				if(lookUp(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookUp(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				gameArray[row][col].setBlockValue(combineTotal);
				
			}
			//If block is in the bottom row, right column
			else if(col == length -1)
			{
				if(lookLeft(row,col).getValue()== gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookLeft(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookUp(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookUp(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				gameArray[row][col].setBlockValue(combineTotal);
				
			}
			//If block is in the bottom row, but not in the bottom corners
			else
			{
	
				if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookRight(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookLeft(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookLeft(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				if(lookUp(row,col).getValue()== gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
				{
					combineTotal = combineTotal*2;
					lookUp(row,col).setBlockValue(0);
					gameArray[row][col].setNewCombination(true);
					setScore(combineTotal);
				}
				
				gameArray[row][col].setBlockValue(combineTotal);
				
			}
		}
		//If block is in the left column, but not in the top or bottom left corners
		else if(col == 0)
		{
			System.out.println("LeftColumn combine start: " + combineTotal);
			if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookRight(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookUp(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookUp(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookDown(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			
			gameArray[row][col].setBlockValue(combineTotal);
		}
		//If block is in the right column, but not in the top or bottom right corners
		else if(col== length -1)
		{
			if(lookLeft(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				lookLeft(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookUp(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				lookUp(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				lookDown(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			
			gameArray[row][col].setBlockValue(combineTotal);
			
		}
		
		//If block is away from all edges
		else
		{
			System.out.println("Middle Array combine start: " + combineTotal);
			if(lookRight(row,col).getValue() == gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookRight(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookLeft(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookLeft(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookUp(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookUp(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			if(lookDown(row,col).getValue()==gameArray[row][col].getValue() && gameArray[row][col].getValue() != 0)
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookDown(row,col).setBlockValue(0);
				gameArray[row][col].setNewCombination(true);
				setScore(combineTotal);
			}
			
			gameArray[row][col].setBlockValue(combineTotal);

		}
		
		didWeCombine = gameArray[row][col].isNewCombination();
		moveDown();
		System.out.println("Combine Total: " + combineTotal);
		combineTotal = 0;
		return didWeCombine;		
	}
	
	@Override
	public void combineRight() {
		
	}

	@Override
	public void combineLeft() {
		
	}

	@Override
	public void combineUp() {
		
	}

	@Override
	public void combineDown() {
		
		for(int col = 0; col < length; col++) {
			for(int row = height -1; row > 0; row--) {
				if(gameArray[row][col].getValue()==gameArray[row-1][col].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[row][col].setBlockValue(gameArray[row][col].getValue()+gameArray[row-1][col].getValue());
					//addToScore(gameScore,gameArray[row][col].getValue());
					gameArray[row-1][col].setBlockValue(0);
				}
			}
		}
		moveDown();
		//populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}
	
	public boolean combineAll() {
		boolean combine = true;
		
		for(int col = 0; col < length; col++) 
		{
			for(int row = height; row > 0; row--) 
			{
				combine = combineAround(row,col);
				System.out.println("Did we combine: "+ combine);
			}
		}
		//populate();
		//printArray();
		
		return combine;
	}
	
	public void printArray() 
	{
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < length; col++)
			{
				//System.out.print(gameArray[i][j].getLockedIn() + " ");
			}
			//System.out.println("");
		}
		//System.out.println();
		//System.out.println();
	}

    public int getScore()
    {
    	return gameScore;
    }
    
    public void setScore(int addToScore)
    {
    	gameScore += addToScore;
    }
}
