import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tab1ActionHandler implements ActionListener {
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
