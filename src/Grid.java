import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Vendor.ImageTool;

public class Grid extends JPanel implements MouseWheelListener ,MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8460475293040995859L;

	private int [][][] gridMetadata = null;	// max number of cells is this capacity divided by 3.
											// [x][y][0] saves the imageID of the label on that index.
											// [x][y][1] saves the imageOrientation of the label on that index.
											// [x][y][2] saves the blockID of the label on that index.
											// [x][y][3] saves the trainstopID of the label on that index.
											// [x][y][4] saves the trainID of the label on that index.
	
	private JPanel actualGrid = null;
	private JLabel[][] myLabels;
	private JLabel lastenteredLabel = null;
	private int lastenteredLabelcol = 0;
	private int lastenteredLabelrow = 0;
	private ImageIcon[][] orignalImages;					//stores the original images.
	private ImageIcon[][] scaledImages;
	private int selected = 0;
	private int selectedOrientation = 0;
	public static int maxCols = 0;
	public static int maxRows = 0;
	
	
	private final int allImages = 10;
	
	private double currentGridTileSize = 0;
	
	public Grid (int cols, int rows, int cellWidth)
	{
		currentGridTileSize = cellWidth;
		maxCols = cols;
		maxRows = rows;
		
		gridMetadata = new int [cols][rows][5];
		actualGrid = new JPanel();
		myLabels = new JLabel[cols][rows];
		
		orignalImages = new ImageIcon[allImages][4];
		loadImages();
		scaledImages = new ImageIcon[allImages][4];
		scaleRailImages();
		
		setLayout(new BorderLayout());
		addMouseListener(this);

		actualGrid.setLayout(new GridLayout(cols, rows));
		actualGrid.addMouseWheelListener(this);
	}
	//Grid init
	//
	public void initGrid(Tab1LabelEventListener labelMouseListener)
	{
		addKeyListener(labelMouseListener);
		labelMouseListener.setGrid(this);
		Dimension labelPrefSize = new Dimension((int)currentGridTileSize, (int)currentGridTileSize);
		//Generates the grid
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				
				int  imageOrientation = rand_int(0,4);
				int  ImageID = rand_int(0,3);
				
				JLabel myLabel = new JLabel(scaledImages[ImageID]
														[imageOrientation]);
				
				myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(labelMouseListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				actualGrid.add(myLabel);
				myLabels[col][row] = myLabel;
				UpdateMetadata(ImageID, imageOrientation, col, row);
				
			}
		}
	}
	//Overload
	//
	public void initGrid(Tab2LabelEventListener labelMouseListener)
	{
		addKeyListener(labelMouseListener);
		labelMouseListener.setGrid(this);
		Dimension labelPrefSize = new Dimension((int)currentGridTileSize, (int)currentGridTileSize);
		//Generates the grid
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				
				int  imageOrientation = rand_int(0,4);
				int  ImageID = rand_int(0,3);
				
				JLabel myLabel = new JLabel(scaledImages[ImageID]
														[imageOrientation]);
				
				myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(labelMouseListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				actualGrid.add(myLabel);
				myLabels[col][row] = myLabel;
				UpdateMetadata(ImageID, imageOrientation, col, row);
				
			}
		}
	}
	//Overload
	//
	public void initGrid(Tab3LabelEventListener labelMouseListener)
	{
		addKeyListener(labelMouseListener);
		labelMouseListener.setGrid(this);
		Dimension labelPrefSize = new Dimension((int)currentGridTileSize, (int)currentGridTileSize);
		//Generates the grid
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				
				int  imageOrientation = rand_int(0,4);
				int  ImageID = rand_int(0,3);
				
				JLabel myLabel = new JLabel(scaledImages[ImageID]
														[imageOrientation]);
				
				myLabel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f))); //// can be an option to turn it of or on as grid.
				myLabel.setOpaque(true);
				myLabel.addMouseListener(labelMouseListener);
				myLabel.setPreferredSize(labelPrefSize);
				
				actualGrid.add(myLabel);
				myLabels[col][row] = myLabel;
				UpdateMetadata(ImageID, imageOrientation, col, row);
				
			}
		}
	}
	//-----------------------------------------------------------------------
	
	/**
     * Loads all available and needed images.
     */
	private void loadImages()
	{

		//Ground 
		//
		//Variation one
		orignalImages[0][0] = new ImageIcon("src/Images/Ground_1_360_0.png");
		orignalImages[0][1] = new ImageIcon("src/Images/Ground_1_90_1.png");
		orignalImages[0][2] = new ImageIcon("src/Images/Ground_1_180_2.png");
		orignalImages[0][3] = new ImageIcon("src/Images/Ground_1_270_3.png");
		//
		//Variation two
		orignalImages[1][0] = new ImageIcon("src/Images/Ground_2_360_0.png");
		orignalImages[1][1] = new ImageIcon("src/Images/Ground_2_90_1.png");
		orignalImages[1][2] = new ImageIcon("src/Images/Ground_2_180_2.png");
		orignalImages[1][3] = new ImageIcon("src/Images/Ground_2_270_3.png");
		//
		//Variation three
		orignalImages[2][0] = new ImageIcon("src/Images/Ground_3_360_0.png");
		orignalImages[2][1] = new ImageIcon("src/Images/Ground_3_90_1.png");
		orignalImages[2][2] = new ImageIcon("src/Images/Ground_3_180_2.png");
		orignalImages[2][3] = new ImageIcon("src/Images/Ground_3_270_3.png");
		//-----------------------------------------------------------------------
		
		
		//Straight rail
		//
		//Variation one
		orignalImages[3][0] = new ImageIcon("src/Images/BrockenRail_straight_1_1.png");
		orignalImages[3][1] = new ImageIcon("src/Images/BrockenRail_straight_1_2.png");
		orignalImages[3][2] = new ImageIcon("src/Images/BrockenRail_straight_1_3.png");
		orignalImages[3][3] = new ImageIcon("src/Images/BrockenRail_straight_1_4.png");
		//
		//Variation two
		orignalImages[4][0] = new ImageIcon("src/Images/BrockenRail_straight_2_1.png");
		orignalImages[4][1] = new ImageIcon("src/Images/BrockenRail_straight_2_2.png");
		orignalImages[4][2] = new ImageIcon("src/Images/BrockenRail_straight_2_3.png");
		orignalImages[4][3] = new ImageIcon("src/Images/BrockenRail_straight_2_4.png");
		//
		//Variation three
		orignalImages[5][0] = new ImageIcon("src/Images/BrockenRail_straight_3_1.png");
		orignalImages[5][1] = new ImageIcon("src/Images/BrockenRail_straight_3_2.png");
		orignalImages[5][2] = new ImageIcon("src/Images/BrockenRail_straight_3_3.png");
		orignalImages[5][3] = new ImageIcon("src/Images/BrockenRail_straight_3_4.png");
		//-----------------------------------------------------------------------
		
		//Diagonal corner rail
		//
		//Variation one
		orignalImages[6][0] = new ImageIcon("src/Images/Brokenrail_diagon_1.png");
		orignalImages[6][1] = new ImageIcon("src/Images/Brokenrail_diagon_2.png");
		orignalImages[6][2] = new ImageIcon("src/Images/Brokenrail_diagon_3.png");
		orignalImages[6][3] = new ImageIcon("src/Images/Brokenrail_diagon_4.png");
		//-----------------------------------------------------------------------
		
		//Corner rail
		//
		//Variation one
		orignalImages[7][0] = new ImageIcon("src/Images/Brokenrail_turn_1.png");
		orignalImages[7][1] = new ImageIcon("src/Images/Brokenrail_turn_2.png");
		orignalImages[7][2] = new ImageIcon("src/Images/Brokenrail_turn_3.png");
		orignalImages[7][3] = new ImageIcon("src/Images/Brokenrail_turn_4.png");
		//-----------------------------------------------------------------------
		
		//The end and start dot for the logicblocks
		//
		//Variation one
		orignalImages[8][0] = new ImageIcon("src/Images/Punkt_mit_Punkt.png");
		orignalImages[8][1] = new ImageIcon("src/Images/Punkt_mit_Punkt.png");
		orignalImages[8][2] = new ImageIcon("src/Images/Punkt_mit_Punkt.png");
		orignalImages[8][3] = new ImageIcon("src/Images/Punkt_mit_Punkt.png");
		//-----------------------------------------------------------------------
		
		//The trainstop mark
		//
		//Variation one
		orignalImages[9][0] = new ImageIcon("src/Images/Trainstop_Mark.png");
		orignalImages[9][1] = new ImageIcon("src/Images/Trainstop_Mark.png");
		orignalImages[9][2] = new ImageIcon("src/Images/Trainstop_Mark.png");
		orignalImages[9][3] = new ImageIcon("src/Images/Trainstop_Mark.png");
		//-----------------------------------------------------------------------
				
	}
	
	
	/**
     * Changes the icon of the pressed label.
     * 
     * @param label The JLabel that got pressed.
     * 
     */
	public void labelPressed(JLabel label)
	{		
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				if(label == myLabels[col][row])
				{
					if(selected == 0)
					{
						label.setIcon(scaledImages[0]
								                  [0]);
					}
					else if(selected == 3)
					{
						int sel = rand_int(selected, selected + 3);
						
						label.setIcon(scaledImages[sel]
				  	  			                  [selectedOrientation]);
						UpdateMetadata(sel,
								   	   selectedOrientation,
								       col,
								       row);
						return;
					}
					else
					{
						label.setIcon(scaledImages[selected]
								  	  			  [selectedOrientation]);
					}
					
					lastenteredLabel = label;
					lastenteredLabelcol = col;
					lastenteredLabelrow = row;

					
					UpdateMetadata(selected,
								   selectedOrientation,
								   col,
								   row);
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
		for(int x = 0; x < allImages; x++){
			for(int y = 0; y < 4; y++){
				
				scaledImages[x][y] = new ImageIcon(orignalImages[x][y].getImage().getScaledInstance((int)(currentGridTileSize),
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
		
		if(currentGridTileSize <= 32)
		{
			currentGridTileSize = 32;
		}
		else if(currentGridTileSize >= 128)
		{
			currentGridTileSize = 128;
		}
		
		Dimension newsize = new Dimension((int) (currentGridTileSize),
										  (int) (currentGridTileSize));
		scaleRailImages();
		
		for(int row = 0; row < myLabels.length; row++)
		{
			for(int col = 0; col < myLabels.length; col++)
			{
				myLabels[col][row].setSize(newsize);
				myLabels[col][row].setPreferredSize(newsize);
				if(gridMetadata[col][row][2] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[8][0].getImage())));
					continue;
				}
				if(gridMetadata[col][row][3] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[9][0].getImage())));
					continue;
				}
				myLabels[col][row].setIcon(scaledImages[gridMetadata[col][row][0]]
													   [gridMetadata[col][row][1]]);
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
     * @param min The inclusive lower bound.
     * 
     * @param max The exclusive upper bound.
     * 
     * @return An random integer in the given bounds.
     * 
     */
	public int rand_int(int min, int max) 
	{
	    int randomElemnt = ThreadLocalRandom.current().nextInt(min, max);
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
	private void UpdateMetadata(int imageID, int imageOrientation, int col, int row )
	{
		gridMetadata[col][row][0] = imageID;
		gridMetadata[col][row][1] = imageOrientation;
		gridMetadata[col][row][2] = 0;
		gridMetadata[col][row][3] = 0;
		gridMetadata[col][row][4] = 0;
	}
	//Overload
	//
	public void UpdateMetadata(LogicBlock lb)
	{
		for(int index = 0; index < lb.blockMetadata.length; index++)
		{
			gridMetadata[lb.blockMetadata[index][0]][lb.blockMetadata[index][1]][2] = lb.blockID;
		}
	}
	//Overload
	//
	public void UpdateMetadata(Trainstop ts)
	{
			gridMetadata[ts.col][ts.row][3] = ts.trainstopID;
			myLabels[ts.col][ts.row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[ts.col][ts.row][0]][gridMetadata[ts.col][ts.row][1]].getImage(),scaledImages[9][0].getImage())));
	}
	//Overload
	//
	public void UpdateMetadata(Train t)
	{
			gridMetadata[t.trainLocation[0][0]][t.trainLocation[0][1]][3] = t.trainID;
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
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				if(label == myLabels[col][row])
				{
					if(selected == 0)
					{
						label.setIcon(scaledImages[gridMetadata[col][row][0]]
								                  [gridMetadata[col][row][1]]);
					}
					else
					{
						label.setIcon(scaledImages[selected]
								  			      [selectedOrientation]);
					}
					
					lastenteredLabel = label;
					lastenteredLabelcol = col;
					lastenteredLabelrow = row;
					
					return;
				}
			}
		}
	}
	//Overload
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
								  			 [selectedOrientation]);
	}
	//-----------------------------------------------------------------------

	public void hideCurrentselect(JLabel label)
	{
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				if(label == myLabels[col][row])
				{
					label.setIcon(scaledImages[gridMetadata[col][row][0]]
											  [gridMetadata[col][row][1]]);
					return;
				}
			}
		}
	}
	
	private Image mergeImages(Image image, Image overlay)
	{
		// load source images
		BufferedImage Image = ImageTool.toBufferedImage(image);
		BufferedImage Overlay =  ImageTool.toBufferedImage(overlay);

		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(Image.getWidth(), Overlay.getWidth());
		int h = Math.max(Image.getHeight(), Overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.createGraphics();
		g.drawImage(Image, 0, 0, null);
		g.drawImage(Overlay, 0, 0, null);
		g.dispose();
		
		return ImageTool.toImage(combined);
		//try 
		//{
		//	ImageIO.write(combined, "PNG", new File("src/Images/", name));
		//}
		//catch (IOException e) 
		//{
		//	// TODO Auto-generated catch block
		//(	e.printStackTrace();
		//}
	}
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
	public void mouseEntered(MouseEvent e) {}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for selectedRail
	//
	public void setSelect(int num)
	{
		selected = num;
	}
	public int getSelect()
	{
		return selected;
	}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for selectedRailOrientation
	//
	public void setSelectedOrientation(int orientation)
	{
		selectedOrientation = orientation;
	}
	public int getSelectedOrientation()
	{
		return selectedOrientation;
	}
	//-----------------------------------------------------------------------
	
	//Get'r and set'r functions for setlastenteredLabelrow
	//
	public void setlastenteredLabelcol(int col)
	{
		lastenteredLabelcol = col;
	}
	public int getlastenteredLabelcol()
	{
		return lastenteredLabelcol;
	}
	//-----------------------------------------------------------------------
		
	//Get'r and set'r functions for setlastenteredLabelcol
	//
	public void setlastenteredLabelrow(int row)
	{
		lastenteredLabelrow = row;
	}
	public int getlastenteredLabelrow()
	{
		return lastenteredLabelrow;
	}
	//-----------------------------------------------------------------------
	
	public boolean islastenteredLabelBlocked()
	{
		if(gridMetadata[lastenteredLabelcol][lastenteredLabelrow][2] == -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addGrid()
	{
	   JScrollPane scrollPane = new JScrollPane ( actualGrid,
									              ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									              ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	   add(scrollPane);
	}
	
	public int getgridMetadata(int col, int row, int id)
	{
		return gridMetadata[col][row][id];
	}
	public int[][][] getgridMetadata()
	{
		return gridMetadata;
	}
	
	public void setgridMetadata(int[][][] newmetadata)
	{
		gridMetadata = newmetadata.clone();
		
		for(int row = 0; row < maxRows; row++)
		{
			for(int col = 0; col < maxCols; col++)
			{
				if(gridMetadata[col][row][2] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[8][0].getImage())));
					continue;
				}
				if(gridMetadata[col][row][3] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[9][0].getImage())));
					continue;
				}
				myLabels[col][row].setIcon(scaledImages[gridMetadata[col][row][0]]
													   [gridMetadata[col][row][1]]);
			}
		}
	}
	public void setgridMetadata(int[][][] newmetadata, Simulator sim)
	{
		gridMetadata = newmetadata.clone();
		
		for(int row = 0; row < maxRows; row++)
		{
			for(int col = 0; col < maxCols; col++)
			{
				if(gridMetadata[col][row][2] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[8][0].getImage())));
					continue;
				}
				if(gridMetadata[col][row][3] != 0)
				{
					myLabels[col][row].setIcon(new ImageIcon(mergeImages(scaledImages[gridMetadata[col][row][0]][gridMetadata[col][row][1]].getImage(),scaledImages[9][0].getImage())));
					sim.createTrainstop(col, row, gridMetadata[col][row][3]);
					continue;
				}
				myLabels[col][row].setIcon(scaledImages[gridMetadata[col][row][0]]
													   [gridMetadata[col][row][1]]);
			}
		}
	}
	
	public void updateMouseInfo(JLabel label)
	{
		for(int row = 0; row < myLabels.length; row++){
			for(int col = 0; col < myLabels.length; col++){
				if(label == myLabels[col][row])
				{
					lastenteredLabel = label;
					lastenteredLabelcol = col;
					lastenteredLabelrow = row;
					
					return;
				}
			}
		}
	}

}
