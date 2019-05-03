import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TrackLayer extends Grid
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2317402967205059398L;
	
	JPanel Toolbar = null;
	
	public TrackLayer(int rows, int cols, int cellWidth, int imgLoader) 
	{	
		super(rows, cols, cellWidth);
		
		Toolbar = new JPanel();
		Toolbar.setPreferredSize(new Dimension(150, 50));
		Toolbar.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		super.initGrid(new Tab1LabelEventListener());
		super.add(Toolbar,BorderLayout.WEST);
		super.addGrid();
	}
}