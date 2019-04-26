import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

//could maybe use some tools like pulling a straight line or something like that
//or like automatic curve laying mode stuff like that
public class Toolbar extends JPanel implements KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8527007348870047047L;
	GridPlanner Grid = null;
	JPanel blueprintPreview = null;  
	JPanel railPreview = null;
	ImageIcon railImage = null;
	int currentblock;
	
	
	public Toolbar (GridPlanner grid)
	{
		this.setPreferredSize(new Dimension(210,500));
		this.Grid = grid;
		this.addKeyListener(this);
		
		 
		
		railPreview = new JPanel();
		railPreview.setPreferredSize(new Dimension(200,200));
		railPreview.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		JScrollPane scrollPane = new JScrollPane ( railPreview,
									               ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		blueprintPreview = new JPanel();
		blueprintPreview.setLayout(new GridLayout());
		blueprintPreview.setPreferredSize(new Dimension(200,200));
		blueprintPreview.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
		
		add(scrollPane);
		add(blueprintPreview);
		setFocusable(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
	}
	
	// Handles the keyboard input for the toolbar.
	// Numeric keys change the selected building block(rail).
	// E : used for the eraser "tool".
	// 
	// Right now only the key e and 1 are bound properly.
	// The other keys will generate errors.
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_1)
		{
			Grid.setSelectedRail(1);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_2)
		{
			Grid.setSelectedRail(2);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_3)
		{
			Grid.setSelectedRail(3);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_4)
		{
			Grid.setSelectedRail(4);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_5)
		{
			Grid.setSelectedRail(5);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
		{
			Grid.setSelectedRail(0);
			Grid.showCurrentselect();
		}
		if(e.getKeyCode() == KeyEvent.VK_R && Grid.getSelectedRail() != 0)
		{
			if(Grid.getSelectedRailOrientation() == 3)
			{
				Grid.setSelectedRailOrientation(0);
			}
			else
			{
				Grid.setSelectedRailOrientation(Grid.getSelectedRailOrientation() + 1);
			}
			Grid.showCurrentselect();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}