package game2048;

public interface GameBoard {

public void combine();
public boolean isGameOver();
public void moveRight();
public void moveLeft();
public void moveUp();
public void moveDown();
public int lookUp();
public int lookRight();
public int lookDown();
public int lookLeft();
public void populate(int width, int height);
public boolean isFull();
public void draw();




}
