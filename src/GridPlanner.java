
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GridPlanner extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2317402967205059398L;
	
	private Toolbar toolbar = null;
	private JLabel[][] myLabels;
	private ImageIcon[] image;
	private int currentRail = 0;
	
	public GridPlanner(int rows, int cols, int cellWidth) 
	{
		myLabels = new JLabel[rows][cols];
		image = new ImageIcon[3];
		
		toolbar =  new Toolbar(this);
		
		image[0] = new ImageIcon("src/DirtGround.png");
		image[1] = new ImageIcon("src/StraightRail-HZ.png");
		image[2] = new ImageIcon("src/CurvedRail_BR.png");
		
		Image newimg = image[0].getImage().getScaledInstance(cellWidth, cellWidth,  java.awt.Image.SCALE_SMOOTH);
		image[0] = new ImageIcon(newimg);
		
		GridMouseListener myListener = new GridMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		
		setLayout(new GridLayout(rows, cols));
		for(int col = 0; col < myLabels.length; col++) 
		{
			for(int row = 0; row < myLabels.length; row++) 
			{	
				JLabel myLabel = new JLabel(image[0]);
				myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
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
					Image newimg = image[currentRail].getImage().getScaledInstance(myLabels[row][col].getWidth(), myLabels[row][col].getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					image[currentRail] = new ImageIcon(newimg); 
					
					myLabels[row][col].setIcon(image[currentRail]);
					
				}
			}
		}
	}
	public void rotateImage(double angle)
	{ 
		image[currentRail].setImage(ImageTool.rotate(image[currentRail].getImage(),angle));
	}
	public Toolbar getThisToolbar()
	{
		return this.toolbar;
	}
	// needs to be renamed
	public void currentrailselect(int num)
	{
		currentRail = num;
	}
	public ImageIcon getCurrentRailImage() {
		// TODO Auto-generated method stub
		return image[currentRail];
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
		if(isPressed)
		{
			colorGrid.labelPressed((JLabel)e.getSource());
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