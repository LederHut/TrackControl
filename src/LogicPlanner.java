import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class LogicPlanner extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9194304540041527537L;
	
	private TrackLayer TLGrid = null;
	public static JButton transferData,
						  gridSave,
						  gridLoad;
	
	private JPanel Toolbar = null;
	private Tab2LabelEventListener Tab2listener = new Tab2LabelEventListener(this);
	private LogicBlock[] LogicBlocks = new LogicBlock[50];
	
	private int[][] coordCache = new int[50][2];
	private int blockCount = 1;
	private int tileCount = 0;
	
	public LogicPlanner (int rows, int cols, int cellWidth, TrackLayer tl)
	{
		super(rows, cols, cellWidth);
		
		TLGrid = tl;
		
		transferData = new JButton("Transfer Data");
		transferData.setMargin(new Insets(0, 0, 0, 0));
		transferData.setPreferredSize(new Dimension(60,30));
		transferData.addActionListener(new Tab2ActionHandler(this));
		
		gridSave = new JButton("Save");
		gridSave.setMargin(new Insets(0, 0, 0, 0));
		gridLoad = new JButton("Load");
		gridLoad.setMargin(new Insets(0, 0, 0, 0));
		
		gridSave.setPreferredSize(new Dimension(60,30));
		gridLoad.setPreferredSize(new Dimension(60,30));
		
		gridSave.addActionListener(new Tab2ActionHandler(this));
		gridLoad.addActionListener(new Tab2ActionHandler(this));
	
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(250, 200));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		Toolbar.add(transferData);
		Toolbar.add(gridSave);
		Toolbar.add(gridLoad);
		
		super.initGrid(Tab2listener);
		super.add(Toolbar,BorderLayout.WEST);
		super.addGrid();
		
	}
	public void cacheCoordinats(int col, int row)
	{
		coordCache[tileCount][0] = col;
		coordCache[tileCount][1] = row;
		tileCount++;
	}
	public void createLogicBlock()
	{
		LogicBlock lb = new LogicBlock(tileCount);
		lb.blockMetadata = coordCache;
		lb.blockID = blockCount;
		LogicBlocks[blockCount] = lb;
		blockCount++;
		tileCount = 0;
		super.UpdateMetadata(lb);

	}
	public void resetCache()
	{
		tileCount = 0;
	}
	public void deleteLogicBlock()
	{
		
	}
	public void transfergridMetadata()
	{
		super.setgridMetadata(TLGrid.getgridMetadata());
	}
	
	void saveFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.showSaveDialog(null);
		String PATH = chooser.getSelectedFile().getAbsolutePath();
		
		try 
		{
			FileWriter fw = new FileWriter(new File(PATH));
			BufferedWriter bw = new BufferedWriter(fw);
			
			
			String str;
			
			for(int row = 0; row < Grid.maxRows; row++){
				for(int col = 0; col < Grid.maxCols; col++){
					
					StringBuilder sb = new StringBuilder();
					
					sb.append(Integer.toString(col));
					sb.append(",");
					sb.append(Integer.toString(row));
					
					for(int id = 0; id < 5; id++)
					{
						sb.append(",");
						sb.append(Integer.toString(super.getgridMetadata(col,row,id)));
					}
					
					sb.append(",");
					str = sb.toString();
					bw.write(str);
					bw.newLine();
				}
			}
			bw.close();			
		}
		catch(IOException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	void loadFile() 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.showOpenDialog(null);
		String PATH = chooser.getSelectedFile().getAbsolutePath();
		
		try
		{
			FileReader fr = new FileReader(new File(PATH));
			BufferedReader br = new BufferedReader(fr);
				
			String current;
			String line = br.readLine();
			
			int[] data = new int[7];
			int [][][] gridMetadata = new int[Grid.maxCols][Grid.maxRows][5];
			
			while(line != null)
			{
				StringBuilder sb = new StringBuilder();
				sb.append(line);
				current = sb.toString();
				
				int begin = 0;
				int end = 0;
				String substr;
				
				for(int index = 0; index < 7; index++)
				{
					end = current.indexOf(",", begin);
					substr = current.substring(begin, end);
					data[index] = Integer.parseInt(substr);
					begin = end+1;
				}

				gridMetadata[data[0]][data[1]][0] = data[2];
				gridMetadata[data[0]][data[1]][1] = data[3];
				gridMetadata[data[0]][data[1]][2] = data[4];
				gridMetadata[data[0]][data[1]][3] = data[5];
				gridMetadata[data[0]][data[1]][4] = data[6];
				
				line = br.readLine();
			}
			br.close();
			super.setgridMetadata(gridMetadata);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
