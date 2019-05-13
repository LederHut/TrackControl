import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimStart implements ActionListener 
{
	Simulator SimGrid = null;
	
	public SimStart (Simulator simgrid)
	{
		SimGrid = simgrid;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 1; SimGrid.trains[i] != null; i ++)
		{
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
				if(t.trainSteps > 2)
				{
					t.lastx =  t.trainPath[t.trainSteps-3][0];
					t.lasty =  t.trainPath[t.trainSteps-3][1];
				}

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