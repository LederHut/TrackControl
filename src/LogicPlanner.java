import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class LogicPlanner extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9194304540041527537L;
	
	JPanel Toolbar = null;
	
	public LogicPlanner (int rows, int cols, int cellWidth, int imgLoader)
	{
		super(rows, cols, cellWidth);
		
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(250, 200));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		super.initGrid(new Tab2LabelEventListener());
		super.add(Toolbar,BorderLayout.WEST);
		super.addGrid();
		
	}
	
}
