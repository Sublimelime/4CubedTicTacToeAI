import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameFrame extends JFrame 
{

	

	public int width = 125;
	public int height = 315;
	public GameFrame()
	{

		super("4x4x4 Tic Tac Toe");
		
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setSize(width,height);


			// creates the panel
		GamePanel gamePanel = new GamePanel(width,height);
		add(gamePanel);

		//Container c = this.getContentPane();
		//c.add(gamePanel, "Center");

			// gets the frames insets
		Insets frameInsets = getInsets();

			// calculates frame insets
		width += (frameInsets.left + frameInsets.right);
		height+= (frameInsets.top + frameInsets.bottom);

		setSize(width-20,height-10);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);

	}

}