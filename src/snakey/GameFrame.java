package snakey;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	GameFrame()
	{
	  this.add(new GamePanel());//same as GamePanel panel = new GamePanel();
	  this.setTitle("Snake");
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setResizable(false);
	  this.pack();
	  this.setVisible(true);
	  this.setLocationRelativeTo(null);
	}
	
}