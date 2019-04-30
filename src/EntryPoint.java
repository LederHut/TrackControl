import java.awt.BorderLayout;
import javax.swing.*;

public class EntryPoint
{
	static JFrame Frame = new JFrame();
	JPanel gridContainer = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
	TrackLayer TLGrid = null;
	
	public EntryPoint() 
	{
		int rows = 50;
		int cols = 50;
		int cellWidth = 32;
		
		tabbedPane.setFocusable(true);
		
		TLGrid = new TrackLayer(rows, cols, cellWidth);
		
		//Frame.addKeyListener(TLGrid);
		
        Frame.setTitle("Track Planner");
        Frame.setSize(1000,500);

        tabbedPane.add(TLGrid,"Track Layer");
        
        
        Frame.add(tabbedPane);
        //tabbedPane.setFocusable(true);
        //Frame.setFocusable(true);
        
        //Frame.add(TLGrid.getThisToolbar(),BorderLayout.WEST);
	}
	
	public static void start ()
	{
        Frame.setVisible(true);
	}
}