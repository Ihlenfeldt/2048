package game2048;

public class Block2048 {
	int blockValue;

	public Block2048(int passedValue){
		this.blockValue = passedValue;
	}
	public int getValue() {
		return blockValue;
	}
	public void setBlockValue(int passedValue) {
		this.blockValue = passedValue;
	}
	
	public boolean isMoving() {
		return false;
	}
	public void drawBlock() {
		
	}
}
