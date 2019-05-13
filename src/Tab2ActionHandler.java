import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tab2ActionHandler implements ActionListener {
	LogicPlanner LPGrid = null;
	
	public Tab2ActionHandler (LogicPlanner lpgrid)
	{
		LPGrid = lpgrid;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//ActionHandler to Save the current written Grid
		if(e.getSource() == LogicPlanner.transferData) {
			
			LPGrid.transfergridMetadata();
			System.out.println("Reading SMTP Info.");
			
		}
		if(e.getSource() == LogicPlanner.gridSave) {
					
			LPGrid.saveFile();
					
		}
		if(e.getSource() == LogicPlanner.gridLoad) {
			
			LPGrid.loadFile();
			
		}
	}

}
