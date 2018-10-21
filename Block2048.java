package game2048;

public class Block2048 {
	int blockValue;
	int xCoord;
	int yCoord;

	public Block2048(int passedValue){
		this.blockValue = passedValue;
	}
	public int getValue() {
		return blockValue;
	}
	public void setBlockValue(int passedValue) {
		this.blockValue = passedValue;
	}
	public int getX()
	{
		return xCoord;
	}
	public int getY()
	{
		return yCoord;
	}
	
	public void setX(int x)
	{
		xCoord = x;
	}
	public void setY(int y)
	{
		yCoord = y;
	}
	public boolean isMoving() {
		return false;
	}
	public void drawBlock() {
		
	}
}
