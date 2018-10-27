package game2048;

import java.util.Random;

public class OriginalGameBoard implements GameBoard 
{
	protected int[][] gameArray;
	int length = 0;
	private int capacity = 0;
	private int numberOfBlocks = 0;
	
	public OriginalGameBoard(int width, int height) 
	{
		gameArray = new int[height][width];
		length = gameArray.length;
		fillGameBoard(width, height);
		capacity = width*height;
		printArray();

	}
	
	private void fillGameBoard(int width, int height) 
	{
		populate(width,height);
		populate(width,height);
	}
	
	public void populate(int width, int height) 
	{
		//this double will be used to determine if a 2 or a 4 is printed
		double whatNumber = Math.random();
		boolean stillPicking = true;
		Random randomInt = new Random();
		while(stillPicking) {
			int firstRandom = randomInt.nextInt(height);
			int secondRandom = randomInt.nextInt(width);
			if(gameArray[firstRandom][secondRandom] == 0) 
			{
				if(whatNumber > 0.5) 
				{
					gameArray[firstRandom][secondRandom] = 4;
				}
				else
				{
					gameArray[firstRandom][secondRandom] = 2;
				}
				
				stillPicking = false;
			}
		}
	}
	public int getCapacity(){
		return this.capacity;
	}
	
	public boolean isFull()
	{
		boolean answer = true;
		if(this.capacity == this.numberOfBlocks)
		{
			answer = false;
		}
		return answer;
	}
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}
	
	public int getNumberOfBlocks() {
		return this.numberOfBlocks;
	}
	
	//Increment the number of blocks variable by 1.
	public void addBlock() {
		this.numberOfBlocks++;
	}
	
	@Override
	public void combine() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isGameOver() 
	{
		boolean gameOver = false;
		if(isFull())
		{
			if(isThereValidMove()== false) {
				gameOver = true;
			}
		}
		return gameOver;
	}

	@Override
	public void moveRight() 
	{
		length = gameArray.length;
		int height = gameArray[0].length;
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = length-1; j > 0; j--) 
			{
				if(gameArray[i][j]==0) 
				{
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder -1; k>=0; k--) 
				{
					if(gameArray[i][k] != 0) 
					{
						gameArray[i][holder] = gameArray[i][k];
						gameArray[i][k] = 0;
						holder --;
					}
				}
			}
		}
		populate(length,height);
		printArray();
		
	}

	@Override
	public void moveLeft() 
	{
		length = gameArray.length;
		int height = gameArray[0].length;
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = 0; j < length; j++) 
			{
				if(gameArray[i][j]==0) 
				{
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder +1; k<length; k++) 
				{
					if(gameArray[i][k] != 0) 
					{
						gameArray[i][holder] = gameArray[i][k];
						gameArray[i][k] = 0;
						holder ++;
					}
				}
			}
		}
		populate(length,height);
		printArray();
		
	}

	@Override
	public void moveUp() 
	{
		length = gameArray.length;
		int height = gameArray[0].length;
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = 0; j < length-1; j++) 
			{
				if(gameArray[j][i]==0) {
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder +1; k < length; k++) 
				{
					if(gameArray[k][i] != 0) 
					{
						gameArray[holder][i] = gameArray[k][i];
						gameArray[k][i] = 0;
						holder ++;
					}
				}
			}
		}
		populate(length,height);
		printArray();
	
	}

	@Override
	public void moveDown() 
	{
		length = gameArray.length;
		int height = gameArray[0].length;
		for(int i = 0; i < length; i++) 
		{
			int holder = -1;
			for(int j = length-1; j > 0; j--) 
			{
				if(gameArray[j][i]==0) 
				{
					holder = j;
					break;
				}
			}
			if(holder !=-1)
			{
				for(int k = holder -1; k>=0; k--) 
				{
					if(gameArray[k][i] != 0) 
					{
						gameArray[holder][i] = gameArray[k][i];
						gameArray[k][i] = 0;
						holder --;
					}
				}
			}
		}
		populate(length,height);
		printArray();
		
	}
	
	public boolean isThereValidMove()
	{
		return false;
	}
	
	public void printArray() 
	{
		
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				System.out.print(gameArray[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	@Override
	public int lookUp(int i, int j) {
		
		return gameArray[i-1][j];
	}

	@Override
	public int lookRight(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i][j+1];
	}

	@Override
	public int lookDown(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i+1][j];
	}

	@Override
	public int lookLeft(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i][j-1];
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
