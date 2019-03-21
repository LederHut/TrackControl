import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

//could maybe use some tools like pulling a straight line or something like that
//or like automatic curve laying mode stuff like that
public class Toolbar extends JPanel implements KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8527007348870047047L;
	GridPlanner Grid = null;
	JButton b1 = null ,b2 = null;
	JLabel railPreview = null;
	ImageIcon railImage = null;
	int currentblock;
	
	
	public Toolbar (GridPlanner grid)
	{
		this.setPreferredSize(new Dimension(210,200));
		this.Grid = grid;
		this.addKeyListener(this);
		
		railPreview = new JLabel();
		railPreview.setPreferredSize(new Dimension(128,128));
		railPreview.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		b1 = new JButton("Stright Rail");
		b2 = new JButton("Corner Rail");
		
		add(b1);
		add(b2);
		add(railPreview);
		setFocusable(true);
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
			railPreview.setIcon(new ImageIcon(Grid.getCurrentRailImage().getImage().getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH)));
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.currentrailselect(2);
			railPreview.setIcon(new ImageIcon(Grid.getCurrentRailImage().getImage().getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH)));
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			Grid.currentrailselect(0);
			railPreview.setIcon(new ImageIcon(Grid.getCurrentRailImage().getImage().getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH)));
		}
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
			Grid.rotateImage(90);
			railPreview.setIcon(new ImageIcon(Grid.getCurrentRailImage().getImage().getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH)));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}