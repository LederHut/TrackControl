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

public class Tab1ActionHandler implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9162599465433349889L;
	
	TrackLayer TLGrid = null;
	
	public Tab1ActionHandler (TrackLayer tlgrid)
	{
		TLGrid = tlgrid;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//ActionHandler to Save the current written Grid
		if(e.getSource() == TrackLayer.gridSave) {
			
			TLGrid.saveFile();
			
		}
		
		//ActionHandler to Load a new Saved Grid
		if(e.getSource() == TrackLayer.gridLoad) {

			TLGrid.loadFile();
			
		}
		
	}

}
