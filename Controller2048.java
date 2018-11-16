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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Controller2048 extends TimerTask implements MouseListener, KeyListener{


	public static final int NUMBER_OF_STARTING_BLOCKS = 2;
	public static final int ARRAY_WIDTH = 8;
	public static final int ARRAY_HEIGHT = ARRAY_WIDTH;
	public static final int FRAME_WIDTH = 68*ARRAY_WIDTH;
	public static final int FRAME_HEIGHT = 68*ARRAY_HEIGHT;
	
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

	
	public static final int TIME_BETWEEN_MOVES = 700;//Time = 0.7 seconds
	public static final int MAX_TIME_TO_MOVE = 6000;//Time = 6 seconds.
	
	public static long currentTime = System.currentTimeMillis();//This will be used to track max time between moves
	
	
	//Keeps track of game type Original = 0, Russian = 1
	public static int gameType = 0;
	
	public static JFrame gameFrame2048;
	private Container contentPane2048;
	private java.util.Timer universalGameTimer = new java.util.Timer();
	
	
	public static int score = 0;
	
	public Controller2048(String JFrameTitle, int locationX, int locationY, int windowWidth, int windowHeight) {
		gameFrame2048 = new JFrame(JFrameTitle);
		gameFrame2048.setSize(windowWidth, windowHeight);
		
		
		
		//Get screen size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Set gameFrame2048 to the middle of the screen
		gameFrame2048.setLocation(dim.width/2-windowWidth/2, dim.height/2-windowHeight/2);
		gameFrame2048.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame2048.setResizable(false);
		
		//Create content pane to which the game is played
		contentPane2048 = gameFrame2048.getContentPane();
		contentPane2048.setLayout(null);
		contentPane2048.setBackground(Color.GRAY);

		menu = new MainMenu();
		menuFrame = menu.getFrame();

		contentPane2048.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		gameFrame2048.setSize(contentPane2048.getWidth(), contentPane2048.getHeight()+24);

		
		
		gameFrame2048.getRootPane().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		//Create content pane for main menu
		
		
		gameFrame2048.setVisible(false);
		menuFrame.setVisible(true);
		
		
		universalGameTimer.schedule(this,0,TIME_BETWEEN_MOVES);
		
		contentPane2048.addMouseListener(this);
		contentPane2048.addKeyListener(this);
		contentPane2048.setFocusable(true);
		
	}
	
	
	public static void newGame() 
	{
		menuFrame.setVisible(false);
		
		if(gameType == 0)
		{
			myGame = new OriginalGameBoard(gameFrame2048, ARRAY_WIDTH, ARRAY_HEIGHT);
		}
		else
		{
			myGame = new RussianGameBoard(ARRAY_WIDTH, ARRAY_HEIGHT);
		}
		
		finished = false;
		gameIsReady = true;
		time = 0;
		gameFrame2048.setVisible(true);
		myGame.draw(FRAME_WIDTH,FRAME_HEIGHT);
		System.out.println("drawing in new game");
	}
	
	
	@Override
	public void run() 
	{
		if(gameType == 0)
		{
			
			if(finished)
			{
				gameIsReady = false;
				
			}
			else if (gameIsReady)
			{
				time++;
				
				
				if(gameType == 0 )
				{
					if(myGame.isFull())
					{
						finished = true;
					}
					else
					{
						time = 0;
						//myGame.populate(ARRAY_WIDTH, ARRAY_HEIGHT);
						myGame.draw(FRAME_WIDTH,FRAME_HEIGHT);
						//System.out.println("drawing");
					}
				}
			
			}
			else //Game is not finished, but also not ready, so regenerate
			{
				//newGame();
			}
		}
		else //Game Type is 1 (Russian)
		{
			
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
		// TODO Auto-generated method stub
		int keyPressed_Code = e.getKeyCode();
		if(gameType == 0) {
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
		else
		{
			switch(keyPressed_Code) 
			{
				case UP_ARROW: System.out.println("Up Arrow");
				break;
				
				case DOWN_ARROW: System.out.println("Down Arrow");
				break;
				
				case RIGHT_ARROW:
				break;
				
				case LEFT_ARROW:
				break;
				
				case NUMPAD_6:
				break;
				
				case NUMPAD_8:
				break;
				
				case NUMPAD_4:
				break;
				
				case NUMPAD_2:
				break;
				
				case KEY_W:
				break;
				
				case KEY_D:
				break;
				
				case KEY_S:
				break;
				
				case KEY_A:
				break; 
				
				case KEY_SPACE:
				break;
				
				case KEY_ESC:
				break;
			}
		}
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
		// TODO Auto-generated method stub
		Controller2048 myController = new Controller2048("Hello", 50,50, FRAME_WIDTH, FRAME_HEIGHT);
		
		
	}
	
	public static void gameover()
	{
		
	}

}
