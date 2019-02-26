import java.awt.event.*;

public class ActionHandler implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JWindow.button) {
			System.out.println("Hallo, das ist ein Text!");
		}
	}
}
