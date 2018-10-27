package game2048;

public interface GameBoard {

public void combine();
public boolean isGameOver();
public void moveRight();
public void moveLeft();
public void moveUp();
public void moveDown();
public int lookUp(int i, int j);
public int lookRight(int i, int j);
public int lookDown(int i, int j);
public int lookLeft(int i, int j);
public void populate(int width, int height);
public boolean isFull();
public void draw();




}
