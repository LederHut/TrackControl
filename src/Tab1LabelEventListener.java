import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Tab1LabelEventListener extends MouseAdapter implements KeyListener
{
	private Grid Grid;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public Tab1LabelEventListener() 
	{
	}
	public void setGrid(Grid grid)
	{
		this.Grid = grid;
	}
	
	//When the left mouse button is pressed the selected image is set as the JLabels's icon
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if (e.getID() == MouseEvent.MOUSE_PRESSED && !isPressed) 
			{
				isPressed = true;
			}
			if(isPressed)
			{
				Grid.labelPressed((JLabel)e.getSource());
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(e.getID() == MouseEvent.MOUSE_RELEASED && isPressed)
			{
				isPressed = false;
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(isPressed)
		{
			Grid.labelPressed((JLabel)e.getSource());
		}
		else
		{
			Grid.showCurrentselect((JLabel)e.getSource());
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		Grid.hideCurrentselect((JLabel)e.getSource());
	}
	
	//KeyListner interface
	//
	// Handles the keyboard input for the toolbar.
	// Numeric keys change the selected building block(rail).
	// E : used for the eraser "tool".
	// 
	// Right now only the key e is bound properly.
	// The other keys will generate errors.
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			Grid.setSelect(3);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.setSelect(6);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_3)
		{
			Grid.setSelect(3);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_4)
		{
			Grid.setSelect(4);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_5)
		{
			Grid.setSelect(5);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			Grid.setSelect(0);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_R && Grid.getSelect() != 0)
		{
			if(Grid.getSelectedOrientation() == 3)
			{
				Grid.setSelectedOrientation(0);
			}
			else
			{
				Grid.setSelectedOrientation(Grid.getSelectedOrientation() + 1);
			}
			Grid.showCurrentselect();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	//-----------------------------------------------------------------------
}
