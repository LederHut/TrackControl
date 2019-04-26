import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
	
	private int [][][] gridMetadata = new int [50][50][3];	// max number of cells is this capacity divided by 3.
															// [x][y][0] saves the imageID of the label on that index.
															// [x][y][1] saves the imageOrientation of the label on that index.
													    	// [x][y][2] saves the ground of the respective image it uses.
	private Toolbar toolbar = null;
	private JLabel[][] myLabels;
	private JLabel lastenteredLabel = null;
	private int lastenteredLabelrow = 0;
	private int lastenteredLabelcol = 0;
	private ImageIcon[][][] orignalImages;					//stores the original images.
	private ImageIcon[][][] scaledImages;
	private int selectedRail = 0;
	private int selectedRailOrientation = 0;
	
	private double currentGridTileSize = 0;
	
	public GridPlanner(int rows, int cols, int cellWidth) 
	{	
		currentGridTileSize = cellWidth;
		
		toolbar =  new Toolbar(this);
		myLabels = new JLabel[rows][cols];
		
		orignalImages = new ImageIcon[5][3][4];
		LoadImages();
		
		scaledImages = new ImageIcon[5][3][4];
		scaleRailImages();
		
		
		LabelMouseListener myListener = new LabelMouseListener(this);
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		
		setLayout(new GridLayout(rows, cols));
		addMouseWheelListener(this);
		
		//Generates the grid
		for(int col = 0; col < myLabels.length; col++)
		{
			for(int row = 0; row < myLabels.length; row++)
			{	
				int  imageOrientation = rand_int(4);
				int  groundImageID = rand_int(3);
				
				JLabel myLabel = new JLabel(scaledImages[0]
														[groundImageID]
														[imageOrientation]);
				
				myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(myListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				add(myLabel);
				myLabels[row][col] = myLabel;
				UpdateMetadata(0, imageOrientation, groundImageID, row, col);
				
			}
		}
		
		
	}
	
	
	/**
     * Loads all available and needed images.
     */
	private void LoadImages()
	{

		//Ground 
		//
		//Variation one
		orignalImages[0][0][0] = new ImageIcon("src/Images/Ground_1_360_0.png");
		orignalImages[0][0][1] = new ImageIcon("src/Images/Ground_1_90_1.png");
		orignalImages[0][0][2] = new ImageIcon("src/Images/Ground_1_180_2.png");
		orignalImages[0][0][3] = new ImageIcon("src/Images/Ground_1_270_3.png");
		//
		//Variation two
		orignalImages[0][1][0] = new ImageIcon("src/Images/Ground_2_360_0.png");
		orignalImages[0][1][1] = new ImageIcon("src/Images/Ground_2_90_1.png");
		orignalImages[0][1][2] = new ImageIcon("src/Images/Ground_2_180_2.png");
		orignalImages[0][1][3] = new ImageIcon("src/Images/Ground_2_270_3.png");
		//
		//Variation three
		orignalImages[0][2][0] = new ImageIcon("src/Images/Ground_3_360_0.png");
		orignalImages[0][2][1] = new ImageIcon("src/Images/Ground_3_90_1.png");
		orignalImages[0][2][2] = new ImageIcon("src/Images/Ground_3_180_2.png");
		orignalImages[0][2][3] = new ImageIcon("src/Images/Ground_3_270_3.png");
		//-----------------------------------------------------------------------
		
		
		//Straight rail
		//
		//Variation one
		orignalImages[1][0][0] = new ImageIcon("src/Images/Prototype_G0_R1.png");
		orignalImages[1][0][1] = new ImageIcon("src/Images/Prototype_G0_R2.png");
		orignalImages[1][0][2] = new ImageIcon("src/Images/Prototype_G0_R3.png");
		orignalImages[1][0][3] = new ImageIcon("src/Images/Prototype_G0_R4.png");
		//
		//Variation two
		orignalImages[1][1][0] = new ImageIcon("src/Images/Prototype_G0_R1.png");
		orignalImages[1][1][1] = new ImageIcon("src/Images/Prototype_G0_R2.png");
		orignalImages[1][1][2] = new ImageIcon("src/Images/Prototype_G0_R3.png");
		orignalImages[1][1][3] = new ImageIcon("src/Images/Prototype_G0_R4.png");
		//
		//Variation three
		orignalImages[1][2][0] = new ImageIcon("src/Images/Prototype_G0_R1.png");
		orignalImages[1][2][1] = new ImageIcon("src/Images/Prototype_G0_R2.png");
		orignalImages[1][2][2] = new ImageIcon("src/Images/Prototype_G0_R3.png");
		orignalImages[1][2][3] = new ImageIcon("src/Images/Prototype_G0_R4.png");
		//-----------------------------------------------------------------------
		
		//Corner rail
		//
		//Variation one
		orignalImages[2][0][0] = new ImageIcon("src/Images/Corner_Prototype_R_0.png");
		orignalImages[2][0][1] = new ImageIcon("src/Images/Corner_Prototype_R_1.png");
		orignalImages[2][0][2] = new ImageIcon("src/Images/Corner_Prototype_R_3.png");
		orignalImages[2][0][3] = new ImageIcon("src/Images/Corner_Prototype_R_2.png");
		//
		//Variation two
		orignalImages[2][1][0] = new ImageIcon("src/Images/Corner_Prototype_R_0.png");
		orignalImages[2][1][1] = new ImageIcon("src/Images/Corner_Prototype_R_1.png");
		orignalImages[2][1][2] = new ImageIcon("src/Images/Corner_Prototype_R_3.png");
		orignalImages[2][1][3] = new ImageIcon("src/Images/Corner_Prototype_R_2.png");
		//
		//Variation three
		orignalImages[2][2][0] = new ImageIcon("src/Images/Corner_Prototype_R_0.png");
		orignalImages[2][2][1] = new ImageIcon("src/Images/Corner_Prototype_R_1.png");
		orignalImages[2][2][2] = new ImageIcon("src/Images/Corner_Prototype_R_3.png");
		orignalImages[2][2][3] = new ImageIcon("src/Images/Corner_Prototype_R_2.png");
		//-----------------------------------------------------------------------
		
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
		for(int col = 0; col < myLabels.length; col++){
			for(int row = 0; row < myLabels.length; row++){
				if(label == myLabels[row][col])
				{
					if(selectedRail == 0)
					{
						label.setIcon(scaledImages[gridMetadata[row][col][0]]
												  [gridMetadata[row][col][2]]
								                  [gridMetadata[row][col][1]]);
					}
					else
					{
						label.setIcon(scaledImages[selectedRail]
												  [gridMetadata[row][col][2]]
								  	  			  [selectedRailOrientation]);
					}
					
					UpdateMetadata(selectedRail,
								   selectedRailOrientation,
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
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				for(int z = 0; z < 4; z++){
					scaledImages[x][y][z] = new ImageIcon(orignalImages[x][y][z].getImage().getScaledInstance((int)(currentGridTileSize),
																											  (int)(currentGridTileSize),
																											  java.awt.Image.SCALE_SMOOTH));
				}
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
		else if(currentGridTileSize >= 128)
		{
			currentGridTileSize = 128;
		}
		
		Dimension newsize = new Dimension((int) (currentGridTileSize),
										  (int) (currentGridTileSize));
		scaleRailImages();
		
		for(int col = 0; col < myLabels.length; col++)
		{
			for(int row = 0; row < myLabels.length; row++)
			{
				myLabels[row][col].setIcon(scaledImages[gridMetadata[row][col][0]]
													   [gridMetadata[row][col][2]]
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
		//Zoom in
		if(e.getPreciseWheelRotation() < 0 && currentGridTileSize != 128)
		{
			gridZoom(1.2);
		}
		//Zoom out
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
	public int rand_int(int bounds) 
	{
	    Random rand = new Random();
	    int randomElemnt = rand.nextInt(bounds); 
	    return randomElemnt;
	}
	
	//UpdateMetadata
	//
	/**
     * Returns an Random integer. #placeholder
     * 
     * @param bounds The exclusive upper bound. #placeholder
     * 
     * @return An random integer in the given bounds. #placeholder
     * 
     */
	private void UpdateMetadata(int imageID, int imageOrientation, int groundImageID, int row, int col )
	{
		gridMetadata[row][col][0] = imageID;
		gridMetadata[row][col][1] = imageOrientation;
		gridMetadata[row][col][2] = groundImageID;
	}
	//Override
	//
	/**
     * Returns an Random integer. #placeholder
     * 
     * @param bounds The exclusive upper bound. #placeholder
     * 
     * @return An random integer in the given bounds. #placeholder
     * 
     */
	private void UpdateMetadata(int imageID, int imageOrientation, int row, int col )
	{
		gridMetadata[row][col][0] = imageID;
		gridMetadata[row][col][1] = imageOrientation;
	}
	//-----------------------------------------------------------------------
	
	//showCurrentselect
	//
	/**
     * Returns an Random integer. #placeholder
     * 
     * @param bounds The exclusive upper bound. #placeholder
     * 
     * @return An random integer in the given bounds. #placeholder
     * 
     */
	public void showCurrentselect(JLabel label)
	{
		for(int col = 0; col < myLabels.length; col++){
			for(int row = 0; row < myLabels.length; row++){
				if(label == myLabels[row][col])
				{
					label.setIcon(scaledImages[selectedRail]
											  [gridMetadata[row][col][2]]
											  [selectedRailOrientation]);
					
					lastenteredLabel = label;
					lastenteredLabelrow = row;
					lastenteredLabelcol = col;
					
					return;
				}
			}
		}
	}
	//Override
	//
	/**
     * Returns an Random integer. #placeholder
     * 
     * @param bounds The exclusive upper bound. #placeholder
     * 
     * @return An random integer in the given bounds. #placeholder
     * 
     */
	public void showCurrentselect()
	{
		lastenteredLabel.setIcon(scaledImages[selectedRail]
								  			 [gridMetadata[lastenteredLabelrow][lastenteredLabelcol][2]]
								  			 [selectedRailOrientation]);
	}
	//-----------------------------------------------------------------------

	public void hideCurrentselect(JLabel label)
	{
		for(int col = 0; col < myLabels.length; col++){
			for(int row = 0; row < myLabels.length; row++){
				if(label == myLabels[row][col])
				{
					label.setIcon(scaledImages[gridMetadata[row][col][0]]
											  [gridMetadata[row][col][2]]
											  [gridMetadata[row][col][1]]);
					return;
				}
			}
		}
	}
	public Toolbar getThisToolbar()
	{
		return this.toolbar;
	}
	
	//Get'r and set'r functions for selectedRail
	//
	public void setSelectedRail(int num)
	{
		selectedRail = num;
	}
	public int getSelectedRail()
	{
		return selectedRail;
	}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for selectedRailOrientation
	//
	public void setSelectedRailOrientation(int orientation)
	{
		selectedRailOrientation = orientation;
	}
	public int getSelectedRailOrientation()
	{
		return selectedRailOrientation;
	}
	//-----------------------------------------------------------------------
}

//Handles all mouse actions performed on the JLable's allocated in the grid
class LabelMouseListener extends MouseAdapter 
{
	private GridPlanner Grid;
	private boolean isPressed = false; //secures that only when the mouse IS pressed that the icon gets replaced
	
	public LabelMouseListener(GridPlanner grid) 
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