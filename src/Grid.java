import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Grid extends JPanel implements MouseWheelListener ,KeyListener ,MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8460475293040995859L;

	private int [][][] gridMetadata = null;	// max number of cells is this capacity divided by 3.
											// [x][y][0] saves the imageID of the label on that index.
											// [x][y][1] saves the imageOrientation of the label on that index.
											// [x][y][2] saves the ground of the respective image it uses.
	
	private JPanel actualGrid = null;
	private JLabel[][] myLabels;
	private JLabel lastenteredLabel = null;
	private int lastenteredLabelrow = 0;
	private int lastenteredLabelcol = 0;
	private ImageIcon[][][] orignalImages;					//stores the original images.
	private ImageIcon[][][] scaledImages;
	private int selected = 0;
	private int selectedOrientation = 0;
	private int labelWidth = 0;
	
	private double currentGridTileSize = 0;
	
	public Grid (int rows, int cols, int cellWidth , int imgLoader)
	{
		labelWidth = cellWidth;
		currentGridTileSize = cellWidth;
		
		gridMetadata = new int [rows][cols][3];
		actualGrid = new JPanel();
		myLabels = new JLabel[rows][cols];
		
		LabelMouseListener labelMouseListener = null;
		if(imgLoader == 1)
		{
			orignalImages = new ImageIcon[5][3][4];
			loadRailImages();
			scaledImages = new ImageIcon[5][3][4];
			scaleRailImages();
			labelMouseListener = new Tab1LabelMouseListener(this);
		}
		else if(imgLoader == 2)
		{
			orignalImages = new ImageIcon[5][3][4];
			loadRailImages();
			scaledImages = new ImageIcon[5][3][4];
			scaleRailImages();
			labelMouseListener = new Tab2LabelMouseListener(this);
		}
		else if(imgLoader == 3)
		{
			orignalImages = new ImageIcon[5][3][4];
			loadRailImages();
			scaledImages = new ImageIcon[5][3][4];
			scaleRailImages();
			labelMouseListener = new Tab3LabelMouseListener(this);
		}
		
		setLayout(new BorderLayout());
		addKeyListener(this);
		addMouseListener(this);

		actualGrid.setLayout(new GridLayout(rows, cols));
		actualGrid.addMouseWheelListener(this);
		
		Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
		//Generates the grid
		for(int col = 0; col < myLabels.length; col++){
			for(int row = 0; row < myLabels.length; row++){
				
				int  imageOrientation = rand_int(4);
				int  groundImageID = rand_int(3);
				
				JLabel myLabel = new JLabel(scaledImages[0]
														[groundImageID]
														[imageOrientation]);
				
				//myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(labelMouseListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				actualGrid.add(myLabel);
				myLabels[row][col] = myLabel;
				UpdateMetadata(0, imageOrientation, groundImageID, row, col);
				
			}
		}
	}
	
	/**
     * Loads all available and needed images.
     */
	private void loadRailImages()
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
		orignalImages[1][0][0] = new ImageIcon("src/Images/BrockenRail_straight_1_1.png");
		orignalImages[1][0][1] = new ImageIcon("src/Images/BrockenRail_straight_1_2.png");
		orignalImages[1][0][2] = new ImageIcon("src/Images/BrockenRail_straight_1_3.png");
		orignalImages[1][0][3] = new ImageIcon("src/Images/BrockenRail_straight_1_4.png");
		//
		//Variation two
		orignalImages[1][1][0] = new ImageIcon("src/Images/BrockenRail_straight_2_1.png");
		orignalImages[1][1][1] = new ImageIcon("src/Images/BrockenRail_straight_2_2.png");
		orignalImages[1][1][2] = new ImageIcon("src/Images/BrockenRail_straight_2_3.png");
		orignalImages[1][1][3] = new ImageIcon("src/Images/BrockenRail_straight_2_4.png");
		//
		//Variation three
		orignalImages[1][2][0] = new ImageIcon("src/Images/BrockenRail_straight_3_1.png");
		orignalImages[1][2][1] = new ImageIcon("src/Images/BrockenRail_straight_3_2.png");
		orignalImages[1][2][2] = new ImageIcon("src/Images/BrockenRail_straight_3_3.png");
		orignalImages[1][2][3] = new ImageIcon("src/Images/BrockenRail_straight_3_4.png");
		//-----------------------------------------------------------------------
		
		//Corner rail
		//
		//Variation one
		orignalImages[2][0][0] = new ImageIcon("src/Images/Brokenrail_diagon_1.png");
		orignalImages[2][0][1] = new ImageIcon("src/Images/Brokenrail_diagon_2.png");
		orignalImages[2][0][2] = new ImageIcon("src/Images/Brokenrail_diagon_3.png");
		orignalImages[2][0][3] = new ImageIcon("src/Images/Brokenrail_diagon_4.png");
		//
		//Variation two
		orignalImages[2][1][0] = new ImageIcon("src/Images/Brokenrail_diagon_1.png");
		orignalImages[2][1][1] = new ImageIcon("src/Images/Brokenrail_diagon_2.png");
		orignalImages[2][1][2] = new ImageIcon("src/Images/Brokenrail_diagon_3.png");
		orignalImages[2][1][3] = new ImageIcon("src/Images/Brokenrail_diagon_4.png");
		//
		//Variation three
		orignalImages[2][2][0] = new ImageIcon("src/Images/Brokenrail_diagon_1.png");
		orignalImages[2][2][1] = new ImageIcon("src/Images/Brokenrail_diagon_2.png");
		orignalImages[2][2][2] = new ImageIcon("src/Images/Brokenrail_diagon_3.png");
		orignalImages[2][2][3] = new ImageIcon("src/Images/Brokenrail_diagon_4.png");
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
					if(selected == 0)
					{
						label.setIcon(scaledImages[gridMetadata[row][col][0]]
												  [gridMetadata[row][col][2]]
								                  [gridMetadata[row][col][1]]);
					}
					else if(selected == 1)
					{
						label.setIcon(scaledImages[selected]
								  				  [rand_int(3)]
				  	  			                  [selectedOrientation]);
					}
					else
					{
						label.setIcon(scaledImages[selected]
												  [gridMetadata[row][col][2]]
								  	  			  [selectedOrientation]);
					}
					
					UpdateMetadata(selected,
								   selectedOrientation,
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
		
		if(currentGridTileSize <= labelWidth)
		{
			currentGridTileSize = labelWidth;
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
					if(selected == 0)
					{
						label.setIcon(scaledImages[gridMetadata[row][col][0]]
												  [gridMetadata[row][col][2]]
								                  [gridMetadata[row][col][1]]);
					}
					else
					{
						label.setIcon(scaledImages[selected]
								  				  [gridMetadata[row][col][2]]
								  			      [selectedOrientation]);
					}
					
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
		lastenteredLabel.setIcon(scaledImages[selected]
								  			 [gridMetadata[lastenteredLabelrow][lastenteredLabelcol][2]]
								  			 [selectedOrientation]);
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
			setSelectedRail(1);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			setSelectedRail(2);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_3)
		{
			setSelectedRail(3);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_4)
		{
			setSelectedRail(4);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_5)
		{
			setSelectedRail(5);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			setSelectedRail(0);
			showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_R && getSelectedRail() != 0)
		{
			if(getSelectedRailOrientation() == 3)
			{
				setSelectedRailOrientation(0);
			}
			else
			{
				setSelectedRailOrientation(getSelectedRailOrientation() + 1);
			}
			showCurrentselect();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	//-----------------------------------------------------------------------
	
	//MouseListner interface
	//
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		requestFocusInWindow();
	}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for selectedRail
	//
	public void setSelectedRail(int num)
	{
		selected = num;
	}
	public int getSelectedRail()
	{
		return selected;
	}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for selectedRailOrientation
	//
	public void setSelectedRailOrientation(int orientation)
	{
		selectedOrientation = orientation;
	}
	public int getSelectedRailOrientation()
	{
		return selectedOrientation;
	}
	//-----------------------------------------------------------------------
	
	public void addGrid()
	{
	   JScrollPane scrollPane = new JScrollPane ( actualGrid,
									              ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	   add(scrollPane);
	}

}
