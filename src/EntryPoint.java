import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;


public class EntryPoint 
{
	static JFrame frame = new JFrame();
	JPanel mainPanel = new JPanel();
	JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	
	GridPlanner grid = null;
	
	public EntryPoint() 
	{
		int rows = 20;
		int cols = 20;
		int cellWidth = 30;
		
		grid = new GridPlanner(rows, cols, cellWidth);
		
        frame.setTitle("JPanel Beispiel");
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