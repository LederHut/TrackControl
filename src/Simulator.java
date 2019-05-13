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
import javax.swing.JTextField;

public class Simulator extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9194304540041527537L;
	
	private LogicPlanner LPGrid = null;
	private JPanel Toolbar = null;
	
	public static JButton start,
						  stop,
						  gridSave,
						  gridLoad,
						  transferData,
						  createtrain;
	
	public static JTextField trainstart,
							 trainend;
	
	private SimStart simStart = new SimStart(this);
	private Tab3ActionHandler actionHandler = new Tab3ActionHandler(this, simStart);
	
	public Train trains[] = new Train[10];
	private Trainstop[] trainstops = new Trainstop[20];
	
	private int trainCount = 1,
				trainstopCount = 1;
	
	public Simulator (int cols, int rows, int cellWidth, LogicPlanner lpgrid)
	{
		super(cols, rows, cellWidth);
		
		LPGrid = lpgrid;
		
		trainstart = new JTextField("1", 3);
		trainend = new JTextField("2", 3);
		
		createtrain = new JButton("Create Train");
		createtrain.setMargin(new Insets(0, 0, 0, 0));
		createtrain.setPreferredSize(new Dimension(80,30));
		createtrain.addActionListener(new Tab3ActionHandler(this));
		
		start = new JButton("Start");
		start.setMargin(new Insets(0, 0, 0, 0));
		start.setPreferredSize(new Dimension(60,30));
		start.addActionListener(actionHandler);
		
		stop = new JButton("Stop");
		stop.setMargin(new Insets(0, 0, 0, 0));
		stop.setPreferredSize(new Dimension(60,30));
		stop.addActionListener(actionHandler);
		
		transferData = new JButton("Transfer Data");
		transferData.setMargin(new Insets(0, 0, 0, 0));
		transferData.setPreferredSize(new Dimension(120,30));
		transferData.addActionListener(new Tab3ActionHandler(this));
		
		gridSave = new JButton("Save");
		gridSave.setMargin(new Insets(0, 0, 0, 0));
		gridLoad = new JButton("Load");
		gridLoad.setMargin(new Insets(0, 0, 0, 0));
		
		gridSave.setPreferredSize(new Dimension(60,30));
		gridLoad.setPreferredSize(new Dimension(60,30));
		
		gridSave.addActionListener(new Tab3ActionHandler(this));
		gridLoad.addActionListener(new Tab3ActionHandler(this));
		
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(250, 200));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		Toolbar.add(start);
		Toolbar.add(stop);
		Toolbar.add(gridSave);
		Toolbar.add(gridLoad);
		Toolbar.add(transferData);
		Toolbar.add(createtrain);
		Toolbar.add(trainstart);
		Toolbar.add(trainend);

			
		super.initGrid(new Tab3LabelEventListener(this));
		super.add(Toolbar,BorderLayout.WEST);
		super.addGrid();
	}
	public void createTrain(int tsstartid, int tsendid)
	{
		Train train = new Train(trainCount, this);
		train.trainStartCol = trainstops[tsstartid].col;
		train.trainStartRow = trainstops[tsstartid].row;
		train.trainEndCol = trainstops[tsendid].col;
		train.trainEndRow = trainstops[tsendid].row;
		train.pathfinder(train.trainStartCol, train.trainStartRow, train.trainEndCol, train.trainEndRow);
		trains[trainCount] = train;
		trainCount++;
	}
	public void createTrainstop(int col, int row)
	{
		Trainstop ts = new Trainstop();
		ts.col = col;
		ts.row = row;
		ts.trainstopID = trainstopCount;
		trainstops[trainstopCount] = ts;
		trainstopCount++;
		super.UpdateMetadata(ts);
	}
	public void createTrainstop(int col, int row, int id)
	{
		Trainstop ts = new Trainstop();
		ts.col = col;
		ts.row = row;
		ts.trainstopID = id;
		trainstops[trainstopCount] = ts;
		trainstopCount++;
		super.UpdateMetadata(ts);
	}
	public void transfergridMetadata()
	{
		super.setgridMetadata(LPGrid.getgridMetadata());
	}
	public void updateTrain(Train t)
	{
		super.UpdateMetadata(t);
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
			super.setgridMetadata(gridMetadata, this);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
