package game2048;

import java.util.Random;

public class OriginalGameBoard implements GameBoard {
	protected int[][] gameArray;
	
	public OriginalGameBoard(int width, int height) {
		gameArray = new int[height][width];
		fillGameBoard(width, height);
		printArray();
		
	}
	private void fillGameBoard(int width, int height) {
		Random randomInt = new Random();
		boolean stillPicking = true;
		
		gameArray[randomInt.nextInt(height)][randomInt.nextInt(width)] = 2;
		while(stillPicking) {
			int firstRandom = randomInt.nextInt(height);
			int secondRandom = randomInt.nextInt(width);
			if(gameArray[firstRandom][secondRandom] == 0) {
				gameArray[firstRandom][secondRandom] = 2;
				stillPicking = false;
			}
			
		}
	}
	
	@Override
	public void combine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub	
	}
	
	public void printArray() {
		
		for(int i = 0; i < gameArray.length; i++)
		{
			for(int j = 0; j < gameArray.length; j++)
			{
				System.out.print(gameArray[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

}
