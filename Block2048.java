package game2048;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block2048 
{
	int blockValue;
	int xCoord;
	int yCoord;
	BufferedImage img;
	BufferedImage num;
	boolean lockedIn;

	public Block2048(int passedValue)
	{
		this.blockValue = passedValue;
		lockedIn = true;
		//Setting an image to the block depending on its value
		
		try 
		{
		    img = ImageIO.read(new File("BlockImage.jpg"));
		    int blockWidth = 67;
		    int blockHeight = 67;
		    int spaceBetween = 8;
		    int edgeSpace = 5;
		    switch(blockValue)
		    {
		    case 2:
		    	num = img.getSubimage(edgeSpace + 3*spaceBetween + 3*blockHeight, edgeSpace + 3*spaceBetween + 3*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 4:
		    	num = img.getSubimage(edgeSpace + 3*spaceBetween + 3*blockHeight, edgeSpace + 2*spaceBetween + 2*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 8:
		    	num = img.getSubimage(edgeSpace + 3*spaceBetween + 3*blockHeight, edgeSpace + spaceBetween + blockWidth, blockWidth, blockHeight);
		    	break;
		    case 16:
		    	num = img.getSubimage(edgeSpace + 3*spaceBetween + 3*blockHeight, edgeSpace, blockWidth, blockHeight);
		    	break;
		    case 32:
		    	num = img.getSubimage(edgeSpace + 2*spaceBetween + 2*blockHeight, edgeSpace, blockWidth, blockHeight);
		    	break;
		    case 64:
		    	num = img.getSubimage(edgeSpace + 2*spaceBetween + 2*blockHeight, edgeSpace + spaceBetween + blockWidth, blockWidth, blockHeight);
		    	break;
		    case 128:
		    	num = img.getSubimage(edgeSpace + 2*spaceBetween + 2*blockHeight, edgeSpace + 2*spaceBetween + 2*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 256:
		    	num = img.getSubimage(edgeSpace + 2*spaceBetween + 2*blockHeight, edgeSpace + 3*spaceBetween + 3*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 512:
			    num = img.getSubimage(edgeSpace + spaceBetween + blockHeight, edgeSpace + 3*spaceBetween + 3*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 1024:
		    	num = img.getSubimage(edgeSpace + spaceBetween + blockHeight, edgeSpace + 2*spaceBetween + 2*blockWidth, blockWidth, blockHeight);
		    	break;
		    case 2048:
		    	num = img.getSubimage(edgeSpace + spaceBetween + blockHeight, edgeSpace + spaceBetween + blockWidth, blockWidth, blockHeight);
		    	break;
		    case 4096:
		    	num = img.getSubimage(edgeSpace + spaceBetween + blockHeight, edgeSpace, blockWidth, blockHeight);
		    	break;
		    case 8192:
		    	num = img.getSubimage(edgeSpace, edgeSpace, blockWidth, blockHeight);
		    	break;
		    
	    	
		    
		    
		    
		    
		    
		    
		    
		    }
		    
		} catch (IOException e) 
		{
			
		}
	}
	
	public int getValue() 
	{
		return blockValue;
	}
	
	public void setBlockValue(int passedValue) 
	{
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
	
	public boolean getLockedIn()
	{
		return lockedIn;
	}
	
	public void setLockedIn(boolean isLocked)
	{
		lockedIn = isLocked;
	}
	
	public boolean isMoving() 
	{
		return false;
	}
	
	public void drawBlock() 
	{
		
	}
}
