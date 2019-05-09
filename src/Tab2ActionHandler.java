import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Tab2ActionHandler implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9162599465433349889L;
	
	LogicPlanner LPGrid = null;
	
	public Tab2ActionHandler (LogicPlanner lpgrid)
	{
		LPGrid = lpgrid;
	}
	
	@SuppressWarnings("null")
	public void actionPerformed(ActionEvent e) {
		
		//ActionHandler to Save the current written Grid
		if(e.getSource() == LogicPlanner.transferData) {
			
			LPGrid.transfergridMetadata();
			
		}
		if(e.getSource() == LogicPlanner.gridSave) {
					
			LPGrid.saveFile();
					
		}
		if(e.getSource() == LogicPlanner.gridLoad) {
			
			LPGrid.loadFile();
			
		}
	}

}
