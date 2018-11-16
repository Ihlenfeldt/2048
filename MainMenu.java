package game2048;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import javafx.scene.layout.Border;

public class MainMenu 
{
	JLabel welcomeLabel;
    JFrame menuJFrame;
	public MainMenu()
	{
		menuJFrame = new JFrame("Menu");
		menuJFrame.setSize(795, 820);
		menuJFrame.setLocation(50,50);
		menuJFrame.getContentPane().setLayout(null);
		menuJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			menuJFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Background.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
        welcomeLabel = new JLabel();
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(64.0f));
        welcomeLabel.setText(" Welcome to 2048! ");
        welcomeLabel.setSize(615,100);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.white);
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.RED, 5);
        welcomeLabel.setBorder(border);
        
        welcomeLabel.setLocation(91, 175); // arbitrary, will change later
        menuJFrame.getContentPane().add(welcomeLabel);
        welcomeLabel.setVisible(false);
        welcomeLabel.setVisible(true);
		welcomeLabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("hello");
			}
		});
		JButton Original = new JButton();
		Original.setFont(welcomeLabel.getFont().deriveFont(32.0f));
		border = BorderFactory.createLineBorder(Color.black, 10);
		Original.setBorder(border);
		Original.setText("Play Original 2048");
		Original.setOpaque(false);
		Original.setForeground(Color.black);
		
		Original.setSize(310,75);
		Original.setBackground(Color.black);
		Original.setLocation(42,315);
		menuJFrame.getContentPane().add(Original);
		
		JButton Russian = new JButton();
		Russian.setFont(welcomeLabel.getFont().deriveFont(32.0f));
		Russian.setBorder(border);
		Russian.setText("Play Russian 2048");
		Russian.setOpaque(false);
		Russian.setForeground(Color.black);
		
		Russian.setSize(310,75);
		Russian.setBackground(Color.black);
		Russian.setLocation(42,440);
		menuJFrame.getContentPane().add(Russian);
		
		Original.addActionListener(new ActionListener()
		{
			@Override
			  public void actionPerformed(ActionEvent e)
			  {
			    //call the Controller function start game
				Controller2048.gameType = 0;
			    Controller2048.newGame();
			  }

			
			});
		
		Russian.addActionListener(new ActionListener()
		{
			@Override
			  public void actionPerformed(ActionEvent e)
			  {
			    //call the Controller function to start game
				Controller2048.gameType = 1;
			    Controller2048.newGame();
			  }

			
			});
		
		
		String[] dimArray = {"4 X 4", "6 X 6", "8 X 8"};
        JComboBox<String> OrigDim = new JComboBox<>(dimArray);
//     OrigDim.setPreferredSize(new Dimension(200, 100));

    OrigDim.setFont(welcomeLabel.getFont().deriveFont(32.0f));
        OrigDim.setSize(400,400);
        //Orig
        OrigDim.setVisible(false);
        OrigDim.setVisible(true);
        
       
        
		
		OrigDim.setOpaque(false);
		OrigDim.setForeground(Color.black);
		
		OrigDim.setLocation(500,315);
		menuJFrame.getContentPane().add(OrigDim);
		
		
		
	}
	
	public JFrame getFrame()
	{
		return menuJFrame;
	}
	
	
}
	