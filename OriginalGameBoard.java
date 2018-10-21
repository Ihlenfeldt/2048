package game2048;

public class OriginalGameBoard implements GameBoard {
	protected Integer[][] gameArray;
	
	public OriginalGameBoard(int width, int height) {
		gameArray = new Integer[height][width];
		fillArray();
		
	}
	private void fillArray() {
		
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
		
	}

}
