import java.awt.BorderLayout;
import javax.swing.*;

public class EntryPoint 
{
	static JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel();
	JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
	GridPlanner grid = null;
	
	public EntryPoint() 
	{
		int rows = 50;
		int cols = 50;
		int cellWidth = 32;
		
		grid = new GridPlanner(rows, cols, cellWidth);

        frame.setTitle("Track Planner");
        frame.setSize(1000,500);
        
        mainPanel.add(grid,BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane ( mainPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tabpane.add(scrollPane,"Ich bin rot");
        
        frame.add(tabpane);
        frame.add(grid.getThisToolbar(),BorderLayout.WEST);
	}
	
	public static void start ()
	{
        frame.setVisible(true);
	}
}