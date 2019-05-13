import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

class Tab2LabelEventListener extends MouseAdapter implements KeyListener
{
	private Grid Grid;
	private LogicPlanner LPref;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public Tab2LabelEventListener(LogicPlanner lp) 
	{
		LPref = lp;
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
				LPref.cacheCoordinats(Grid.getlastenteredLabelcol(), Grid.getlastenteredLabelrow());
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
				LPref.createLogicBlock();
			}
			//else	
			//{
			//	LPref.resetCache();
			//	isPressed = false;
			//}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(isPressed)
		{
			Grid.labelPressed((JLabel)e.getSource());
			LPref.cacheCoordinats(Grid.getlastenteredLabelcol(), Grid.getlastenteredLabelrow());
		}
		else
		{
			Grid.showCurrentselect((JLabel)e.getSource());
			if(!Grid.isFocusOwner())
			{
				Grid.requestFocusInWindow();
			}
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		Grid.hideCurrentselect((JLabel)e.getSource());
	}
	
	//KeyListner interface
	//
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			Grid.setSelect(8);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.setSelect(11);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_3)
		{
			Grid.setSelect(14);
			Grid.showCurrentselect();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------------------------------------------
}