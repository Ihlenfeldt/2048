package game2048;


import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class OriginalGameBoard implements GameBoard 
{
	protected Block2048[][] gameArray;
	int length = 0;
	private int capacity = 0;
	private int numberOfBlocks = 0;
	
	public OriginalGameBoard(JFrame frame, int width, int height) 
	{
		gameArray = new Block2048[height][width];
		length = gameArray.length;
		fillGameBoard(frame, width, height);
		capacity = width*height;
	
		printArray();

	}
	
	private void fillGameBoard(JFrame frame, int width, int height) 
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				gameArray[j][i] = new Block2048(frame, 0);
				gameArray[j][i].setX(j*67);
				gameArray[j][i].setY(i*67);
			}
			
		}
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
			if(gameArray[firstRandom][secondRandom].getValue() == 0) 
			{
				if(whatNumber > 0.5) 
				{
					gameArray[firstRandom][secondRandom].setBlockValue(4);
				}
				else
				{
					gameArray[firstRandom][secondRandom].setBlockValue(2);;
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
				if(gameArray[i][j].getValue() == 0) 
				{
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder -1; k>=0; k--) 
				{
					if(gameArray[i][k].getValue() != 0) 
					{
						gameArray[i][holder] = gameArray[i][k];
						gameArray[i][k].setBlockValue(0);
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
				if(gameArray[i][j].getValue() == 0) 
				{
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder +1; k<length; k++) 
				{
					if(gameArray[i][k].getValue() != 0) 
					{
						gameArray[i][holder] = gameArray[i][k];
						gameArray[i][k].setBlockValue(0);
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
				if(gameArray[j][i].getValue() == 0) {
					holder = j;
					break;
				}
			}
			if(holder !=-1){
				for(int k = holder +1; k < length; k++) 
				{
					if(gameArray[k][i].getValue() != 0) 
					{
						gameArray[holder][i] = gameArray[k][i];
						gameArray[k][i].setBlockValue(0);
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
						gameArray[holder][i] = gameArray[k][i];
						gameArray[k][i].setBlockValue(0);
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
				System.out.print(gameArray[i][j].getValue() + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public Block2048 lookUp(int i, int j) {
		
		return gameArray[i-1][j];
	}

	@Override
	public Block2048 lookRight(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i][j+1];
	}

	@Override
	public Block2048 lookDown(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i+1][j];
	}

	@Override
	public Block2048 lookLeft(int i, int j) {
		// TODO Auto-generated method stub
		return gameArray[i][j-1];
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
}
