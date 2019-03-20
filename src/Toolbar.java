import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel
{
	GridPlanner Grid = null;
	JButton b1 = null ,b2 = null;
	int currentblock;
	
	
	public Toolbar (GridPlanner grid)
	{
		this.Grid = grid;
		this.addKeyListener(new ToolbarKeyListner(this.Grid));
		b1 = new JButton("Stright Rail");
		b2 = new JButton("Corner Rail");
		add(b1);
		add(b2);
		setFocusable(true);
	}
}

final class ToolbarKeyListner implements KeyListener
{
	GridPlanner Grid = null;
	
	public ToolbarKeyListner(GridPlanner grid) 
	{
		this.Grid = grid;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			Grid.currentrailselect(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.currentrailselect(2);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}