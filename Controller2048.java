package game2048;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller2048 extends TimerTask implements MouseListener, KeyListener{

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 600;
	public static final int NUMBER_OF_STARTING_BLOCKS = 2;
	public static final int ARRAY_WIDTH = 4;
	public static final int ARRAY_HEIGHT = 4;
	
	public static final int TIME_BETWEEN_MOVES = 700;//Time = 0.7 seconds
	public static final int MAX_TIME_TO_MOVE = 6000;//Time = 6 seconds.
	public static long currentTime = System.currentTimeMillis();//This will be used to track max time between moves
	
	private JFrame gameFrame2048;
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
		contentPane2048 = gameFrame2048.getContentPane();
		contentPane2048.setLayout(null);
		contentPane2048.setBackground(Color.GRAY);
		gameFrame2048.setVisible(true);

		
		universalGameTimer.schedule(this,0,TIME_BETWEEN_MOVES);
		
		contentPane2048.addMouseListener(this);
		contentPane2048.addKeyListener(this);
		
		
	}
	
	private boolean isGameOver() {
		return false;
		
	}
	private void newGame() {
		OriginalGameBoard game2048 = new OriginalGameBoard(ARRAY_WIDTH, ARRAY_HEIGHT);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//If game is ready
		//Move block
		}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller2048 myController = new Controller2048("Hello", 50,50, FRAME_WIDTH, FRAME_HEIGHT);
	}

}
