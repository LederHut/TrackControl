
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GridPlanner extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar = null;
	private JLabel[][] myLabels;
	private ImageIcon[] image;
	private int currentRail = 0;
	
	public GridPlanner(int rows, int cols, int cellWidth) {
		myLabels = new JLabel[rows][cols];
		
		toolbar =  new Toolbar(this);
		
		image = new ImageIcon[3];
		image[0] = new ImageIcon();
		image[1] = new ImageIcon("src/StraightRail-HZ.png");
		image[1].getImage().flush();
		image[2] = new ImageIcon("src/CurvedRail_BR.png");
		image[2].getImage().flush();
		
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
					Image newimg = image[currentRail].getImage().getScaledInstance(myLabels[row][col].getWidth(), myLabels[row][col].getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					image[currentRail] = new ImageIcon(newimg);  
					
					myLabels[row][col].setIcon(image[currentRail]);
					image[currentRail].getImage().flush();
					
				}
			}
		}
	}
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
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
}
final class GridMouseListener extends MouseAdapter 
{
	private GridPlanner colorGrid;
	private boolean isPressed = false;
	private int pullcounter = 0;
	
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
		if(isPressed && pullcounter < 7)
		{
			colorGrid.labelPressed((JLabel)e.getSource());
			pullcounter++;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(isPressed && pullcounter < 7)
		{
			colorGrid.labelPressed((JLabel)e.getSource());
			pullcounter++;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(e.getID() == MouseEvent.MOUSE_RELEASED && isPressed)
		{
			isPressed = false;
			pullcounter = 0;
		}
	}
}