import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SimStart implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9162599465433349889L;
	
	Simulator SimGrid = null;
	
	public SimStart (Simulator simgrid)
	{
		SimGrid = simgrid;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 1; SimGrid.trains[i] != null; i ++)
		{
			System.out.print("Sim start's now");
			
			Train t = SimGrid.trains[i];
			
			if(t.trainSteps == 0)
			{
				t.trainLocation[0][0] = t.trainPath[t.trainSteps][0];
				t.trainLocation[0][1] = t.trainPath[t.trainSteps][1];
				
				t.trainLocation[1][0] = t.trainPath[t.trainSteps][0];
				t.trainLocation[1][1] = t.trainPath[t.trainSteps][1];
				
				t.trainLocation[2][0] = t.trainPath[t.trainSteps][0];
				t.trainLocation[2][1] = t.trainPath[t.trainSteps][1];
				t.trainSteps++;
			}
			else if(t.trainSteps == 1)
			{
				t.trainLocation[0][0] = t.trainPath[t.trainSteps][0];
				t.trainLocation[0][1] = t.trainPath[t.trainSteps][1];
				
				t.trainLocation[1][0] = t.trainPath[t.trainSteps-1][0];
				t.trainLocation[1][1] = t.trainPath[t.trainSteps-1][1];
				
				t.trainLocation[2][0] = t.trainPath[t.trainSteps-1][0];
				t.trainLocation[2][1] = t.trainPath[t.trainSteps-1][1];
				t.trainSteps++;
			}
			else if(t.trainSteps != t.maxTrainSteps)
			{
				t.trainLocation[0][0] = t.trainPath[t.trainSteps][0];
				t.trainLocation[0][1] = t.trainPath[t.trainSteps][1];
				
				t.trainLocation[1][0] = t.trainPath[t.trainSteps-1][0];
				t.trainLocation[1][1] = t.trainPath[t.trainSteps-1][1];
				
				t.trainLocation[2][0] = t.trainPath[t.trainSteps-2][0];
				t.trainLocation[2][1] = t.trainPath[t.trainSteps-2][1];
				t.trainSteps++;
			}
			SimGrid.updateTrain(t);
		}
	}

}