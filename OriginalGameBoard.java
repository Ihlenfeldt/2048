package game2048;


import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class OriginalGameBoard implements GameBoard 
{
	protected Block2048[][] gameArray;
	int length = 0;
	int height = 0;
	private int capacity = 0;
	private int numberOfBlocks = 0;
	public int gameScore = 0;

	public OriginalGameBoard(JFrame frame, int passedLength, int passedHeight)
	{
		gameArray = new Block2048[passedHeight][passedLength];
		length = passedLength;
		height = passedHeight;
		fillGameBoard(frame);
		capacity = length*height;
		printArray();

	}
	

	private void fillGameBoard(JFrame frame) 
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < length; j++)
			{
				gameArray[i][j] = new Block2048(frame, 0,true);
				gameArray[i][j].setX(j*67);
				gameArray[i][j].setY(i*67);
			}
			
		}
		
		populate();
		populate();
	}
	
	public void populate() 
	{
		//this double will be used to determine if a 2 or a 4 is printed
		double whatNumber = Math.random();
		boolean stillPicking = true;
		Random randomInt = new Random();
		while(stillPicking) {
			int firstRandom = randomInt.nextInt(height);
			int secondRandom = randomInt.nextInt(length);
			if(gameArray[firstRandom][secondRandom].getValue() == 0) 
			{
				if(whatNumber > 0.5) 
				{
					gameArray[firstRandom][secondRandom].setBlockValue(2);
				}
				else
				{
					gameArray[firstRandom][secondRandom].setBlockValue(4);;
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
		
		for(int i = 0; i < length; i++) 
		{
			for(int j = 0; j < length; j++)
			{
				if(gameArray[i][j].getValue()== 0)
				{
					answer = false;
					break;
				}
			}
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
						gameArray[i][holder].setBlockValue(gameArray[i][k].getValue());
						gameArray[i][k].setBlockValue(0);
						holder --;
					}
				}
			}
		}
		
		
		
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
						gameArray[i][holder].setBlockValue(gameArray[i][k].getValue());
						gameArray[i][k].setBlockValue(0);
						holder ++;
					}
				}
			}
		}
		
		
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
						gameArray[holder][i].setBlockValue(gameArray[k][i].getValue());
						gameArray[k][i].setBlockValue(0);
						holder ++;
					}
				}
			}
		}
			
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
						gameArray[holder][i].setBlockValue(gameArray[k][i].getValue());
						gameArray[k][i].setBlockValue(0);
						holder --;
					}
				}
			}
		}
		
		
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
				//System.out.print(gameArray[i][j].getValue() + " ");
			}
			//System.out.println("");
		}
	}

	@Override
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
		int length = gameArray.length;
		for(int i = 0; i < length; i++) {
			for(int j = length-1; j >0; j--) {
				if(gameArray[i][j].getValue()==gameArray[i][j-1].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[i][j].setBlockValue(gameArray[i][j].getValue()+gameArray[i][j-1].getValue());
					addToScore(gameScore,gameArray[i][j].getValue());
					gameArray[i][j-1].setBlockValue(0);
				}
			}
		}
		moveRight();
		populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}

	@Override
	public void combineLeft() {
		int length = gameArray.length;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j <length-1; j++) {
				if(gameArray[i][j].getValue()==gameArray[i][j+1].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[i][j].setBlockValue(gameArray[i][j].getValue()+gameArray[i][j+1].getValue());
					addToScore(gameScore,gameArray[i][j].getValue());
					gameArray[i][j+1].setBlockValue(0);
				}
			}
		}
		moveLeft();
		populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}

	@Override
	public void combineUp() {
		int length = gameArray.length;
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length-1; j++) {
				if(gameArray[j][i].getValue()==gameArray[j+1][i].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[j][i].setBlockValue(gameArray[j][i].getValue()+gameArray[j+1][i].getValue());
					addToScore(gameScore,gameArray[i][j].getValue());
					gameArray[j+1][i].setBlockValue(0);
				}
			}
		}
		moveUp();
		populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}

	@Override
	public void combineDown() {
		int length = gameArray.length;
		for(int i = 0; i < length; i++) {
			for(int j = length -1; j > 0; j--) {
				if(gameArray[j][i].getValue()==gameArray[j-1][i].getValue()) {
					//sum the two block values and assign to the block on the right
					gameArray[j][i].setBlockValue(gameArray[j][i].getValue()+gameArray[j-1][i].getValue());
					addToScore(gameScore,gameArray[i][j].getValue());
					gameArray[j-1][i].setBlockValue(0);
				}
			}
		}
		moveDown();
		populate();
		printArray();
		System.out.println("Score is: " + gameScore);
	}
	// This function takes in the current score and the value to add to the score as integers and adds them togehter.  
	public void addToScore(int currentScore, int addToScore) 
	{
		gameScore = gameScore + addToScore; 
	}
	
	public int getScore()
	{
		return gameScore;
	}


	@Override
	public void fall() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean getNeedToPopulate() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setNeedToPopulate(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
