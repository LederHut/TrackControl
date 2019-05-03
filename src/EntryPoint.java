import javax.swing.*;

public class EntryPoint
{
	static JFrame Frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
	TrackLayer TLGrid = null;
	LogicPlanner LPGrid = null;
	Simulator SimGrid = null;
	
	public EntryPoint() 
	{
		int rows = 100;
		int cols = 100;
		int cellWidth = 16;
		
		tabbedPane.setFocusable(true);
		
		TLGrid = new TrackLayer(rows, cols, cellWidth, 1);
		LPGrid = new LogicPlanner(rows, cols, cellWidth, 2);
		SimGrid = new Simulator(rows, cols, cellWidth, 3);
		
        Frame.setTitle("Track Planner");
        Frame.setSize(1000,500);

        tabbedPane.add(TLGrid,"Track Layer");
        tabbedPane.add(LPGrid,"Logic Planner");
        tabbedPane.add(SimGrid,"Simulator");
        
        Frame.add(tabbedPane);
	}
	
	public static void start ()
	{
        Frame.setVisible(true);
	}
}