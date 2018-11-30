package game2048;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Controller2048 extends TimerTask implements MouseListener, KeyListener{

	public static final int NUMBER_OF_STARTING_BLOCKS = 2;

	public static int ARRAY_WIDTH = 7;
	public static int ARRAY_HEIGHT = ARRAY_WIDTH;
	public static final int RUSSIAN_ARRAY_HEIGHT = ARRAY_WIDTH +2;
	public static int FRAME_WIDTH = 67*ARRAY_WIDTH;
	public static int FRAME_HEIGHT = 67*ARRAY_HEIGHT + 60;
	public static final int RUSSIAN_FRAME_HEIGHT = FRAME_HEIGHT+132;

	//Key_Codes for keyListener below
	public static final int UP_ARROW = 38;
	public static final int DOWN_ARROW = 40;
	public static final int RIGHT_ARROW = 39;
	public static final int LEFT_ARROW = 37;
	public static final int NUMPAD_6 = 102;
	public static final int NUMPAD_5 = 0;
	public static final int NUMPAD_4 = 100;
	public static final int NUMPAD_2 = 98;
	public static final int NUMPAD_8 = 104;
	public static final int KEY_W = 87;
	public static final int KEY_D = 68;
	public static final int KEY_S = 83;
	public static final int KEY_A = 65;
	public static final int KEY_SPACE = 32;
	public static final int KEY_ESC = 27;
	
	
	//These are tracking variables used in the game.
	public static boolean gameIsReady;
	public static int time;
	static GameBoard myGame; 
	public static boolean finished;
	public static Graphics g;
	public boolean inMainMenu;
	public MainMenu menu;
	public static JFrame menuFrame;
	public static Dimension dim;
	public static JLabel bar;
	public static int barHeight;

	public static final int TIME_BETWEEN_MOVES = 700;//Time = 0.7 seconds
	public static final int MAX_TIME_TO_MOVE = 6000;//Time = 6 seconds.
	
	public static long currentTime = System.currentTimeMillis();//This will be used to track max time between moves
	
	//Keeps track of game type Original = 0, Russian = 1
	public static final int RUSSIAN_GAME = 1;
	public static final int ORIGINAL_GAME = 0;
	
	public static int gameType = 1;
	
	public static JFrame gameFrame2048;
	private static Container contentPane2048;
	private java.util.Timer universalGameTimer = new java.util.Timer();
	public static int score = 0;
	
	public Controller2048(String JFrameTitle, int locationX, int locationY, int windowWidth, int windowHeight) {
	
		menu = new MainMenu();
		menuFrame = menu.getFrame();
		menuFrame.setVisible(true);
		
		gameFrame2048 = new JFrame(JFrameTitle);
		
		gameFrame2048.setVisible(false);
		
		//Get screen size
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Set gameFrame2048 to the middle of the screen
		gameFrame2048.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame2048.setResizable(false);
		
		//Create content pane to which the game is played
		contentPane2048 = gameFrame2048.getContentPane();
		contentPane2048.setLayout(null);
		contentPane2048.setBackground(Color.GRAY);
		universalGameTimer.schedule(this,0,TIME_BETWEEN_MOVES);
		
		contentPane2048.addMouseListener(this);
		contentPane2048.addKeyListener(this);
		contentPane2048.setFocusable(true);
		((JComponent) contentPane2048).setOpaque(true);
		
		bar = new JLabel();
		contentPane2048.add(bar);
		bar.setBackground(Color.BLUE);
		bar.setOpaque(true);
		bar.setVisible(true);
		bar.setForeground(Color.WHITE);
		bar.setFont(bar.getFont().deriveFont(32.0f));
		barHeight = 60;
		
	}
	
	
	public static void newGame() 
	{
		menuFrame.setVisible(false);
		
		
		//Now to reset sizing variables
		FRAME_WIDTH = 67*ARRAY_WIDTH;
		FRAME_HEIGHT = 67*ARRAY_HEIGHT + barHeight + 22;
		
		gameFrame2048.getContentPane().setSize(FRAME_WIDTH, FRAME_HEIGHT - barHeight - 22);
		gameFrame2048.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		gameFrame2048.setLocation(dim.width/2-FRAME_WIDTH/2, dim.height/2-FRAME_HEIGHT/2);
		bar.setSize(FRAME_WIDTH, barHeight);
		bar.setLocation(0,0);
		
		//Create the new game
		if(gameType == 0)
		{
			if(myGame != null)
			{
				myGame = null;
			}
			gameFrame2048.getContentPane().removeAll();
			 myGame = new OriginalGameBoard(gameFrame2048, ARRAY_WIDTH, ARRAY_HEIGHT);
		}
		else
		{
			myGame = new RussianGameBoard(gameFrame2048,ARRAY_WIDTH, ARRAY_HEIGHT);
		}
		
		gameFrame2048.getContentPane().add(bar);   
		finished = false;
		gameIsReady = true;
		time = 0;
		gameFrame2048.setVisible(true);
		myGame.draw();
		score = 0;
	}
	
	
	@Override
	public void run() 
	{
		System.out.println("Running");
		if(gameType == 0)
		{
			if(finished && gameIsReady)
			{
				gameIsReady = false;
				System.out.println("Game Over");
				gameOver();
			}
			else if(gameIsReady)
			{
				myGame.draw();
				System.out.println("Drawing");
				time++;
				score = myGame.getScore();
				bar.setText("Score: " + score);
				if(myGame.isGameOver())
				{
					finished = true;
				}
				else if(time >= 5000)
				{
					time = 0;
					myGame.populate();
					
				}
			}
			
		}
		else //Game Type is 1 (Russian)
		{
			if(finished)
			{
				gameIsReady = false;
				System.out.println("Game Over"); 
				gameOver();
				
				
			}
			else if (gameIsReady)
			{
				time++;
				score = myGame.getScore();
				bar.setText("Score: " + score);
				
				if(gameType == 1)
				{
					if(myGame.isGameOver())
					{
						finished = true;
					}
					else
					{
						if(myGame.getNeedToPopulate()== true)
						{
							myGame.populate();
							myGame.setNeedToPopulate(false);
						}
						else
						{
							myGame.fall();
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(myGame.isFull())
		{
			finished = true;
		}
		// TODO Auto-generated method stub
		int keyPressed_Code = e.getKeyCode();
		if(gameType == ORIGINAL_GAME && !finished && gameIsReady)
		{
			switch(keyPressed_Code) {
			case UP_ARROW: 
				myGame.moveUp();
				myGame.combineUp();
			break;
			
			case DOWN_ARROW: 
				myGame.moveDown();
				myGame.combineDown();
			break;
			
			case RIGHT_ARROW:
				myGame.moveRight();
				myGame.combineRight();
			break;
			
			case LEFT_ARROW:
				myGame.moveLeft();
				myGame.combineLeft();
			break;
			
			case NUMPAD_6:
				myGame.moveRight();
				myGame.combineRight();
			break;
			
			case NUMPAD_8:
				myGame.moveUp();
				myGame.combineUp();
			break;
			
			case NUMPAD_4:
				myGame.moveLeft();
				myGame.combineLeft();
			break;
			
			case NUMPAD_2:
				myGame.moveDown();
				myGame.combineDown();
			break;
			
			case KEY_W:
				myGame.moveUp();
				myGame.combineUp();
			break;
			
			case KEY_D:
				myGame.moveRight();
				myGame.combineRight();
			break;
			
			case KEY_S:
				myGame.moveDown();
				myGame.combineDown();
			break;
			
			case KEY_A:
				myGame.moveLeft();
				myGame.combineLeft();
			break; 
			
			case KEY_SPACE:
			break;
			
			case KEY_ESC:
			break;
			}
		}
		else if(!finished && gameIsReady)
		{
			switch(keyPressed_Code) {
			case UP_ARROW: 
				myGame.moveUp();
			break;
			
			case DOWN_ARROW: 
				((RussianGameBoard) myGame).dropBlock();
			break;
			
			case RIGHT_ARROW:
				myGame.moveRight();
			break;
			
			case LEFT_ARROW:
				myGame.moveLeft();
			break;
			
			case NUMPAD_6:
				myGame.moveRight();
			break;
			
			case NUMPAD_8:
				myGame.moveUp();
			break;
			
			case NUMPAD_4:
				myGame.moveLeft();
			break;
			
			case NUMPAD_2:
				myGame.moveDown();
			break;
			
			case KEY_W:
				myGame.moveUp();
			break;
			
			case KEY_D:
				myGame.moveRight();
			break;
			
			case KEY_S:
				myGame.moveDown();
			break;
			
			case KEY_A:
				myGame.moveLeft();
			break; 
			
			case KEY_SPACE:
			break;
			
			case KEY_ESC:
			break;
			}
		}
		myGame.draw();	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) 
	{
		Controller2048 myController = new Controller2048("2048", 50,50, FRAME_WIDTH, FRAME_HEIGHT);	
	}
	
	public static void gameOver()
	{
		String[] buttons = {"Go to Main Menu", "Play Again!"};

		System.out.println("Prompt");
	    int playOrMain = JOptionPane.showOptionDialog(null, "Your final score was " + score + "!\nWould you like to play again or return to the main menu?", 
	    		"Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
	    System.out.println("Prompt2");
	    if(playOrMain == 1)
	    {
	    	System.out.println("New Game");
	    	newGame();
	    }
	    else
	    {
	    	System.out.println("Main Menu");
	    	gameFrame2048.setVisible(false);
	    	menuFrame.setVisible(true);
	    }
	}
}
