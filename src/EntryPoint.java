import java.awt.BorderLayout;
import javax.swing.*;

public class EntryPoint
{
	static JFrame Frame = new JFrame();
	JPanel gridContainer = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
	GridPlanner Grid = null;
	
	public EntryPoint() 
	{
		int rows = 50;
		int cols = 50;
		int cellWidth = 32;
		
		Grid = new GridPlanner(rows, cols, cellWidth);

        Frame.setTitle("Track Planner");
        Frame.setSize(1000,500);
        
        gridContainer.add(Grid,BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane ( gridContainer,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tabbedPane.add(scrollPane,"Track Layer");
        
        Frame.add(tabbedPane);
        Frame.add(Grid.getThisToolbar(),BorderLayout.WEST);
	}
	
	public static void start ()
	{
        Frame.setVisible(true);
	}
}