package game2048;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Block2048 
{
	int blockValue;
	int xCoord;
	int yCoord;
	BufferedImage img;
	BufferedImage num;
	boolean lockedIn;
	boolean newCombination = false;
	int blockWidth;
	int blockHeight;
	int spaceBetween;
    int edgeSpace;
    JLabel blockJLabel;

	public Block2048(int passedValue)
	{
		this.blockValue = passedValue;
		lockedIn = true;
		//Setting an image to the block depending on its value
		
		try 
		{
			//Read in image 
		    img = ImageIO.read(new File("BlockImage.png"));
		    blockWidth = 67;
		    blockHeight = 67;
		    spaceBetween = 8;
		    edgeSpace = 5;
		    
		    blockJLabel = new JLabel();
	        blockJLabel.setBounds (10, 10, 10, 10); // arbitrary, will change later
	        
	        Controller2048.gameFrame2048.getContentPane().add(blockJLabel);
	        blockJLabel.setVisible(false);
	        blockJLabel.setVisible(true);
		    
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
			System.out.println("Image reading error!");
			e.printStackTrace();
		}
	}
	
	public int getValue() 
	{
		return blockValue;
	}
	
	public void setBlockValue(int passedValue) 
	{
		this.blockValue = passedValue;
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
	
	public boolean isNewCombination(){
		return newCombination;
	}
	
	public void setNewCombination(boolean passedNewCombination) {
		this.newCombination = passedNewCombination;
	}
	
	public boolean isMoving() 
	{
		return !lockedIn;
	}
	
	public void drawBlock(int width, int heigth) 
	{
		System.out.println("Num: " + num);
		System.out.println("X: " + xCoord);
		System.out.println("Y: " + yCoord);
		System.out.println("Block Dimension: " + blockWidth);
		
		
		 //blockJLabel.setIcon(num);
		 
		 
		 //g.drawImage(num, xCoord, yCoord, blockWidth, blockHeight, null);
	}
}
