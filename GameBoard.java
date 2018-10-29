package game2048;

import java.awt.Graphics;

public interface GameBoard {

public void combine();
public boolean isGameOver();
public void moveRight();
public void moveLeft();
public void moveUp();
public void moveDown();
public Block2048 lookUp(int i, int j);
public Block2048 lookRight(int i, int j);
public Block2048 lookDown(int i, int j);
public Block2048 lookLeft(int i, int j);
public void populate(int width, int height);
public boolean isFull();
public void draw(int height, int width);




}
