import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class GridPlanner extends JPanel implements MouseWheelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2317402967205059398L;
	
	private Toolbar toolbar = null;
	private JLabel[][] myLabels;
	private ImageIcon[][] orignalImages;	//stores all the original images
	private ImageIcon[][] scaledImages;
	private int currentSelectedRail = 0;
	private int[] railOrientation;
	private int [][][] gridMetadata = new int [50][50][2];	// max number of cells is this capacity divided by 3.
													   		// [x][y][0] saves the imageID of the label on that index.
													   		// [x][y][1] saves the imageOrientation of the label on that index.
	private double currentGridTileSize = 0;
	
	public GridPlanner(int rows, int cols, int cellWidth) 
	{	
		currentGridTileSize = cellWidth;
		
		toolbar =  new Toolbar(this);
		myLabels = new JLabel[rows][cols];
		
		orignalImages = new ImageIcon[4][4];
		LoadImages();
		 
		scaledImages = new ImageIcon[4][4];
		scaleRailImages();
		
		railOrientation = new int[] {0,0,0,0,0,0,0,0};
		
		LabelMouseListener myListener = new LabelMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		
		setLayout(new GridLayout(rows, cols));
		addMouseWheelListener(this);
		for(int col = 0; col < myLabels.length; col++)
		{
			for(int row = 0; row < myLabels.length; row++)
			{	
				int  imageID = rand_int(2);
				int  imageOrientation = rand_int(3);
				
				JLabel myLabel = new JLabel(scaledImages[imageID][imageOrientation]);
				//myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(myListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				add(myLabel);
				myLabels[row][col] = myLabel;
				UpdateMetadata(imageID, imageOrientation, row, col);
				
			}
		}
		
		
	}
	
	
	/**
     * Loads all available images.
     */
	private void LoadImages()
	{
		//Ground variation one
		orignalImages[0][0] = new ImageIcon("src/Images/Ground_1_360_0.png");
		orignalImages[0][1] = new ImageIcon("src/Images/Ground_1_90_1.png");
		orignalImages[0][2] = new ImageIcon("src/Images/Ground_1_180_2.png");
		orignalImages[0][3] = new ImageIcon("src/Images/Ground_1_270_3.png");
		
		//Ground variation two
		orignalImages[1][0] = new ImageIcon("src/Images/Ground_2_360_0.png");
		orignalImages[1][1] = new ImageIcon("src/Images/Ground_2_90_1.png");
		orignalImages[1][2] = new ImageIcon("src/Images/Ground_2_180_2.png");
		orignalImages[1][3] = new ImageIcon("src/Images/Ground_2_270_3.png");
		
		//Ground variation three
		orignalImages[2][0] = new ImageIcon("src/Images/Ground_3_360_0.png");
		orignalImages[2][1] = new ImageIcon("src/Images/Ground_3_90_1.png");
		orignalImages[2][2] = new ImageIcon("src/Images/Ground_3_180_2.png");
		orignalImages[2][3] = new ImageIcon("src/Images/Ground_3_270_3.png");
		
		//Straight rail
		orignalImages[3][0] = new ImageIcon("src/Images/Rail_S_360_0.png");
		orignalImages[3][1] = new ImageIcon("src/Images/Rail_S_90_1.png");
		orignalImages[3][2] = new ImageIcon("src/Images/Rail_S_180_2.png");
		orignalImages[3][3] = new ImageIcon("src/Images/Rail_S_270_3.png");
		
		//orignalImages[4][0] = new ImageIcon("src/Schiene_Rotate.png");
		
		//orignalImages[5][0] = new ImageIcon("src/Cross_Junction.png");
		
		//orignalImages[6][0] = new ImageIcon("src/Diagonale.png");
		
		//orignalImages[7][0] = new ImageIcon("src/Cross_Y_Weiche.png");
	}
	
	
	/**
     * Changes the icon of the pressed label.
     * 
     * @param label The JLabel that got pressed.
     * 
     */
	public void labelPressed(JLabel label) 
	{		
		for(int col = 0; col < myLabels.length; col++)
		{
			for(int row = 0; row < myLabels.length; row++)
			{
				if(label == myLabels[row][col])
				{
					label.setIcon(scaledImages[currentSelectedRail]
											  [railOrientation[currentSelectedRail]]);
					
					UpdateMetadata(currentSelectedRail,
								   railOrientation[currentSelectedRail],
								   row,
								   col);
					return;
				}
			}
		}
		
	}
	
	
	/**
     * Scales images to the currentGridTileSize.
     */
	private void scaleRailImages()
	{		
		for(int col = 0; col < orignalImages.length; col++)
		{
			for(int row = 0; row < orignalImages[col].length; row++)
			{
				scaledImages[col][row] = new ImageIcon(orignalImages[col][row].getImage().getScaledInstance((int)(currentGridTileSize),
		  				  																					(int)(currentGridTileSize),
		  				  																					java.awt.Image.SCALE_SMOOTH));
			}
			
		}
	}
	
	/**
	 * Resizes the grid to the specified zoom
	 * 
	 * @param zoomscale The amount of zoom/scale as percent given in decimal
	 *
	 */
	private void gridZoom(double zoomscale)
	{
		
		currentGridTileSize *= zoomscale;
		
		if(currentGridTileSize <= 16)
		{
			currentGridTileSize = 16;
		}
		else if(currentGridTileSize >= 64)
		{
			currentGridTileSize = 64;
		}
		
		Dimension newsize = new Dimension((int) (currentGridTileSize),
										  (int) (currentGridTileSize));
		scaleRailImages();

		for(int col = 0; col < myLabels.length; col++)
		{
			for(int row = 0; row < myLabels.length; row++)
			{
				myLabels[row][col].setIcon(scaledImages[gridMetadata[row][col][0]]
													   [gridMetadata[row][col][1]]);
				myLabels[row][col].setSize(newsize);
				myLabels[row][col].setPreferredSize(newsize);
			}
		}
	}
	
	/**
	 * Handles MouseWheel input.
	 * 
	 * @param e MouseWheelEvent of this class
	 *
	 */
	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		if(e.getPreciseWheelRotation() < 0 && currentGridTileSize != 64)
		{
			gridZoom(1.2);
		}
		if(e.getPreciseWheelRotation() > 0 && currentGridTileSize != 16)
		{
			gridZoom(0.9);
		}
		
	}
	
	
	/**
     * Returns an Random integer.
     * 
     * @param bounds The exclusive upper bound.
     * 
     * @return An random integer in the given bounds.
     * 
     */
	private int rand_int(int bounds) 
	{
	    Random rand = new Random();
	    int randomElemnt = rand.nextInt(bounds); 
	    return randomElemnt;
	}
	
	
	private void UpdateMetadata(int imageID , int imageOrientation,int row  ,int col )
	{
		gridMetadata[row][col][0] = imageID;
		gridMetadata[row][col][1] = imageOrientation;
	}
	public Toolbar getThisToolbar()
	{
		return this.toolbar;
	}
	public void setSelectedRail(int num)
	{
		currentSelectedRail = num;
	}
	public void setSelectedRailOrientation(int orientation)
	{
		railOrientation[currentSelectedRail] = orientation;
	}
	public int getSelectedRailOrientation()
	{
		return railOrientation[currentSelectedRail];
	}
	public ImageIcon getCurrentRailImage()
	{
		return orignalImages[currentSelectedRail]
							[railOrientation[currentSelectedRail]];
	}
	
}

//Handles all mouse actions performed on the JLable's allocated in the grid
class LabelMouseListener extends MouseAdapter 
{
	private GridPlanner colorGrid;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public LabelMouseListener(GridPlanner grid) 
	{
		this.colorGrid = grid;
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
				colorGrid.labelPressed((JLabel)e.getSource());
			}
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
		if(SwingUtilities.isLeftMouseButton(e))
		{
			if(e.getID() == MouseEvent.MOUSE_RELEASED && isPressed)
			{
				isPressed = false;
			}
		}
		
	}
}