import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class LabelMouseListener extends MouseAdapter 
{
	private Grid Grid;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public LabelMouseListener(Grid grid) 
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
	
}