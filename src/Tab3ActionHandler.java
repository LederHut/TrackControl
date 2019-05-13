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
import javax.swing.Timer;

public class Tab3ActionHandler implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9162599465433349889L;
	
	Simulator SimGrid = null;
	Timer timer = null;
	
	private final int TIMER_DELAY = 100;
	
	public Tab3ActionHandler (Simulator simgird, SimStart simstart)
	{
		SimGrid = simgird;
		timer = new Timer(TIMER_DELAY, simstart);
		timer.setRepeats(true);
	}
	public Tab3ActionHandler (Simulator simgird)
	{
		SimGrid = simgird;
	}
	public void actionPerformed(ActionEvent e) {
		
		//ActionHandler to Save the current written Grid
		if(e.getSource() == Simulator.start) {
			timer.start();
		}
		else if(e.getSource() == Simulator.stop) {
			timer.stop();
		}
		else if(e.getSource() == Simulator.transferData) {
			SimGrid.transfergridMetadata();
		}
		else if(e.getSource() == Simulator.gridLoad) {
			SimGrid.loadFile();
		}
		else if(e.getSource() == Simulator.gridSave) {
			SimGrid.saveFile();
		}
		else if(e.getSource() == Simulator.createtrain) {
			SimGrid.createTrain(Integer.parseInt(SimGrid.trainstart.getText()),
								Integer.parseInt(SimGrid.trainend.getText()));
		}
	}

}

