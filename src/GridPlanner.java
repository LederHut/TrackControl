
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GridPlanner extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel[][] myLabels;
	private ImageIcon image = null;
	
	public GridPlanner(int rows, int cols, int cellWidth) {
		myLabels = new JLabel[rows][cols];
		image = new ImageIcon("src/suckmyballs.png");
		image.getImage().flush();
		
		GridMouseListener myListener = new GridMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		
		setLayout(new GridLayout(rows, cols));
		for(int col = 0; col < myLabels.length; col++) 
		{
			for(int row = 0; row < myLabels.length; row++) 
			{	
				JLabel myLabel = new JLabel();
				myLabel.setOpaque(true);
				myLabel.addMouseListener(myListener);
				myLabel.setPreferredSize(labelPrefSize);
				add(myLabel);
				myLabels[row][col] = myLabel;
			}
		}
	}
	public void labelPressed(JLabel label) 
	{
		for(int row = 0; row < myLabels.length; row++) 
		{
			for(int col = 0; col < myLabels.length; col++) 
			{
				if(label == myLabels[row][col]) 
				{
					myLabels[row][col].setIcon(image);
					image.getImage().flush();
				}
			}
		}
	}
}
final class GridMouseListener extends MouseAdapter 
{
	private GridPlanner colorGrid;
	private boolean isPressed = false;
	
	public GridMouseListener(GridPlanner colorGrid) {
		this.colorGrid = colorGrid;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (e.getID() == MouseEvent.MOUSE_PRESSED && !isPressed) 
		{
			isPressed = true;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(isPressed)
		{
			colorGrid.labelPressed((JLabel)e.getSource());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(e.getID() == MouseEvent.MOUSE_RELEASED && isPressed)
		{
			isPressed = false;
		}
	}
}