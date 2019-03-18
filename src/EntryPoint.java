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
	
	public static void main(String[] args) 
	{
		int rows = 100;
		int cols = 100;
		int cellWidth = 15;
		
		 // Erzeugung eines neuen Dialoges
        JFrame frame = new JFrame();
        frame.setTitle("JPanel Beispiel");
        frame.setSize(500,500);
        
        JPanel mainPanel = new JPanel();
        JPanel toolbar = new JPanel();
        toolbar.add(new JButton("pic 1"));
        toolbar.add(new JButton("pic 2"));
        toolbar.add(new JButton("pic 3"));
        GridPlanner grid = new GridPlanner(rows, cols, cellWidth);
        mainPanel.add(toolbar,BorderLayout.WEST);
        mainPanel.add(grid,BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane ( mainPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
        
        MyPanel drawingRect = new MyPanel();
        drawingRect.setSize(600,600);
        //panelRot.add(drawing);

        // Erzeugung eines JTabbedPane-Objektes
        JTabbedPane tabpane = new JTabbedPane
            (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
		
        
        // Hier werden die JPanels als Registerkarten hinzugefügt
        tabpane.add(scrollPane,"Ich bin rot");
        tabpane.addTab("Ich bin blau", drawingRect);
        //tabpane.add(panelGreen, "Ich bin grün");
        
        // JTabbedPane wird unserem Dialog hinzugefügt
        frame.add(tabpane);
        //meinJDialog.pack();
        // Wir lassen unseren Dialog anzeigen
        frame.setVisible(true);
        
	}
}
class MyPanel extends JPanel 
{

    private List<MyRectangle> lstShapes;
    private Timer populate;

    public MyPanel() {

        lstShapes = new ArrayList<MyRectangle>(15);

        populate = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int x = (int) (Math.random() * (getWidth()+100));
                int y = (int) (Math.random() * (getHeight()+100));
                int width = (int) (Math.random() * ((getWidth() +100) / 4));
                int height = (int) (Math.random() * ((getHeight() +100) / 4));

                
                Color color = new Color(
                        (int) (Math.random() * 255),
                        (int) (Math.random() * 255),
                        (int) (Math.random() * 255));

                lstShapes.add(new MyRectangle(x, y, width, height, color));
                repaint();
            }
        });
        populate.setInitialDelay(0);
        populate.setRepeats(true);
        populate.setCoalesce(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                populate.restart();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                populate.stop();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (MyRectangle rect : lstShapes) {
            rect.paint(g2d);
        }

        FontMetrics fm = g2d.getFontMetrics();
        String text = Integer.toString(lstShapes.size());

        g2d.setColor(getForeground());
        g2d.drawString(text, getWidth() - fm.stringWidth(text), getHeight() - fm.getHeight() + fm.getAscent());

    }

    public class MyRectangle extends Rectangle {

        private Color color;

        public MyRectangle(int x, int y, int width, int height, Color color) {
            super(x, y, width, height);
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void paint(Graphics2D g2d) {

            g2d.setColor(getColor());
            g2d.fill(this);

        }
    }
}


