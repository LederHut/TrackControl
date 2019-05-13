import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class Tab3LabelEventListener extends MouseAdapter implements KeyListener
{
	private Grid Grid;
	private Simulator SimGrid;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public Tab3LabelEventListener(Simulator simgrid) 
	{	
		SimGrid = simgrid;
	}
	public void setGrid(Grid grid)
	{
		this.Grid = grid;
		Grid.setSelect(0);
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
				if(Grid.getSelect() == 1)
				{
					SimGrid.createTrainstop(Grid.getlastenteredLabelcol(), Grid.getlastenteredLabelrow());
				}
				if(Grid.getSelect() == 2)
				{
					//SimGrid.createTrain(Grid.getlastenteredLabelrow(), Grid.getlastenteredLabelcol());
				}
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
			Grid.updateMouseInfo((JLabel)e.getSource());
			if(!Grid.isFocusOwner())
			{
				Grid.requestFocusInWindow();
			}
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	
	//KeyListner interface
	//
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			Grid.setSelect(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.setSelect(2);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------------------------------------------
	
}