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
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				gameArray[i][j] = new Block2048(frame, 0, true);
				gameArray[i][j].setX(j*67);
				gameArray[i][j].setY(i*67);
			}
		}
		populate();
	}
	
	@Override
	public void moveRight() {
		for(int i = height-1; i >= 0; i--)
		{
			for(int j = length-1; j >=0; j--)
			{
				//Identifies the falling block
				if(gameArray[i][j].getLockedIn() == false)
				{
					//prevents out of bounds exception or falling block moving into already occupied space. 
					if(j != length-1 &&(gameArray[i][j+1].getValue()==0))
					{
						gameArray[i][j+1].setBlockValue(gameArray[i][j].getValue());
						gameArray[i][j+1].setLockedIn(false);
						gameArray[i][j].setBlockValue(0);
					}
				}
				
			}
		}
		
	}

	@Override
	public void moveLeft() {
		for(int i = height-1; i >= 0; i--)
		{
			for(int j = 0; j < length; j++)
			{
				//Identifies the falling block
				if(gameArray[i][j].getLockedIn() == false)
				{
					//prevents out of bounds exception or falling block moving into already occupied space. 
					if(j != 0 &&(gameArray[i][j-1].getValue()==0))
					{
						gameArray[i][j-1].setBlockValue(gameArray[i][j].getValue());
						gameArray[i][j-1].setLockedIn(false);
						gameArray[i][j].setBlockValue(0);
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
	 for(int i = height-1; i >= 0; i--)
		{
			for(int j = 0; j < length; j++)
			{
				//If falling block is not on the bottom row
				if(gameArray[i][j].getLockedIn() == false && i != height-1)
				{
					//if the space below the falling block is open, then block falls.
					if(lookDown(i,j).getValue()==0)
					{
						gameArray[i+1][j].setBlockValue(gameArray[i][j].getValue());
						gameArray[i+1][j].setLockedIn(false);
						gameArray[i][j].setBlockValue(0);
					}
					//if there is a block beneath falling block then lock the falling block in place and look for combinations around it. 
					else
					{
						gameArray[i][j].setLockedIn(true);
						//look for combinations around the block 
						wasThereACombination = combineAround(i,j);
						
						//while loop continues to make combinations until there are no more on the board.
						while(wasThereACombination)
						{
							loopCount ++;
							System.out.println("Second Pass At Combine Around *******************");
							for(int k = height-1; k >= 0; k--)
							{
								for(int l = 0; l < length; l++)
								{
									if(gameArray[k][l].isNewCombination()) {
										System.out.println("Block " + k + ", " + l + " is a new combination");
										wasThereACombination = combineAround(k,l);
										System.out.println("Was there a combine on pass " + loopCount  + wasThereACombination);
									}
								}
							}	
						}

						needToPopulate = true;
					}
				
				}
				if(gameArray[i][j].getLockedIn() == false && i == height-1)
				{
					gameArray[i][j].setLockedIn(true);
					wasThereACombination = combineAround(i,j);
					
					while(wasThereACombination)
					{
						System.out.println("Second Pass At Combine Around *******************");
						for(int k = height-1; k >= 0; k--)
						{
							for(int l = 0; l < length; l++)
							{
								if(gameArray[k][l].isNewCombination()) {
									System.out.println("Block " + k + ", " + l + " is a new combination");
									wasThereACombination = combineAround(k,l);
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
	}
	
	
	@Override
	public void moveDown() {
		printArray();
		System.out.println();
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = height-1; j > 0; j--) 
			{
				if(gameArray[j][i].getValue() == 0) 
				{
					holder = j;
					break;
				}
			}
			if(holder != -1)
			{
				for(int k = holder - 1; k >= 0; k--) 
				{
					
					if(gameArray[k][i].getValue() != 0) 
					{
						if(gameArray[k][i].isNewCombination())
						{
							gameArray[holder][i].setNewCombination(true);
							gameArray[k][i].setNewCombination(false);
							
						}
						gameArray[holder][i].setBlockValue(gameArray[k][i].getValue());
						gameArray[k][i].setBlockValue(0);
						holder --;
					}
				}
			}
		}
		printArray();
	}

	public void dropBlock()
	{
		boolean wereThereCombinations = false;
		moveDown();
		
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = height-1; j > 0; j--) 
			{
				if(gameArray[j][i].isNewCombination()) 
				{
					wereThereCombinations = combineAround(i,j);
					
					while(wereThereCombinations)
					{
						System.out.println("Second Pass At Combine Around *******************");
						for(int k = height-1; k >= 0; k--)
						{
							for(int l = 0; l < length; l++)
							{
								if(gameArray[k][l].isNewCombination()) {
									System.out.println("Block " + k + ", " + l + " is a new combination");
									wereThereCombinations = combineAround(k,l);
								}
							}
						}	
					}
				}
			}
		}
		needToPopulate = true;
		
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

public Block2048 lookUp(int i, int j) {
	System.out.println("looking UP at :" + (i-1) + ", " + j );
		return gameArray[i-1][j];
	}

	@Override
	public Block2048 lookRight(int i, int j) {
		
		System.out.println("looking RIGHT at :" + i + ", " + (j+1) );
		return gameArray[i][j+1];
	}

	@Override
	public Block2048 lookDown(int i, int j) {
		System.out.println("looking DOWN at :" + (i+1) + ", " + j );
		return gameArray[i+1][j];
	}

	@Override
	public Block2048 lookLeft(int i, int j) {
		System.out.println("looking LEFT at :" + i + ", " + (j-1) );
		return gameArray[i][j-1];
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
		//System.out.println("Height: " + height);
		//System.out.println("Length: " + length);
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				gameArray[i][j].drawBlock();
			}
		}
	}
	
	public boolean combineAround(int i, int j)
	{
		System.out.println(i + ", "+ j);
		boolean didWeCombine = false;
		gameArray[i][j].setNewCombination(false);
		combineTotal= gameArray[i][j].getValue();
		Block2048 myBlock = gameArray[i][j];
		System.out.println(myBlock.getValue());
		//If the block is in the top row
		if(i==0) 
		{
			//If block is in the top row, left column
			if(j==0) 
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookRight(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookDown(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				gameArray[i][j].setBlockValue(combineTotal);
				

			}
			//If block is in the top row, right column.
			else if(j== length-1)
			{
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookLeft(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookDown(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				gameArray[i][j].setBlockValue(combineTotal);
				
				
			}
			//If block is in the top row but not in the left or right corner
			else
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookRight(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookLeft(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookDown(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				gameArray[i][j].setBlockValue(combineTotal);
				
				
			}
		}
		// If block is in the bottom row
		else if(i == height-1)
		{
			//If block is in the bottom left corner
			if(j == 0)
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookRight(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}

				if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookUp(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				gameArray[i][j].setBlockValue(combineTotal);
				
				
			}
			//If block is in the bottom row, right column
			else if(j == length -1)
			{
				if(lookLeft(i,j).getValue()== gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookLeft(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookUp(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				gameArray[i][j].setBlockValue(combineTotal);
				
				
			}
			//If block is in the bottom row, but not in the bottom corners
			else
			{
	
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookRight(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookLeft(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				if(lookUp(i,j).getValue()== gameArray[i][j].getValue())
				{
					combineTotal = combineTotal*2;
					lookUp(i,j).setBlockValue(0);
					gameArray[i][j].setNewCombination(true);
				}
				
				gameArray[i][j].setBlockValue(combineTotal);
				
				
			}
		}
		//If block is in the left column, but not in the top or bottom left corners
		else if(j == 0)
		{
			System.out.println("LeftColumn combine start: " + combineTotal);
			if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookRight(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookUp(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Left Column combine: " +combineTotal);
				lookDown(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			
			gameArray[i][j].setBlockValue(combineTotal);
			
		}
		//If block is in the right column, but not in the top or bottom right corners
		else if(j== length -1)
		{
			if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				lookLeft(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				lookUp(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				lookDown(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			
			gameArray[i][j].setBlockValue(combineTotal);

			
		}
		
		//If block is away from all edges
		else
		{
			System.out.println("Middle Array combine start: " + combineTotal);
			if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookRight(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookLeft(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookUp(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal = combineTotal*2;
				System.out.println("Middle Array combine: " + combineTotal);
				lookDown(i,j).setBlockValue(0);
				gameArray[i][j].setNewCombination(true);
			}
			
			gameArray[i][j].setBlockValue(combineTotal);
	
			
		}
		
		didWeCombine = gameArray[i][j].isNewCombination();
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
		boolean combine = false;
		for(int i = 0; i < length; i++) {
			for(int j = height -1; j > 0; j--) {
				if(gameArray[j][i].getValue()==gameArray[j-1][i].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[j][i].setBlockValue(gameArray[j][i].getValue()+gameArray[j-1][i].getValue());
					//addToScore(gameScore,gameArray[j][i].getValue());
					gameArray[j-1][i].setBlockValue(0);
				}
			}
		}
		moveDown();
		//populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}
	
	public boolean combineAll() {
		boolean combine = false;
		for(int i = 0; i < length; i++) {
			for(int j = height -1; j > 0; j--) {
				combineAround(j,i);
			}
		}
		moveDown();
		//populate();
		printArray();
		System.out.println("Score is: " + gameScore);
		return combine;
	}
	
	public void printArray() 
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				//System.out.print(gameArray[i][j].isNewCombination() + " ");
			}
			//System.out.println("");
		}
	}



}
