import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
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

public class TrackLayer extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2317402967205059398L;
	
	private JPanel Toolbar = null;
	public static JButton gridSave, gridLoad;
	
	private int maxRows = 0;
	private int maxCols = 0;
	
	public TrackLayer(int rows, int cols, int cellWidth) 
	{	
		super(rows, cols, cellWidth);
		
		maxRows = rows;
		maxCols = cols;
		
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(150, 50));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		gridSave = new JButton("Save");
		gridSave.setMargin(new Insets(0, 0, 0, 0));
		gridLoad = new JButton("Load");
		gridLoad.setMargin(new Insets(0, 0, 0, 0));
		
		gridSave.setPreferredSize(new Dimension(60,30));
		gridLoad.setPreferredSize(new Dimension(60,30));
		
		gridSave.addActionListener(new Tab1ActionHandler(this));
		gridLoad.addActionListener(new Tab1ActionHandler(this));
		
		Toolbar.add(gridSave);
		Toolbar.add(gridLoad);
		
		
		
		
		
		super.initGrid(new Tab1LabelEventListener());
		super.add(Toolbar,BorderLayout.WEST);
		super.addGrid();
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
			
			for(int col = 0; col < maxCols; col++){
				for(int row = 0; row < maxRows; row++){
					
					StringBuilder sb = new StringBuilder();
					
					sb.append(Integer.toString(row));
					sb.append(",");
					sb.append(Integer.toString(col));
					
					for(int id = 0; id < 3; id++)
					{
						sb.append(",");
						sb.append(Integer.toString(super.getgridMetadata(row,col,id)));
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
			
			int[] data = new int[5];
			int [][][] gridMetadata = new int[maxRows][maxCols][3];
			
			while(line != null)
			{
				StringBuilder sb = new StringBuilder();
				sb.append(line);
				current = sb.toString();
				
				int begin = 0;
				int end = 0;
				String substr;
				
				for(int index = 0; index < 5; index++)
				{
					end = current.indexOf(",", begin);
					substr = current.substring(begin, end);
					data[index] = Integer.parseInt(substr);
					begin = end+1;
				}

				gridMetadata[data[0]][data[1]][0] = data[2];
				gridMetadata[data[0]][data[1]][1] = data[3];
				gridMetadata[data[0]][data[1]][2] = data[4];
				
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