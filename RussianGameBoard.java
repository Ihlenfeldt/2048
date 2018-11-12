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
	
	public RussianGameBoard(JFrame frame,int passedLength, int passedHeight) {
		gameArray = new Block2048[passedHeight][passedLength];
		length = passedLength;
		height = passedHeight;
		centerColumn = (passedLength/2);
		fillGameBoard(frame);
		//System.out.println(centerColumn);
		printArray();
		
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
		boolean moving = true;
		
	}

	@Override
	public void moveLeft() {
		
	}

	@Override
	public void moveUp() {
		
	}
	
	public void moveToHolderCell() {
		
	}
	
	public void fall() {
	
	}

	@Override
	public void moveDown() {
	
		
	}

	@Override
	public boolean isGameOver() {
		
		return false;
	}

	@Override
	public Block2048 lookUp(int i, int j) {
		
		return null;
	}

	@Override
	public Block2048 lookRight(int i, int j) {
		
		return null;
	}

	@Override
	public Block2048 lookDown(int i, int j) {
		
		return null;
	}

	@Override
	public Block2048 lookLeft(int i, int j) {
		
		return null;
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
		
		return false;
	}

	@Override
	public void draw( int width, int height) 
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				gameArray[i][j].drawBlock(height/4, width/4);
			}
		}
	}

	@Override
	public void combineRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void combineLeft() {
		
		
	}

	@Override
	public void combineUp() {
		
		
	}

	@Override
	public void combineDown() {
		
		
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
