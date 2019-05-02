import java.awt.BasicStroke;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Simulator extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9194304540041527537L;
	
	JPanel Toolbar = null;
	
	public Simulator (int rows, int cols, int cellWidth, int imgLoader)
	{
		super(rows, cols, cellWidth, imgLoader);
		
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(200, 200));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));

		super.add(Toolbar);
		super.addGrid();
	}
	
}
