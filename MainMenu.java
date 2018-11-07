package game2048;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        welcomeLabel.setText("Welcome to 2048!");
        welcomeLabel.setSize(700,100);
        
        welcomeLabel.setLocation(110, 175); // arbitrary, will change later
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
	}
	
	public JFrame getFrame()
	{
		return menuJFrame;
	}
}
