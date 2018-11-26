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
				//Looks for the moving block
				if(gameArray[i][j].getLockedIn() == false)
				{
					//prevents out of bounds exception if block is already on the left edge of the gameboard
					if(j != length-1)
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
				//Looks for the moving block
				if(gameArray[i][j].getLockedIn() == false)
				{
					//prevents out of bounds exception if block is already on the left edge of the gameboard
					if(j != 0)
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
		
	 for(int i = height-1; i >= 0; i--)
		{
			for(int j = 0; j < length; j++)
			{
				
				if(gameArray[i][j].getLockedIn() == false && i != height-1)
				{
					if(lookDown(i,j).getValue()==0)
					{
						gameArray[i+1][j].setBlockValue(gameArray[i][j].getValue());
						gameArray[i+1][j].setLockedIn(false);
						gameArray[i][j].setBlockValue(0);
					}
					else
					{
						gameArray[i][j].setLockedIn(true);
							combineAround(i,j);
							moveDown();
							
						needToPopulate = true;
					}
				
				}
				if(gameArray[i][j].getLockedIn() == false && i == height-1)
				{
					gameArray[i][j].setLockedIn(true);
					combineAround(i,j);
					moveDown();
					needToPopulate = true;
				}
				
			}
		}
	}

	
	@Override
	public void moveDown() {
		
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
						gameArray[holder][i].setBlockValue(gameArray[k][i].getValue());
						gameArray[k][i].setBlockValue(0);
						holder --;
					}
				}
			}
		}
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
		
		return gameArray[i-1][j];
	}

	@Override
	public Block2048 lookRight(int i, int j) {

		return gameArray[i][j+1];
	}

	@Override
	public Block2048 lookDown(int i, int j) {
		
		return gameArray[i+1][j];
	}

	@Override
	public Block2048 lookLeft(int i, int j) {
		
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
		System.out.println("Height: " + height);
		System.out.println("Length: " + length);
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
		combineTotal = gameArray[i][j].getValue();
		boolean combined = false;
		if(i==0) 
		{
			if(j==0) 
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal += lookRight(i,j).getValue();
					gameArray[i][j+1].setBlockValue(0);
					combined = true;
				}
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookDown(i,j).getValue();
					gameArray[i+1][j].setBlockValue(0);
					combined = true;
				}

			}
			else if(j== length-1)
			{
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookLeft(i,j).getValue();
					gameArray[i][j-1].setBlockValue(0);
					combined = true;
				}
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookDown(i,j).getValue();
					gameArray[i+1][j].setBlockValue(0);
					combined = true;
				}
			}
			else
			{
				
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal += lookRight(i,j).getValue();
					gameArray[i][j+1].setBlockValue(0);
					combined = true;
				}
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookLeft(i,j).getValue();
					gameArray[i][j-1].setBlockValue(0);
					combined = true;
				}
				
				if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookDown(i,j).getValue();
					gameArray[i+1][j].setBlockValue(0);
					combined = true;
				}
			}
		}
		else if(i == height-1)
		{
			if(j == 0)
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal += lookRight(i,j).getValue();
					gameArray[i][j+1].setBlockValue(0);
					combined = true;
				}

				if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookUp(i,j).getValue();
					gameArray[i-1][j].setBlockValue(0);
					combined = true;
				}
			}
			else if(j == length -1)
			{
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookLeft(i,j).getValue();
					gameArray[i][j-1].setBlockValue(0);
					combined = true;
				}
				if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookUp(i,j).getValue();
					gameArray[i-1][j].setBlockValue(0);
					combined = true;
				}
			}
			else
			{
				if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
				{
					combineTotal += lookRight(i,j).getValue();
					gameArray[i][j+1].setBlockValue(0);
					combined = true;
				}
				if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookLeft(i,j).getValue();
					gameArray[i][j-1].setBlockValue(0);
					combined = true;
				}
				if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
				{
					combineTotal += lookUp(i,j).getValue();
					gameArray[i-1][j].setBlockValue(0);
					combined = true;
				}
			}
		}
		else if(j == 0)
		{
			if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
			{
				combineTotal += lookRight(i,j).getValue();
				gameArray[i][j+1].setBlockValue(0);
				combined = true;
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookUp(i,j).getValue();
				gameArray[i-1][j].setBlockValue(0);
				combined = true;
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookDown(i,j).getValue();
				gameArray[i+1][j].setBlockValue(0);
				combined = true;
			}
		}
		else if(j== length -1)
		{
			if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookLeft(i,j).getValue();
				gameArray[i][j-1].setBlockValue(0);
				combined = true;
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookUp(i,j).getValue();
				gameArray[i-1][j].setBlockValue(0);
				combined = true;
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookDown(i,j).getValue();
				gameArray[i+1][j].setBlockValue(0);
				combined = true;
			}
		}
		else
		{
			if(lookRight(i,j).getValue() == gameArray[i][j].getValue())
			{
				combineTotal += lookRight(i,j).getValue();
				gameArray[i][j+1].setBlockValue(0);
				combined = true;
			}
			if(lookLeft(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookLeft(i,j).getValue();
				gameArray[i][j-1].setBlockValue(0);
				combined = true;
			}
			if(lookUp(i,j).getValue()==gameArray[i][j].getValue())
			{
				combineTotal += lookUp(i,j).getValue();
				gameArray[i-1][j].setBlockValue(0);
				combined = true;
			}
			if(lookDown(i,j).getValue()==gameArray[i][j].getValue())
			{
				System.out.println("Looking Down");
				combineTotal += lookDown(i,j).getValue();
				gameArray[i+1][j].setBlockValue(0);
				combined = true;
			}
		}
		
		gameArray[i][j].setBlockValue(combineTotal);
		combineTotal = 0;
		return combined;
	}
	@Override
	public void combineRight() {
		System.out.println("Combine Right");
		
	}

	@Override
	public void combineLeft() {
		System.out.println("Combine Left");
		
	}
	
	@Override
	public void combineUp() {
		
		System.out.println("Combine Up");
	}

	@Override
	public void combineDown() {
		
		System.out.println("Combine Down");
	}
	
	public void printArray() 
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				System.out.print(gameArray[i][j].getValue() + " ");
			}
			System.out.println("");
		}
	}



}
